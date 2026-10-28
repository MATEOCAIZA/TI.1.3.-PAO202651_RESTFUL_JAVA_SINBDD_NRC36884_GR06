package ec.edu.monster.controlador;

import ec.edu.monster.servicio.LoginServicio;

public class LoginControlador {

    LoginServicio servicio = new LoginServicio();

    public boolean login(String usuario, String clave) {
        return servicio.login(usuario, clave);
    }
}