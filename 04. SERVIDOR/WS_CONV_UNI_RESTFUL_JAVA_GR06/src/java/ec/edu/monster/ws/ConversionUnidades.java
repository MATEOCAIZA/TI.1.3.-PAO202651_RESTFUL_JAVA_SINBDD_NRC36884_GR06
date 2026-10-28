package ec.edu.monster.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("conversion")
public class ConversionUnidades {

    @GET
    @Path("{valor}/{origen}/{destino}")
    public String convertir(@PathParam("valor") double valor,
                            @PathParam("origen") String origen,
                            @PathParam("destino") String destino) {

        double resultado = 0;

        // LONGITUD
        if (origen.equals("m") && destino.equals("cm")) resultado = valor * 100;
        else if (origen.equals("cm") && destino.equals("m")) resultado = valor / 100;
        else if (origen.equals("m") && destino.equals("km")) resultado = valor / 1000;
        else if (origen.equals("km") && destino.equals("m")) resultado = valor * 1000;
        else if (origen.equals("m") && destino.equals("in")) resultado = valor * 39.37;
        else if (origen.equals("in") && destino.equals("m")) resultado = valor / 39.37;
        else if (origen.equals("m") && destino.equals("ft")) resultado = valor * 3.28084;
        else if (origen.equals("ft") && destino.equals("m")) resultado = valor / 3.28084;

        // PESO
        else if (origen.equals("kg") && destino.equals("g")) resultado = valor * 1000;
        else if (origen.equals("g") && destino.equals("kg")) resultado = valor / 1000;
        else if (origen.equals("kg") && destino.equals("lb")) resultado = valor * 2.20462;
        else if (origen.equals("lb") && destino.equals("kg")) resultado = valor / 2.20462;
        else if (origen.equals("kg") && destino.equals("oz")) resultado = valor * 35.274;
        else if (origen.equals("oz") && destino.equals("kg")) resultado = valor / 35.274;
        else if (origen.equals("kg") && destino.equals("t")) resultado = valor / 1000;
        else if (origen.equals("t") && destino.equals("kg")) resultado = valor * 1000;

        // TEMPERATURA
        else if (origen.equals("C") && destino.equals("F")) resultado = (valor * 9/5) + 32;
        else if (origen.equals("F") && destino.equals("C")) resultado = (valor - 32) * 5/9;
        else if (origen.equals("C") && destino.equals("K")) resultado = valor + 273.15;
        else if (origen.equals("K") && destino.equals("C")) resultado = valor - 273.15;
        else if (origen.equals("C") && destino.equals("R")) resultado = (valor + 273.15) * 9/5;
        else if (origen.equals("R") && destino.equals("C")) resultado = (valor - 491.67) * 5/9;

        else if (origen.equals(destino)) resultado = valor;

        return String.valueOf(resultado);
    }
}