package doit.apps.paperaya.com.br.doit.util;

import android.widget.Spinner;

/**
 * Created by pedro on 11/22/15.
 */
public class SpinnerObject {
    private int id;
    private String texto;

    public SpinnerObject(){

    }

    public SpinnerObject (int id, String texto){
        this.id = id;
        this.texto = texto;
    }

    public int getId(){
        return this.id;
    }

    public String getTexto(){
        return this.texto;
    }

    @Override
    public String toString(){
        return this.texto;
    }
}