package doit.apps.paperaya.com.br.doit;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListaTarefas extends AppCompatActivity {
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tarefas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DBTarefa tarefa_db = new DBTarefa(getBaseContext());
        final Cursor cursor = tarefa_db.carregaDados();

        String[] nome_campos = new String[] {"id_tarefa", "nome_tarefa"};
        int[] id_views = new int[] {R.id.layout_id_tarefa, R.id.layout_nome_tarefa};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.lista_tarefas_layout, cursor, nome_campos, id_views, 0);
        lista = (ListView) findViewById(R.id.listView);
        lista.setAdapter(adaptador);

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
