package com.tads.ifrn.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class AlertaDAO {

    private static AlertaDAO instancia;
    private BDHelper helper;

    private final String LIST_ALERTAS = "SELECT * FROM alerta";

    private AlertaDAO (Context context) {
        helper = new BDHelper(context);
    }

    public static AlertaDAO getInstancia(Context context) {
        if (instancia == null)
            instancia = new AlertaDAO(context);
        return instancia;
    }

    public int Inserir (String titulo, String categoria, String mensagem) {
        ContentValues cv = new ContentValues();
        cv.put("titulo", titulo);
        cv.put("categoria", categoria);
        cv.put("mensagem", mensagem);
        long id = helper.getWritableDatabase().insert("alerta", null, cv);
        return (int) id;
    }

    public List<Alerta> getAlertas(){
        Cursor cursor = helper.getReadableDatabase().rawQuery(LIST_ALERTAS, null);

        List<Alerta> lista = new ArrayList<Alerta>();

        while (cursor.moveToNext()){
            Alerta alerta = new Alerta();
            alerta.setId(cursor.getInt(0));
            alerta.setTitulo(cursor.getString(1));
            alerta.setCategoria(cursor.getString(2));
            alerta.setMensagem(cursor.getString(3));
            lista.add(alerta);
        }

        cursor.close();

        return  lista;
    }


}
