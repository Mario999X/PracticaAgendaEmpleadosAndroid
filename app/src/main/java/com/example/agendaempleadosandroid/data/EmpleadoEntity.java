package com.example.agendaempleadosandroid.data;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

// 1. Generar Entidad
@Entity(tableName = "datos_tabla")
public class EmpleadoEntity {

    // 2. Generamos key primaria y demas atributos
    @PrimaryKey(autoGenerate = true)
    public Integer id;
    public String nombre, departamento;

    // 3. Generacion get & set
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
