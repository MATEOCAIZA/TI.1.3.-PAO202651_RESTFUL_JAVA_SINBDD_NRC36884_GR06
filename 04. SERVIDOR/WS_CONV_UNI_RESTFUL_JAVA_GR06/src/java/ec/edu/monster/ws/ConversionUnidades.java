package ec.edu.monster.ws;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("conversion")
public class ConversionUnidades {

    // LONGITUD: factor a metros
    private static final Map<String, Double> LONGITUD = new HashMap<>();
    // PESO: factor a kilogramos
    private static final Map<String, Double> PESO = new HashMap<>();

    static {
        LONGITUD.put("m", 1.0);
        LONGITUD.put("cm", 0.01);
        LONGITUD.put("km", 1000.0);
        LONGITUD.put("in", 0.0254);
        LONGITUD.put("ft", 0.3048);

        PESO.put("kg", 1.0);
        PESO.put("g", 0.001);
        PESO.put("lb", 0.45359237);
        PESO.put("oz", 0.028349523125);
        PESO.put("t", 1000.0);
    }

    @GET
    @Path("{valor}/{origen}/{destino}")
    public String convertir(@PathParam("valor") double valor,
                            @PathParam("origen") String origen,
                            @PathParam("destino") String destino) {

        double resultado = 0;

        if (origen.equals(destino)) resultado = valor;
        // cualquier par de la misma magnitud pasa por la unidad base
        else if (LONGITUD.containsKey(origen) && LONGITUD.containsKey(destino))
            resultado = valor * LONGITUD.get(origen) / LONGITUD.get(destino);
        else if (PESO.containsKey(origen) && PESO.containsKey(destino))
            resultado = valor * PESO.get(origen) / PESO.get(destino);
        // TEMPERATURA: se pasa por grados Celsius
        else if (esTemperatura(origen) && esTemperatura(destino))
            resultado = desdeCelsius(aCelsius(valor, origen), destino);

        return String.valueOf(resultado);
    }

    private static boolean esTemperatura(String u) {
        return u.equals("C") || u.equals("F") || u.equals("K") || u.equals("R");
    }

    private static double aCelsius(double v, String u) {
        switch (u) {
            case "F": return (v - 32) * 5 / 9;
            case "K": return v - 273.15;
            case "R": return (v - 491.67) * 5 / 9;
            default:  return v;
        }
    }

    private static double desdeCelsius(double c, String u) {
        switch (u) {
            case "F": return c * 9 / 5 + 32;
            case "K": return c + 273.15;
            case "R": return (c + 273.15) * 9 / 5;
            default:  return c;
        }
    }
}
