package Dom;

import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Marcos Cardoso on 24/09/2014.
 */
public class Atividade {


    String Nome;
    public ArrayList<Integer> Tempos = new ArrayList<Integer>();
    public ArrayList<String> Datas = new ArrayList<String>();
    ArrayList<String> TemposeDatas = new ArrayList<String>();


    public void setNome(String nome){this.Nome = nome;}

    public Atividade(String nome) {
        this.Nome = nome;
    }

    public void insereTempo(int t,String Data){

        Tempos.add(t);
        Datas.add(Data);
        TemposeDatas.add((Data) + "    -    " + Contador.montaTempo(t));
    }

    public String toString(){return (this.Nome + " - " + Contador.montaTempo(this.getTempoTotal()));}

    public void insereArrays(ArrayList<Integer> tempos,ArrayList<String> datas){


        int tam = Tempos.size();
        for(int i = tam ; i < tempos.size() ; i++){

            Tempos.add(tempos.get(i -tam));
            Datas.add(datas.get(i - tam));
            TemposeDatas.add(datas.get(i -tam) + "           -             " + Contador.montaTempo(tempos.get(i - tam)));
        }
    }

    public String getNome() {
        return Nome;
    }

    public int getTempoTotal(){

        int tempototal=0;
        for(int i=0; i< Tempos.size(); i++){
            tempototal += Tempos.get(i);
        }
        return tempototal;
    }
    public ArrayList<Integer> getListaTempos(){
        return Tempos;
    }
    public ArrayList<String> getListaDatas(){
        return Datas;
    }
    public ArrayList<String> getTempoeDatas(){ return TemposeDatas;
    }
}


