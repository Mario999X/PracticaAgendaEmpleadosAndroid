package com.example.agendaempleadosandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.agendaempleadosandroid.fragmentos.FragmentoGestion;
import com.example.agendaempleadosandroid.fragmentos.FragmentoListado;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    /*  Orden Proyecto:
            1. Dependencias Room
            2. Generacion de entidades y demas (data -> 1.DataEntity, 2.DataDao, 3.DataRoomDatabase)
            3. Momento Layout App
            4. Programacion de actividades y fragmentos.

                !! [Android Studio] MIRAR LA FUNCION "App Inspection" PARA LA BASE DE DATOS,
            SOLO FUNCIONA CON LA APP EN MOVIMIENTO
     */

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enlazarComponentes();
        menu();
        reemplazarFragmentos(new FragmentoListado());
    }

    private void enlazarComponentes() {
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
    }

    private void reemplazarFragmentos(Fragment f) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragLayout, f);
        ft.commit();
    }

    private void menu() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        menuListeners();
    }

    private void menuListeners() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_frag_mod:
                        reemplazarFragmentos(new FragmentoGestion());
                        break;
                    case R.id.menu_frag_lista:
                        reemplazarFragmentos(new FragmentoListado());
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }
}
