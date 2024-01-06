package com.myapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//Se va a encargar de hacer la conexión por socket con el otro usuario.
public class TCPHandler {
    private Socket socket;
    private ServerSocket serverSocket;
    short conn_state = 0;
    short time_out = 10;
    String ip = Datos.videochat_ip;
    int port = Datos.videochat_port;
    boolean servidor = true;
    DataOutputStream out;
    DataInputStream in;

    protected void conectar(){
        //Se va a conectar con la ip que corresponda.
        try {
            if (servidor) {
                System.out.println("Abriendo el socket. . .");
                serverSocket = new ServerSocket(port);
                socket = serverSocket.accept();
            }
            else {
                System.out.println("Abriendo el socket. . .");
                socket = new Socket(ip, port);
            }
            conn_state = 1;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            System.out.println("Conexión establecida con " + socket.getInetAddress().getHostName());
        }catch(IOException e){
            System.out.println("Error al abrir el socket: " + e.getMessage());
        }
    }

    protected void desconectar(){
        try {
            if (servidor) {
                serverSocket.close();
            }
            socket.close();
            System.out.println("Conexión cerrada");
        }catch(IOException e){
            System.out.println("Error al cerrar el socket: " + e.getMessage());
        }
    }

    protected byte[] recibir(){
        if (conn_state == 1) {
            byte[] buffer = new byte[1024];
        try {
            socket.getInputStream().read(buffer);
        }catch(IOException e){
            System.out.println("Error al recibir datos: " + e.getMessage());
        }
        return buffer;
        }
        else return null;
    }

    protected void enviar(byte[] buffer){
        if (conn_state == 1) {
            try {
                socket.getOutputStream().write(buffer.length);
                socket.getOutputStream().write(buffer);
            }catch(IOException e){
                System.out.println("Error al enviar datos: " + e.getMessage());
            }
        }
    }


    
}
