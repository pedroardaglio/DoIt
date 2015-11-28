package doit.apps.paperaya.com.br.doit.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by pedro on 11/20/15.
 */
public class BancoController {
    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context){
        banco = new CriaBanco(context);
    }

    public long insereDado(String tabela, String nullColumnHack, ContentValues valores){
        long resultado;

        db = banco.getWritableDatabase();

        resultado = db.insert(tabela, nullColumnHack, valores);
        db.close();

//        if (resultado == -1)
//            return "Erro ao inserir registro";
//        else
//            return "Registro inserido com sucesso";

        return resultado;  // -1 erro, senao foi ok
    }

    // Carrega todos os dados, sem parametros/where
    public Cursor carregaDados(String tabela, String[] campos, String query){
        Cursor cursor;
        db = banco.getReadableDatabase();
        if (query != null)
            cursor = db.rawQuery(query, null);
        else
            cursor = db.query(tabela, campos, null, null, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        db.close();

        return cursor;
    }

    public long alterar_dado(String tabela, ContentValues valores, String where, String[] where_args){
        long resultado;
        db = banco.getWritableDatabase();

        resultado = db.update(tabela, valores, where, where_args);
        db.close();
        return resultado;
    }


}
