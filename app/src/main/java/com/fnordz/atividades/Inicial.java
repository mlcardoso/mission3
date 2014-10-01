package com.fnordz.atividades;

import android.app.ActionBar;
import android.app.Activity;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import Dom.Atividade;
import Dom.Atividades;
import Dom.Item;
import Dom.MySQLite;


public class Inicial extends Activity {


    MySQLite db;

    ArrayAdapter<String> ad;

    ArrayList<String> NomeDasAtividades = new ArrayList<String>();

    Atividades lAtividades = new Atividades();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal);

        db = new MySQLite(this);
        ActionBar ab = getActionBar();
        ab.setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.button_floating_action);
        floatingActionButton.attachToListView(((ListView)findViewById(R.id.lstAtividades)));

        floatingActionButton.setOnClickListener(funcaoBotoes);

        ((ListView)findViewById(R.id.lstAtividades)).setOnItemLongClickListener(FuncaoItemLongLista);
        ((ListView)findViewById(R.id.lstAtividades)).setOnItemClickListener(FuncaoItemLista);

        NomeDasAtividades.addAll(db.selectAllNames());

        ad = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,NomeDasAtividades);
        ((ListView)findViewById(R.id.lstAtividades)).setAdapter(ad);



  }

    View.OnClickListener funcaoBotoes =   new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        switch(view.getId()){
            case R.id.button_floating_action:
                Dialog();
                break;
        }

        }
    };

    AdapterView.OnItemClickListener FuncaoItemLista =   new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

            Object Obj = ((ListView) findViewById(R.id.lstAtividades)).getItemAtPosition(position);
            String a = (String) Obj;
            String Name = a.toString();

            Intent NovosTempos = new Intent(Inicial.this, TemposAtividade.class);

            NovosTempos.putExtra("Nome", Name);

            setResult(1, NovosTempos);
            startActivityForResult(NovosTempos, 1);
        }
    };

    AdapterView.OnItemLongClickListener FuncaoItemLongLista =   new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


            Object Obj = ((ListView)findViewById(R.id.lstAtividades)).getItemAtPosition(position);
            String a = (String)Obj;
            String Name = a.toString();

            DialogExcluir(Name,position);


            return true;
        }
    };


    public void Dialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Inicial.this);

        alertDialog.setTitle("Nome da Atividade");


        final EditText nome = new EditText(this);
        nome.setGravity(Gravity.CENTER);
        nome.setHeight(300);

        alertDialog.setView(nome);

        alertDialog.setPositiveButton("Salve",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog

                        db.insert(nome.getText().toString(), null, null);
                        NomeDasAtividades.add(nome.getText().toString());
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

    ArrayList<Integer> TemposTotaisDasAtividades(){

        ArrayList<Integer> tempos = new ArrayList<Integer>();

        for(int i = 0; i<NomeDasAtividades.size(); i++){
            ArrayList<Integer> tAtividade = new ArrayList<Integer>();
            tAtividade.addAll(db.SelectAllTime(NomeDasAtividades.get(i).toString()));
            int tempo = 0;
            for(int j = 0; j<tAtividade.size();j++)
                tempo+=tAtividade.get(j);

            tempos.add(tempo);
        }

        return tempos;
    }


    public void DialogExcluir(final String Nome, final int position){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Inicial.this);

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

                        db.DeleteByName(Nome);
                       NomeDasAtividades.remove(position);
                       CarregaListView();
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



    private void insereNovaAtividade(String nome){

        ad.notifyDataSetChanged();
    }

    void CarregaListView(){
        ad.notifyDataSetChanged();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        int i = 0;
        if(resultCode == 1){
            if(data.hasExtra("Nome") && data.hasExtra("Tempos") && data.hasExtra("Datas"))
               i = lAtividades.insereTempoPeloNome(data.getStringExtra("Nome").toString(),data.getIntegerArrayListExtra("Tempos"),data.getStringArrayListExtra("Datas"));
            else if (data.hasExtra("Nome"))
              lAtividades.insereAtividade(data.getStringExtra("Nome").toString());

            ad.notifyDataSetChanged();

            (Toast.makeText(getApplicationContext(), ("Retorno " + i), Toast.LENGTH_LONG)).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inicial, menu);
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
        }if(id == R.id.btGrafic){

            Intent i = new Intent(Inicial.this,Grafico.class);

            i.putStringArrayListExtra("Nomes",NomeDasAtividades);
            i.putIntegerArrayListExtra("Tempos",TemposTotaisDasAtividades());

            startActivity(i);

        }
        return super.onOptionsItemSelected(item);
    }

}