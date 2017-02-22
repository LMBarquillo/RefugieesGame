package es.riberadeltajo.refugiadosgame.common.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;

import es.riberadeltajo.refugiadosgame.ruta3.view.Player;

/**
 * Clase para definir el objeto que contendrá los datos de progreso del jugador.
 * Añadir las propiedades que sean necesarias y modificar los constructores, el readFromParcel y el writeToParcel.
 * Created by Luismi on 22/01/2017.
 */

public class PlayerStatus {

    private static final String NOMBRE_FICHERO = "jugador";

    private static final String KEY_RUTA = "ruta";
    private static final String KEY_TRAMO = "tramo";
    private static final String KEY_DINERO = "dinero";

    private static final int DEFAULT_RUTA = 1;
    private static final int DEFAULT_TRAMO = 1;
    private static final int DEFAULT_DINERO = 0;

    private static PlayerStatus instancia = null;

    private Context context;
    private SharedPreferences sharedPreferences;

    private PlayerStatus(){}

    public static PlayerStatus getInstancia(Context context) {
        if(instancia == null) {
            instancia = new PlayerStatus();
        }
        instancia.setContext(context);
        return instancia;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
        sharedPreferences = getContext().getSharedPreferences(NOMBRE_FICHERO, getContext().MODE_PRIVATE);
    }

    public void resetStatus() {
        sharedPreferences.edit().clear().commit();
    }

    public int getRuta() {
        return sharedPreferences.getInt(KEY_RUTA, DEFAULT_RUTA);
    }

    public void setRuta(int ruta) {
        sharedPreferences.edit().putInt(KEY_RUTA, ruta).commit();
    }

    public int getTramo() {
        return sharedPreferences.getInt(KEY_TRAMO, DEFAULT_TRAMO);
    }

    public void setTramo(int tramo) {
        sharedPreferences.edit().putInt(KEY_TRAMO, tramo).commit();
    }

    public int getDinero() {
        return sharedPreferences.getInt(KEY_DINERO, DEFAULT_DINERO);
    }

    public void setDinero(int dinero) {
        sharedPreferences.edit().putInt(KEY_DINERO, dinero).commit();
    }

}
