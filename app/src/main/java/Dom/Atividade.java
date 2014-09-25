package Dom;

import java.util.ArrayList;

/**
 * Created by Marcos Cardoso on 24/09/2014.
 */
public class Atividade {


    String Nome;
    ArrayList<Integer> Tempos = new ArrayList<Integer>();

    public Atividade(String nome, Integer tempo) {
        Nome = nome;
        Tempos.add(tempo);
    }

    public void insereTempo(int t){
        Tempos.add(t);
    }

    public String getNome() {
        return Nome;
    }

    public int UltimoTempo(){
        return Tempos.get(Tempos.size()-1);
    }
    public ArrayList<Integer> getTempos() {
        return Tempos;
    }

    public int getTempoTotal(){

        int tempototal=0;
        for(int i=0; i< Tempos.size(); i++){
            tempototal += Tempos.get(i);
        }
        return tempototal;
    }
}
