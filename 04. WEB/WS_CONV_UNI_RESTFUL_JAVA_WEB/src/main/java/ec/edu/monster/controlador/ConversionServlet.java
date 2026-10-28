package ec.edu.monster.controlador;

import ec.edu.monster.servicios.ConversionServicio;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ConversionServlet", urlPatterns = {"/ConversionServlet"})
public class ConversionServlet extends HttpServlet {

    private final ConversionServicio servicio = new ConversionServicio();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            double valor = Double.parseDouble(request.getParameter("valor"));
            String origen = request.getParameter("origen");
            String destino = request.getParameter("destino");

            // Invocación a la API REST del servidor backend
            String resultado = servicio.ejecutarConversion(valor, origen, destino);

            // Se devuelven los atributos a la vista JSP
            request.setAttribute("resultado", resultado);
            request.setAttribute("valor", valor);
            request.setAttribute("origenSeleccionado", origen);
            request.setAttribute("destinoSeleccionado", destino);

        } catch (NumberFormatException e) {
            request.setAttribute("resultado", "⚠ Ingrese un valor numérico válido");
        }

        request.getRequestDispatcher("conversion.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("conversion.jsp");
    }
}