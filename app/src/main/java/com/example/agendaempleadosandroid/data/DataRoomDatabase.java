package com.example.agendaempleadosandroid.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {EmpleadoEntity.class}, version = 1) // 1. Entidades y version
public abstract class DataRoomDatabase extends RoomDatabase {

    // 2. Crear constante con el nombre de la BBDD
    private static String DATABASE_NAME = "basededatos";

    // 3. Definir metodo abstracto de la interfaz Dao
    public abstract DataDao dataDao();

    // 4. Crear una instancia de tipo DataRoomDatabase
    private static volatile DataRoomDatabase INSTANCE;

    // 5. Definir metodo getInstance, se copia y pega casi entero
    public synchronized static DataRoomDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    DataRoomDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

}
