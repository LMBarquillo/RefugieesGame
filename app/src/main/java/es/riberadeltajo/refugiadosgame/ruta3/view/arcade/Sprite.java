package es.riberadeltajo.refugiadosgame.ruta3.view.arcade;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

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
    private boolean safe;

    public Sprite(GameView gameView, Bitmap bitmap, int filas, int columnas, int width, int height, int posX, int posY, int speedX, int speedY) {
        setGameView(gameView);
        setFilas(filas);
        setColumnas(columnas);
        setWidth(width);
        setHeight(height);
        setPosX(posX);
        setPosY(posY);
        setSpeedX(speedX);
        setSpeedY(speedY);
        setSprite(bitmap);
        setSafe(true);
    }

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
        setSafe(true);
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

    public void setSprite(Bitmap bm) {
        this.sprite = Bitmap.createScaledBitmap(bm, getWidth() * getColumnas(), getHeight() * getFilas(), false);
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

    public boolean isSafe() {
        return safe;
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }

    public void draw(Canvas canvas) {
        update();
        if (getFilas() > 1 && getColumnas() > 1) {
            Rect src = new Rect(getCurrentFrame() * getWidth(), getDireccion() * getHeight(), (getCurrentFrame() * getWidth()) + getWidth(), (getDireccion() * getHeight() + getHeight()));
            Rect dst = new Rect(getPosX(), getPosY(), getPosX() + getWidth(), getPosY() + getHeight());
            canvas.drawBitmap(getSprite(), src, dst, null);
        } else {
            canvas.drawBitmap(getSprite(), (float)getPosX(), (float)getPosY(), null);
        }
        if(!isSafe()) {
            getGameView().setFin(true);
        }
    }

    private void update() {
        /*if(getPosX() >= getGameSurface().getWidth() - getWidth() - getSpeedX() || getPosX() + getSpeedX() <= 0) {
            setSpeedX(-getSpeedX());
        } else {
            setPosX(getPosX() + getSpeedX());
        }
        if(getPosY() >= getGameSurface().getHeight() - getHeight() - getSpeedY() || getPosY() + getSpeedY() <= 0) {
            setSpeedY(-getSpeedY());
        } else {
            setPosY(getPosY() + getSpeedY());
        }*/

        if(getSpeedX() != 0 || getSpeedY() != 0) {
            if(getPosY() + getSpeedY() < 0 || getPosY() + getHeight() + getSpeedY() > getGameView().getHeight()) {
                setSpeedX(0);
                setSpeedY(0);
            } else {
                setCurrentFrame(++currentFrame % getColumnas());
            }
        }
        setPosX(getPosX() + getSpeedX());
        setPosY(getPosY() + getSpeedY());

    }

    public boolean isCollition(Sprite s) {
        int x = s.getPosX() + (s.getWidth() / 2);
        int y1 = (s.getPosY() + s.getHeight()) - (s.getHeight() / 10);
        int y2 = s.getPosY() + s.getHeight();
        Rect r1 = new Rect(x, y1, x, y2);
        Rect r2 = new Rect(getPosX(), getPosY(), getPosX() + getWidth(), getPosY() + getHeight());
        return r1.intersect(r2);
    }

    public boolean isWatterCollition(int y, int height) {
        int x = getPosX() + (getWidth() / 2);
        int y1 = (getPosY() + getHeight()) - (getHeight() / 10);
        int y2 = getPosY() + getHeight();
        Rect r1 = new Rect(x, y1, x, y2);
        Rect r2 = new Rect(0, y, getGameView().getWidth(), y + height);
        return r1.intersect(r2);
    }

}
