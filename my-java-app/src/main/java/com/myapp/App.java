/*Main. Ejecuta las funciones para que el programa funcione.
*  Primero se ejecuta la interfaz. En ella se ven dos pantallas de video, la nuestra funcionando y la externa en negro. Además hay una
* tercera pantalla con la visualización de las cartas.
*  Cuando se pulse el botón conectar, se conectará con el otro usuario.
*/


package com.myapp;

public class App {
    public static void main(String[] args) {
        //Inicializo los dos sockets que mandan y reciben video y texto
        //BuscadorCartas.buscar("island", 0);
        
        //InterfazVideos interfazVideos = new InterfazVideos();
        TextHandler textHandler = new TextHandler();

        //System.out.println("EJECUTANDO INTERFAZ DE CARTAS");
        //InterfazCartas i = new InterfazCartas();
        //System.out.println("EJECUTANDO INTERFAZ DE MI VIDEO");
        //InterfazMiVideo miVideo = new InterfazMiVideo();


    }
}