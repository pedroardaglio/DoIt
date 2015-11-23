package doit.apps.paperaya.com.br.doit.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import doit.apps.paperaya.com.br.doit.banco.BancoController;

/**
 * Created by pedro on 11/22/15.
 */
public class ClassificacaoDAO {
    private Context context;

    public ClassificacaoDAO(){

    }

    public ClassificacaoDAO(Context context){
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public ClassificacaoDAO setContext(Context context) {
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
        String query = "SELECT id_classificacao as _id, * FROM classificacao_tarefa ORDER BY id_classificacao";
        BancoController banco = new BancoController(getContext());

        cursor = banco.carregaDados("classificacao_tarefa", null, query);

        return cursor;
    }

}
