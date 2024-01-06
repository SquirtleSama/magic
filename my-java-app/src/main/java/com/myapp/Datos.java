package com.myapp;

import java.awt.Dimension;
import java.util.LinkedList;
import java.util.Queue;

public class Datos {
    //Datos del servidor que nos pondrá en contacto
    //En principio no lo voy a usar
        static String server_ip = "192.168.1.64";
        static int server_port = 8080;
        static String peticion = server_ip + ":" + server_port + "/Carrera/rest/ms";
	//Si es servidor o no
        static boolean servidor = true;
    //Puerto de conexión
	    static int videochat_port = 5550;
	//Datos del chat
	    static Queue<String> chat_queue =  new LinkedList<>();
	//Datos de las cartas
	
	//Datos del video
	static int frame_rate = 60;
	static int frameWidth = 1280;
	static int frameHeight = 720;
	static Dimension resolucion = new Dimension(frameWidth, frameHeight);
	
	//Datos de pruebas
	static boolean probando = true;
	static String videochat_ip = "192.168.1.64";

	
	public class Head
	{
		static final int REQUEST_CODE = 10;
		static final int REPLAY_READY = 11;
		static final int VIDEO_DATA = 12;
		static final int CHAT_DATA = 13;
	}
    
}
