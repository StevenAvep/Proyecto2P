package com.example.proyetogrupo9;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyetogrupo9.R;

import java.io.FileOutputStream;
import java.io.IOException;

public class Etapa extends AppCompatActivity {

    private EditText etNombreEtapa, etIdEtapa;
    private Button btnGuardarEtapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etapa); // Asegúrate de que el archivo XML tenga este nombre

        // Referenciar elementos del diseño
        etNombreEtapa = findViewById(R.id.etNombreEtapa);
        etIdEtapa = findViewById(R.id.etIdEtapa);
        btnGuardarEtapa = findViewById(R.id.btnGuardarEtapa);

        // Configurar el botón "Guardar Etapa"
        btnGuardarEtapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombreEtapa.getText().toString().trim();
                String id = etIdEtapa.getText().toString().trim();

                if (nombre.isEmpty() || id.isEmpty()) {
                    Toast.makeText(Etapa.this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
                } else {
                    guardarEnArchivo(nombre, id);
                }
            }
        });
    }

    private void guardarEnArchivo(String nombre, String id) {
        String data = "Nombre: " + nombre + ", ID: " + id + "\n";

        try (FileOutputStream fos = openFileOutput("etapas.txt", MODE_APPEND)) {
            fos.write(data.getBytes());
            Toast.makeText(this, "Etapa guardada exitosamente.", Toast.LENGTH_SHORT).show();

            // Limpiar campos
            etNombreEtapa.setText("");
            etIdEtapa.setText("");
        } catch (IOException e) {
            Toast.makeText(this, "Error al guardar la etapa.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}