package com.example.marcoscardoso.tasker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ThreadPoolExecutor;

import Dom.Contador;

public class Atividade extends Activity {

    int hora = 0, minuto = 0,segundo = 0;
    int  SegundosRestantes = 0;
    public int TempoCorrido;
    Contador contador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_atividade);

        /*Button maisHora = (Button)findViewById(R.id.btMaisHora);
        Button maisMinuto = (Button)findViewById(R.id.btMaisMin);
        Button maisSegundo = (Button)findViewById(R.id.btMaisSeg);
        Button menosHora = (Button)findViewById(R.id.btMenosHora);
        Button menosMinuto = (Button)findViewById(R.id.btMenosMin);
        Button menosSegundo = (Button)findViewById(R.id.btMenosSeg);
        Button btStart = (Button)findViewById(R.id.btStart);
        Button btSalvar = (Button)findViewById(R.id.btSalvar);

        maisHora.setOnClickListener(funcoesBotoes);
        maisMinuto.setOnClickListener(funcoesBotoes);
        maisSegundo.setOnClickListener(funcoesBotoes);
        menosHora.setOnClickListener(funcoesBotoes);
        menosMinuto.setOnClickListener(funcoesBotoes);
        menosSegundo.setOnClickListener(funcoesBotoes);
        btStart.setOnClickListener(funcoesBotoes);
        btSalvar.setOnClickListener(funcoesBotoes);

        if(getIntent().hasExtra("Nome")){
            EditText et = (EditText)findViewById(R.id.etNome);
            et.setText(getIntent().getExtras().getString("Nome"));
            et.setEnabled(false);
        }*/

        Button btNovoTempo = (Button)findViewById(R.id.btNovoTempo);
        btNovoTempo.setOnClickListener(funcoesBotoes);
    }


    View.OnClickListener funcoesBotoes =  new View.OnClickListener() {
        @Override
        public void onClick(View view) {

     /*       TextView tvhora = (TextView) findViewById(R.id.tvHora);
            TextView tvmin = (TextView) findViewById(R.id.tvMin);
            TextView tvseg = (TextView) findViewById(R.id.tvSeg);
*/

            switch(view.getId()) {

                case R.id.btNovoTempo:

                    Intent intent = new Intent(Atividade.this, NovoTempo.class);
                    startActivityForResult(intent, 1);
/*
                case R.id.btStart:

                    if(((Button)findViewById(R.id.btStart)).getText().equals("Start")) {

                        SegundosRestantes = segundo + minuto * 60 + hora * 3600;

                        if(SegundosRestantes != 0) {
                            DesabilitaBotoes();
                            contador = new Contador(SegundosRestantes, com.example.marcoscardoso.tasker.Atividade.this);
                            ((Button) findViewById(R.id.btStart)).setText("Pause");

                            contador.start();
                        }

                    }else if (((Button)findViewById(R.id.btStart)).getText().equals("Pause")){;

                        SegundosRestantes = contador.stop();

                        HabilitaBotoes();

                        hora = (int) SegundosRestantes / 3600;
                        minuto = ((int) SegundosRestantes / 60) - hora * 60;
                        segundo = (int) (SegundosRestantes % 60);


                        tvhora.setText("" + hora);
                        tvmin.setText("" + minuto);
                        tvseg.setText("" + segundo);

                        contador = null;

                        ((Button)findViewById(R.id.btStart)).setText("Start");
                    }

                    break;

                case R.id.btSalvar:
                    EditText et = (EditText)findViewById(R.id.etNome);

                        Intent data = new Intent();
                        data.putExtra("Nome",et.getText().toString());
                        data.putExtra("Tempo",("" + TempoCorrido));
                        setResult(2, data);
                        finish();

                    break;
                case R.id.btRecUltimoTempo:

                    SegundosRestantes = Integer.parseInt(getIntent().getExtras().getString("UltimoTempo"));

                    hora = (int) SegundosRestantes / 3600;
                    minuto = ((int) SegundosRestantes / 60) - hora * 60;
                    segundo = (int) (SegundosRestantes % 60);


                    tvhora.setText("" + hora);
                    tvmin.setText("" + minuto);
                    tvseg.setText("" + segundo);

*/

            }
        }
    };

/*
    public void SetRegresso(String text){

        TextView tvRegresso = (TextView) findViewById(R.id.tvRegresso);
        tvRegresso.setText(text);


    }
    */
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.atividade, menu);
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
