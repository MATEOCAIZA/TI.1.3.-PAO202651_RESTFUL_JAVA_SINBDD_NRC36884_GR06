package ec.edu.gr02.controlador;

import ec.edu.gr02.modelo.CredencialesUsuario;
import ec.edu.gr02.servicio.LoginServicio;

public class LoginControlador {

    private final LoginServicio servicio = new LoginServicio();

    public boolean login(String usuario, String clave) {

        if (usuario == null || clave == null) return false;

        if (usuario.isEmpty() || clave.isEmpty()) return false;

        CredencialesUsuario credenciales = new CredencialesUsuario(usuario, clave);
        return servicio.autenticar(credenciales);
    }
}
