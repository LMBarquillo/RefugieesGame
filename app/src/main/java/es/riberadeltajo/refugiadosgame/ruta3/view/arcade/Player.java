package es.riberadeltajo.refugiadosgame.ruta3.view.arcade;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Alex on 07/02/2017.
 */

public class Player {

    private GameView gameView;
    private Bitmap sprite;
    private int currentFrame;
    private int filas;
    private int columnas;
    private int width;
    private int height;
    private int posX;
    private int posY;
    private int speedX;
    private int speedY;
    private boolean safe;
    private boolean arriba;

    public Player(GameView gameView, Bitmap bitmap, int filas, int columnas, int width, int height, int posX, int posY, int speedX, int speedY) {
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
        setArriba(true);
    }

    public Player(GameView gameView, int spriteRes, int filas, int columnas, int width, int height, int posX, int posY, int speedX, int speedY) {
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

    public boolean isArriba() {
        return arriba;
    }

    public void setArriba(boolean arriba) {
        this.arriba = arriba;
    }

    public void draw(Canvas canvas) {
        update();
        if(getSpeedY() > 0) {
            Rect src = new Rect(getCurrentFrame() * getWidth(), 0, (getCurrentFrame() * getWidth()) + getWidth(), 0 + getHeight());
            Rect dst = new Rect(getPosX(), getPosY(), getPosX() + getWidth(), getPosY() + getHeight());
            canvas.drawBitmap(getSprite(), src, dst, null);
            setArriba(false);
        } else
        if(getSpeedY() < 0) {
            Rect src = new Rect(getCurrentFrame() * getWidth(), 3 * getHeight(), (getCurrentFrame() * getWidth()) + getWidth(), 3 * getHeight() + getHeight());
            Rect dst = new Rect(getPosX(), getPosY(), getPosX() + getWidth(), getPosY() + getHeight());
            canvas.drawBitmap(getSprite(), src, dst, null);
            setArriba(true);
        } else {
            if(arriba) {
                Rect src = new Rect(2 * getWidth(), 3 * getHeight(), (2 * getWidth()) + getWidth(), 3 * getHeight() + getHeight());
                Rect dst = new Rect(getPosX(), getPosY(), getPosX() + getWidth(), getPosY() + getHeight());
                canvas.drawBitmap(getSprite(), src, dst, null);
            } else {
                Rect src = new Rect(2 * getWidth(), 0, (2 * getWidth()) + getWidth(), 0 + getHeight());
                Rect dst = new Rect(getPosX(), getPosY(), getPosX() + getWidth(), getPosY() + getHeight());
                canvas.drawBitmap(getSprite(), src, dst, null);
            }
        }
        if(!isSafe()) {
            getGameView().setFin(true);
            getGameView().setFin(true);
            getGameView().getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    /*AlertDialog.Builder dialog = new AlertDialog.Builder(getGameView().getActivity());
                    dialog.setTitle("You Lost");
                    dialog.setMessage("You lost because time is up");
                    dialog.setPositiveButton("Reintentar", null);
                    dialog.setNegativeButton("Salir", null);
                    dialog.create();
                    dialog.show();*/
                    new LoseDialog(getGameView().getActivity()).show();
                }
            });
        }
    }

    private void update() {
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
