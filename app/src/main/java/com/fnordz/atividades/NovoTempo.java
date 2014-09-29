package com.fnordz.atividades;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import Dom.Contador;


public class NovoTempo extends Activity {

    Contador c ;


    Boolean isPaused = false;
    Boolean Started = false;

    int NotificationID = 100;

    int TempoThread = 1;

    NotificationManager mNotificationManager;


    Thread thread = new Thread() {
        public void run() {

            while (true) {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Notification(getIntent().getStringExtra("Nome"), c.montaTempo(TempoThread));

                            if(isPaused)
                                 TempoThread++;
                        }
                    });
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_novo_tempo);

        ((Button)findViewById(R.id.btStart)).setOnClickListener(funcoesbotao);
        ((Button)findViewById(R.id.btConcluido)).setOnClickListener(funcoesbotao);

       c = new Contador(((Chronometer)findViewById(R.id.chronometer)));

    }



    View.OnClickListener funcoesbotao = new  View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch(view.getId()){

                case R.id.btStart:

                    if(isPaused == false){
                        if(Started == false) {
                            TempoThread = c.TempoTotal();
                            thread.start();
                        }
                        Started = true;
                        ((Button)findViewById(R.id.btStart)).setText("Pause");
                        c.IniciaContador();
                        isPaused = true;
                    }else if(isPaused == true){
                        ((Button)findViewById(R.id.btStart)).setText("Start");
                        c.PausarContador();
                        isPaused = false;
                    }

                    break;
                case R.id.btConcluido:
                    c.PausarContador();

                    Intent RetornaTempo = new Intent(NovoTempo.this,TemposAtividade.class);
                    RetornaTempo.putExtra("Tempo", c.TempoTotal());

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                    RetornaTempo.putExtra("Data", sdf.format(new Date()).toString());

                    setResult(2,RetornaTempo);

                    Thread i = thread;
                    thread = null;
                    i.interrupt();


                    mNotificationManager.cancelAll();
                     finish();
            }


        }
    };
   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.novo_tempo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }if(id == R.id.btOcultar){

            Intent homeIntent= new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory(Intent.CATEGORY_HOME);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(homeIntent);

        }
        return super.onOptionsItemSelected(item);
    }

    public void Notification(String Atividade, String Tempo){

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);

        mBuilder.setSmallIcon(R.drawable.ic_launcher);
        mBuilder.setContentTitle(Atividade);
        mBuilder.setContentText(Tempo);

        Intent intent = new Intent(this, this.getClass());
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(getClass());

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(getIntent());
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        mBuilder.setContentIntent(pendingIntent);

        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // notificationID allows you to update the notification later on.
        mNotificationManager.notify(NotificationID, mBuilder.build());

    }

}
