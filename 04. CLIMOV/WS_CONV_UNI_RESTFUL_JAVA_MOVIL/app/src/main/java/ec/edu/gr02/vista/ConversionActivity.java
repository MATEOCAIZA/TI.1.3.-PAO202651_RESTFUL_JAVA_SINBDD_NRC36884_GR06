package ec.edu.gr02.vista;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import ec.edu.gr02.R;
import ec.edu.gr02.controlador.ConversionControlador;

public class ConversionActivity extends AppCompatActivity {

    private EditText etValor;
    private Spinner spCategoria, spOrigen, spDestino;
    private TextView txtResultado;

    private final ConversionControlador controlador = new ConversionControlador();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        etValor = findViewById(R.id.etValor);
        spCategoria = findViewById(R.id.spCategoria);
        spOrigen = findViewById(R.id.spOrigen);
        spDestino = findViewById(R.id.spDestino);

        // Mismas categorías y unidades que los demás clientes móviles (códigos del servidor)
        spCategoria.setAdapter(adaptador("Masa", "Longitud", "Temperatura"));
        spCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                String[] u = position == 1 ? new String[]{"m", "cm", "km", "in", "ft"}
                        : position == 2 ? new String[]{"C", "F", "K", "R"}
                        : new String[]{"kg", "g", "lb", "oz", "t"};
                spOrigen.setAdapter(adaptador(u));
                spDestino.setAdapter(adaptador(u));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
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

                String origen = (String) spOrigen.getSelectedItem();
                String destino = (String) spDestino.getSelectedItem();

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

    private ArrayAdapter<String> adaptador(String... items) {
        return new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
    }
}
