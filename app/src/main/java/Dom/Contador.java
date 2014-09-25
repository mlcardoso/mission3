package Dom;

import android.app.Activity;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.example.marcoscardoso.tasker.*;


public class Contador{

    int Segundos;
    int TempoCorrido = 0;
    int hora,min,seg;
    private CountDownTimer cT;


    public Contador( int Seg,final com.example.marcoscardoso.tasker.Atividade a) {

        this.Segundos = Seg;

        cT = new CountDownTimer(Segundos * 1000, 1000) {

            public void onTick(long ContadorSegundos) {

                hora = (int) ContadorSegundos / 3600000;
                min = ((int) ContadorSegundos / 60000) - hora * 60;
                seg = (int) ((ContadorSegundos % 60000) / 1000);
        //        a.SetRegresso("" + String.format("%02d", hora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", seg));
                Segundos = (int) ContadorSegundos;
        //        a.TempoCorrido++;

            };
            public void onFinish() {
          //      a.SetRegresso("Done");
           //     a.HabilitaBotoes();
           //     a.TempoCorrido++;
            }
        };
    }



    public void start(){

        cT.start();
    }

    public int stop(){
        cT.cancel();
        return (Segundos)/1000;
    }

}
