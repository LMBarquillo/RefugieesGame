package es.riberadeltajo.refugiadosgame.ruta3.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import es.riberadeltajo.refugiadosgame.R;

/**
 * Created by Alex on 28/01/2017.
 */

public class Sprite {

    private final int[] DIRECCION = {3, 1, 0, 2};

    private GameView gameView;
    private Bitmap sprite;
    private int currentFrame;
    private int direccion;
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

    public void setSprite(Bitmap sprite) {
        this.sprite = sprite;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentframe) {
        this.currentFrame = currentframe;
    }

    public int getDireccion() {
        double dir = Math.atan2(getSpeedX(), getSpeedY()) / (Math.PI / 2) + 2;
        int direccion = (int) Math.round(dir) % getColumnas();
        return DIRECCION[direccion];
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
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

    public void draw(Canvas canvas) {
        update();
        Rect src = new Rect(getCurrentFrame() * getWidth(), getDireccion() * getHeight(), (getCurrentFrame() * getWidth()) + getWidth(), (getDireccion() * getHeight() + getHeight()));
        Rect dst = new Rect(getPosX(), getPosY(), getPosX() + getWidth(), getPosY() + getHeight());
        canvas.drawBitmap(getSprite(), src, dst, null);
    }

    private void update() {
        /*if(getPosX() >= getGameView().getWidth() - getWidth() - getSpeedX() || getPosX() + getSpeedX() <= 0) {
            setSpeedX(-getSpeedX());
        } else {
            setPosX(getPosX() + getSpeedX());
        }
        if(getPosY() >= getGameView().getHeight() - getHeight() - getSpeedY() || getPosY() + getSpeedY() <= 0) {
            setSpeedY(-getSpeedY());
        } else {
            setPosY(getPosY() + getSpeedY());
        }*/
        setPosX(getPosX() + getSpeedX());
        setPosY(getPosY() + getSpeedY());
        setCurrentFrame(++currentFrame%getColumnas());

    }

}
