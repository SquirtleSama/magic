/**
 * Tendrá el cuadro donde se ve lo que pilla la cámara y un botón para hacer la captura de imagen para procesar el texto.
 */

package com.myapp;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class InterfazMiVideo {
    private JFrame frame;
    private JLabel imageLabel;
    private JButton captureButton;
    private Webcam webcam;
    private BufferedImage recorte;

    public InterfazMiVideo() {
        frame = new JFrame("Video Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Datos.resolucion);

        imageLabel = new JLabel();
        frame.add(imageLabel);

        captureButton = new JButton("Capture");
        captureButton.addActionListener(e -> {
            BufferedImage image = webcam.getImage();
            // Recorta la esquina superior izquierda de la imagen
            recorte = image.getSubimage(0, 0, image.getWidth() / 2, image.getHeight() / 2);
            // Guarda la imagen en un archivo en la carpeta de fotos. 
            try {
                File f = new File("src/main/cartas/foto.png");
                ImageIO.write(recorte, "PNG", f);
            } catch (IOException ex) {
                System.out.println("Error al guardar la imagen: " + ex.getMessage());
            }
        });
        

        // Inicializa la webcam
        webcam = Webcam.getDefault();
        if (webcam == null) {
            System.out.println("No se ha encontrado ninguna webcam");
        }
        webcam.setCustomViewSizes(Datos.resolucion);
        webcam.setViewSize(Datos.resolucion);
        WebcamPanel webcamPanel = new WebcamPanel(webcam);
        webcamPanel.setFPSDisplayed(true);
        webcamPanel.setDisplayDebugInfo(true);
        webcamPanel.setImageSizeDisplayed(true);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(webcamPanel);
        panel.add(captureButton);
        //frame.add(webcamPanel);
        frame.add(panel);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.add(captureButton);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazMiVideo());
    }

    public static void main() {
        SwingUtilities.invokeLater(() -> new InterfazMiVideo());
    }


}