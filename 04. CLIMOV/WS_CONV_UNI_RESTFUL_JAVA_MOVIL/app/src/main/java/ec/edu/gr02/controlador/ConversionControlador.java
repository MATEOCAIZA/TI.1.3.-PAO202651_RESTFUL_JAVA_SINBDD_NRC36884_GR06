package ec.edu.gr02.controlador;

import ec.edu.gr02.servicio.ConversionServicio;

public class ConversionControlador {

    ConversionServicio servicio = new ConversionServicio();

    public String convertir(double valor, String origen, String destino) {
        return servicio.convertir(valor, origen, destino);
    }
}