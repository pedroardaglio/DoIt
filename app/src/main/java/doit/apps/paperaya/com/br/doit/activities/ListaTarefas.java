package doit.apps.paperaya.com.br.doit.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import doit.apps.paperaya.com.br.doit.R;
import doit.apps.paperaya.com.br.doit.adapters.ListaTarefasAdapter;
import doit.apps.paperaya.com.br.doit.dao.DBTarefa;
import doit.apps.paperaya.com.br.doit.model.Tarefa;

public class ListaTarefas extends AppCompatActivity {
    private ListView lista;

    private ArrayList<Tarefa> lista_tarefas = new ArrayList<Tarefa>();
    private ListaTarefasAdapter tarefa_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tarefas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DBTarefa tarefa_db = new DBTarefa(getBaseContext());
        final Cursor cursor = tarefa_db.carregaDados();
        // _id, id_tarefa, dt_inicio, dt_final, id_status, id_class_tarefa, nome_tarefa, desc_tarefa

        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            Tarefa tarefa = new Tarefa();

            tarefa.set_nome_tarefa(cursor.getString(6));
            tarefa.set_dt_inicio(cursor.getString(2));
            tarefa.set_dt_final(cursor.getString(3));

            lista_tarefas.add(tarefa);

            cursor.moveToNext();
        } // end while

        lista = (ListView) findViewById(R.id.lstv_tarefas);
        tarefa_adapter = new ListaTarefasAdapter(getBaseContext(), R.layout.layout_tarefa, lista_tarefas);
        lista.setAdapter(tarefa_adapter);

        FloatingActionButton fab_cadastrar_tarefa = (FloatingActionButton) findViewById(R.id.fab_adicionar_tarefa);
        fab_cadastrar_tarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaTarefas.this, CadastroTarefa.class);
                startActivity(intent);
                finish();
            }
        });
    }

}