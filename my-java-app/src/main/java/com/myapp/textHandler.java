package com.myapp;

import java.io.*;
import java.net.Socket;

class TextHandler {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private int textPort = Datos.chat_port;

    public TextHandler() {
        try {
            socket = new Socket("localhost", textPort);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviarTexto(String text) {
        out.println(text);
    }
    public String recibirTexto(){
        try {
            String textoRecibido = in.readLine();
            if (textoRecibido != null) {
                return textoRecibido;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}