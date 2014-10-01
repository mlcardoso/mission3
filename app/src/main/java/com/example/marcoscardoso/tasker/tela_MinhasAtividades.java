package com.example.marcoscardoso.tasker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import Dom.Atividades;



public class tela_MinhasAtividades extends Activity {

    private Atividades Atividades = new Atividades();

    private ArrayList<Button> btAtividades= new ArrayList<Button>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__minhas_atividades);
        Button btNovaAtividade = (Button)findViewById(R.id.btNAtividade);
        btNovaAtividade.setOnClickListener(funcoesBotoes);
        CarregaAtividades();

    }


    View.OnClickListener funcoesBotoes =  new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(view.getId() ==  R.id.btNAtividade) {

                Intent intent = new Intent(tela_MinhasAtividades.this, Atividade.class);
                startActivityForResult(intent, 1);

            }else{
                for (int i = 0 ; i <= btAtividades.size();i++){
                    if( view.getId() == i){

                        String nome = Atividades.getAtividadeForId(i).getNome();

                        Intent myIntent = new Intent(tela_MinhasAtividades.this, Atividade.class);
                        myIntent.putExtra("Nome", nome);
                        myIntent.putExtra("UltimoTempo", Atividades.getAtividadeForId(i).UltimoTempo());
                        startActivityForResult(myIntent,1);
                        break;
                    }
                }
            }

        }
    };

    private void CarregaAtividades(){

        LinearLayout buttonsContainer = (LinearLayout) findViewById(R.id.layScroll);
        String Nome; int tempo;
        buttonsContainer.removeAllViews();

        for (int i = 0;i<Atividades.getListaAtividadeSize();i++) {

            Nome = Atividades.getAtividadeForId(i).getNome();
            tempo = Atividades.getAtividadeForId(i).getTempoTotal();

            Button b = new Button(tela_MinhasAtividades.this);
            btAtividades.add(b);
            b.setId(i);

            buttonsContainer.addView(b);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        String Nome = data.getExtras().getString("Nome");
        int tempo = Integer.parseInt(data.getExtras().getString("Tempo"));
        Atividades.insereAividades(Nome, tempo);

        CarregaAtividades();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tela__minhas_atividades, menu);
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
