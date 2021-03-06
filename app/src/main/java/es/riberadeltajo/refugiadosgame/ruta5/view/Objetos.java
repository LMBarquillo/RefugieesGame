package es.riberadeltajo.refugiadosgame.ruta5.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Profesor on 26/01/2017.
 */

public class Objetos {

    private int corx,cory;      //posicion de la pantalla en la que empiezan
    private int ySpeed;         //Solo se mueven hacia abajo
    private int velocidad;          //Puntos de la moneda. +1, +5, o -1...
    private GameView gameView;
    private Bitmap bmp;
    private int width;          //Ancho de los objetos
    private int height;         //Alto de los objetos
    private boolean coger;
    private int segundo;

    public Objetos(GameView gameView, Bitmap bmp, int velocidad, boolean coger, int seg){
        setWidth(bmp.getWidth());
        setHeight(bmp.getHeight());
        setGameView(gameView);
        setCorx((int)(Math.random()*(getGameView().getWidth()-getWidth())));
        setCory(0-getHeight());
        setBmp(bmp);
        setVelocidad(velocidad);
        setySpeed(velocidad);
        setCoger(coger);
        setSegundo(seg);
    }

    private void update(){      //Movimiento
        if(getCory()<getGameView().getHeight()+getHeight()){       //+getHeight()
            setCory(getCory()+getySpeed());
        }
        else{
            setySpeed(0);
        }

    }

    public boolean choqueJugador(Player p){
        boolean chocan=false;
        if (p.getCory() <= getCory() + getHeight()/2 &&
                p.getCory() + p.getHeight()/2 >= getCory() &&
                p.getCorx() + p.getWidth()/2 >= getCorx() &&
                p.getCorx() <= getCorx() + getWidth()/2){
            chocan= true;
        }

        //if (p.getCory() <= getCory() + getHeight() &&
        //        p.getCory() + p.getHeight() >= getCory()    &&
        //        p.getCorx() + p.getWidth() >= getCorx() &&
        //        p.getCorx() <= getCorx() + getWidth()){
        //    chocan= true;
        //}

        return chocan;
    }

    public boolean finalPantalla(){    //Comprueba si el objeto ha llegado al final de la pantalla(abajo)
        boolean llega=false;
        if(getCory()>=getGameView().getHeight()){
            llega=true;
        }
        return llega;
    }


    public void draw(Canvas canvas){    //Dibujar
        update();
        canvas.drawBitmap(getBmp(),getCorx(),getCory(),null);
    }


    public int getSegundo() {
        return segundo;
    }

    public void setSegundo(int segundo) {
        this.segundo = segundo;
    }

    public boolean isCoger() {
        return coger;
    }

    public void setCoger(boolean coger) {
        this.coger = coger;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCorx() {
        return corx;
    }

    public void setCorx(int corx) {
        this.corx = corx;
    }

    public int getCory() {
        return cory;
    }

    public void setCory(int cory) {
        this.cory = cory;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public Bitmap getBmp() {
        return bmp;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }
}
