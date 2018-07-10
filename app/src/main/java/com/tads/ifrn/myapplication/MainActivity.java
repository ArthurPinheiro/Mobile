package com.tads.ifrn.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView imgContato, imgAlerta, imgListar, imgNotificacao;

    private AlertaDAO alertaDAO;

    private TextView textViewAlertas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgContato = (ImageView) findViewById(R.id.imgContato);
        imgContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrudContato();
            }
        });

        imgAlerta = (ImageView) findViewById(R.id.imgAlerta);
        imgAlerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrudAlerta();
            }
        });

        imgListar = (ImageView) findViewById(R.id.imgListar);
       imgListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Listar();
            }
        });

        imgNotificacao = (ImageView) findViewById(R.id.imgNotificacao);
        imgNotificacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notificacao();
            }
        });

        alertaDAO = AlertaDAO.getInstancia(this);


    }
/*
    @Override
    protected void onStart(){
        super.onStart();

        List<Alerta> alertaList = alertaDAO.getAlertas();
        String result = "";

        for(Alerta a : alertaList){
            result += a;
        }

        textViewAlertas.setText(result);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                String titulo = data.getStringExtra("titulo");
                String categoria = data.getStringExtra("categoria");
                String mensagem = data.getStringExtra("mensagem");
                String atual = textViewAlertas.getText().toString();

                atual += titulo + ": " + categoria + " : " + mensagem + "\n";
                textViewAlertas.setText(atual);
            }
        }
    }
*/
    private void CrudAlerta() {
        Intent alerta = new Intent(this, Alertas.class);
        startActivity(alerta );
    }

    public void Notificacao(){
        Intent notificacao = new Intent(this, GerarNotificacao.class);
        startActivity(notificacao );
    }

     public void CrudContato(){
        Intent contato = new Intent(this, Contato.class);
        startActivity(contato);
    }

    public void Listar(){
        Intent lista = new Intent(this, ListarAlertas.class);
        startActivity(lista);
    }

}
