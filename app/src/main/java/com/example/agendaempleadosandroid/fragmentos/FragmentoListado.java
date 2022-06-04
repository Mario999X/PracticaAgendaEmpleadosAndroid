package com.example.agendaempleadosandroid.fragmentos;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.agendaempleadosandroid.AdapterRecyclerView;
import com.example.agendaempleadosandroid.ClickListener;
import com.example.agendaempleadosandroid.DetalleActivity;
import com.example.agendaempleadosandroid.R;
import com.example.agendaempleadosandroid.data.DataRoomDatabase;
import com.example.agendaempleadosandroid.data.EmpleadoEntity;

import java.util.ArrayList;
import java.util.List;

public class FragmentoListado extends Fragment {

    Spinner spinnerDepartamentos2;
    String opcionSpinner;
    Button btnOrdenarNombre, btnOrdenarId, btnFiltroDepartamento;

    DataRoomDatabase database;

    RecyclerView recyclerView;
    List<EmpleadoEntity> empleadoEntities = new ArrayList<>();
    AdapterRecyclerView adapterView;
    LinearLayoutManager linearLayoutManager;
    ClickListener clickListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = DataRoomDatabase.getInstance(getContext());

        empleadoEntities = database.dataDao().selectEmpleados();
        verDetalles();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragmento_listado, container, false);

        spinnerDepartamentos2 = view.findViewById(R.id.spinnerDepartamentos2);
        String[] departamentos = new String[]{"Europa", "América", "Asia", "África"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, departamentos);
        spinnerDepartamentos2.setAdapter(adapter);
        spinnerDepartamentos2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                opcionSpinner = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnOrdenarNombre = view.findViewById(R.id.btnOrdenarNombre);
        btnOrdenarId = view.findViewById(R.id.btnOrdenarId);
        btnFiltroDepartamento = view.findViewById(R.id.btnFiltroDepartamento);

        recyclerView = view.findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterView = new AdapterRecyclerView(empleadoEntities, getActivity(), clickListener);
        recyclerView.setAdapter(adapterView);

        btnOrdenarNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                empleadoEntities = database.dataDao().selectEmpleadosNombre();
                Toast.makeText(getContext(), "Ordenados Alfabéticamente", Toast.LENGTH_SHORT).show();

                adapterView = new AdapterRecyclerView(empleadoEntities, getActivity(), clickListener);
                recyclerView.setAdapter(adapterView);
            }
        });
        btnOrdenarId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                empleadoEntities = database.dataDao().selectEmpleadosId();
                Toast.makeText(getContext(), "Ordenados por ID", Toast.LENGTH_SHORT).show();

                adapterView = new AdapterRecyclerView(empleadoEntities, getActivity(), clickListener);
                recyclerView.setAdapter(adapterView);

            }
        });
        btnFiltroDepartamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                empleadoEntities = database.dataDao().searchDepartamento(opcionSpinner);
                Toast.makeText(getContext(), "Filtro Aplicado", Toast.LENGTH_SHORT).show();

                adapterView = new AdapterRecyclerView(empleadoEntities, getActivity(), clickListener);
                recyclerView.setAdapter(adapterView);
            }
        });

        return view;
    }

    private void verDetalles() {
        clickListener = posicion -> {
            Intent i = new Intent(getContext(), DetalleActivity.class);
            i.putExtra("id", empleadoEntities.get(posicion).getId());
            i.putExtra("nombre", empleadoEntities.get(posicion).getNombre());
            i.putExtra("departamento", empleadoEntities.get(posicion).getDepartamento());
            startActivity(i);
        };
    }
}