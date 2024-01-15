package com.myapp;
import javax.imageio.ImageIO;
import javax.swing.*;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class InterfazVideos {
    private Webcam webcam;
    private WebcamPanel webcamPanel;
    private JButton captureButton;
    private JFrame frame;
    private JLabel imageLabel;
    private BufferedImage image;
    private TextHandler textHandler;
    private VideoHandler videoHandler;
    private InterfazCartas interfazCartas;
    private EnvioAzure envioAzure;
    private int contador = 0;

    public InterfazVideos() {
        textHandler = new TextHandler();
        videoHandler = new VideoHandler();
        interfazCartas = new InterfazCartas();
        envioAzure = new EnvioAzure(textHandler);

        frame = new JFrame("Los Cartones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);

        // Inicializar la webcam
        webcam = Webcam.getDefault();
        webcamPanel = new WebcamPanel(webcam);
        webcamPanel.setFPSDisplayed(true);
        webcamPanel.setDisplayDebugInfo(true);
        webcamPanel.setImageSizeDisplayed(true);
        webcamPanel.setMirrored(true);

        // Inicializar el JLabel para la imagen
        ImageIcon imageIcon = new ImageIcon("MyFile.jpg");
        imageLabel = new JLabel(imageIcon);

        // Inicializar el botón
        captureButton = new JButton("Capture");
        captureButton.addActionListener(e -> {
            // Guarda un recorte de la webcam en el fichero MyFile.jpg
            image = webcam.getImage();
            BufferedImage recorte = image.getSubimage(0, 0, image.getWidth()/3, image.getHeight()/3);
            try {
                ImageIO.write(recorte, "jpg", new File("MyFile.jpg"));
                envioAzure.buscar();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        // Añadir los componentes al frame
        frame.setLayout(new GridLayout(3, 1));
        frame.add(webcamPanel);
        frame.add(imageLabel);
        frame.add(captureButton);

        frame.pack();
        frame.setVisible(true);

        hilos();
    }

    private void hilos() {
        // Hilo para enviar el video
        new Thread(() -> {
            while (true) {
                videoHandler.sendVideo(webcam.getImage());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();

        // Hilo para recibir el video
        new Thread(() -> {
                // Recibir el video
                System.out.println("----" + contador++);
                BufferedImage receivedVideo = videoHandler.recibirImagen();
                if (receivedVideo != null) {
                    // Mostrar el video recibido en InterfazVideos
                    imageLabel.setIcon(new ImageIcon(receivedVideo));
                    System.out.println("        ----");
                }
        }).start();

        // Hilo para recibir texto
        new Thread(() -> {
            while (true) {
                // Recibir el texto
                String cartaRecibida = textHandler.recibirTexto();
                if (cartaRecibida != null) {
                    // Chequeo del nombre de la carta
                    System.out.println(cartaRecibida);
                    // Buscar la carta
                    interfazCartas.addCarta(cartaRecibida);
                }
            }
        }).start();

    }


}