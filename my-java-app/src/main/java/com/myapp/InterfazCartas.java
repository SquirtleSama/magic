package com.myapp;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

public class InterfazCartas implements ActionListener {
    //Tendrá un panel de imagenes, 2 botones para desplazarse entre las cartas y un número que indique qué número es la carta actual.
    //Además, tendrá un botón para borrar todas las cartas de memoria.
    int posicionActual;
    String rutaCarpeta = "my-java-app/src/main/cartas";
    File carpeta;
    String[] cartas;
    JLabel etiquetaImagen, etiquetaPosicion;
    JButton menosButton, masButton, borrarButton;
    BuscadorCartas buscadorCartas;


    public InterfazCartas()
    {
        posicionActual = 0;
        carpeta = new File(rutaCarpeta);
        cartas = carpeta.list();
        buscadorCartas = new BuscadorCartas();

        //Configuracion de la etiqueta de la imagen y posicion
        etiquetaImagen = new JLabel();
        etiquetaImagen.setHorizontalAlignment(JLabel.CENTER);
        etiquetaPosicion = new JLabel();
        etiquetaImagen.setIcon(new ImageIcon(rutaCarpeta + "/" + cartas[posicionActual]));
        etiquetaPosicion.setText("1/1");

        //Configuracion de los botones
        menosButton = new JButton("-");
        menosButton.addActionListener(this);
        masButton = new JButton("+");
        masButton.addActionListener(this);
        borrarButton = new JButton("Borrar todas las cartas");
        borrarButton.addActionListener(this);
        
        //Configuración del panel inferior
        JPanel panelInferior = new JPanel();
        panelInferior.add(menosButton);
        panelInferior.add(etiquetaPosicion);
        panelInferior.add(masButton);
        panelInferior.add(borrarButton);

        //Configuracion del JFrame
        JFrame ventana = new JFrame();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setTitle("Cartas");
        ventana.setLayout(new BorderLayout());

        //Añadir los componentes al JFrame
        ventana.add(etiquetaImagen, BorderLayout.CENTER);
        ventana.add(panelInferior, BorderLayout.SOUTH);
        ventana.setSize(700, 700);
        panelInferior.setVisible(true);
        ventana.setVisible(true);
        System.out.println("Interfaz de cartas creada. Numero de cartas actual: " + cartas.length);
        System.out.println("Ruta a la imagen: " + rutaCarpeta + "/" + cartas[posicionActual]);

    }

    public void addCarta(String nombreCarta)
    {
        //Añade una carta a la carpeta de cartas
        buscadorCartas.buscar(nombreCarta, posicionActual);
        cartas = carpeta.list();
        etiquetaPosicion.setText((posicionActual + 1) + "/" + cartas.length);
    }


    private void borrarCartas() {
        // Añade un popup para confirmar que se quieren borrar las cartas
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres borrar todas las cartas?", "Confirmar borrado", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            // Borra todas las cartas de la carpeta de cartas
            for(int i = 0; i < cartas.length; i++) {
                File f = new File(rutaCarpeta + "/" + cartas[i]);
                f.delete();
            }
            buscadorCartas.buscar("island", 0);
            cartas = carpeta.list();
            etiquetaPosicion.setText((posicionActual + 1) + "/" + cartas.length);
        }
    }



    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        if(e.getSource() == menosButton)
        {
            System.out.println("Menos");
            posicionActual--;
            if(posicionActual < 0)
            {
                posicionActual = cartas.length - 1;
            }
        }
        else if(e.getSource() == masButton)
        {
            System.out.println("Mas");
            posicionActual++;
            if(posicionActual >= cartas.length)
            {
                posicionActual = 0;
            }
        }
        else if(e.getSource() == borrarButton)
        {
            System.out.println("Borrar");
            borrarCartas();
        }
        etiquetaImagen.setIcon(new ImageIcon(rutaCarpeta + "/" + cartas[posicionActual]));
        etiquetaPosicion.setText((posicionActual + 1) + "/" + cartas.length);
    }



}


