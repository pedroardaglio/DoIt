package doit.apps.paperaya.com.br.doit.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by pedro on 11/22/15.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
    private int recurso_solicitado;

    public void set_recurso(int recurso){
        this.recurso_solicitado = recurso;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar c = Calendar.getInstance();
        int hora = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hora, min, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int arg1, int arg2){
        String hora, min, horario_completo;
        if (arg1 < 10)
            hora = "0" + Integer.toString(arg1);
        else
            hora = Integer.toString(arg1);
        if (arg2 < 10)
            min = "0" +  Integer.toString(arg2);
        else
            min = Integer.toString(arg2);

        horario_completo = hora + ":" + min;

        EditText etxt = (EditText) getActivity().findViewById(recurso_solicitado);
        etxt.setText(horario_completo);
    }
}
