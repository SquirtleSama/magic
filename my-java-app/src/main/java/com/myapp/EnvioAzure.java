package com.myapp;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.net.URI;

public class EnvioAzure
{
    static String subscriptionKey = "626cc768808249dda4b6e6febbcd64e8";
    static String endpoint = "https://westeurope.api.cognitive.microsoft.com/";

    TextHandler envioTexto;



    public EnvioAzure(TextHandler textHandler){
        this.envioTexto = textHandler;
    }


    public void buscar(){
        String urlResultados = enviarImagen();
        String nombreCarta = recibirResultados(urlResultados);
        if (nombreCarta != null) {
            //Enviar nombreCarta al otro jugador.
            envioTexto.enviarTexto(nombreCarta);
            
        }
    }


    private static String enviarImagen() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try{
            URIBuilder builder = new URIBuilder(endpoint + "vision/v3.2/read/analyze?language=en&model-version=latest");
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/octet-stream");
            request.setHeader("Ocp-Apim-Subscription-Key",  subscriptionKey);

            //Pasar la imagen
            File f = new File("MyFile.jpg");
            FileEntity reqEntity = new FileEntity(f);
            request.setEntity(reqEntity);

            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                System.out.println(EntityUtils.toString(entity));
            }
            String urlResultado = response.getFirstHeader("Operation-Location").getValue();
            System.out.println(urlResultado);
            return urlResultado;
        }catch(Exception e){
            System.out.println("Error al crear el cliente");
        }
        return null;
    }

    private static String recibirResultados(String urlResultados){
        System.out.println(urlResultados);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try{
            URIBuilder builder = new URIBuilder(urlResultados);
            URI uri = builder.build();
            HttpGet request= new HttpGet(uri);
            request.setHeader("Ocp-Apim-Subscription-Key",  subscriptionKey);

            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            String jsonString = EntityUtils.toString(entity);
            System.out.println(jsonString);
            JSONObject jsonObject = new JSONObject(jsonString);
            while(!jsonObject.getString("status").equals("succeeded"))
            {
                Thread.sleep(250);
                response = httpClient.execute(request);
                entity = response.getEntity();
                jsonString = EntityUtils.toString(entity);
                jsonObject = new JSONObject(jsonString);
                System.out.println(jsonObject);
            }
            jsonObject = jsonObject.getJSONObject("analyzeResult");
            JSONArray jsonArray = jsonObject.getJSONArray("readResults");
            jsonObject = (JSONObject) jsonArray.get(0);
            jsonArray = jsonObject.getJSONArray("lines");

            jsonObject = (JSONObject) jsonArray.get(0);
            String nombreCarta = jsonObject.getString("text");
            System.out.println(nombreCarta);
            return nombreCarta;
        }
        catch(Exception e){
            System.out.println("Error al crear el cliente");
        }
        
        return null;
    }


}