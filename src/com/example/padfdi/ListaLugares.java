package com.example.padfdi;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Pomer on 26/04/2015.
 */
public class ListaLugares {

    public static List<Lugar> listado;
    public static Map<String, Lugar> ITEM_MAP = new HashMap<String, Lugar>();

    private static int i;
    private static LugarBD bbdd;
    private LayoutInflater inflater = null;
    private static int convertViewCounter = 0;

    public static void setContext(Context context, int mOrdenadoPor, int mOrdenacion) {
        listado = new ArrayList<Lugar>();
        i = 0;
        bbdd = new LugarBD(context);//contexto
        SQLiteDatabase db;
        /* Solo descomentar para pruebas, añader 2 elementos en la BBDD*/
        /*db = bbdd.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(LugarBD.COLUMN_NAME_ENTRY_ID, 1);
        values.put(LugarBD.COLUMN_NAME_NOMBRE, "prueba 1");
        values.put(LugarBD.COLUMN_NAME_DIRECCION, "calle falsa 123");
        //values.put(LugarBD.COLUMN_NAME_LONGITUD, " 40°32'8''N");
        //values.put(LugarBD.COLUMN_NAME_LATITUD, "3°38'45''W");
        values.put(LugarBD.COLUMN_NAME_TELEFONO, "+34913333333");
        values.put(LugarBD.COLUMN_NAME_URL, "restaurante.com");
        values.put(LugarBD.COLUMN_NAME_COMENTARIO, "No volver !! xD");
        values.put(LugarBD.COLUMN_NAME_VALORACION, "1");
        values.put(LugarBD.COLUMN_NAME_TIPOESTABLECIMIENTO, "restaurante");

        long newRowId;//la id asociada a la entrada que vamos a crear
        newRowId = db.insert(LugarBD.TABLE_NAME, null, values);

        ContentValues values2 = new ContentValues();
        //values2.put(LugarBD.COLUMN_NAME_ENTRY_ID, 1);
        values2.put(LugarBD.COLUMN_NAME_NOMBRE, "prueba 2");
        values2.put(LugarBD.COLUMN_NAME_DIRECCION, "calle falsa 456");
        //values2.put(LugarBD.COLUMN_NAME_LONGITUD, " 20°32'8''N");
        //values2.put(LugarBD.COLUMN_NAME_LATITUD, "31°38'45''W");
        values2.put(LugarBD.COLUMN_NAME_TELEFONO, "+34919999999");
        values2.put(LugarBD.COLUMN_NAME_URL, "Burger King.com");
        values2.put(LugarBD.COLUMN_NAME_COMENTARIO, "lo barato sale caro");
        values2.put(LugarBD.COLUMN_NAME_VALORACION, "0");
        values2.put(LugarBD.COLUMN_NAME_TIPOESTABLECIMIENTO, "restaurante");

        newRowId = db.insert(LugarBD.TABLE_NAME, null, values2);
        db.close();*/



        db = bbdd.getReadableDatabase();
        String[] projection = {//solo las columnas que vamos a necesitar
               //LugarBD.COLUMN_NAME_ENTRY_ID,
                LugarBD.COLUMN_NAME_NOMBRE,
                LugarBD.COLUMN_NAME_DIRECCION,
                //LugarBD.COLUMN_NAME_LATITUD,
                //LugarBD.COLUMN_NAME_LONGITUD,
                LugarBD.COLUMN_NAME_TELEFONO,
                LugarBD.COLUMN_NAME_URL,
                LugarBD.COLUMN_NAME_COMENTARIO,
                LugarBD.COLUMN_NAME_VALORACION,
                LugarBD.COLUMN_NAME_TIPOESTABLECIMIENTO,
                LugarBD.COLUMN_NAME_ENTRY_ID
        };

        String sortOrder = "";

        switch(mOrdenadoPor){
            case 1: sortOrder = LugarBD.COLUMN_FECHA; break;
            case 2: sortOrder = LugarBD.COLUMN_NAME_VALORACION; break;
            case 3: sortOrder = LugarBD.COLUMN_NAME_NOMBRE; break;
        }

        switch(mOrdenacion){
            case 1: sortOrder += " DESC"; break;
            case 2: sortOrder += " ASC"; break;
        }


        Cursor c = db.query(
                LugarBD.TABLE_NAME,
                projection,
                null,                // The columns for the WHERE clause
                null,                // The values for the WHERE clause
                null,                // don't group the rows
                null,                // don't filter by row groups
                sortOrder            // The sort order
        );
        //ArrayList<Lugar> listado = new ArrayList<Lugar>();
        //ListaLugares listado = new ListaLugares();

        if(c.moveToFirst()){
            do{
                listado.add(new Lugar(c.getString(0), c.getString(1), c.getString(2),
                        c.getString(3), c.getString(4), c.getInt(5), c.getString(6), i));
                i++;//identificador para el listview
            }while(c.moveToNext());
        }


        c.close();
        db.close();


    }


    public static void addItem(Lugar item) {
        //llamar a insertar y consultar el id que se le asigna -> ojo restar uno por que listview empieza en 0
        SQLiteDatabase db;
        db = bbdd.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LugarBD.COLUMN_NAME_NOMBRE, item.getNombre());
        values.put(LugarBD.COLUMN_NAME_DIRECCION, item.getDireccion());
        values.put(LugarBD.COLUMN_NAME_TELEFONO, item.getTelefono());
        values.put(LugarBD.COLUMN_NAME_URL, item.getUrl());
        values.put(LugarBD.COLUMN_NAME_COMENTARIO, item.getComentario());
        values.put(LugarBD.COLUMN_NAME_VALORACION, item.getValoracion());
        values.put(LugarBD.COLUMN_NAME_TIPOESTABLECIMIENTO, item.getTipo());
        values.put(LugarBD.COLUMN_FECHA, item.getFecha());

        db.insert(LugarBD.TABLE_NAME, null, values);
        db.close();

        item.set_id(i);
        i++;
        listado.add(item);
        ITEM_MAP.put(item.getNombre(), item);
    }

    public static void insertProfile(Lugar lugar) {
       /* db.insertProfile(lugar); // add item to the database
        addItem(lugar); // the same addItem provided with the eclipse wizard*/
    }

    public static Lugar getLugar(int id){
        return listado.get(id);
    }



}

