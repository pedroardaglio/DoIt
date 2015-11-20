package doit.apps.paperaya.com.br.doit;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by pedro on 11/20/15.
 */
public class DBTarefa {
    private Context context;

    public DBTarefa(){

    }

    public DBTarefa(Context context){
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public DBTarefa setContext(Context context) {
        this.context = context;
        return this;
    }

    public String insereDado(String dt_inicio, String dt_final, int id_status, int id_class_tarefa, String nome_tarefa, String desc_tarefa){
        ContentValues valores;
        long resultado;
        BancoController banco = new BancoController(getContext());

        valores = new ContentValues();
        valores.put("dt_inicio", dt_inicio);
        valores.put("dt_final", dt_final);
        valores.put("id_status", id_status);
        valores.put("id_class_tarefa", id_class_tarefa);
        valores.put("nome_tarefa", nome_tarefa);
        valores.put("desc_tarefa", desc_tarefa);

        resultado = banco.insereDado("tarega", null, valores);

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro inserido com sucesso";
    }
}