package com.tads.ifrn.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BDHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "alertaBD";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_ALERTAS = "CREATE TABLE alertas ("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "titulo TEXT, categoria TEXT, mensagem TEXT);";

    //public static final String TABLE_ALERTAS = "alertas";
    public static final String ID = "id";
    public static final String TITULO = "titulo";
    public static final String CATEGORIA = "categoria";
    public static final String MENSAGEM = "mensagem";

    private BDHelper helper;

    private final String LIST_ALERTAS = "SELECT * FROM alerta";

    public BDHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase banco) {

        banco.execSQL(TABLE_ALERTAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase banco, int versaoAntiga, int novaVersao) {
        banco.execSQL("DROP TABLE IF EXISTS alertas" + TABLE_ALERTAS);
        onCreate(banco);
    }

    public void addAlerta(Alerta alerta){
        ContentValues values = new ContentValues();
        values.put(TITULO, alerta.getTitulo());
        values.put(CATEGORIA, alerta.getCategoria());
        values.put(MENSAGEM, alerta.getMensagem());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_ALERTAS, null, values);
        db.close();
    }

    public void updateAlerta(Alerta alerta){
        ContentValues values = new ContentValues();
        values.put(TITULO, alerta.getTitulo());
        values.put(CATEGORIA, alerta.getCategoria());
        values.put(MENSAGEM, alerta.getMensagem());

        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_ALERTAS, values, ID + "=?",
                new String[]{
                 String.valueOf(alerta.getId())
                });
        db.close();
    }

    public void deletarAlerta(int alerta_id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM" + TABLE_ALERTAS + "WHERE " + ID + "=" + alerta_id + ";" );
        db.close();
    }

    public Cursor alertaid(int id){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ALERTAS
                        + "WHERE" + ID + "=" + id + ";";
        Cursor c = db.rawQuery(query, null);

        if(c != null){
            c.moveToFirst();
        }

        return c;

    }


   public ArrayList listar(){
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM alertas";
        Cursor registros = db.rawQuery(query, null);

        if(registros.moveToFirst()){
            do{
                lista.add(registros.getString(1));
            } while (registros.moveToNext());
        }

        return lista;
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
