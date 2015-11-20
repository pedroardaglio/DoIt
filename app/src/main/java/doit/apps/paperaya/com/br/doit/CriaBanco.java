package doit.apps.paperaya.com.br.doit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pedro on 11/20/15.
 */

public class CriaBanco extends SQLiteOpenHelper {
    public static final String NOME_BANCO = "banco.db";
    public static final int VERSAO = 1;

    public CriaBanco(Context context){
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
    // Cria o banco de dados. So e' executado na primeira vez
        String sql = "create table status_tarefa("
            + "id_status_tarefa int primary key,"
            + "nome_status text,"
            + "desc_status text);";
        db.execSQL(sql);

        sql = "create table classificacao_tarefa("
                + "id_classificacao int primary key,"
                + "nome_classificacao text,"
                + "desc_classificacao text);";
        db.execSQL(sql);

        sql = "create table tarefa("
                + "id_tarefa int primary key,"
                + "dt_inicio datetime,"
                + "dt_final datetime,"
                + "id_status int,"
                + "id_class_tarefa int,"
                + "nome_tarefa text,"
                + "desc_tarefa text,"
                + "foreign key (id_status) references status_tarefa(id_status),"
                + "foreign key (id_class_tarefa) references classificacao_tarefa(id_classificacao));";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS status_tarefa");
        db.execSQL("DROP TABLE IF EXISTS classificacao_tarefa");
        db.execSQL("DROP TABLE IF EXISTS tarefa");
        onCreate(db);
    }
}
