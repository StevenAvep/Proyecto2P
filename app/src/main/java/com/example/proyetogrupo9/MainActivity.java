package com.example.proyetogrupo9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyetogrupo9.Contacto;
import com.example.proyetogrupo9.Evento;
import com.example.proyetogrupo9.ListadoVehiculos;
import com.example.proyetogrupo9.R;
import com.example.proyetogrupo9.Residencia;
import com.example.proyetogrupo9.VehiculoResidente;
import com.example.proyetogrupo9.VehiculoVisitante;
import com.example.proyetogrupo9.VisitanteEvento;

public class MainActivity extends AppCompatActivity {

    private Button btnRegistrarEtapa, btnRegistrarResidencia, btnRegistrarContacto, btnRegistrarEvento,
            btnRegistrarVisitante, btnVehiculoResidente, btnVehiculoVisitante, btnListadoVehiculos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referenciar los botones del diseño
        btnRegistrarEtapa = findViewById(R.id.btnRegistrarEtapa);
        btnRegistrarResidencia = findViewById(R.id.btnRegistrarResidencia);
        btnRegistrarContacto = findViewById(R.id.btnRegistrarContacto);
        btnRegistrarEvento = findViewById(R.id.btnRegistrarEvento);
        btnRegistrarVisitante = findViewById(R.id.btnRegistrarVisitante);
        btnVehiculoResidente = findViewById(R.id.btnVehiculoResidente);
        btnVehiculoVisitante = findViewById(R.id.btnVehiculoVisitante);
        btnListadoVehiculos = findViewById(R.id.btnListadoVehiculos);

        // Configurar listeners para los botones
        btnRegistrarEtapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Etapa.class);
                startActivity(intent);
            }
        });

        btnRegistrarResidencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Residencia.class);
                startActivity(intent);
            }
        });

        btnRegistrarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Contacto.class);
                startActivity(intent);
            }
        });

        btnRegistrarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Evento.class);
                startActivity(intent);
            }
        });

        btnRegistrarVisitante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VisitanteEvento.class);
                startActivity(intent);
            }
        });

        btnVehiculoResidente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VehiculoResidente.class);
                startActivity(intent);
            }
        });

        btnVehiculoVisitante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VehiculoVisitante.class);
                startActivity(intent);
            }
        });

        btnListadoVehiculos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListadoVehiculos.class);
                startActivity(intent);
            }
        });
    }
}