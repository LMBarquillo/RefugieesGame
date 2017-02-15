package es.riberadeltajo.refugiadosgame.ruta3.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Profesor on 15/02/2017.
 */

public class Coche {

    private GameView gameView;

    private Bitmap sprite;
    private int currentCol;
    private int currentRow;
    private int posX;
    private int posY;
    private int speedX;
    private int speedY;
    private int filas;
    private int columnas;
    private int width;
    private int height;

    public Coche(GameView gameView, int bitmapID, int posX, int posY, int speedX, int speedY, int filas, int columnas, int width, int height) {
        setGameView(gameView);
        setSprite(BitmapFactory.decodeResource(getGameView().getResources(), bitmapID));
        setPosX(posX);
        setPosY(posY);
        setSpeedX(speedX);
        setSpeedY(speedY);
        setFilas(filas);
        setColumnas(columnas);
        setWidth(width);
        setHeight(height);
        setCurrentCol(0);
        setCurrentRow(0);
    }

    public int getCurrentCol() {
        return currentCol;
    }

    public void setCurrentCol(int currentCol) {
        this.currentCol = currentCol;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
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

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public Bitmap getSprite() {
        return sprite;
    }

    public void setSprite(Bitmap sprite) {
        this.sprite = sprite;
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

    public void draw(Canvas canvas) {
        Rect src = new Rect(getCurrentCol() * getSprite().getWidth()/getColumnas(), getCurrentRow() * getSprite().getHeight()/getFilas(), getCurrentCol() * getSprite().getWidth()/getColumnas() + getSprite().getWidth()/getColumnas(), getCurrentRow() * getSprite().getHeight()/getFilas() + getSprite().getHeight()/getFilas());
        Rect dst = new Rect(getPosX(), getPosY(), getPosX() + getWidth(), getPosY() + getHeight());
        canvas.drawBitmap(getSprite(), src, dst, null);
        if(++currentCol == getColumnas()) {
            currentCol = 0;
            if(++currentRow == getFilas()) {
                currentRow = 0;
            }
        }
        setPosX(getPosX() + getSpeedX());
        setPosY(getPosY() + getSpeedY());
    }
}
