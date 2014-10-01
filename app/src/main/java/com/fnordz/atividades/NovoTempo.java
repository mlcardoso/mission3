package com.fnordz.atividades;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.net.ContentHandler;
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
    Boolean Concluido = false;
    Boolean isReverse = false;

    int NotificationID = 100;

    int TempoThread = 1;
    int TempoInicial = 0;

    NotificationManager mNotificationManager;

    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);

    Thread thread = new Thread() {
        public void run() {
            Notification(getIntent().getStringExtra("Nome"), "Atvidade iniciada em " + c.montaTempo(TempoThread));
            while (!Thread.interrupted()) {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            int hora = (int) TempoThread / 3600;
                            int min = ((int) TempoThread / 60) - hora * 60;
                            int seg = (int) ((TempoThread % 60) / 1);

                            if(isPaused && !isReverse) {
                                TempoThread++;
                            }else if(isReverse && isPaused) {
                                TempoThread--;
                                TempoInicial--;
                            }
                            if(((min == 0 ||min == 30) && seg == 0)) {
                                if (isReverse) {

                                    mBuilder.setContentText("Restam " + String.format("%02d", hora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", seg) + " para o fim da atividade");
                                    mNotificationManager.notify(NotificationID, mBuilder.build());

                                } else {

                                    mBuilder.setContentText("Já se passaram  " + String.format("%02d", hora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", seg) + " na sua atividade ");
                                    mNotificationManager.notify(NotificationID, mBuilder.build());

                                }
                            }
                        }
                    });
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            if(Concluido)
                break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_novo_tempo);

        ActionBar ab = getActionBar();
        ab.setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));

        ((Button)findViewById(R.id.btStart)).setOnClickListener(funcoesbotao);
        ((Button)findViewById(R.id.btConcluido)).setOnClickListener(funcoesbotao);

       c = new Contador(((Chronometer)findViewById(R.id.chronometer)));

     /*   Intent i = new Intent(NovoTempo.this, Notification.class);

        String titulo = "SEU TITULO";
        String mensagem = "SUA MENSAGEM";

        i.putExtra("br_mensagem", mensagem);
        i.putExtra("br_title", titulo);

        sendBroadcast(i);*/
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

                        if(isReverse && isPaused == false){
                            TempoThread = TempoInicial;
                            c.IniciaContadorReverso(TempoInicial);
                        }
                        else
                            c.IniciaContador();
                        isPaused = true;
                    }else if(isPaused == true){
                        ((Button)findViewById(R.id.btStart)).setText("Start");
                        if(isReverse) {
                            c.PausarContadorReverso();
                        }else
                            c.PausarContador();
                        isPaused = false;

                    }

                    break;
                case R.id.btConcluido:


                    c.PausarContador();

                    Intent RetornaTempo = new Intent(NovoTempo.this,TemposAtividade.class);

                    if(c.TempoTotal() != 0) {

                        RetornaTempo.putExtra("Tempo", c.TempoTotal());

                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                        RetornaTempo.putExtra("Data", sdf.format(new Date()).toString());
                        mNotificationManager.cancelAll();
                        setResult(2, RetornaTempo);

                    }

                    Concluido = true;
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

        }if(id == R.id.btReverso){

            DialogReverso();
        }
        return super.onOptionsItemSelected(item);
    }

    public void Notification(String Atividade, String Tempo){

        mBuilder = new NotificationCompat.Builder(this);

        mBuilder.setSmallIcon(R.drawable.ic_launcher);
        mBuilder.setContentTitle(Atividade);
        mBuilder.setContentText(Tempo);

        Intent intent = new Intent(this, this.getClass());
        intent.getStringExtra("Nome");
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

    public void DialogReverso(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(NovoTempo.this);

        alertDialog.setTitle("Segundos");

        final EditText nome = new EditText(this);
        nome.setGravity(Gravity.CENTER);
        nome.setHeight(100);


        final TimePicker Time = new TimePicker(this);
        Time.setIs24HourView(true);
        Time.setCurrentHour(0);
        Time.setCurrentMinute(0);

        alertDialog.setView(Time);


        alertDialog.setPositiveButton("Set",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        TempoInicial = Time.getCurrentHour()*3600 + Time.getCurrentMinute()*60;
                        ((Chronometer)findViewById(R.id.chronometer)).setText(Contador.montaTempo(TempoInicial));
                        isReverse = true;
                        // Write your code here to execute after dialog
                    }
                });


        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        dialog.cancel();

                    }
                });
        alertDialog.show();
    }

}
