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
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import Dom.Atividade;
import Dom.Atividades;
import Dom.MySQLite;


public class Inicial extends Activity {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */


    ArrayAdapter<Atividade> ad;

    Atividades lAtividades = new Atividades();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.button_floating_action);
        floatingActionButton.attachToListView(((ListView)findViewById(R.id.lstAtividades)));

        floatingActionButton.setOnClickListener(funcaoBotoes);

        ((ListView)findViewById(R.id.lstAtividades)).setOnItemLongClickListener(FuncaoItemLongLista);
        ((ListView)findViewById(R.id.lstAtividades)).setOnItemClickListener(FuncaoItemLista);

        ad = new ArrayAdapter<Atividade>(this,android.R.layout.simple_list_item_1,lAtividades.getListaAtividade());
        ((ListView)findViewById(R.id.lstAtividades)).setAdapter(ad);

     //   GeraDadosTeste();
        CarregaListView();

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
            Atividade a = (Atividade) Obj;
            String Name = a.getNome();

            Intent NovosTempos = new Intent(Inicial.this, TemposAtividade.class);

            NovosTempos.putExtra("Nome", Name);

            NovosTempos.putIntegerArrayListExtra("Tempos", lAtividades.getAtividadeForId(position).getListaTempos());
            NovosTempos.putStringArrayListExtra("Datas", lAtividades.getAtividadeForId(position).getListaDatas());


            setResult(1, NovosTempos);
            startActivityForResult(NovosTempos, 1);

        }
    };

    AdapterView.OnItemLongClickListener FuncaoItemLongLista =   new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


            Object Obj = ((ListView)findViewById(R.id.lstAtividades)).getItemAtPosition(position);
            Atividade a = (Atividade)Obj;
            String Name = a.getNome();

            DialogExcluir(Name);


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

                        lAtividades.insereAtividade(nome.getText().toString());
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

    public void DialogExcluir(String Nome){
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

                //        insereNovaAtividade(nome.getText().toString());
                    }
                });


        alertDialog.setNegativeButton("Não",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                   //     dialog.cancel();

                    }
                });
        alertDialog.show();
    }



    private void insereNovaAtividade(String nome){

       lAtividades.insereAtividade(nome);
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

    /*----------------------------------------------------------*/

    public void GeraDadosTeste(){
        lAtividades.insereAtividade("Circuitos Eletricos");
        lAtividades.getAtividadeForId(0).insereTempo(300,"20/01/1992");
        lAtividades.getAtividadeForId(0).insereTempo(200,"20/01/1993");
        lAtividades.getAtividadeForId(0).insereTempo(100,"20/01/1994");
        lAtividades.getAtividadeForId(0).insereTempo(400,"20/01/1995");
        lAtividades.getAtividadeForId(0).insereTempo(20,"20/01/1996");

        lAtividades.insereAtividade("Academia");
        lAtividades.getAtividadeForId(1).insereTempo(300,"20/02/1992");
        lAtividades.getAtividadeForId(1).insereTempo(200,"20/03/1993");
        lAtividades.getAtividadeForId(1).insereTempo(100,"20/04/1994");
        lAtividades.getAtividadeForId(1).insereTempo(400,"20/05/1995");
        lAtividades.getAtividadeForId(1).insereTempo(20,"20/06/1996");

    }
}