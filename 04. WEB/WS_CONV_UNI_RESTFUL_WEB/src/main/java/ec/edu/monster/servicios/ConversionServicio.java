package ec.edu.monster.servicios;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConversionServicio {

    // URL base que apunta a la API de tu proyecto servidor backend
    private static final String BASE_URL = "http://localhost:8080/WS_CONV_UNI_RESTFUL_JAVA/webresources/conversion/";

    public String ejecutarConversion(double valor, String origen, String destino) {
        try {
            // Se arma la URL dinámica: http://localhost:8080/WS_CONV_UNI_RESTFUL_JAVA/webresources/conversion/20/m/cm
            String urlEndpoint = BASE_URL + valor + "/" + origen + "/" + destino;

            URL url = new URL(urlEndpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "text/plain");

            // Si el servidor backend no responde con estado 200 OK
            if (conn.getResponseCode() != 200) {
                return "Error: El servidor REST respondió con código HTTP " + conn.getResponseCode();
            }

            // Lectura del valor devuelto por el servidor REST
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String respuestaServidor = in.readLine();
            in.close();

            return respuestaServidor;

        } catch (Exception e) {
            return "Error: No se pudo conectar con el servidor WS_CONV_UNI_RESTFUL_JAVA. Asegúrate de que esté desplegado en Payara.";
        }
    }
}