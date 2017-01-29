package es.riberadeltajo.refugiadosgame.ruta3.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import es.riberadeltajo.refugiadosgame.R;

/**
 * Created by Alex on 28/01/2017.
 */

public class Sprite {

    private GameView gameView;
    private Bitmap sprite;
    private Bitmap frame;
    private int filas;
    private int columnas;
    private int width;
    private int height;
    private int posX;
    private int posY;
    private int speedX;
    private int speedY;

    public Sprite(GameView gameView, int spriteRes, int filas, int columnas, int width, int height, int posX, int posY, int speedX, int speedY) {
        setGameView(gameView);
        setFilas(filas);
        setColumnas(columnas);
        setWidth(width);
        setHeight(height);
        setPosX(posX);
        setPosY(posY);
        setSpeedX(speedX);
        setSpeedY(speedY);
        setSprite(spriteRes);
    }

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public Bitmap getSprite() {
        return sprite;
    }

    public void setSprite(int res) {
        Bitmap bm = BitmapFactory.decodeResource(getGameView().getResources(), res);
        this.sprite = Bitmap.createScaledBitmap(bm, getWidth() * getColumnas(), getHeight() * getFilas(), false);
    }

    public Bitmap getFrame() {
        return frame;
    }

    private void setFrame(Bitmap frame) {
        this.frame = frame;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
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

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

}
