package com.myapp;

import java.io.*;
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
            socket = new Socket("localhost", videoPort);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendVideo(BufferedImage video) {
        try {
            ImageIO.write(video, "jpg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage recibirImagen() {
        try {
            BufferedImage imagenRecibida = ImageIO.read(ImageIO.createImageInputStream(in));
            if (imagenRecibida != null) {
                return imagenRecibida;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}