//package com.cuatro.pad.padfdi;
package com.example.padfdi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class LugarBD extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Lugares.db";

    public static final String TABLE_NAME = "lugar";
    public static final String COLUMN_NAME_ENTRY_ID = "id";
    public static final String COLUMN_NAME_NOMBRE = "nombre";
    public static final String COLUMN_NAME_DIRECCION = "direccion";
    public static final String COLUMN_NAME_LONGITUD = "longitud";
    public static final String COLUMN_NAME_LATITUD = "latitud";
    public static final String COLUMN_NAME_TELEFONO = "telefono";
    public static final String COLUMN_NAME_URL = "url";
    public static final String COLUMN_NAME_COMENTARIO = "comentario";
    public static final String COLUMN_NAME_VALORACION = "valoracion";
    public static final String COLUMN_NAME_TIPOESTABLECIMIENTO = "tipo";

   /* String sqlCreate = "CREATE TABLE Usuarios (id INTEGER, nombre VARCHAR(255), direccion VARCHAR(255), longitud FLOAT, latitud FLOAT, " +
            "telefono VARCHAR(15), url VARCHAR(255), comentario TEXT, valoracion INT, tipo VARCHAR(25))";*/


    private static final String TEXT_TYPE = " TEXT";
    private static final String VARCHAR_TYPE = " VARCHAR(255)";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String FLOAT_TYPE = " FLOAT";

    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + LugarBD.TABLE_NAME + " (" +
                    LugarBD.COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY," +
                    LugarBD.COLUMN_NAME_NOMBRE + TEXT_TYPE + COMMA_SEP +
                    LugarBD.COLUMN_NAME_DIRECCION + TEXT_TYPE + COMMA_SEP +
                    LugarBD.COLUMN_NAME_LATITUD + VARCHAR_TYPE + COMMA_SEP +
                    LugarBD.COLUMN_NAME_LONGITUD + VARCHAR_TYPE + COMMA_SEP +
                    LugarBD.COLUMN_NAME_TELEFONO + VARCHAR_TYPE + COMMA_SEP +
                    LugarBD.COLUMN_NAME_URL + VARCHAR_TYPE + COMMA_SEP +
                    LugarBD.COLUMN_NAME_COMENTARIO + TEXT_TYPE + COMMA_SEP +
                    LugarBD.COLUMN_NAME_VALORACION + INTEGER_TYPE + COMMA_SEP +
                    LugarBD.COLUMN_NAME_TIPOESTABLECIMIENTO + VARCHAR_TYPE +

            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + LugarBD.TABLE_NAME;

    public LugarBD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}



