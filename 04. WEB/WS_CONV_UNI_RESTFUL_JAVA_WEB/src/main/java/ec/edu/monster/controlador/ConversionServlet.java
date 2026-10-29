package ec.edu.monster.controlador;

import ec.edu.monster.servicios.ConversionServicio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ConversionServlet", urlPatterns = {"/ConversionServlet"})
public class ConversionServlet extends HttpServlet {

    private final ConversionServicio servicio = new ConversionServicio();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String valorStr = request.getParameter("valor");
        String tipoConversion = request.getParameter("tipoConversion");

        try {
            if (valorStr != null && !valorStr.trim().isEmpty()) {
                // Remplazar coma por punto para evitar fallo en el endpoint REST
                valorStr = valorStr.replace(",", ".").trim();
                double valor = Double.parseDouble(valorStr);

                if (tipoConversion == null || !tipoConversion.contains("_")) {
                    tipoConversion = "m_cm";
                }

                String[] partes = tipoConversion.split("_");
                String origen = partes[0];
                String destino = partes[1];

                // Petición al servicio REST
                String respuestaREST = servicio.ejecutarConversion(valor, origen, destino);

                request.setAttribute("valor", valorStr);
                request.setAttribute("tipoSeleccionado", tipoConversion);
                request.setAttribute("resultado", respuestaREST);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("resultado", "Error: Ingrese un número válido.");
        } catch (Exception e) {
            request.setAttribute("resultado", "Error interno: " + e.getMessage());
        }

        request.getRequestDispatcher("conversion.jsp").forward(request, response);
    }
}