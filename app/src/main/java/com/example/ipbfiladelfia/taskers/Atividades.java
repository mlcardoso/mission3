package com.example.ipbfiladelfia.taskers;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;


public class Atividades extends Activity {


    public Thread contador = Contador_thread();
    public int segundos = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_atividades);

        Button bt = (Button)findViewById(R.id.btStart);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public Thread Contador_thread(){

        Thread t = new Thread() {
            public void run() {
               int i = 0;
                while (segundos++ < 1000) {
                    try {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                TextView tvNum = (TextView) findViewById(R.id.tvNum);
                                tvNum.setText("# " + segundos);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.atividades, menu);
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
        }
        return super.onOptionsItemSelected(item);
    }
}
