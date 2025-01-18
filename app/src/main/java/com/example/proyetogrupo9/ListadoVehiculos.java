package com.example.proyetogrupo9;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ListadoVehiculos extends AppCompatActivity {
    private ListView lvVehiculos;

    private static final String FILE_NAME = "vehiculos.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_vehiculos);

        // Referenciar el ListView
        lvVehiculos = findViewById(R.id.lvVehiculos);

        // Cargar datos en el ListView
        cargarListado();
    }

    // Método para cargar el listado de vehículos
    private void cargarListado() {
        ArrayList<String> vehiculos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(getFilesDir() + "/" + FILE_NAME))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    vehiculos.add(linea);
                }
            }
        } catch (IOException e) {
            Toast.makeText(this, "No se pudo cargar el listado de vehículos.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        if (vehiculos.isEmpty()) {
            vehiculos.add("No hay vehículos registrados.");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, vehiculos);
        lvVehiculos.setAdapter(adapter);
    }
}