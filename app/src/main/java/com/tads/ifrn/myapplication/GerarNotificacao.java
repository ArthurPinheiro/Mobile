package com.tads.ifrn.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import android.content.Intent[]intent;

public class GerarNotificacao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerar_notificacao);

        final  EditText msg =  (EditText)  findViewById(R.id.edtMensagem);
        Button  enviar  =  (Button)  findViewById(R.id.btnEnviar);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GerarNotificacao.this, VisualizarNotificacao.class);
                intent.putExtra("mensagem", msg.getText().toString());
                int id = (int) (Math.random() * 1000);
                PendingIntent pi;
                pi = PendingIntent.getActivities(getBaseContext(), id,
                        new Intent[]{intent}, PendingIntent.FLAG_UPDATE_CURRENT);

                Notification notification=  new  Notification.Builder(getBaseContext()).setContentTitle("SmartAlerta" )
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentText(msg.getText())
                        .setContentIntent(pi).build();

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notification.flags|= Notification.FLAG_AUTO_CANCEL;

                notificationManager.notify(id, notification);
            }
        });
    }
}
