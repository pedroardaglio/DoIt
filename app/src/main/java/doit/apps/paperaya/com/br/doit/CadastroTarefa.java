package doit.apps.paperaya.com.br.doit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroTarefa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_tarefa);

        Button botao_cadastrar = (Button) findViewById(R.id.btn_cadastrar_tarefa);

        botao_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBTarefa tarefa_db = new DBTarefa(getBaseContext());
                String resultado;

                EditText nome_tarefa = (EditText) findViewById(R.id.etxt_nome_tarefa);
                EditText desc_tarefa = (EditText) findViewById(R.id.etxt_desc_tarefa);
                EditText dt_inicio = (EditText) findViewById(R.id.etxt_dt_inicio);
                EditText dt_final = (EditText) findViewById(R.id.etxt_dt_final);
                EditText id_classificacao = (EditText) findViewById(R.id.etxt_id_classificacao);

                String nome_tarefa_str = nome_tarefa.getText().toString();
                String desc_tarefa_str = desc_tarefa.getText().toString();
                String dt_inicio_str = dt_inicio.getText().toString();
                String dt_final_str = dt_final.getText().toString();
                int id_classificacao_int = Integer.parseInt(id_classificacao.getText().toString());

                resultado = tarefa_db.insereDado(dt_inicio_str, dt_final_str, 1, id_classificacao_int, nome_tarefa_str, desc_tarefa_str);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(CadastroTarefa.this, ListaTarefas.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
