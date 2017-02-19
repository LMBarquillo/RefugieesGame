package es.riberadeltajo.refugiadosgame.ruta4.view.models;

/**
 * Created by Luismi on 19/02/2017.
 */

public interface Distribuidor {
    public void setOpciones(int cancion, int dificultad, int lugar);
    public void cambiarFase(int fase);
    public void finalizar();
}
