package com.myapp;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class EnvioAzure {
    final static String subscriptionKey = "3044375f169f4383aa59e662b65820a8";
    final static String endpoint = "https://westeurope.api.cognitive.microsoft.com/";
    final static String uriBase = endpoint + "vision/v3.2/read/analyze?language=en&model-version=latest";


    public static void main(String[] args) {
        // Aquí puedes comenzar a escribir tu código
    }

    public EnvioAzure() {}

//Enviar una imagen
    public static void enviarImagen(BufferedImage image) throws UnsupportedEncodingException {
        try {
            URIBuilder uriBuilder = new URIBuilder(uriBase);
            URI uri = uriBuilder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key","´" +  subscriptionKey + "}");

            StringEntity reqEntity = new StringEntity("{'url':'https://cards.scryfall.io/normal/front/c/4/c4e319d7-53f3-40e8-9a75-fe1fd8716733.jpg?1562870478'}");
            request.setEntity(reqEntity);

            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                System.out.println(EntityUtils.toString(entity));
            }

            
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
