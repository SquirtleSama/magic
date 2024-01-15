package com.myapp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

class VideoHandler {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private int videoPort = Datos.video_port;

    public VideoHandler() {
        try {
            if(Datos.servidor)
            {
                System.out.println("Abriendo servidor");
                ServerSocket serverSocket = new ServerSocket(videoPort);
                socket = serverSocket.accept();
                System.out.println("conexion con " + socket.getInetAddress().getHostName());
            }
            else
            {
                System.out.println("Buscando servidor");
                socket = new Socket(Datos.ip, videoPort);
            }
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendVideo(BufferedImage imagen) {
        //Convertir imagen a array de bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(imagen, "jpg", baos);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte[] bytes = baos.toByteArray();
        //enviar longitud del array
        try {
            out.writeInt(bytes.length);
            //enviar array
            out.write(bytes);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public BufferedImage recibirImagen() {
        
        while(true){
            try {
                int length = in.readInt();
                byte[] imagenBytes = new byte[length];
                in.readFully(imagenBytes, 0, length);
                BufferedImage imagenRecibida = ImageIO.read(new ByteArrayInputStream(imagenBytes));
            if (imagenRecibida != null) {
                return imagenRecibida;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
    
}