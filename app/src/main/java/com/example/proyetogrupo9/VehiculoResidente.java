package com.example.proyetogrupo9;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.FileOutputStream;
import java.io.IOException;

public class VehiculoResidente extends AppCompatActivity {
    private EditText etPlacaVehiculo;
    private RadioButton rbIngreso, rbSalida;
    private Button btnGuardarMovimiento;

    private static final String FILE_NAME = "residentes.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehiculo_residente);

        // Referenciar componentes del diseño
        etPlacaVehiculo = findViewById(R.id.etPlacaVehiculo);
        rbIngreso = findViewById(R.id.rbIngreso);
        rbSalida = findViewById(R.id.rbSalida);
        btnGuardarMovimiento = findViewById(R.id.btnGuardarMovimiento);

        // Evento para guardar movimiento del vehículo
        btnGuardarMovimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String placa = etPlacaVehiculo.getText().toString().trim();
                String tipoMovimiento = rbIngreso.isChecked() ? "Ingreso" : rbSalida.isChecked() ? "Salida" : "";

                if (placa.isEmpty() || tipoMovimiento.isEmpty()) {
                    Toast.makeText(VehiculoResidente.this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                guardarEnArchivo(placa, tipoMovimiento);
            }
        });
    }

    // Método para guardar el movimiento del vehículo en un archivo
    private void guardarEnArchivo(String placa, String tipoMovimiento) {
        String data = "Placa: " + placa + ", Movimiento: " + tipoMovimiento + "\n";

        try (FileOutputStream fos = openFileOutput(FILE_NAME, MODE_APPEND)) {
            fos.write(data.getBytes());
            Toast.makeText(this, "Movimiento registrado exitosamente", Toast.LENGTH_SHORT).show();
            limpiarCampos();
        } catch (IOException e) {
            Toast.makeText(this, "No se pudo guardar el movimiento. Inténtelo de nuevo.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    // Método para limpiar los campos después de guardar
    private void limpiarCampos() {
        etPlacaVehiculo.setText("");
        rbIngreso.setChecked(false);
        rbSalida.setChecked(false);
    }
}