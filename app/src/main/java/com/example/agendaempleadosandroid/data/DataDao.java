package com.example.agendaempleadosandroid.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DataDao {

    @Insert
    public long insert(EmpleadoEntity e); // Devuelve 1, si falla 0 | Inserta un Empleado

    @Query("DELETE FROM datos_tabla WHERE id = :mId")
    public void deleteEmpleado(Integer mId); // Elimina un empleado con mismo ID

    @Query("DELETE FROM datos_tabla")
    public void deleteAllEmpleados(); // Eliminacion completa

    /* @Query("DELETE FROM datos_tabla WHERE departamento = :mDepartamento")
     public void deleteAllDepartamento(String mDepartamento); // Elimina a todos los empleados de un departamento
 */
    @Query("SELECT * FROM datos_tabla")
    public List<EmpleadoEntity> selectEmpleados(); // Muestra los empleados

    @Query("SELECT * FROM datos_tabla ORDER BY nombre")
    public List<EmpleadoEntity> selectEmpleadosNombre(); // Muestra los empleados ordenados por nombre

    @Query("SELECT * FROM datos_tabla ORDER BY id")
    public List<EmpleadoEntity> selectEmpleadosId(); // Muestra los empleados ordenados por ID

    /*@Query("SELECT * FROM datos_tabla WHERE nombre = :mNombre")
    public List<EmpleadoEntity> searchNombre(String mNombre); // Busca el nombre de empleado
*/
    @Query("SELECT * FROM datos_tabla where departamento = :mDepartamento")
    public List<EmpleadoEntity> searchDepartamento(String mDepartamento); // Busca el departamento

}
