package doit.apps.paperaya.com.br.doit.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import doit.apps.paperaya.com.br.doit.banco.BancoController;
import doit.apps.paperaya.com.br.doit.model.Tarefa;

/**
 * Created by pedro on 11/20/15.
 */
public class TarefaDAO {
    private Context context;

    public TarefaDAO(){

    }

    public TarefaDAO(Context context){
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public TarefaDAO setContext(Context context) {
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

        resultado = banco.insereDado("tarefa", null, valores);

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro inserido com sucesso";
    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos = {"id_tarefa", "nome_tarefa", "dt_inicio", "dt_final", "desc_tarefa", "id_status", "id_class_tarefa"};
        String query = "SELECT id_tarefa as _id, * FROM tarefa ORDER BY dt_inicio";
        BancoController banco = new BancoController(getContext());

        cursor = banco.carregaDados("tarefa", null, query);

        return cursor;
    }

    public Tarefa tarefa_por_id(int id_tarefa){
        Cursor cursor;
        Tarefa tarefa = new Tarefa();
        String query = "SELECT id_tarefa as _id, * FROM tarefa WHERE id_tarefa = " + Integer.toString(id_tarefa) + " ORDER BY dt_inicio";
        BancoController banco = new BancoController(getContext());
        cursor = banco.carregaDados("tarefa", null, query);

        cursor.moveToPosition(0);
        tarefa.set_id_tarefa(cursor.getInt(1));
        tarefa.set_dt_inicio(cursor.getString(2));
        tarefa.set_dt_final(cursor.getString(3));
        tarefa.set_id_status(cursor.getInt(4));
        tarefa.set_id_class_tarefa(cursor.getInt(5));
        tarefa.set_nome_tarefa(cursor.getString(6));
        tarefa.set_desc_tarefa(cursor.getString(7));

        return tarefa;
    }

    public String alterar_tarefa(int id_tarefa, String dt_inicio, String dt_final, int id_status, int id_class_tarefa, String nome_tarefa, String desc_tarefa){
        ContentValues valores;
        String where;
        long resultado;

        BancoController banco = new BancoController(getContext());

        where = "id_tarefa = " + id_tarefa;

        valores = new ContentValues();
        valores.put("dt_inicio", dt_inicio);
        valores.put("dt_final", dt_final);
        valores.put("id_status", id_status);
        valores.put("id_class_tarefa", id_class_tarefa);
        valores.put("nome_tarefa", nome_tarefa);
        valores.put("desc_tarefa", desc_tarefa);

        resultado = banco.alterar_dado("tarefa", valores, where, null);

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro inserido com sucesso";
    }
}
