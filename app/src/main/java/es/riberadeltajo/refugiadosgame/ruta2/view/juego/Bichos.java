package es.riberadeltajo.refugiadosgame.ruta2.view.juego;

import android.graphics.Bitmap;

/**
 * Created by lagar on 11/02/2017.
 */

public class Bichos {

    private final int VELOCIDAD=20;
    private final int  BMP_COLUMNS=3;
    private final int BMP_ROWS=2;
    private final int[] DIRECCION={1,0};
    private int corx, cory;
    private Bitmap bmp; //Imagen de las figuritas
    private int currentFrame; //Figura que ha aparecido última
    private int width; //Ancho del muñeco
    private int height; //Alto del muñeco
    private GameView2 gameView2;
    private int xSpeed;

    public Bichos(int corx, int cory, Bitmap bmp, GameView2 gameView2) {
        setGameView2(gameView2);
        setCorx(corx);
        setCory(cory);

        setBmp(bmp);
        setWidth(bmp.getWidth()/BMP_COLUMNS);
        setHeight(bmp.getHeight()/BMP_ROWS);
        setCurrentFrame(0);
        setxSpeed(VELOCIDAD);
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
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

    public GameView2 getGameView2() {
        return gameView2;
    }

    public void setGameView2(GameView2 gameView2) {
        this.gameView2 = gameView2;
    }
}
