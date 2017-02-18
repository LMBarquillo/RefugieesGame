package es.riberadeltajo.refugiadosgame.ruta1.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Created by alumno on 18/01/2017.
 */

public class Sprite {
    private final int[] DIRECCION={4,5,3,1,0,2,7,6};
    private final int BMP_COLUMS=13;
    private final int BMP_ROWS=8;
    private float corx,cory;
    private float xSpeed,ySpeed;
    private GameView gameView;
    private Bitmap bmp;
    private int currentFrame;
    private int width;
    private int height;
    private int vida;
    private float posX,posY;
    private boolean pintar=false;
    private int direccion;

    public Sprite(GameView gameView, Bitmap bmp, int vida){
        setWidth(bmp.getWidth()/BMP_COLUMS);
        setHeight(bmp.getHeight()/BMP_ROWS);
        setGameView(gameView);
        setBmp(bmp);
        setCurrentFrame(0);
        setxSpeed(0);
        setySpeed(0);
        setPosX(0);
        setPosY(0);
        setCorx((float) (Math.random()*(gameView.getWidth()-(2*getWidth()+(gameView.getWidth()*0.1))))+(float) (gameView.getWidth()*0.1));
        setCory((float) (Math.random()*(gameView.getHeight()-(2*getHeight()+(gameView.getHeight()*0.1))))+(float) (gameView.getHeight()*0.1));
        setVida(vida);
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

    public float getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    public float getySpeed() {
        return ySpeed;
    }

    public void setySpeed(float ySpeed) {
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

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
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

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    //Método para que el Sprite mire en la dirección correcta
    //Meto un rango de 0 a 10 para que se muestre más a menudo el sprite hacia X e Y ya que al haber float tendrían que coincidir hasta los decimales
    private int getDireccion(){
        if(((getPosX()-getCorx()>0 && (getPosX()-getCorx()<11)) || (getCorx()-getPosX()>0 && (getCorx()-getPosX()<11))) && (getPosY()>getCory())){
            direccion=4;
        }
        else if(getPosX()>getCorx() && (getPosY()>getCory())){
            direccion=5;
        }
        else if(((getPosY()-getCory()>0 && (getPosY()-getCory()<11)) || (getCory()-getPosY()>0 && (getCory()-getPosY()<11))) && (getPosX()<getCorx())){
            direccion=2;
        }
        else if(getPosX()<getCorx() && (getPosY()>getCory())){
            direccion=3;
        }
        else if(((getPosX()-getCorx()>0 && (getPosX()-getCorx()<11)) || (getCorx()-getPosX()>0 && (getCorx()-getPosX()<11))) && (getPosY()<getCory())){
            direccion=0;
        }
        else if(getPosX()<getCorx() && (getPosY()<getCory())){
            direccion=1;
        }
        else if(((getPosY()-getCory()>0 && (getPosY()-getCory()<11)) || (getCory()-getPosY()>0 && (getCory()-getPosY()<11))) && (getPosX()>getCorx())){
            direccion=6;
        }
        else if(getPosX()>getCorx() && (getPosY()<getCory())){
            direccion=7;
        }
        return DIRECCION[direccion];
    }

    //Movimiento del sprite
    private void update(){
        if (getCorx() >= getGameView().getWidth() - getWidth() - getxSpeed() || getCorx() - getxSpeed() - (getWidth()*0.5) <= 0) {
            if (getCorx() < getPosX()) {
                setCorx(getCorx() + getxSpeed());
                if(getCorx()>=getGameView().getWidth()-getWidth()-getxSpeed()){
                    setCorx(getGameView().getWidth()-getWidth()-getxSpeed());
                    setPosX(getCorx()+getxSpeed());
                }
            }else if (getCorx() > getPosX()){
                setCorx(getCorx() - getxSpeed());
                if(getCorx()-getxSpeed()-(getWidth()*0.5)<=0){
                    setCorx((float)(getWidth()*0.25));
                    setPosX(getCorx());
                }
                else if(getCorx()-getPosX()<0.2){
                    setCorx(getPosX());
                }
            } else if (getCorx() == getPosX()) {
                setCorx(getPosX());
            }
        } else {
            if (getCorx() < getPosX()) {
                setCorx(getCorx() + getxSpeed());
                if(getPosX()-getCorx()<0.2){
                    setCorx(getPosX());
                }
            } else if (getCorx() > getPosX()) {
                setCorx(getCorx() - getxSpeed());
                if(getCorx()-getPosX()<0.2){
                    setCorx(getPosX());
                }
            } else if (getCorx() == getPosX()) {
                setCorx(getPosX());
            }
        }
        if (getCory() >= getGameView().getHeight() - getHeight() - getySpeed() || getCory() - getySpeed() - (getHeight()*0.5) <= 0) {
            if (getCory() < getPosY()) {
                setCory(getCory() + getySpeed());
                if(getCory()>=getGameView().getHeight()-getHeight()-getySpeed()){
                    setCory(getGameView().getHeight()-getHeight()-getySpeed());
                    setPosY(getCory()+getySpeed());
                }
            } else if (getCory() > getPosY()) {
                setCory(getCory() - getySpeed());
                if(getCory()-getySpeed()-(getHeight())<=(float)(getGameView().getHeight()*0.3)){
                    setCory((float)(getHeight()*0.65));
                    setPosY((float)(getGameView().getHeight()*0.1)-getCory());
                }
                if(getCory()-getPosY()<0.2){
                    setCory(getPosY());
                }
            } else if (getCory() == getPosY()) {
                setCory(getPosY());
            }
        } else {
            if (getCory() < getPosY()) {
                setCory(getCory() + getySpeed());
                if(getPosY()-getCory()<0.2){
                    setCory(getPosY());
                }
            } else if (getCory() > getPosY()) {
                setCory(getCory() - getySpeed());
                if(getCory()-getPosY()<0.2){
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

    //Calculo el desplazamiento en diagonal inversamente proporcional
    private void calcularDiagonal(){
        float trayectoX,trayectoY,velX,velY;

        if(getPosX()>getCorx()) {
            trayectoX = getPosX() - getCorx();
        }else{
            trayectoX = getCorx() - getPosX();
        }
        if(getPosY()>getCory()) {
            trayectoY = getPosY() - getCory();
        }else{
            trayectoY = getCory() - getPosY();
        }

        if(trayectoX<trayectoY){
            if(trayectoY>0) {//Evito divisiones entre 0
                velX = (25 * trayectoX) / trayectoY;
            }else{
                velX=0;
            }
            velY=25;
        }
        else{
            velX=25;
            if(trayectoX>0) {//Evito divisiones entre 0
                velY = (25 * trayectoY) / trayectoX;
            }
            else{
                velY=0;
            }
        }
        setxSpeed(velX);
        setySpeed(velY);
    }

    //Dibujo el rectángulo y actualizo si el touch es true o no lo hago si es false
    public void draw(Canvas canvas){
        calcularDiagonal();
        getDireccion();
        if(isPintar()) {
            update();
        }
        int srcx=getCurrentFrame()*getWidth();
        int srcy=getDireccion()*getHeight();
        Rect src=new Rect(srcx,srcy,srcx+getWidth(),srcy+getHeight());
        RectF dst=new RectF(getCorx(),getCory(),getCorx()+getWidth(),getCory()+getHeight());
        canvas.drawBitmap(getBmp(),src,dst,null);

        //Temporal para ver la velocidad y la pos en pantalla
        /*Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(80);
        canvas.drawText(String.format("posX: %.2f\tposY: %.2f",getPosX(),getPosY()),(float)(getWidth()*0.8),(float)(getHeight()*1.2),paint);
        canvas.drawText(String.format("corX: %.2f\tcorY: %.2f",getCorx(),getCory()),(float)(getWidth()*0.8),(float)(getHeight()*1.7),paint);
        canvas.drawText(String.format("xSpeed: %.2f\tySpeed: %.2f",getxSpeed(),getySpeed()),(float)(getWidth()*0.5),(float)(getHeight()*2.2),paint);
        canvas.drawText(String.format("coins: %d\t",getGameView().getMonedas().size()),(float)(getWidth()*0.8),(float)(getHeight()*2.7),paint);*/
    }

    //Devuelvo la posición donde quiero que se mueva el personaje al hacer touch desde GameView
    public void touch(float x, float y, boolean pintar){
        setPosX(x-(float) (getWidth()*0.5));
        setPosY(y-(float) (getHeight()*0.5));
        setPintar(pintar);
    }


}
