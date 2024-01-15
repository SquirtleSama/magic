package com.myapp;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;

public class PruebaVideo {
    public static void main(String[] args) {
        Webcam webcam = Webcam.getDefault();
        webcam.open();

        //Guardar la imagen como un fichero
        try {
            ImageIO.write(webcam.getImage(), "PNG", new File("test.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
