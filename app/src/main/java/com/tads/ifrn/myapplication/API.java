package com.tads.ifrn.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class API extends AppCompatActivity {

    private EditText editTextCEP;
    private TextView showRua, showBairro, showCidade;
    private CEP cep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCEP = (EditText) findViewById(R.id.editCEP);
        showRua = (TextView) findViewById(R.id.showRua);
        showBairro = (TextView) findViewById(R.id.showBairro);
        showCidade = (TextView) findViewById(R.id.showCidade);
    }

    public void resolveCEP(View view) {
        String consulta = editTextCEP.getText().toString();
        String url = "https://viacep.com.br/ws/"+consulta+"/json/";
        new DownloadCEP().execute("GET", url);
    }

    public class DownloadCEP extends AsyncTask<String, Void, String> {

        private String acao = null;
        private URL url = null;
        private String method;
        private HttpURLConnection urlConnection = null;

        @Override
        protected String doInBackground(String... params) {
            try {
                method = params[0];
                url = new URL(params[1]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.setRequestMethod(method);
                urlConnection.connect();
                int response = urlConnection.getResponseCode();
                if (response == HttpURLConnection.HTTP_OK) {
                    Reader reader = new InputStreamReader(urlConnection.getInputStream());
                    Gson gson = new Gson();
                    cep = gson.fromJson(reader, CEP.class);
                } else {
                    trataErro(response);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            showBairro.setText(cep.getBairro());
            showRua.setText(cep.getLogradouro());
            showCidade.setText(cep.getLocalidade());
        }

        // Depois vale a pena expandir esse método!
        private void trataErro(int response) {
        }
    }


}
