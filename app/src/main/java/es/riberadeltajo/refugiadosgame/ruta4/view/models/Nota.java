package es.riberadeltajo.refugiadosgame.ruta4.view.models;

/**
 * Objeto Nota.
 * Almacena la información de cada nota musical.
 * Created by Luismi on 29/01/2017.
 */

public class Nota {
    private float traste;   // aunque usemos valores enteros, algunas cancione pueden ser ajustadas a decimales
    private int posicion;
    private float duracion;

    public Nota(float traste, int posicion, float duracion) {
        setTraste(traste);
        setPosicion(posicion);
        setDuracion(duracion);
    }

    public float getTraste() {
        return traste;
    }

    public void setTraste(float traste) {
        this.traste = traste;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }
}
