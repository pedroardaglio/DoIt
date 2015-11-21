package doit.apps.paperaya.com.br.doit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by pedro on 11/21/15.
 */
public class ListaTarefasAdapter extends ArrayAdapter {
    private ArrayList<Tarefa> tarefas;

    public ListaTarefasAdapter(Context context, int textViewResourceId, ArrayList<Tarefa> tarefas) {
        super(context, textViewResourceId, tarefas);
        this.tarefas = tarefas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        // assign the view we are converting to a local variable
        View v = convertView;

        // first check to see if the view is null. if so, we have to inflate it.
        // to inflate it basically means to render, or show, the view.
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.layout_tarefa, null);
        }

		/*
		 * Recall that the variable position is sent in as an argument to this method.
		 * The variable simply refers to the position of the current object in the list. (The ArrayAdapter
		 * iterates through the list we sent it)
		 *
		 * Therefore, i refers to the current Item object.
		 */
        Tarefa i = tarefas.get(position);

        if (i != null) {

            // This is how you obtain a reference to the TextViews.
            // These TextViews are created in the XML files we defined.

            TextView txt_dia = (TextView) v.findViewById(R.id.txtv_dia);
            TextView txt_mes = (TextView) v.findViewById(R.id.txtv_mes);
            TextView txt_periodo = (TextView) v.findViewById(R.id.txtv_periodo);
            TextView txt_nome_tarefa = (TextView) v.findViewById(R.id.txtv_nome_tarefa);

            // check to see if each individual textview is null.
            // if not, assign some text!
            txt_dia.setText(i.get_dia_inicio());
            txt_mes.setText(i.nome_mes());
            txt_periodo.setText(i.range_horario());
            txt_nome_tarefa.setText(i.get_nome_tarefa());
        }

        // the view must be returned to our activity
        return v;

    }

}
