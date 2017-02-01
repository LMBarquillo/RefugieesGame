package es.riberadeltajo.refugiadosgame.ruta4.view.models;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import es.riberadeltajo.refugiadosgame.ruta4.view.engine.GameView;

/**
 * Sprite de las notas musicales.
 * Created by Profesor on 18/01/2017.
 */

public class SpriteNotas {
    private GameView gameView;
    private Bitmap bmp;
    private int posx;
    private float duracion;
    private int altura;

    public SpriteNotas(GameView gameView, Bitmap bmp, int posicion, float duracion){
        setGameView(gameView);
        setBmp(bmp);
        setPosx(gameView.getWidth() / 4 * posicion - bmp.getWidth());
        setDuracion(duracion);
        setAltura(-bmp.getHeight());
    }


    private void update(){
        setAltura(getAltura() + 40 );   // temporalmente, hasta hacer los cálculos para ajustar la velocidad
    }

    public void draw(Canvas canvas){
        update();
        canvas.drawBitmap(getBmp(),getPosx(),getAltura(),null);
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
}
