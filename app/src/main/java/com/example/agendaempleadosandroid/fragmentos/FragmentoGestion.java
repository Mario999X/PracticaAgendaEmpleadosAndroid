package com.example.agendaempleadosandroid.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.agendaempleadosandroid.R;
import com.example.agendaempleadosandroid.data.DataRoomDatabase;
import com.example.agendaempleadosandroid.data.EmpleadoEntity;
import com.google.android.material.navigation.NavigationBarView;

public class FragmentoGestion extends Fragment {

    Spinner spinnerDepartamentos;
    String opcionSpinner;
    Button btnAgregarEmpleado, btnBorrarEmpleado, btnBorrarAllEmpleados;

    EditText campoNombreEmpleado, campoIdBorrar;

    DataRoomDatabase database;
    EmpleadoEntity empleado;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = DataRoomDatabase.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragmento_gestion, container, false);

        spinnerDepartamentos = view.findViewById(R.id.spinnerDepartamentos);
        String[] departamentos = new String[]{"Europa", "América", "Asia", "África"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, departamentos);
        spinnerDepartamentos.setAdapter(adapter);
        spinnerDepartamentos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                opcionSpinner = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAgregarEmpleado = view.findViewById(R.id.btnAgregarEmpleado);
        btnBorrarEmpleado = view.findViewById(R.id.btnBorrarEmpleado);
        btnBorrarAllEmpleados = view.findViewById(R.id.btnBorrarAllEmpleados);

        btnAgregarEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                empleado = new EmpleadoEntity();

                empleado.setNombre(campoNombreEmpleado.getText().toString());
                empleado.setDepartamento(opcionSpinner);

                long resultado = database.dataDao().insert(empleado);
                Log.i("insert() = ", "" + resultado); // Comprobacion
                Toast.makeText(getContext(), "Empleado Añadido", Toast.LENGTH_SHORT).show();
            }
        });
        btnBorrarEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.dataDao().deleteEmpleado(Integer.parseInt(campoIdBorrar.getText().toString()));
                Toast.makeText(getContext(), "Empleado Eliminado", Toast.LENGTH_SHORT).show();
            }
        });
        btnBorrarAllEmpleados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.dataDao().deleteAllEmpleados();
                Toast.makeText(getContext(), "Eliminación Completa Realizada", Toast.LENGTH_SHORT).show();
            }
        });

        campoNombreEmpleado = view.findViewById(R.id.campoNombreEmpleado);
        campoIdBorrar = view.findViewById(R.id.campoIdBorrar);


        return view;
    }
}