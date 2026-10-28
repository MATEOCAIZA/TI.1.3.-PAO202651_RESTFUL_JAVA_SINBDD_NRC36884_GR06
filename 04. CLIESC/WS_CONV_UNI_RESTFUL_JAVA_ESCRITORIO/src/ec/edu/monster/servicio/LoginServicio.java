package ec.edu.monster.servicio;

public class LoginServicio {

    public boolean login(String usuario, String clave) {

        if (usuario == null || usuario.isEmpty()) return false;
        if (clave == null || clave.isEmpty()) return false;

        return usuario.equals("monster") && clave.equals("monster9");
    }
}