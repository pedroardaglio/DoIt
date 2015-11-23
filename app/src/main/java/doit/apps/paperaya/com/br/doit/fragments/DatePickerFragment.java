package doit.apps.paperaya.com.br.doit.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by pedro on 11/21/15.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private int recurso_solicitado;

    public void set_recurso(int recurso){
        this.recurso_solicitado = recurso;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        final Calendar c = Calendar.getInstance();
        int ano = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, ano, mes, dia);
    }

    @Override
    public void onDateSet(DatePicker view, int arg1, int arg2, int arg3){
        String dia, mes, ano, data_completa;
        if (arg1 < 10)
            ano = "0" + Integer.toString(arg1);
        else
            ano = Integer.toString(arg1);
        if (arg2 < 10)
            mes = "0" + Integer.toString(arg2 + 1); // Ele sempre tras com um numero abaixo (provavelmente comeca a partir do mes 0 (janeiro))
        else
            mes = Integer.toString(arg2 + 1); // Ele sempre tras com um numero abaixo (provavelmente comeca a partir do mes 0 (janeiro))
        if (arg3 < 10)
            dia = "0" + Integer.toString(arg3);
        else
            dia = Integer.toString(arg3);
        data_completa = dia + "/" + mes + "/" + ano;

        EditText etxt = (EditText) getActivity().findViewById(recurso_solicitado);
        etxt.setText(data_completa);
    }

}
