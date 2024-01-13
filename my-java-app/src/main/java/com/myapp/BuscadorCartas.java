package com.myapp;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class BuscadorCartas
{

    public static void buscar(String name, int contadorCartas)
    {
        String url = "https://api.scryfall.com/cards/named?fuzzy="+name;
        String jsonString = peticion(url);
        JSONObject jsonObject = new JSONObject(jsonString);

        JSONObject imageURIs = jsonObject.getJSONObject("image_uris");
        String urlImage = imageURIs.getString("normal");
        peticion(urlImage);

        try (BufferedInputStream bis = new BufferedInputStream(new URL(urlImage).openStream());
             FileOutputStream fos = new FileOutputStream("src/main/cartas/carta" + contadorCartas + ".jpg") )
        {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while((bytesRead = bis.read(buffer, 0, 1024)) != -1)
            {
                fos.write(buffer, 0, bytesRead);
            }
            System.out.println("Imagen descargada.");
            contadorCartas++;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }



    }

    private static String peticion (String _url)
    {
        try
        {
            //Conectar
            URL url = new URL(_url);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            if(c.getResponseCode() != 200)
            {
                throw new RuntimeException("Failed. HTTP error code : " + c.getResponseCode());
            }

            //Lectura
            BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
            String output;
            String returnString = "";

            while((output = br.readLine()) != null)
            {
                returnString += output + "\n";
            }

            //Desconexión
            c.disconnect();
            return returnString;
        }
        catch (Exception e)
        {
            return e.toString();
        }
    }

}