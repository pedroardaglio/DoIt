package doit.apps.paperaya.com.br.doit;

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
        valores = new ContentValues();

        resultado = db.insert(tabela, nullColumnHack, valores);
        db.close();

//        if (resultado == -1)
//            return "Erro ao inserir registro";
//        else
//            return "Registro inserido com sucesso";

        return resultado;  // -1 erro, senao foi ok
    }

    // Carrega todos os dados, sem parametros/where
    public Cursor carregaDados(String tabela, String[] campos){
        Cursor cursor;
        db = banco.getReadableDatabase();
        cursor = db.query(tabela, campos, null, null, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        db.close();

        return cursor;
    }


}
