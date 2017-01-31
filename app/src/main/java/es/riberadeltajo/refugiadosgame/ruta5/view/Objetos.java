package es.riberadeltajo.refugiadosgame.ruta5.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Created by Profesor on 26/01/2017.
 */

public class Objetos {

    private int corx,cory;      //posicion de la pantalla en la que empiezan
    private int ySpeed;         //Solo se mueven hacia abajo
    private int valor;          //Puntos de la moneda. +1, +5, o -1...
    private GameView gameView;
    private Bitmap bmp;
    private int width;          //Ancho de los objetos
    private int height;         //Alto de los objetos

    public Objetos(GameView gameView, Bitmap bmp, int valor){
        setWidth(bmp.getWidth());
        setHeight(bmp.getHeight());
        setGameView(gameView);
        setCorx((int)Math.random()*(gameView.getWidth())-getWidth());
        setCory(getGameView().getHeight()-getHeight());
        setBmp(bmp);
        setValor(valor);
    }

    private void update(){      //Movimiento
        if(cory>=getGameView().getHeight()-height-ySpeed || cory+ySpeed<=0){
            ySpeed=-ySpeed;
        }
    }

    public void draw(Canvas canvas){    //Dibujar

    }

    public boolean isCollition(){
        return true;
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

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
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
