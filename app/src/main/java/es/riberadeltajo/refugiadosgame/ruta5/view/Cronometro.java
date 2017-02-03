package es.riberadeltajo.refugiadosgame.ruta5.view;

import java.util.Observable;

/**
 * Created by Profesor on 03/02/2017.
 */

public class Cronometro extends Observable implements Runnable {

    private int segundos;
    private boolean continuar;

    public Cronometro(){
        setSegundos(0);
        setContinuar(true);
    }


    @Override
    public void run() {
        while(isContinuar()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {

            }
            setSegundos(getSegundos() + 1);
            setChanged();
            notifyObservers();
        }
    }

    public boolean isContinuar() {
        return continuar;
    }

    public void setContinuar(boolean continuar) {
        this.continuar = continuar;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }
}
