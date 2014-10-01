package Dom;

<<<<<<< HEAD
<<<<<<< HEAD
import android.widget.LinearLayout;
import android.widget.TextView;

=======
>>>>>>> a34e1c565e1c11b78a9f9918f9ecfb4ce2f35cb9
=======
>>>>>>> a34e1c565e1c11b78a9f9918f9ecfb4ce2f35cb9
import java.util.ArrayList;

/**
 * Created by Marcos Cardoso on 24/09/2014.
 */
public class Atividade {


    String Nome;
<<<<<<< HEAD
<<<<<<< HEAD
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
=======
=======
>>>>>>> a34e1c565e1c11b78a9f9918f9ecfb4ce2f35cb9
    ArrayList<Integer> Tempos = new ArrayList<Integer>();

    public Atividade(String nome, Integer tempo) {
        Nome = nome;
        Tempos.add(tempo);
    }

    public void insereTempo(int t){
        Tempos.add(t);
<<<<<<< HEAD
>>>>>>> a34e1c565e1c11b78a9f9918f9ecfb4ce2f35cb9
=======
>>>>>>> a34e1c565e1c11b78a9f9918f9ecfb4ce2f35cb9
    }

    public String getNome() {
        return Nome;
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> a34e1c565e1c11b78a9f9918f9ecfb4ce2f35cb9
    public int UltimoTempo(){
        return Tempos.get(Tempos.size()-1);
    }
    public ArrayList<Integer> getTempos() {
        return Tempos;
    }

<<<<<<< HEAD
>>>>>>> a34e1c565e1c11b78a9f9918f9ecfb4ce2f35cb9
=======
>>>>>>> a34e1c565e1c11b78a9f9918f9ecfb4ce2f35cb9
    public int getTempoTotal(){

        int tempototal=0;
        for(int i=0; i< Tempos.size(); i++){
            tempototal += Tempos.get(i);
        }
        return tempototal;
    }
<<<<<<< HEAD
<<<<<<< HEAD
    public ArrayList<Integer> getListaTempos(){
        return Tempos;
    }
    public ArrayList<String> getListaDatas(){
        return Datas;
    }
    public ArrayList<String> getTempoeDatas(){ return TemposeDatas;
    }
}


=======
}
>>>>>>> a34e1c565e1c11b78a9f9918f9ecfb4ce2f35cb9
=======
}
>>>>>>> a34e1c565e1c11b78a9f9918f9ecfb4ce2f35cb9
