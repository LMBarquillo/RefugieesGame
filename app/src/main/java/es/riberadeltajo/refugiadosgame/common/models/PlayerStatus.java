package es.riberadeltajo.refugiadosgame.common.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Clase para definir el objeto que contendrá los datos de progreso del jugador.
 * Añadir las propiedades que sean necesarias y modificar los constructores, el readFromParcel y el writeToParcel.
 * Created by Luismi on 22/01/2017.
 */

public class PlayerStatus implements Parcelable{
    private double dinero;

    public PlayerStatus() {
        setDinero(0);
    }

    protected PlayerStatus(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<PlayerStatus> CREATOR = new Creator<PlayerStatus>() {
        @Override
        public PlayerStatus createFromParcel(Parcel in) {
            return new PlayerStatus(in);
        }

        @Override
        public PlayerStatus[] newArray(int size) {
            return new PlayerStatus[size];
        }
    };

    public double getDinero() {
        return dinero;
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(dinero);
    }

    public void readFromParcel(Parcel src) {
        setDinero(src.readDouble());
    }
}
