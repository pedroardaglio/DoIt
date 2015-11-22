package doit.apps.paperaya.com.br.doit.util;

/**
 * Created by pedro on 11/22/15.
 */
public class DateTime {
    // Retorna uma String com o formato aceito pelo sqlite a partir de duas strings (data e horario)
    // O formato dos parametros sao: DD/MM/YYYY e HH:MM
    public static String data_completa_sqlite(String data, String horario){
        String ano, mes, dia;
        dia = data.substring(0, 2);
        mes = data.substring(3, 5);
        ano = data.substring(6, 10);

        // Adicao dos segundos e milisegundos
        horario = " " + horario + "00.000";

        return ano + "-" + mes + "-" + dia + horario;
    }
}
