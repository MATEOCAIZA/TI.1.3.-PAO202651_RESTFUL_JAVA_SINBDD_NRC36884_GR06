package ec.edu.monster.servicio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConversionServicio {

    public String convertir(double valor, String origen, String destino) {

        try {
            String ruta = "http://localhost:8080/WS_CONV_UNI_RESTFUL_JAVA/webresources/conversion/"
                    + valor + "/" + origen + "/" + destino;

            URL url = new URL(ruta);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String resultado = in.readLine();

            in.close();

            return resultado;

        } catch (Exception e) {
            return "Error de conexión";
        }
    }
}