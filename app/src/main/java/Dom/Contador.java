package Dom;

import android.app.Activity;
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
}
