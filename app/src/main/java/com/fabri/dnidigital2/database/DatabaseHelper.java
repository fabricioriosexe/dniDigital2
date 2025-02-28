package com.fabri.dnidigital2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import com.fabri.dnidigital2.model.Socio;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "socios.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "socios";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "dni TEXT UNIQUE, " +
                "apellidoNombre TEXT, " +
                "estado TEXT, " +
                "activo INTEGER, " +
                "gremio TEXT, " +
                "fechaVigenciaGremial TEXT, " +
                "clave TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertarSocio(Socio socio) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("dni", socio.getDni());
        values.put("apellidoNombre", socio.getApellidoNombre());
        values.put("estado", socio.getEstado().toString());
        values.put("activo", socio.isActivo() ? 1 : 0);
        values.put("gremio", socio.getGremio().toString());
        values.put("fechaVigenciaGremial", socio.getFechaVigenciaGremial().toString());
        values.put("clave", socio.getClave());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
}
