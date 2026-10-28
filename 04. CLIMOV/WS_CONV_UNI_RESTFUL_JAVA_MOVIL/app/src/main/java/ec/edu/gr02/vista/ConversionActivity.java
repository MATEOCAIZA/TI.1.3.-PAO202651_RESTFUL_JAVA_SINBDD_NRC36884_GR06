package ec.edu.gr02.vista;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import ec.edu.gr02.R;
import ec.edu.gr02.controlador.ConversionControlador;

public class ConversionActivity extends AppCompatActivity {

    private EditText etValor;
    private RadioButton rbPulgadasACm;
    private TextView txtResultado;

    private final ConversionControlador controlador = new ConversionControlador();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        etValor = findViewById(R.id.etValor);
        rbPulgadasACm = findViewById(R.id.rbPulgadasACm);
        Button btnConvertir = findViewById(R.id.btnConvertir);
        Button btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        txtResultado = findViewById(R.id.txtResultado);

        btnConvertir.setOnClickListener(v -> {

            String valorTxt = etValor.getText().toString().trim();

            if (valorTxt.isEmpty()) {
                txtResultado.setText("Ingrese un valor");
                return;
            }

            try {
                double valor = Double.parseDouble(valorTxt);

                String origen, destino;

                if (rbPulgadasACm.isChecked()) {
                    origen = "in";
                    destino = "cm";
                } else {
                    origen = "cm";
                    destino = "in";
                }

                txtResultado.setText("Convertiendo...");

                new Thread(() -> {
                    String respuestaServidor = controlador.convertir(valor, origen, destino);
                    String finalResultado = (respuestaServidor != null) ? respuestaServidor : "Error de conexión";

                    runOnUiThread(() -> txtResultado.setText("Resultado: " + finalResultado));
                }).start();

            } catch (NumberFormatException e) {
                txtResultado.setText("Valor numérico inválido");
            }
        });

        btnCerrarSesion.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}
