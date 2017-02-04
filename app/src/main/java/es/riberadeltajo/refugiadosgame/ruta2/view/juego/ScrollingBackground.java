package es.riberadeltajo.refugiadosgame.ruta2.view.juego;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by lagar on 04/02/2017.
 */

public class ScrollingBackground {
    private Bitmap fondo;                   // Fondo del juego
    private int x, y, topeancho, topealto;  // Coordenadas por donde va el fondo y dimensiones de la pantalla
    private final int VELOCIDAD = 20;        // Velocidad a la que se moverá el fondo
    private Jugador jugador;
    private int anchosalida, altosalida;
    public ScrollingBackground(Bitmap fondo, int anchopantalla, int altopantalla, Jugador jugador, int anchosalida, int altosalida)
    {
        setFondo(fondo);

        x = y = 0;
        setTopeancho(anchopantalla);
        setTopealto(altopantalla);
        setJugador(jugador);
        setAltosalida(altosalida);
        setAnchosalida(anchosalida);

    }

    public Bitmap getFondo() {
        return fondo;
    }

    public void setFondo(Bitmap fondo) {
        this.fondo = fondo;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTopeancho() {
        return topeancho;
    }

    public void setTopeancho(int topeancho) {
        this.topeancho = topeancho;
    }

    public int getTopealto() {
        return topealto;
    }

    public void setTopealto(int topealto) {
        this.topealto = topealto;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public int getAnchosalida() {
        return anchosalida;
    }

    public void setAnchosalida(int anchosalida) {
        this.anchosalida = anchosalida;
    }

    public int getAltosalida() {
        return altosalida;
    }

    public void setAltosalida(int altosalida) {
        this.altosalida = altosalida;
    }

    public void avanzarX()
    {
        x += VELOCIDAD;
        if(x > fondo.getWidth())
            x = fondo.getWidth() - topeancho;
    }
    public void retrocederX()
    {
        x -= VELOCIDAD;
        if(x < 0)
            x = 0;
    }

    public void onDraw(Canvas lienzo)
    {
        if(getJugador().isEnMarcha() && getJugador().isDelante()){
            avanzarX();
        }
        else if(getJugador().isEnMarcha() && getJugador().isDetras()){
            retrocederX();
        }

        int x_tope = x + topeancho;
        Rect imagenfondoactual = new Rect(x, 0, x_tope, topealto);
        Rect imagenenpantalla = new Rect(0, 0, topeancho, topealto);
        imagenenpantalla.set(0,0, getAnchosalida(),getAltosalida());
        lienzo.drawBitmap(fondo, imagenfondoactual, imagenenpantalla, null);
    }
}
