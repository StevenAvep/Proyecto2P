package com.example.proyetogrupo9;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.FileOutputStream;
import java.io.IOException;

public class Evento extends AppCompatActivity {
    private EditText etNombreEvento, etUbicacion, etFecha, etCantidadPersonas;
    private Button btnGuardarEvento;

    private static final String FILE_NAME = "eventos.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evento);

        // Referenciar componentes del diseño
        etNombreEvento = findViewById(R.id.etNombreEvento);
        etUbicacion = findViewById(R.id.etUbicacion);
        etFecha = findViewById(R.id.etFecha);
        etCantidadPersonas = findViewById(R.id.etCantidadPersonas);
        btnGuardarEvento = findViewById(R.id.btnGuardarEvento);

        // Evento para guardar el evento
        btnGuardarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreEvento = etNombreEvento.getText().toString().trim();
                String ubicacion = etUbicacion.getText().toString().trim();
                String fecha = etFecha.getText().toString().trim();
                String cantidadPersonas = etCantidadPersonas.getText().toString().trim();

                if (nombreEvento.isEmpty() || ubicacion.isEmpty() || fecha.isEmpty() || cantidadPersonas.isEmpty()) {
                    Toast.makeText(Evento.this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                guardarEnArchivo(nombreEvento, ubicacion, fecha, cantidadPersonas);
            }
        });
    }

    // Método para guardar el evento en un archivo
    private void guardarEnArchivo(String nombreEvento, String ubicacion, String fecha, String cantidadPersonas) {
        String data = "Evento: " + nombreEvento + ", Ubicación: " + ubicacion + ", Fecha: " + fecha + ", Personas: " + cantidadPersonas + "\n";

        try (FileOutputStream fos = openFileOutput(FILE_NAME, MODE_APPEND)) {
            fos.write(data.getBytes());
            Toast.makeText(this, "Evento guardado exitosamente", Toast.LENGTH_SHORT).show();
            limpiarCampos();
        } catch (IOException e) {
            Toast.makeText(this, "No se pudo guardar el evento. Inténtelo de nuevo.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    // Método para limpiar los campos después de guardar
    private void limpiarCampos() {
        etNombreEvento.setText("");
        etUbicacion.setText("");
        etFecha.setText("");
        etCantidadPersonas.setText("");
    }
}