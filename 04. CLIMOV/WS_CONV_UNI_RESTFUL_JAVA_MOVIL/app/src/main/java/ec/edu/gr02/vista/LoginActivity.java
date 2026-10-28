package ec.edu.gr02.vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import ec.edu.gr02.R;
import ec.edu.gr02.controlador.LoginControlador;

public class LoginActivity extends AppCompatActivity {

    EditText usuarioField, contrasenaField;
    LoginControlador controlador = new LoginControlador();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuarioField = findViewById(R.id.usuarioField);
        contrasenaField = findViewById(R.id.contrasenaField);
    }

    // ESTE MÉTODO ES LLAMADO DESDE EL XML (onClick="onLogin")
    public void onLogin(View view) {

        String usuario = usuarioField.getText().toString();
        String clave = contrasenaField.getText().toString();

        if (usuario.isEmpty() || clave.isEmpty()) {
            Toast.makeText(this, "Campos vacíos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (controlador.login(usuario, clave)) {
            Intent intent = new Intent(this, ConversionActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }
    }
}