package Dom;

import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcos Cardoso on 24/09/2014.
 */
public class Atividades {

    public ArrayList<Atividade> ListaAtividade = new ArrayList<Atividade>();
    private ArrayList<String> NomeMaisTempo = new ArrayList<String>();


    public ArrayList<Atividade> getListaAtividade() {
        return ListaAtividade;
    }

    public Atividade getAtividadeForId(int i) {
        return ListaAtividade.get(i);
    }


    public int RemoveAtividade(String Nome) {

        for (int i = 0; i < ListaAtividade.size(); i++) {

            if (ListaAtividade.get(i).getNome().toString().equals(Nome)) {
                ListaAtividade.remove(i);
                return 0;
            }
        }
        return 1;

    }

    public int insereAtividade(String Nome) {

        for (int i = 0; i < ListaAtividade.size(); i++) {

            if (ListaAtividade.get(i).getNome().toString().equals(Nome)) {
                return 0;
            }
        }
        Atividade a = new Atividade(Nome);
        ListaAtividade.add(a);
        NomeMaisTempo.add(a.getNome() + " - " + Contador.montaTempo(a.getTempoTotal()));

        return 1;

    }

    public Atividade getAtividadePeloNome(String nome) {
        for (int i = 0; i < ListaAtividade.size(); i++) {
            if (ListaAtividade.get(i).getNome().equals(nome)) {
                return ListaAtividade.get(i);
            }
        }
        return null;
    }

    public int insereTempoPeloNome(String Nome, ArrayList<Integer> tempos, ArrayList<String> datas) {

        int i;

        for (i = 0; i < ListaAtividade.size(); i++) {

            if (ListaAtividade.get(i).getNome().toString().equals(Nome)) {
                ListaAtividade.get(i).insereArrays(tempos,datas);
                return 0;
            }
        }


        if (i >= ListaAtividade.size()) {
            Atividade a = new Atividade(Nome);

            if (tempos != null && datas != null) {
                a.insereArrays(tempos,datas);

            }
            ListaAtividade.add(a);
        }
        return 1;


    }

    public ArrayList<String> retornaNomeMaisTempo() {
        return NomeMaisTempo;
    }
}