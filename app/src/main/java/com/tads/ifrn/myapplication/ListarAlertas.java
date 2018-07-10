package com.tads.ifrn.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class ListarAlertas extends AppCompatActivity {

    ListView lv;
    List<Alerta> lista;
    ArrayAdapter adaptador;
    private BDHelper helper;
    private final String LIST_CONTATOS = "SELECT * FROM contato";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_alertas);

        lv = (ListView) findViewById(R.id.lvlitems);
        BDHelper db = new BDHelper(super.getApplicationContext());
        lista = db.getAlertas();
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,lista);
        lv.setAdapter(adaptador);
    }
}