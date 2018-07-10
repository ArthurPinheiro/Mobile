package com.tads.ifrn.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Alertas extends AppCompatActivity {

    EditText editTextTitulo, editTextCategoria, editTextMensagem;
    Button Insere, Fechar, Listar;
    private AlertaDAO alertaDAO;

    private BDHelper helper;

    private final String LIST_ALERTAS = "SELECT * FROM alerta";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertas);

        editTextTitulo = (EditText) findViewById(R.id.editTextTitulo);
        editTextCategoria = (EditText) findViewById(R.id.editTextCategoria);
        editTextMensagem = (EditText) findViewById(R.id.editTextMensagem);
        Insere = (Button) findViewById(R.id.btnInserir);
        Fechar = (Button) findViewById(R.id.btnFechar);
     //   Listar = (Button) findViewById(R.id.btnListar);

        alertaDAO = AlertaDAO.getInstancia(this);
    }

    public void Inserir(View view) {
        String titulo = editTextTitulo.getText().toString();
        String categoria = editTextCategoria.getText().toString();
        String mensagem = editTextMensagem.getText().toString();
        if (!titulo.isEmpty() && !categoria.isEmpty() && !mensagem.isEmpty()) {
            alertaDAO.Inserir(titulo, categoria, mensagem);
            Intent data = new Intent();
            data.putExtra("titulo", titulo);
            data.putExtra("categoria", categoria);
            data.putExtra("mensagem", mensagem);
            setResult(RESULT_OK, data);
        } else {
            Toast.makeText(this, "Preencha todos os campos do form!",
                    Toast.LENGTH_LONG).show();
        }

        confirmacao();
    }

    public void confirmacao(){
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Foi adicionado com sucesso");
        dlgAlert.setTitle("Adicionar Alerta");
        dlgAlert.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }
/*
    public List<Alerta> Listar(){
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
*/
    public void Fechar(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}

