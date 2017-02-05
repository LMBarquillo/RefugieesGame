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
    private GameView gameView;
    private Bitmap bmp;
    private int posx;
    private float duracion;
    private int altura;
    private int sizeNota;
    private int avance;

    public SpriteNotas(GameView gameView, Bitmap bmp, int posicion, float duracion){
        setGameView(gameView);
        setBmp(bmp);
        setSizeNota(gameView.getWidth() / 5);
        setPosx((getSizeNota() * posicion - getSizeNota()) + (getSizeNota()/2));
        setDuracion(duracion);
        setAltura(-getSizeNota());
        setAvance(getSizeNota() / 60 * 4);  // avance = tamaño traste / FPS / trastes por segundo  ???
    }

    private void update(){
        setAltura(getAltura() + avance );
        if(getAltura() >= getGameView().getHeight()) {
            //getGameView().deleteFirstNote();    // La nota se ha salido por debajo y la eliminamos del arraylist
        }
   }

    public void draw(Canvas canvas){
        update();
        canvas.drawBitmap(getBmp(),getPosx(),getAltura(),null);


        // Monitores y/o contadores
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(60);
        //canvas.drawText(String.valueOf(getPosAL()),getPosx()-50,getAltura(),paint);
        //canvas.drawText(String.valueOf(getAltura()),getPosx(),getGameView().getHeight()-200,paint);
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
}
