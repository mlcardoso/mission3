package com.fnordz.atividades;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Random;

import holographlibrary.PieGraph;
import holographlibrary.PieSlice;


public class Grafico extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_grafico);

        PieGraph pie = new PieGraph(this);

        PieSlice slice;


        if(getIntent().hasExtra("Tempos")){

            for(int i = 0; i<getIntent().getIntegerArrayListExtra("Tempos").size(); i++){

                Random corRamdom = new Random();

                slice = new PieSlice();
                slice.setValue(getIntent().getIntegerArrayListExtra("Tempos").get(i));
                slice.setTitle(getIntent().getStringArrayListExtra("Nomes").get(i).toString());
                slice.setColor(corRamdom.nextInt()*2);
                pie.addSlice(slice);

                LinearLayout Item = new LinearLayout(this);
                Button bt = new Button(this);
                TextView tv = new TextView(this);

                bt.setBackgroundColor(slice.getColor());
                tv.setText("           " + slice.getTitle());

                Item.setOrientation(LinearLayout.HORIZONTAL);
                Item.addView(bt);
                Item.addView(tv);

                ((LinearLayout)findViewById(R.id.lAtividades)).addView(Item);


            }


        }

        ((RelativeLayout)findViewById(R.id.relative)).addView(pie);






    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.grafico, menu);
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
