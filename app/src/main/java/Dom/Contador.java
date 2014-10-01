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
    boolean isStartedReverso = false;

    Chronometer cr ;

    CountDownTimer cT =  new CountDownTimer(TempoCorrido*1000, 1000) {

        public void onTick(long millisUntilFinished) {


            int hora = (int) millisUntilFinished / 3600000;
            int min = ((int) millisUntilFinished / 60000) - hora * 60;
            int seg = (int) ((millisUntilFinished % 60000) / 1000);

            cr.setText(String.format("%02d", hora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", seg));
        }

        public void onFinish() {
            cr.setText("done!");
        }
    };

    public Contador(Chronometer cr) {

        this.cr = cr;
    }



    public void IniciaContadorReverso(int Segundos){

        if(isStartedReverso) {
            cT.onFinish();
            cT = new CountDownTimer(Segundos * 1000, 1000) {

                public void onTick(long millisUntilFinished) {


                    int hora = (int) millisUntilFinished / 3600000;
                    int min = ((int) millisUntilFinished / 60000) - hora * 60;
                    int seg = (int) ((millisUntilFinished % 60000) / 1000);

                    cr.setText(String.format("%02d", hora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", seg));
                }

                public void onFinish() {
                    cr.setText("done!");
                }
            };


        }else {
            cT = new CountDownTimer(Segundos * 1000, 1000) {

                public void onTick(long millisUntilFinished) {


                    int hora = (int) millisUntilFinished / 3600000;
                    int min = ((int) millisUntilFinished / 60000) - hora * 60;
                    int seg = (int) ((millisUntilFinished % 60000) / 1000);

                    cr.setText(String.format("%02d", hora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", seg));
                }

                public void onFinish() {
                    cr.setText("done!");
                }
            };
        }
        cT.start();
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

        if(!(cr.isActivated())) {

            TempoCorrido = cr.getBase() - SystemClock.elapsedRealtime();
            paused = true;
            cr.stop();
        }

    }
    public  void PausarContadorReverso() {

        TempoCorrido = cr.getBase() - SystemClock.elapsedRealtime();
        paused = true;
        cT.cancel();
        isStartedReverso = false;

    }

    public int TempoTotalReverso() {
        TempoCorrido = cr.getBase() - SystemClock.elapsedRealtime();
        return (int)((TempoCorrido*-1)/1000);
    }

    public int TempoTotal() {
        TempoCorrido = cr.getBase() - SystemClock.elapsedRealtime();
        return (int)((TempoCorrido*-1)/1000);
    }
}

