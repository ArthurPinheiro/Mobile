package com.tads.ifrn.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class VisualizarNotificacao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_notificacao);

        TextView msg =  (TextView)findViewById(R.id.txtMsg);
        String  mensagem=  getIntent().getStringExtra("mensagem");
        msg.setText(mensagem);
    }
}
