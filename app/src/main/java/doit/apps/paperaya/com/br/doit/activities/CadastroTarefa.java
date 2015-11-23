package doit.apps.paperaya.com.br.doit.activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import doit.apps.paperaya.com.br.doit.R;
import doit.apps.paperaya.com.br.doit.adapters.ListaTarefasAdapter;
import doit.apps.paperaya.com.br.doit.dao.ClassificacaoDAO;
import doit.apps.paperaya.com.br.doit.dao.TarefaDAO;
import doit.apps.paperaya.com.br.doit.fragments.DatePickerFragment;
import doit.apps.paperaya.com.br.doit.fragments.TimePickerFragment;
import doit.apps.paperaya.com.br.doit.model.Tarefa;
import doit.apps.paperaya.com.br.doit.util.DateTime;
import doit.apps.paperaya.com.br.doit.util.SpinnerObject;

public class CadastroTarefa extends AppCompatActivity {

    private EditText nome_tarefa;
    private EditText desc_tarefa;
    private EditText dt_inicio;
    private EditText hr_inicio;
    private EditText dt_final;
    private EditText hr_final;
    private Spinner spn_classificacao;
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
        spn_classificacao = (Spinner) findViewById(R.id.spn_classificacao);
//        id_classificacao = (EditText) findViewById(R.id.etxt_id_classificacao);

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

        List<SpinnerObject> list_class_spn = new ArrayList<SpinnerObject>();

        ClassificacaoDAO class_db = new ClassificacaoDAO(getBaseContext());
        final Cursor cursor = class_db.carregaDados();
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false){
            SpinnerObject spn_tmp = new SpinnerObject(Integer.parseInt(cursor.getString(1)), cursor.getString(2));
            list_class_spn.add(spn_tmp);
            cursor.moveToNext();
        } // end while

        // Creating adapter for spinner
        ArrayAdapter<SpinnerObject> spn_adapter = new ArrayAdapter<SpinnerObject>(this,
                android.R.layout.simple_spinner_item, list_class_spn);
        // Drop down layout style - list view with radio button
        spn_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spn_classificacao.setAdapter(spn_adapter);

        botao_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TarefaDAO tarefa_db = new TarefaDAO(getBaseContext());
                String resultado, dt_hr_inicio, dt_hr_final;

                // Obtendo os valores
                String nome_tarefa_str = nome_tarefa.getText().toString();
                String desc_tarefa_str = desc_tarefa.getText().toString();
                String dt_inicio_str = dt_inicio.getText().toString();
                String hr_inicio_str = hr_inicio.getText().toString();
                String dt_final_str = dt_final.getText().toString();
                String hr_final_str = hr_final.getText().toString();
                int id_classificacao_int; //Integer.parseInt(id_classificacao.getText().toString());
                id_classificacao_int = ((SpinnerObject) spn_classificacao.getSelectedItem()).getId();

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
