package com.example.proyetogrupo9;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class VisitanteEvento extends AppCompatActivity {
    private Spinner spinnerEventos;
    private EditText etNombreVisitante, etFechaEvento;
    private Button btnGuardarVisitante;

    private static final String FILE_EVENTOS = "eventos.txt";
    private static final String FILE_VISITANTES = "visitantesEsperados.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visitante_evento);

        // Referenciar componentes del diseño
        spinnerEventos = findViewById(R.id.spinnerEventos);
        etNombreVisitante = findViewById(R.id.etNombreVisitante);
        etFechaEvento = findViewById(R.id.etFechaEvento);
        btnGuardarVisitante = findViewById(R.id.btnGuardarVisitante);

        // Cargar eventos al spinner
        cargarEventos();

        // Evento para guardar visitante
        btnGuardarVisitante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventoSeleccionado = spinnerEventos.getSelectedItem().toString();
                String nombreVisitante = etNombreVisitante.getText().toString().trim();
                String fechaEvento = etFechaEvento.getText().toString().trim();

                if (eventoSeleccionado.isEmpty() || nombreVisitante.isEmpty() || fechaEvento.isEmpty()) {
                    Toast.makeText(VisitanteEvento.this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                guardarEnArchivo(eventoSeleccionado, nombreVisitante, fechaEvento);
            }
        });
    }

    // Método para cargar eventos desde el archivo al Spinner
    private void cargarEventos() {
        ArrayList<String> eventos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(getFilesDir() + "/" + FILE_EVENTOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    eventos.add(linea);
                }
            }
        } catch (IOException e) {
            Toast.makeText(this, "No se pudo cargar la lista de eventos.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        if (eventos.isEmpty()) {
            eventos.add("No hay eventos disponibles");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, eventos);
        spinnerEventos.setAdapter(adapter);
    }

    // Método para guardar el visitante en un archivo
    private void guardarEnArchivo(String evento, String nombreVisitante, String fechaEvento) {
        String data = "Evento: " + evento + ", Visitante: " + nombreVisitante + ", Fecha: " + fechaEvento + "\n";

        try (FileOutputStream fos = openFileOutput(FILE_VISITANTES, MODE_APPEND)) {
            fos.write(data.getBytes());
            Toast.makeText(this, "Visitante registrado exitosamente", Toast.LENGTH_SHORT).show();
            limpiarCampos();
        } catch (IOException e) {
            Toast.makeText(this, "No se pudo guardar el visitante. Inténtelo de nuevo.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    // Método para limpiar los campos después de guardar
    private void limpiarCampos() {
        etNombreVisitante.setText("");
        etFechaEvento.setText("");
    }
}