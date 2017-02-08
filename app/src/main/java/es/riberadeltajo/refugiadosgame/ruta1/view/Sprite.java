package es.riberadeltajo.refugiadosgame.ruta1.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by alumno on 18/01/2017.
 */

public class Sprite {//0-1-2-3-4-5-6-7 //1,3,0,2,4,5,6,7
    private final int[] DIRECCION={4,5,3,1,0,2,7,6};
    private final int BMP_COLUMS=13;
    private final int BMP_ROWS=8;
    private int corx,cory;
    private int xSpeed,ySpeed;
    private GameView gameView;
    private Bitmap bmp;
    private int currentFrame;
    private int width;
    private int height;
    private int puntos;
    private int posX,posY;
    private boolean pintar=false;
    private int direccion;

    public Sprite(GameView gameView, Bitmap bmp, int puntos){
        setWidth(bmp.getWidth()/BMP_COLUMS);
        setHeight(bmp.getHeight()/BMP_ROWS);
        setGameView(gameView);
        setBmp(bmp);
        setCurrentFrame(0);
        setxSpeed(10);
        setySpeed(10);
        setPosX(0);
        setPosY(0);
        setCorx((int) (Math.random()*(gameView.getWidth()-getWidth())));
        setCory((int) (Math.random()*(gameView.getHeight()-getHeight())));
        setPuntos(puntos);
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

    public boolean isPintar() {
        return pintar;
    }

    public void setPintar(boolean pintar) {
        this.pintar = pintar;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
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

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    //Método para que el Sprite mire en la dirección correcta
    private int getDireccion(){
        if(getPosX()==getCorx() && (getPosY()>getCory())){
            direccion=4;
        }
        else if(getPosX()>getCorx() && (getPosY()>getCory())){
            direccion=5;
        }
        else if(getPosY()==getCory() && (getPosX()<getCorx())){
            direccion=2;
        }
        else if(getPosX()<getCorx() && (getPosY()>getCory())){
            direccion=3;
        }
        else if(getPosX()==getCorx() && (getPosY()<getCory())){
            direccion=0;
        }
        else if(getPosX()<getCorx() && (getPosY()<getCory())){
            direccion=1;
        }
        else if(getPosY()==getCory() && (getPosX()>getCorx())){
            direccion=6;
        }
        else if(getPosX()>getCorx() && (getPosY()<getCory())){
            direccion=7;
        }
        return DIRECCION[direccion];
    }

    private void update(){
            if (getCorx() >= getGameView().getWidth() - getWidth() - getxSpeed() || getCorx() + getxSpeed() <= 0) {
                setPintar(false);
            } else {
                if (getCorx() < getPosX()) {
                    setCorx(getCorx() + getxSpeed());
                    if(getPosX()-getCorx()<10){
                        setCorx(getPosX());
                    }
                } else if (getCorx() > getPosX()) {
                    setCorx(getCorx() - getxSpeed());
                    if(getCorx()-getPosX()<10){
                        setCorx(getPosX());
                    }
                } else if (getCorx() == getPosX()) {
                    setCorx(getPosX());
                }
            }
            if (getCory() >= getGameView().getHeight() - getHeight() - getySpeed() || getCory() + getySpeed() <= 0) {
                setPintar(false);
            } else {
                if (getCory() < getPosY()) {
                    setCory(getCory() + getySpeed());
                    if(getPosY()-getCory()<10){
                        setCory(getPosY());
                    }
                } else if (getCory() > getPosY()) {
                    setCory(getCory() - getySpeed());
                    if(getCory()-getPosY()<10){
                        setCory(getPosY());
                    }
                } else if (getCory() == getPosY()) {
                    setCory(getPosY());
                }
            }
            if(getCorx()==getPosX() && getCory()==getPosY()){
                setPintar(false);
            }

        setCurrentFrame(++currentFrame % BMP_COLUMS);
    }

    public void draw(Canvas canvas){
        getDireccion();
        if(isPintar()) {
            update();
        }
        int srcx=getCurrentFrame()*getWidth();
        int srcy=getDireccion()*getHeight();
        Rect src=new Rect(srcx,srcy,srcx+getWidth(),srcy+getHeight());
        Rect dst=new Rect(getCorx(),getCory(),getCorx()+getWidth(),getCory()+getHeight());
        canvas.drawBitmap(getBmp(),src,dst,null);

        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(80);
        canvas.drawText(String.format("posX: %d\tposY: %d",getPosX(),getPosY()),(float)(getWidth()*0.8),(float)(getHeight()*1.2),paint);
        canvas.drawText(String.format("corX: %d\tcorY: %d",getCorx(),getCory()),(float)(getWidth()*0.8),(float)(getHeight()*1.7),paint);
    }

    public boolean isCollition(float x2,float y2){
        return x2>getCorx() && x2<getCorx()+getWidth() && y2>getCory() && y2<getCory()+getHeight();
    }

    //Devuelvo la posición donde quiero que se mueva el personaje al hacer touch desde GameView
    public void touch(int x, int y, boolean pintar){
        setPosX(x);
        setPosY(y);
        setPintar(pintar);
    }


}
