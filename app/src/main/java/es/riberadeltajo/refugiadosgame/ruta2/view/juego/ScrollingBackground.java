package es.riberadeltajo.refugiadosgame.ruta2.view.juego;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

import es.riberadeltajo.refugiadosgame.R;

/**
 * Created by lagar on 04/02/2017.
 */

public class ScrollingBackground {
    private final int NUM_MONEDAS=30;
    private final int NUM_BICHOS=30;
    private Bitmap fondo;                   // Fondo del juego
    private Bitmap explosion;
    private Bitmap destello;
    private int x, y, topeancho, topealto;  // Coordenadas por donde va el fondo y dimensiones de la pantalla
    private final int VELOCIDAD = 10;        // Velocidad a la que se moverá el fondo
    private Jugador jugador;
    private int anchosalida, altosalida;
    private ArrayList<Monedas> monedas;
    private ArrayList<Bichos> bichos;
    private List<Temporales> temporales;
    private int anchoTotal;
    private int monedasBajas, monedasAltas;
    private GameView2 gameview2;
    public ScrollingBackground(GameView2 gameview2,Bitmap fondo, int anchopantalla, int altopantalla, Jugador jugador, int anchosalida, int altosalida) {
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
        bichos=new ArrayList<Bichos>();
        temporales=new ArrayList<Temporales>();

        setDestello(BitmapFactory.decodeResource(getGameview2().getResources(),R.drawable.milan_destellocoin));
        setExplosion(BitmapFactory.decodeResource(getGameview2().getResources(),R.drawable.milan_explosion));
        crearMonedas();
        crearBichos();

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
    private void crearBichos(){
        double variable=0.15;
        Bitmap imagenBicho= BitmapFactory.decodeResource(getGameview2().getResources(), R.drawable.milan_rana);
        Bitmap redimension = Bitmap.createScaledBitmap(imagenBicho, (int)(getGameview2().getWidth()*0.5),(int)( getGameview2().getHeight()*0.5), false);
        Bitmap imagenBicho2= BitmapFactory.decodeResource(getGameview2().getResources(), R.drawable.milan_murcielago);
        Bitmap redimension2 = Bitmap.createScaledBitmap(imagenBicho2, (int)(getGameview2().getWidth()*0.4),(int)( getGameview2().getHeight()*0.5), false);
        Bichos bicho;
        for (int i=0;i<NUM_BICHOS;i++) {
            if(i%2==0) {
                bicho = new Bichos((int) (getAnchoTotal() * variable), getMonedasBajas(), redimension, getGameview2());
            }
            else{
                bicho = new Bichos((int) (getAnchoTotal() * variable), getMonedasAltas(), redimension2, getGameview2());
            }
            getBichos().add(bicho);
            variable+=0.08;
        }

    }

    public Bitmap getDestello() {
        return destello;
    }

    public void setDestello(Bitmap destello) {
        this.destello = destello;
    }

    public Bitmap getExplosion() {
        return explosion;
    }

    public void setExplosion(Bitmap explosion) {
        this.explosion = explosion;
    }

    public List<Temporales> getTemporales() {
        return temporales;
    }

    public void setTemporales(List<Temporales> temporales) {
        this.temporales = temporales;
    }

    public ArrayList<Bichos> getBichos() {
        return bichos;
    }

    public void setBichos(ArrayList<Bichos> bichos) {
        this.bichos = bichos;
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
            p.setCorx(p.getCorx()- ((int) (VELOCIDAD * 4)));
        }
        for (Bichos p:getBichos()){
            p.setCorx(p.getCorx()- ((int) (VELOCIDAD * 4)));
        }
        for(Temporales p:getTemporales()){
            p.setCorx(p.getCorx()- ((int) (VELOCIDAD * 4)));
        }



    }
    public void retrocederX() {


        x -= VELOCIDAD;
        if (x < 0) {
            x = 0;
        } else {
            for (Monedas p : getMonedas()) {
                p.setCorx(p.getCorx() + ((int) (VELOCIDAD * 4)));
            }
            for (Bichos p : getBichos()) {
                p.setCorx(p.getCorx() + ((int) (VELOCIDAD * 4)));
            }
            for (Temporales p : getTemporales()) {
                p.setCorx(p.getCorx() + ((int) (VELOCIDAD * 4)));
            }

        }
    }

    public void onDraw(Canvas lienzo) {
        if (getJugador().isEnMarcha() && getJugador().isDelante()) {
            avanzarX();

        } else if (getJugador().isEnMarcha() && getJugador().isDetras()) {
            retrocederX();
        }

        int x_tope = x + topeancho;
        Rect imagenfondoactual = new Rect(x, 0, x_tope, topealto);
        Rect imagenenpantalla = new Rect(0, 0, topeancho, topealto);
        imagenenpantalla.set(0, 0, getAnchosalida(), getAltosalida());
        lienzo.drawBitmap(fondo, imagenfondoactual, imagenenpantalla, null);
        for (Monedas p : getMonedas()) {
            p.onDraw(lienzo);
        }
        for (Bichos p : getBichos()) {
            p.onDraw(lienzo);
        }
        for(int i=getTemporales().size()-1;i>=0;i--){
            getTemporales().get(i).draw(lienzo);
        }


        synchronized (getGameview2().getHolder()) {
            for (int i = getBichos().size()-1; i >= 0; i--) {
                if (getJugador().isCollition(getBichos().get(i).getCorx()+getBichos().get(i).getxInicio()+getBichos().get(i).getxSpeed()+getBichos().get(i).getWidth(),
                        getBichos().get(i).getCory())
                        ) {
                    getTemporales().add(new Temporales(getTemporales(),getGameview2(),
                            getBichos().get(i).getCorx()+getBichos().get(i).getWidth(),getBichos().get(i).getCory(),getExplosion()));

                    getBichos().remove(i);
                    getJugador().setVidas(getJugador().getVidas()-1);
                }
            }
            for (int i=getMonedas().size()-1;i>=0;i--){
                if(getJugador().isCollition(getMonedas().get(i).getCorx(), getMonedas().get(i).getCory())
                        || getJugador().isCollition(getMonedas().get(i).getCorx()+getMonedas().get(i).getWidth(),
                        getMonedas().get(i).getCory()+getMonedas().get(i).getHeight())){
                    getTemporales().add(new Temporales(getTemporales(),getGameview2(),
                            getMonedas().get(i).getCorx()+getMonedas().get(i).getWidth(),getMonedas().get(i).getCory(),getDestello()));
                    getMonedas().remove(i);
                    getJugador().setMonedas(getJugador().getMonedas()+1);
                }
            }

        }
    }

    }

