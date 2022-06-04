package com.example.agendaempleadosandroid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

public class DetalleActivity extends AppCompatActivity {

    Toolbar toolbar2;
    TextView nombreDepartDetalle, idDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        setToolbar2();

        nombreDepartDetalle = findViewById(R.id.nombreDepartDetalle);
        idDetalle = findViewById(R.id.idDetalle);
        pasoDatos();
    }

    private void setToolbar2() {
        toolbar2 = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void pasoDatos() {
        String nombre = "";
        String departamento = "";
        int id = 0;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nombre = extras.getString("nombre");
            departamento = extras.getString("departamento");
            id = extras.getInt("id");
        }
        nombreDepartDetalle.setText("Nombre: " + nombre + "\nDepartamento: " + departamento);
        idDetalle.setText("ID: " + id);
    }


}