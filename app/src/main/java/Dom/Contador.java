package Dom;

import android.app.Activity;
<<<<<<< HEAD
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

=======
import android.widget.TextView;

import com.example.ipbfiladelfia.taskers.R;

/**
 * Created by IPB Filadelfia on 23/09/2014.
 */
public class Contador extends Activity {

    public Thread contador = Contador_thread();
    public int segundos = 0;


    public Thread Contador_thread(){

        Thread t = new Thread() {
            public void run() {
                int i = 0;
                while (segundos++ < 1000) {
                    try {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                          /*      TextView tvNum = (TextView) findViewById(R.id.tvNum);
                                tvNum.setText("# " + segundos);*/
                            }
                        });
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        return t;
    }
    private void acao_contador(String acao) {
        if (acao.equals("start")){
            contador.start();
        }else if(acao.equals("stop")){
            contador.stop();
        }
    }
>>>>>>> 82e807bd8957266324de93b2507b099c320d7fbc
}
