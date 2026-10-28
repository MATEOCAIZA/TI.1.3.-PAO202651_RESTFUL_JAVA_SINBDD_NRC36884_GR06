package ec.edu.monster.controlador;

import ec.edu.monster.servicio.ConversionServicio;

public class ConversionControlador {

    ConversionServicio servicio = new ConversionServicio();

    public String convertir(double valor, String origen, String destino) {

        // VALIDACIONES BÁSICAS
        if (origen == null || origen.isEmpty()) {
            return "⚠ Unidad origen vacía";
        }

        if (destino == null || destino.isEmpty()) {
            return "⚠ Unidad destino vacía";
        }

        // Llamar al servicio
        return servicio.convertir(valor, origen, destino);
    }
}