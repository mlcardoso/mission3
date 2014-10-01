package com.example.marcoscardoso.tasker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


public class NovoTempo extends Activity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_novotempo);
        GeraScrolls();




    }

    private void GeraScrolls(){
        LinearLayout layHora = (LinearLayout)findViewById(R.id.layoutHora);
        LinearLayout layMin = (LinearLayout)findViewById(R.id.layoutMin);
        LinearLayout laySeg = (LinearLayout)findViewById(R.id.layoutSeg);
        for (int i = 0; i < 60; i++){

            if(i<24){

                Button a = new Button(this);
                a.setText(String.format("%02d", i));
                a.setId(60 + 24 + i);
                a.setBackgroundColor(Color.WHITE);
                a.setOnClickListener(funcoesBotoes);
                layHora.addView(a);
            }

            Button b = new Button(this);
            b.setText(String.format("%02d", i));
            b.setId(i);
            b.setBackgroundColor(Color.WHITE);
            b.setOnClickListener(funcoesBotoes);
            layMin.addView(b);


            Button c = new Button(this);
            c.setText(String.format("%02d", i));
            c.setId(60 + i);
            c.setBackgroundColor(Color.WHITE);
            c.setOnClickListener(funcoesBotoes);
            laySeg.addView(c);

        }

    }

    View.OnClickListener funcoesBotoes =  new View.OnClickListener() {
        @Override
        public void onClick(View view) {

              for (int i = 0 ; i <= 60;i++){
                    if( view.getId() == i){

                        Button b = (Button)findViewById(i);
                        b.setBackgroundColor(Color.LTGRAY);

                    }else if( view.getId() == i+60){
                        Button b = (Button)findViewById(i+60);
                        b.setBackgroundColor(Color.LTGRAY);

                    }else if(view.getId() == i+60+24){

                        Button b = (Button)findViewById(i+60+24);
                        b.setBackgroundColor(Color.LTGRAY);

                    }else{
                        Button a = (Button)findViewById(i);
                        a.setBackgroundColor(Color.WHITE);
                 /*       Button b = (Button)findViewById(i+60);
                        b.setBackgroundColor(Color.WHITE);
*/
                        if(i<24) {
                            Button c = (Button) findViewById(i + 60 + 24);
                            c.setBackgroundColor(Color.WHITE);
                       }
                    }
              }
        }

    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.novo_tempo, menu);
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
