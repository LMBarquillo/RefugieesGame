package es.riberadeltajo.refugiadosgame.ruta2.view.juego;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import es.riberadeltajo.refugiadosgame.R;

/**
 * Created by lagar on 11/02/2017.
 */

public class Monedas {
    private final int VELOCIDAD=20;
    private final int  BMP_COLUMNS=10;
    private int corx, cory;
    private Bitmap bmp; //Imagen de las figuritas
    private int currentFrame; //Figura que ha aparecido última
    private int width; //Ancho del muñeco
    private int height; //Alto del muñeco
    private GameView2 gameView2;

    public Monedas(GameView2 gameView2, int corx, int cory, Bitmap imagen) {
        setGameView2(gameView2);
        setCorx(corx);
        setCory(cory);
        setBmp(imagen);
        setWidth(imagen.getWidth()/BMP_COLUMNS);
        setHeight(imagen.getHeight());
    }

    public GameView2 getGameView2() {
        return gameView2;
    }

    public void setGameView2(GameView2 gameView2) {
        this.gameView2 = gameView2;
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

    public Bitmap getBmp() {
        return bmp;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
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
    public void update(){
        currentFrame = ++currentFrame % BMP_COLUMNS;

    }
    public void onDraw(Canvas canvas){
        int srcx = getCurrentFrame() * getWidth();
        int srcy=0;

        update();
        Rect src = new Rect(srcx, srcy, srcx + width, srcy + height);
        Rect dst = new Rect(corx, cory, corx + width, cory + height);
        canvas.drawBitmap(bmp, src, dst, null);
    }
}
