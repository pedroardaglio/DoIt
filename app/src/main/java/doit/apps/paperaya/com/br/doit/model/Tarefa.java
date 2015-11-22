package doit.apps.paperaya.com.br.doit.model;

/**
 * Created by pedro on 11/21/15.
 */
public class Tarefa {
    private int id_tarefa;
    private String nome_tarefa;
    private String desc_tarefa;
    private String dt_inicio;
    private String dt_final;
    private int id_status;
    private int id_class_tarefa;

    public Tarefa(int id_tarefa, String nome_tarefa, String desc_tarefa, String dt_inicio, String dt_final, int id_status, int id_class_tarefa) {
        this.id_tarefa = id_tarefa;
        this.nome_tarefa = nome_tarefa;
        this.desc_tarefa = desc_tarefa;
        this.dt_inicio = dt_inicio;
        this.dt_final = dt_final;
        this.id_status = id_status;
        this.id_class_tarefa = id_class_tarefa;
    }

    public Tarefa() {
    }

    public int get_id_tarefa() {
        return id_tarefa;
    }

    public void set_id_tarefa(int id_tarefa) {
        this.id_tarefa = id_tarefa;
    }

    public String get_nome_tarefa() {
        return nome_tarefa;
    }

    public void set_nome_tarefa(String nome_tarefa) {
        this.nome_tarefa = nome_tarefa;
    }

    public String get_desc_tarefa() {
        return desc_tarefa;
    }

    public void set_desc_tarefa(String desc_tarefa) {
        this.desc_tarefa = desc_tarefa;
    }

    public String get_dt_inicio() {
        return dt_inicio;
    }

    public void set_dt_inicio(String dt_inicio) {
        this.dt_inicio = dt_inicio;
    }

    public String get_dt_final() {
        return dt_final;
    }

    public void set_dt_final(String dt_final) {
        this.dt_final = dt_final;
    }

    public int get_id_status() {
        return id_status;
    }

    public void set_id_status(int id_status) {
        this.id_status = id_status;
    }

    public int get_id_class_tarefa() {
        return id_class_tarefa;
    }

    public void set_id_class_tarefa(int id_class_tarefa) {
        this.id_class_tarefa = id_class_tarefa;
    }

    public String range_horario(){
        String range_horario;

        range_horario = this.dt_inicio.substring(11, 16);
        range_horario = range_horario + " - ";
        range_horario = range_horario + this.dt_final.substring(11, 16);
        System.out.println("RANGE_HORARIO= " + range_horario);

        return range_horario;
    }

    public String nome_mes(){
        String mes_tarefa;
        int mes;

        mes = Integer.parseInt(this.dt_inicio.substring(5, 7));
        System.out.println("MES_INT_STR= " + mes);
        mes_tarefa = "AAA";
        switch(mes){
            case 1:
                mes_tarefa = "JAN";
                break;
            case 2:
                mes_tarefa = "FEV";
                break;
            case 3:
                mes_tarefa = "MAR";
                break;
            case 4:
                mes_tarefa = "ABR";
                break;
            case 5:
                mes_tarefa = "MAI";
                break;
            case 6:
                mes_tarefa = "JUN";
                break;
            case 7:
                mes_tarefa = "JUL";
                break;
            case 8:
                mes_tarefa = "AGO";
                break;
            case 9:
                mes_tarefa = "SET";
                break;
            case 10:
                mes_tarefa = "OUT";
                break;
            case 11:
                mes_tarefa = "NOV";
                break;
            case 12:
                mes_tarefa = "DEZ";
                break;
        }

        return mes_tarefa;
    }

    public String get_dia_inicio(){
        return this.dt_inicio.substring(8, 10);
    }

}
