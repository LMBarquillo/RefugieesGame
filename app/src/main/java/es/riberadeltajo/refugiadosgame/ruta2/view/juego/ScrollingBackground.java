package es.riberadeltajo.refugiadosgame.ruta2.view.juego;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

import es.riberadeltajo.refugiadosgame.R;

/**
 * Created by lagar on 04/02/2017.
 */

public class ScrollingBackground {
private final int NUM_MONEDAS=30;
    private Bitmap fondo;                   // Fondo del juego
    private int x, y, topeancho, topealto;  // Coordenadas por donde va el fondo y dimensiones de la pantalla
    private final int VELOCIDAD = 20;        // Velocidad a la que se moverá el fondo
    private Jugador jugador;
    private int anchosalida, altosalida;
    private ArrayList<Monedas> monedas;
    private int anchoTotal;
    private int monedasBajas, monedasAltas;
    private GameView2 gameview2;
    public ScrollingBackground(GameView2 gameview2,Bitmap fondo, int anchopantalla, int altopantalla, Jugador jugador, int anchosalida, int altosalida)
    {
        setFondo(fondo);

        x = y = 0;
        setGameview2(gameview2);
        setTopeancho(anchopantalla);
        setTopealto(altopantalla);
        setJugador(jugador);
        setAltosalida(altosalida);
        setAnchosalida(anchosalida);
        int anchoImagen=fondo.getWidth();
        int altoImagen=fondo.getHeight();
        int escaladoAncho=altosalida*anchoImagen/altoImagen;
        setAnchoTotal(escaladoAncho);
        setMonedasAltas((int) (altosalida*0.3));
        setMonedasBajas((int)(altosalida*0.6));
        monedas=new ArrayList<Monedas>();
        crearMonedas();

    }

    private void crearMonedas() {

        double variable=0.08;
        Bitmap imagen= BitmapFactory.decodeResource(getGameview2().getResources(), R.drawable.milan_moneda);
        Monedas moneda=new Monedas(getGameview2(), 2000,getMonedasAltas(),imagen);
        getMonedas().add(moneda);
       for (int i=0;i<NUM_MONEDAS;i++) {
            variable+=0.03;
           if(i%2==0) {
               moneda = new Monedas(getGameview2(), (int) (getAnchoTotal() * variable), getMonedasBajas(), imagen);
               getMonedas().add(moneda);
           }
           else{
               moneda = new Monedas(getGameview2(), (int) (getAnchoTotal() * variable), getMonedasAltas(), imagen);
               getMonedas().add(moneda);
           }
        }
    }

    public GameView2 getGameview2() {
        return gameview2;
    }

    public void setGameview2(GameView2 gameview2) {
        this.gameview2 = gameview2;
    }

    public int getMonedasAltas() {
        return monedasAltas;
    }

    public void setMonedasAltas(int monedasAltas) {
        this.monedasAltas = monedasAltas;
    }

    public int getMonedasBajas() {
        return monedasBajas;
    }

    public void setMonedasBajas(int monedasBajas) {
        this.monedasBajas = monedasBajas;
    }

    public ArrayList<Monedas> getMonedas() {
        return monedas;
    }

    public void setMonedas(ArrayList<Monedas> monedas) {
        this.monedas = monedas;
    }

    public int getAnchoTotal() {
        return anchoTotal;
    }

    public void setAnchoTotal(int anchoTotal) {
        this.anchoTotal = anchoTotal;
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

    public void avanzarX() {


        x += VELOCIDAD;
        if (x > fondo.getWidth()){
            x = fondo.getWidth() - topeancho;
        }
        for (Monedas p:getMonedas()){
            p.setCorx(p.getCorx()-((int)(VELOCIDAD*3.5)));
        }



    }
    public void retrocederX()
    {


        x -= VELOCIDAD;
        if(x < 0) {
            x = 0;
        }
        else{
        for (Monedas p:getMonedas()) {
            p.setCorx(p.getCorx()+((int)(VELOCIDAD*3.5)));
        }}

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
        for (Monedas p:getMonedas()){
            p.onDraw(lienzo);
        }

    }
}
