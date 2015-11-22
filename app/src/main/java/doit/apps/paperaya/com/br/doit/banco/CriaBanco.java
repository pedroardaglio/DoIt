package doit.apps.paperaya.com.br.doit.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pedro on 11/20/15.
 */

public class CriaBanco extends SQLiteOpenHelper {
    public static final String NOME_BANCO = "banco.db";
    public static final int VERSAO = 4;

    public CriaBanco(Context context){
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
    // Cria o banco de dados. So e' executado na primeira vez
        String sql = "create table status_tarefa("
            + "id_status_tarefa integer primary key autoincrement,"
            + "nome_status text,"
            + "desc_status text);";
        db.execSQL(sql);

        sql = "create table classificacao_tarefa("
                + "id_classificacao integer primary key autoincrement,"
                + "nome_classificacao text,"
                + "desc_classificacao text);";
        db.execSQL(sql);

        sql = "create table tarefa("
                + "id_tarefa integer primary key autoincrement,"
                + "dt_inicio datetime,"
                + "dt_final datetime,"
                + "id_status integer,"
                + "id_class_tarefa integer,"
                + "nome_tarefa text,"
                + "desc_tarefa text,"
                + "foreign key (id_status) references status_tarefa(id_status),"
                + "foreign key (id_class_tarefa) references classificacao_tarefa(id_classificacao));";
        db.execSQL(sql);

        sql = "insert into status_tarefa(nome_status, desc_status)"
                + "values"
                + "(\"Ativo\", \"Tarefa ativa\"),"
                + "(\"Concluído\", \"Tarefa Concluída\"),"
                + "(\"Passada\", \"Tarefa passada, mas não concluída\");";
        db.execSQL(sql);

        sql = "insert into classificacao_tarefa(nome_classificacao, desc_classificacao)"
                + "values"
                + "(\"Faculdade\", \"Trabalhos, tarefas, provas e eventos da faculdade\"),"
                + "(\"Trabalho\", \"Assuntos, reuniões e tarefas relacionadas ao trabalho\"),"
                + "(\"Esportes\", \"Atividades esportivas\"),"
                + "(\"Música\", \"Estudo dirigido a área musical\");";
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
