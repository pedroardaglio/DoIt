package doit.apps.paperaya.com.br.doit.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import doit.apps.paperaya.com.br.doit.R;
import doit.apps.paperaya.com.br.doit.dao.DBTarefa;
import doit.apps.paperaya.com.br.doit.fragments.DatePickerFragment;
import doit.apps.paperaya.com.br.doit.fragments.TimePickerFragment;
import doit.apps.paperaya.com.br.doit.util.DateTime;

public class CadastroTarefa extends AppCompatActivity {

    private EditText nome_tarefa;
    private EditText desc_tarefa;
    private EditText dt_inicio;
    private EditText hr_inicio;
    private EditText dt_final;
    private EditText hr_final;
    private EditText id_classificacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_tarefa);

        nome_tarefa = (EditText) findViewById(R.id.etxt_nome_tarefa);
        desc_tarefa = (EditText) findViewById(R.id.etxt_desc_tarefa);
        dt_inicio = (EditText) findViewById(R.id.etxt_dt_inicio);
        hr_inicio = (EditText) findViewById(R.id.etxt_hr_inicio);
        dt_final = (EditText) findViewById(R.id.etxt_dt_final);
        hr_final = (EditText) findViewById(R.id.etxt_hr_final);
        id_classificacao = (EditText) findViewById(R.id.etxt_id_classificacao);

        Button botao_cadastrar = (Button) findViewById(R.id.btn_cadastrar_tarefa);

        dt_inicio.setInputType(InputType.TYPE_NULL);
        dt_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(findViewById(R.id.etxt_dt_inicio).getId());
            }
        });
        dt_inicio.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDatePickerDialog(findViewById(R.id.etxt_dt_inicio).getId());
                }
            }
        });

        hr_inicio.setInputType(InputType.TYPE_NULL);
        hr_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(findViewById(R.id.etxt_hr_inicio).getId());
            }
        });
        hr_inicio.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showTimePickerDialog(findViewById(R.id.etxt_hr_inicio).getId());
                }
            }
        });

        dt_final.setInputType(InputType.TYPE_NULL);
        dt_final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(findViewById(R.id.etxt_dt_final).getId());
            }
        });
        dt_final.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDatePickerDialog(findViewById(R.id.etxt_dt_final).getId());
                }
            }
        });

        hr_final.setInputType(InputType.TYPE_NULL);
        hr_final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(findViewById(R.id.etxt_hr_final).getId());
            }
        });
        hr_final.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showTimePickerDialog(findViewById(R.id.etxt_hr_final).getId());
                }
            }
        });

        botao_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBTarefa tarefa_db = new DBTarefa(getBaseContext());
                String resultado, dt_hr_inicio, dt_hr_final;

                // Obtendo os valores
                String nome_tarefa_str = nome_tarefa.getText().toString();
                String desc_tarefa_str = desc_tarefa.getText().toString();
                String dt_inicio_str = dt_inicio.getText().toString();
                String hr_inicio_str = hr_inicio.getText().toString();
                String dt_final_str = dt_final.getText().toString();
                String hr_final_str = hr_final.getText().toString();
                int id_classificacao_int = Integer.parseInt(id_classificacao.getText().toString());

                // Formatando a data corretamente para o datetime do sqlite
                dt_hr_inicio = DateTime.data_completa_sqlite(dt_inicio_str, hr_inicio_str);
                dt_hr_final = DateTime.data_completa_sqlite(dt_final_str, hr_final_str);
                resultado = tarefa_db.insereDado(dt_hr_inicio, dt_hr_final, 1, id_classificacao_int, nome_tarefa_str, desc_tarefa_str);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(CadastroTarefa.this, ListaTarefas.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void showDatePickerDialog(int recurso){
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.set_recurso(recurso);
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(int recurso){
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.set_recurso(recurso);
        newFragment.show(getFragmentManager(), "timePicker");
    }

}
