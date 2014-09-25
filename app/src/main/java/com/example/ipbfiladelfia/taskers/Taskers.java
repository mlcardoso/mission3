package com.example.ipbfiladelfia.taskers;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


public class Taskers extends Activity{

    private CharSequence mTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskers);

        Button btAtividades = (Button) findViewById(R.id.btMinhasAtividades);
        Button btHistorico = (Button) findViewById(R.id.btHistorico);

        btAtividades.setOnClickListener(BotaoFuncao);


    }

    View.OnClickListener BotaoFuncao  = new View.OnClickListener(){
        public void onClick(View v){
           switch(v.getId()){
               case R.id.btMinhasAtividades:
                   Intent Atividades = new Intent(Taskers.this,Atividades.class);
                   startActivity(Atividades);
                   break;
           }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.taskers, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */

}