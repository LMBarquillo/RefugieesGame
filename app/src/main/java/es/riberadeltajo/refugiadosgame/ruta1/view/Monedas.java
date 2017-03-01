package es.riberadeltajo.refugiadosgame.ruta1.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.List;

/**
 * Created by alumno on 18/01/2017.
 */

public class Monedas {
    private final int[] DIRECCION={0};
    private final int BMP_COLUMS=6;
    private final int BMP_ROWS=1;
    private int width;
    private int height;
    private float corx;
    private float cory;
    private Bitmap bmp;
    private int life;
    private List<Monedas> coins;
    private int puntos;
    private int currentFrame;
    private int xSpeed,ySpeed;
    private GameView gameView;

    public Monedas(List<Monedas> temps, GameView gameview,Bitmap bmp,int puntos){
        setWidth(bmp.getWidth()/BMP_COLUMS);
        setHeight(bmp.getHeight()/BMP_ROWS);
        life=150;
        setCoins(temps);
        setBmp(bmp);
        setGameView(gameview);
        setCurrentFrame(0);
        setxSpeed(1);
        setySpeed(1);
        setCorx((int) (Math.random()*(getGameView().getWidth()-(2*getWidth()+(getGameView().getWidth()*0.1))))+(int)(getGameView().getWidth()*0.1));
        setCory((int) (Math.random()*(getGameView().getHeight()-(2*getHeight()+(getGameView().getHeight()*0.1))))+(int)(getGameView().getHeight()*0.1));
        setPuntos(puntos);
    }


    private int getDireccion(){
        double dir=(Math.atan2(getxSpeed(),getySpeed())/(Math.PI/2)+2);
        int direccion=(int)Math.round(dir)%BMP_ROWS;
        return DIRECCION[direccion];
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
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

    public float getCorx() {
        return corx;
    }

    public void setCorx(float corx) {
        this.corx = corx;
    }

    public float getCory() {
        return cory;
    }

    public void setCory(float cory) {
        this.cory = cory;
    }

    public List<Monedas> getCoins() {
        return coins;
    }

    public void setCoins(List<Monedas> coins) {
        this.coins = coins;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
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

    private void update(){
        setCurrentFrame(++currentFrame % BMP_COLUMS);
    }

    public void draw(Canvas canvas){
        update();
        if(--life<1){
            getCoins().remove(this);
        }
        else{
            int srcx=getCurrentFrame()*getWidth();
            int srcy=getDireccion()*getHeight();
            Rect src=new Rect(srcx,srcy,srcx+getWidth(),srcy+getHeight());
            Rect dst=new Rect((int)getCorx(),(int)getCory(),(int)getCorx()+getWidth(),(int)getCory()+getHeight());
            canvas.drawBitmap(getBmp(),src,dst,null);
        }
    }

    //Método que devuelve true si el personaje colisiona con las monedas
    public boolean isCollition(Sprite p) {
        boolean collition = false;
        if (p.getCory() <= getCory() + getHeight() / 2 &&
                p.getCory() + p.getHeight() / 2 >= getCory() &&
                p.getCorx() + p.getWidth() / 2 >= getCorx() &&
                p.getCorx() <= getCorx() + getWidth() / 2) {
            collition = true;
        }
        return collition;
    }
}
