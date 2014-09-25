package Dom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcos Cardoso on 24/09/2014.
 */
public class Atividades {

    private  ArrayList<Atividade>  ListaAtividade = new ArrayList<Atividade>();



   public void insereAividades(String nome, Integer tempo){
        int i;
       for( i = 0; i < ListaAtividade.size() ; i++){
           if(ListaAtividade.get(i).getNome().equals(nome)){
              ListaAtividade.get(i).insereTempo(tempo);
              break;
           }
       }
       if(i == ListaAtividade.size()){
           Atividade a = new Atividade(nome,tempo);
           ListaAtividade.add(a);
       }
   }

    public Atividade getAtividadeForId(int i){
        return ListaAtividade.get(i);
    }
   public int getListaAtividadeSize(){
       return ListaAtividade.size();
   }
}
