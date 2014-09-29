package Dom;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.widget.Chronometer;
import android.widget.TextView;

import com.fnordz.atividades.Inicial;
import com.fnordz.atividades.R;


public class Contador {


    long TempoCorrido = 0;
    boolean paused = false;

    Chronometer cr ;

    public Contador(Chronometer cr) {
        this.cr = cr;
    }

    public  android.widget.Chronometer.OnChronometerTickListener CronometroListener = new Chronometer.OnChronometerTickListener() {

        @Override
        public void onChronometerTick(Chronometer chronometer) {

            CharSequence text = chronometer.getText();
            if (text.length() == 5) {
                chronometer.setText("00:" + text);
            } else if (text.length() == 7) {
                chronometer.setText("00:" + text);
            }
        }
    };



    public static String montaTempo(long segundos){

        int hora = (int) segundos / 3600;
        int min = ((int) segundos / 60) - hora * 60;
        int seg = (int) ((segundos % 60) / 1);

        return (String.format("%02d", hora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", seg));
    }

    public  void IniciaContador() {

        cr.setOnChronometerTickListener(CronometroListener);
        if(paused == true){

            cr.setBase(TempoCorrido + SystemClock.elapsedRealtime());
            paused = false;

        }
        cr.start();
    }

    public  void PausarContador() {

        TempoCorrido = cr.getBase() - SystemClock.elapsedRealtime();
        paused = true;
        cr.stop();

    }

    public int TempoTotal() {
        TempoCorrido = cr.getBase() - SystemClock.elapsedRealtime();
        return (int)((TempoCorrido*-1)/1000);
    }
}

