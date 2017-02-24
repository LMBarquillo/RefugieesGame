package es.riberadeltajo.refugiadosgame.ruta3.view.arcade;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Alex on 07/02/2017.
 */

public class Nenufar {

    private GameView gameView;
    private Bitmap sprite;
    private int width;
    private int height;
    private int posX;
    private int posY;
    private int speedX;
    private int speedY;

    public Nenufar(GameView gameView, Bitmap bitmap, int width, int height, int posX, int posY, int speedX, int speedY) {
        setGameView(gameView);
        setWidth(width);
        setHeight(height);
        setPosX(posX);
        setPosY(posY);
        setSpeedX(speedX);
        setSpeedY(speedY);
        setSprite(bitmap);
    }

    public Nenufar(GameView gameView, int spriteRes, int width, int height, int posX, int posY, int speedX, int speedY) {
        setGameView(gameView);
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
        this.sprite = Bitmap.createScaledBitmap(bm, getWidth(), getHeight(), false);
    }

    public void setSprite(Bitmap bm) {
        this.sprite = Bitmap.createScaledBitmap(bm, getWidth(), getHeight(), false);
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
        canvas.drawBitmap(getSprite(), getPosX(), getPosY(), null);
    }

    private void update() {
        setPosX(getPosX() + getSpeedX());
        setPosY(getPosY() + getSpeedY());
    }

    public boolean isCollition(Player s) {
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
