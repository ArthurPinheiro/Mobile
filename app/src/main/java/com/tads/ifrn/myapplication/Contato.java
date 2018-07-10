package com.tads.ifrn.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Contato extends AppCompatActivity {

    EditText editTextNome, editTextEmail, editTextTelefone;
    Button Inserir, Fechar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        editTextNome = (EditText) findViewById(R.id.editTextNome);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextTelefone = (EditText) findViewById(R.id.editTextTelefone);
        Inserir = (Button) findViewById(R.id.btnInserir);
        Fechar = (Button) findViewById(R.id.btnFechar);
    }

    public void Inserir(View view) {
        if (view == Inserir) {
            Intent data = new Intent();
            String nome = editTextNome.getText().toString();
            String fone = editTextTelefone.getText().toString();
            String email = editTextEmail.getText().toString();
            data.putExtra("nome", nome);
            data.putExtra("fone", fone);
            data.putExtra("email", email );
            setResult(RESULT_OK, data);
            finish();
        } else if(view == Fechar){
            setResult(RESULT_CANCELED);
            finish();
        }
    }
}
