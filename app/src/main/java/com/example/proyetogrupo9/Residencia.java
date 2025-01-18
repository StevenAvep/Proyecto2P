package com.example.proyetogrupo9;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.FileOutputStream;
import java.io.IOException;

public class Residencia extends AppCompatActivity {
    private EditText etDireccion, etIdResidencia, etIdEtapa;
    private Button btnGuardarResidencia;

    private static final String FILE_NAME = "residencias.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.residencia);

        // Referenciar componentes del diseño
        etDireccion = findViewById(R.id.etDireccion);
        etIdResidencia = findViewById(R.id.etIdResidencia);
        etIdEtapa = findViewById(R.id.etIdEtapa);
        btnGuardarResidencia = findViewById(R.id.btnGuardarResidencia);

        // Evento para guardar residencia
        btnGuardarResidencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String direccion = etDireccion.getText().toString().trim();
                String idResidencia = etIdResidencia.getText().toString().trim();
                String idEtapa = etIdEtapa.getText().toString().trim();

                if (direccion.isEmpty() || idResidencia.isEmpty() || idEtapa.isEmpty()) {
                    Toast.makeText(Residencia.this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                guardarEnArchivo(direccion, idResidencia, idEtapa);
            }
        });
    }

    // Método para guardar la residencia en un archivo
    private void guardarEnArchivo(String direccion, String idResidencia, String idEtapa) {
        String data = "Dirección: " + direccion + ", ID Residencia: " + idResidencia + ", ID Etapa: " + idEtapa + "\n";

        try (FileOutputStream fos = openFileOutput(FILE_NAME, MODE_APPEND)) {
            fos.write(data.getBytes());
            Toast.makeText(this, "Residencia guardada exitosamente", Toast.LENGTH_SHORT).show();
            limpiarCampos();
        } catch (IOException e) {
            Toast.makeText(this, "No se pudo guardar la residencia. Inténtelo de nuevo.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    // Método para limpiar los campos después de guardar
    private void limpiarCampos() {
        etDireccion.setText("");
        etIdResidencia.setText("");
        etIdEtapa.setText("");
    }
}