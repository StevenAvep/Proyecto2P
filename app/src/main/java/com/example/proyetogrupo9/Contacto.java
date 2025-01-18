package com.example.proyetogrupo9;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.FileOutputStream;
import java.io.IOException;

public class Contacto extends AppCompatActivity {
    private EditText etDireccionResidencia, etNombreContacto, etNumeroContacto, etPlacaVehiculo;
    private Button btnGuardarContacto;

    private static final String FILE_NAME = "contactos.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacto);

        // Referenciar componentes del diseño
        etDireccionResidencia = findViewById(R.id.etDireccionResidencia);
        etNombreContacto = findViewById(R.id.etNombreContacto);
        etNumeroContacto = findViewById(R.id.etNumeroContacto);
        etPlacaVehiculo = findViewById(R.id.etPlacaVehiculo);
        btnGuardarContacto = findViewById(R.id.btnGuardarContacto);

        // Evento para guardar contacto
        btnGuardarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String direccionResidencia = etDireccionResidencia.getText().toString().trim();
                String nombreContacto = etNombreContacto.getText().toString().trim();
                String numeroContacto = etNumeroContacto.getText().toString().trim();
                String placaVehiculo = etPlacaVehiculo.getText().toString().trim();

                if (direccionResidencia.isEmpty() || nombreContacto.isEmpty() || numeroContacto.isEmpty() || placaVehiculo.isEmpty()) {
                    Toast.makeText(Contacto.this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                guardarEnArchivo(direccionResidencia, nombreContacto, numeroContacto, placaVehiculo);
            }
        });
    }

    // Método para guardar contacto y vehículo en un archivo
    private void guardarEnArchivo(String direccionResidencia, String nombreContacto, String numeroContacto, String placaVehiculo) {
        String data = "Residencia: " + direccionResidencia + ", Contacto: " + nombreContacto + ", Número: " + numeroContacto + ", Vehículo: " + placaVehiculo + "\n";

        try (FileOutputStream fos = openFileOutput(FILE_NAME, MODE_APPEND)) {
            fos.write(data.getBytes());
            Toast.makeText(this, "Contacto y vehículo guardados exitosamente", Toast.LENGTH_SHORT).show();
            limpiarCampos();
        } catch (IOException e) {
            Toast.makeText(this, "No se pudo guardar la información. Inténtelo de nuevo.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    // Método para limpiar los campos después de guardar
    private void limpiarCampos() {
        etDireccionResidencia.setText("");
        etNombreContacto.setText("");
        etNumeroContacto.setText("");
        etPlacaVehiculo.setText("");
    }
}