package es.riberadeltajo.refugiadosgame.ruta2.view.juego;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by lagar on 11/02/2017.
 */

public class Bichos {

    private final int VELOCIDAD=20;
    private final int  BMP_COLUMNS=3;
    private final int BMP_ROWS=2;
    private final int RANGO_MOVIMIENTO=500;
    private final int[] DIRECCION={1,0};
    private int corx, cory;
    private Bitmap bmp; //Imagen de las figuritas
    private int currentFrame; //Figura que ha aparecido última
    private int width; //Ancho del muñeco
    private int height; //Alto del muñeco
    private GameView2 gameView2;
    private int xSpeed;
    private int xInicio;
    private int xMaximo;
    private boolean delante;

    public Bichos(int corx, int cory, Bitmap bmp, GameView2 gameView2) {
        setGameView2(gameView2);
        setCorx(corx);
        setCory(cory);
        setxInicio(1);
        setBmp(bmp);
        setWidth(bmp.getWidth()/BMP_COLUMNS);
        setHeight(bmp.getHeight()/BMP_ROWS);
        setCurrentFrame(0);
        setxSpeed(VELOCIDAD);
        setxMaximo(RANGO_MOVIMIENTO);
        setDelante(true);
        setxSpeed(VELOCIDAD);
    }

    public boolean isDelante() {
        return delante;
    }

    public void setDelante(boolean delante) {
        this.delante = delante;
    }

    public int getxMaximo() {
        return xMaximo;
    }

    public void setxMaximo(int xMaximo) {
        this.xMaximo = xMaximo;
    }

    public int getxInicio() {
        return xInicio;
    }

    public void setxInicio(int xInicio) {
        this.xInicio = xInicio;
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
    public void update(){
        if(getxInicio()>=RANGO_MOVIMIENTO){
            xSpeed=-xSpeed; //Se le cambia la dirección
            setDelante(false);
        }
        else if(getxInicio()<=0){
            xSpeed=-xSpeed;
            setDelante(true);

        }

            setxInicio(getxInicio()+xSpeed);//Se le incrementa la dirección


        currentFrame=++currentFrame%BMP_COLUMNS;
    }
    private int getDirection(){
        if (isDelante())
            return DIRECCION[0];
        else
            return DIRECCION[1];

    }
    public void onDraw(Canvas canvas){
        update();
        int srcx = getCurrentFrame() * getWidth();
        int srcy=getDirection()*getHeight();
        Rect src=new Rect(srcx, srcy, srcx+getWidth(), srcy+getHeight());
        Rect dst=new Rect(getCorx()+getxInicio()+getxSpeed(),getCory(), getCorx()+getxInicio()+getWidth(), getCory()+getHeight());
        canvas.drawBitmap(bmp, src, dst, null);
    }

}
