package doit.apps.paperaya.com.br.doit.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import doit.apps.paperaya.com.br.doit.R;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        Button botao_consulta = (Button) findViewById(R.id.btn_consultar);

        botao_consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, ListaTarefas.class);
                startActivity(intent);
                finish();
            }
        });
    }
}


