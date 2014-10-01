package com.fnordz.atividades;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

import Dom.Atividade;
import Dom.Atividades;
import Dom.Contador;
import Dom.MySQLite;


public class TemposAtividade extends Activity {

    Atividade atividade ;

    ArrayAdapter<String> ad;

    MySQLite db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_tempos_atividade);

        db = new MySQLite(this);

        ActionBar ab = getActionBar();
        ab.setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.button_floating_action2);
        floatingActionButton.attachToListView(((ListView)findViewById(R.id.lstTempos)));

        floatingActionButton.setOnClickListener(funcaoBotoes);

        ((ListView)findViewById(R.id.lstTempos)).setOnItemLongClickListener(FuncaoItemLongLista);


        atividade = new Atividade("");

        ad = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,atividade.Datas);
        ((ListView)findViewById(R.id.lstTempos)).setAdapter(ad);

        if(getIntent().hasExtra("Nome")){

            String Nome = getIntent().getExtras().getString("Nome").toString();
            atividade.setNome(Nome);
            atividade.Tempos.addAll(db.SelectAllTime(Nome.toString()));
            atividade.Datas.addAll(db.SelectAllDate(Nome.toString()));


        }
        ad.notifyDataSetChanged();
        AtualizaTempoTotal();
    }

    AdapterView.OnItemLongClickListener FuncaoItemLongLista =   new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


            Object Obj = ((ListView)findViewById(R.id.lstTempos)).getItemAtPosition(position);
            String a = (String)Obj;
            String Name = a.toString();

            DialogExcluir(Name,position);


            return true;
        }
    };

    public void DialogExcluir(final String Nome, final int position){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(TemposAtividade.this);

        alertDialog.setTitle("Deseja Excluir?");

        final TextView nome = new TextView(this);
        nome.setText(Nome);
        nome.setGravity(Gravity.CENTER);
        nome.setHeight(100);

        alertDialog.setView(nome);

        alertDialog.setPositiveButton("Sim",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog

                        db.DeleteByDate(Nome);
                        db.DeleteByTime(Nome);
                        atividade.Tempos.remove(position);
                        atividade.Datas.remove(position);
                        ad.notifyDataSetChanged();
                    }
                });


        alertDialog.setNegativeButton("Não",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        dialog.cancel();

                    }
                });
        alertDialog.show();
    }
    public void AtualizaTempoTotal(){
        ((TextView)findViewById(R.id.tvTempoTotal)).setText(Contador.montaTempo(atividade.getTempoTotal()));
    }

    View.OnClickListener funcaoBotoes =   new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch(view.getId()){
                case R.id.button_floating_action2:

                    Intent NovoTempo = new Intent(TemposAtividade.this,NovoTempo.class);
                    NovoTempo.putExtra("Nome",atividade.getNome());
                    setResult(2,NovoTempo);
                    startActivityForResult(NovoTempo,2);

                    break;

            }

        }
    };

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == 2){
            if(data.hasExtra("Tempo") && data.hasExtra("Data"))

                db.insert(atividade.getNome(),data.getStringExtra("Data"),data.getIntExtra("Tempo", 0));
                atividade.Tempos.add(data.getIntExtra("Tempo", 0));
                atividade.Datas.add(data.getStringExtra("Data"));
                ad.notifyDataSetChanged();
                AtualizaTempoTotal();

        }
    }

    public void onBackPressed() {

        Intent i = new Intent();

        if(atividade != null) {
            i.putExtra("Nome", atividade.getNome());
            if(atividade.getListaTempos().size() != 0 && atividade.getListaDatas().size() != 0) {
                i.putIntegerArrayListExtra("Tempos", atividade.getListaTempos());
                i.putStringArrayListExtra("Datas", atividade.getListaDatas());
            }
        }
        setResult(1,i);
        finish();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tempos_atividade, menu);
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
