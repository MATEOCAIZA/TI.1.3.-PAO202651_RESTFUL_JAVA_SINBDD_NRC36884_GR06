package ec.edu.monster.servicios;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConversionServicio {

    private static final String BASE_URL = "http://localhost:8080/WS_CONV_UNI_RESTFUL_JAVA_GR06/webresources/conversion/";

    public String ejecutarConversion(double valor, String origen, String destino) {
        try {
            // Se arma la URL asegurando que valor sea numérico estándar con punto
            String urlEndpoint = BASE_URL + valor + "/" + origen.trim() + "/" + destino.trim();

            URL url = new URL(urlEndpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "text/plain");

            if (conn.getResponseCode() != 200) {
                return "Error: El servidor REST respondió con código HTTP " + conn.getResponseCode();
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String respuestaServidor = in.readLine();
            in.close();

            return respuestaServidor;

        } catch (Exception e) {
            return "Error: No se pudo conectar con el servidor REST backend.";
        }
    }
}