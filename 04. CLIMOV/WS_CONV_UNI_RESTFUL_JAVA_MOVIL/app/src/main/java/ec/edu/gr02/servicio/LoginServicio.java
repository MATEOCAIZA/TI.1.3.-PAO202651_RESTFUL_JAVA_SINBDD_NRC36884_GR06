package ec.edu.gr02.servicio;

import ec.edu.gr02.modelo.CredencialesUsuario;

public class LoginServicio {

    // Credenciales permitidas
    private static final String USUARIO_PERMITIDO = "MONSTER";
    private static final String CONTRASEÑA_PERMITIDA = "MONSTER69";

    public boolean autenticar(CredencialesUsuario credenciales) {
        // Validar que los datos no sean nulos
        if (credenciales == null ||
                credenciales.getNombreUsuario() == null ||
                credenciales.getContraseña() == null) {
            return false;
        }

        String user = credenciales.getNombreUsuario().trim();
        String pass = credenciales.getContraseña().trim();

        // Validar credenciales (MONSTER/MONSTER69)
        return user.equalsIgnoreCase(USUARIO_PERMITIDO) && pass.equals(CONTRASEÑA_PERMITIDA);
    }
}
