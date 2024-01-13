package com.myapp;
import java.awt.Dimension;

public class Datos {
	//Si es servidor o no
        static boolean servidor = true;
	//Si no es servidor, la ip del servidor
		static String ip = "";
    //Puerto de conexión
	    static int video_port = 5550;
		static int chat_port = 5551;
	//Datos de las cartas
	
	//Datos del video
	static int frame_rate = 60;
	static int frameWidth = 1280;
	static int frameHeight = 720;
	static Dimension resolucion = new Dimension(frameWidth, frameHeight);

	public class Tipo
	{
		static final int VIDEO = 1;
		static final int CHAT = 2;
	}

	
	public class Head
	{
		static final int REQUEST_CODE = 10;
		static final int REPLAY_READY = 11;
		static final int VIDEO_DATA = 12;
		static final int CHAT_DATA = 13;
	}
    
}
