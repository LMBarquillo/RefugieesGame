package es.riberadeltajo.refugiadosgame.ruta4.view.models;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import es.riberadeltajo.refugiadosgame.ruta4.view.engine.GameView;

/**
 * Sprite de las notas musicales.
 * Created by Profesor on 18/01/2017.
 */

public class SpriteNotas {
    /**
     * La dificultad sólo hace que las notas salgan más separadas entre sí y la velocidad sea mayor
     * pero la velocidad de la canción sigue siendo la misma, por lo que el sincronismo se mantiene
     */
    private GameView gameView;
    private Bitmap bmp;
    private int posx;
    private float duracion;
    private int altura;
    private int sizeNota;
    private int avance;
    private int dificultad;

    public SpriteNotas(GameView gameView, Bitmap bmp, int posicion, float duracion, int dificultad){
        setGameView(gameView);
        setBmp(bmp);
        setSizeNota(gameView.getWidth() / 5);
        setPosx((getSizeNota() * posicion - getSizeNota()) + (getSizeNota()/2));
        setDuracion(duracion);
        setAltura(-getSizeNota());
        setDificultad(dificultad);
        setAvance((int)(getSizeNota() / 60 * getGameView().getTPS() * getDificultad()));
    }

    private void update(){
        setAltura(getAltura() + avance);
   }

    public void dibujar(Canvas canvas){
        update();

        Rect src = new Rect(0,0,getBmp().getWidth(),getBmp().getHeight());
        Rect dst = new Rect(getPosx(),getAltura(),getSizeNota()+getPosx(),getSizeNota()+getAltura());
        canvas.drawBitmap(getBmp(),src,dst,null);
    }

    public boolean isCollition(float x, float y) {
         return getPosx() <= x && getAltura() <= y && (getSizeNota()+getPosx()) >= x && (getSizeNota()+getAltura()) >= y;
    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posicion) {
        this.posx = posicion;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
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

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getSizeNota() {
        return sizeNota;
    }

    public void setSizeNota(int sizeNota) {
        this.sizeNota = sizeNota;
    }

    public int getAvance() {
        return avance;
    }

    public void setAvance(int avance) {
        this.avance = avance;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }
}
