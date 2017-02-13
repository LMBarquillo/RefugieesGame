package es.riberadeltajo.refugiadosgame.ruta1.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by alumno on 18/01/2017.
 */

public class Sprite {
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
    private int vida;
    private int posX,posY;
    private boolean pintar=false;
    private int direccion;

    public Sprite(GameView gameView, Bitmap bmp, int vida){
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
        setVida(vida);
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

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
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

    //Movimiento del sprite
    private void update(){
        if (getCorx() >= getGameView().getWidth() - getWidth() - getxSpeed() || getCorx() + getxSpeed() <= 0) {
            if (getCorx() < getPosX()) {
                setCorx(getCorx() + getxSpeed());
                if(getCorx()>=getGameView().getWidth()-getWidth()-getxSpeed()){
                    setCorx(getGameView().getWidth()-getWidth()-getxSpeed());
                    setPosX(getCorx()+getxSpeed());
                }
            }else if (getCorx() > getPosX()){
                setCorx(getCorx() - getxSpeed());
                if(getCorx()-getPosX()<21){
                    setCorx(getPosX());
                }
            } else if (getCorx() == getPosX()) {
                setCorx(getPosX());
            }
        } else {
            if (getCorx() < getPosX()) {
                setCorx(getCorx() + getxSpeed());
                if(getPosX()-getCorx()<21){
                    setCorx(getPosX());
                }
            } else if (getCorx() > getPosX()) {
                setCorx(getCorx() - getxSpeed());
                if(getCorx()-getPosX()<21){
                    setCorx(getPosX());
                }
            } else if (getCorx() == getPosX()) {
                setCorx(getPosX());
            }
        }
        if (getCory() >= getGameView().getHeight() - getHeight() - getySpeed() || getCory() + getySpeed() <= 0) {
            if (getCory() < getPosY()) {
                setCory(getCory() + getySpeed());
                if(getCory()>=getGameView().getHeight()-getHeight()-getySpeed()){
                    setCory(getGameView().getHeight()-getHeight()-getySpeed());
                    setPosY(getCory()+getySpeed());
                }
            } else if (getCory() > getPosY()) {
                setCory(getCory() - getySpeed());
                if(getCory()-getPosY()<21){
                    setCory(getPosY());
                }
            } else if (getCory() == getPosY()) {
                setCory(getPosY());
            }
        } else {
            if (getCory() < getPosY()) {
                setCory(getCory() + getySpeed());
                if(getPosY()-getCory()<21){
                    setCory(getPosY());
                }
            } else if (getCory() > getPosY()) {
                setCory(getCory() - getySpeed());
                if(getCory()-getPosY()<21){
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

    //Calculo la diagonal para un desplazamiento libre sin movimientos cuadriculares (sigue siendo por ser Integer)
    private void calcularDiagonal(){
        int trayectoX,trayectoY,velX,velY,divisor;
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

        divisor=mcd(trayectoX,trayectoY);
        if(divisor>0){
            velX=Math.round((float)trayectoX/divisor);
            velY=Math.round((float)trayectoY/divisor);
        }
        else{
            velX=trayectoX;
            velY=trayectoY;
        }
        velRed(velX,velY);
    }

    //Reduzco al mínimo proporcionalmente para que no haga saltos de posición
    public void velRed(int x, int y){
        if((x>1000 && x<1981) || (y>1000 && y<1981)){
            if(x>=198 || y>=198) {
                x = Math.round((float)x / 198);
                y = Math.round((float)y / 198);
            }
            else if(x<198){
                x = (x-198)/x;
                y = Math.round((float)y / 198);
            }
            else if(y<198){
                x = Math.round((float)x / 198);
                y = Math.round((float)y-198)/y;
            }
        }
        if((x>500 && x<1001) || (y>500 && y<1001)){
            if(x>=100 || y>=100) {
                x = Math.round((float)x / 100);
                y = Math.round((float)y / 100);
            }
            else if(x<100){
                x = Math.round((float)x-100)/x;
                y = Math.round((float)y / 100);
            }
            else if(y<100){
                x = Math.round((float)x / 100);
                y = Math.round((float)y-100)/y;
            }
        }
        if((x>200 && x<501) || (y>200 && y<501)){
            if(x>=50 || y>=50) {
                x = Math.round((float)x / 50);
                y = Math.round((float)y / 50);
            }
            else if(x<50){
                x = Math.round((float)x-50)/x;
                y = Math.round((float)y / 50);
            }
            else if(y<50){
                x = Math.round((float)x / 50);
                y = Math.round((float)y-50)/y;
            }
        }
        if((x>100 && x<201) || (y>100 && y<201)){
            if(x>=20 || y>=20) {
                x = Math.round((float)x / 20);
                y = Math.round((float)y / 20);
            }
            else if(x<20){
                x = Math.round((float)x-20)/x;
                y = Math.round((float)y / 20);
            }
            else if(y<20){
                x = Math.round((float)x / 20);
                y = Math.round((float)y-20)/y;
            }
        }
        if((x>50 && x<101) || (y>50 && y<101)){
            if(x>=10 || y>=10) {
                x = Math.round((float)x / 10);
                y = Math.round((float)y / 10);
            }
            else if(x<10){
                x = Math.round((float)x-10)/x;
                y = Math.round((float)y / 10);
            }
            else if(y<10){
                x = Math.round((float)x / 10);
                y = Math.round((float)y-10)/y;
            }
        }
        if((x>20 && x<51) || (y>20 && y<51)){
            if(x>=5 || y>=5) {
                x = Math.round((float)x / 5);
                y = Math.round((float)y / 5);
            }
            else if(x<5){
                x = Math.round((float)x-5)/x;
                y = Math.round((float)y / 5);
            }
            else if(y<5){
                x = Math.round((float)x / 5);
                y = Math.round((float)y-5)/y;
            }
        }
        if((x>10 && x<21) || (y>10 && y<21)){
            if(x>=2 || y>=2) {
                x = Math.round((float)x / 2);
                y = Math.round((float)y / 2);
            }
            else if(x<2){
                x = Math.round((float)x-2)/x;
                y = Math.round((float)y / 2);
            }
            else if(y<2){
                x = Math.round((float)x / 2);
                y = Math.round((float)y-2)/y;
            }
        }
        if(getCorx()==getPosX()) {
            setxSpeed(0);
            setySpeed(y + 19);
        }
        else if(getCory()==getPosY()){
            setxSpeed(x + 19);
            setySpeed(0);
        }
        else{
            setxSpeed(x+10);
            setySpeed(y+10);
        }
    }

    //Método que devuelve el máximo común divisor
    private int mcd(int x,int y){
            if(y==0) {
                return x;
            }else{
                return mcd(y, x % y);
        }
    }

    //Dibujo el recténgulo y actualizo si el touch es true o no lo hago si es false
    public void draw(Canvas canvas){
        getDireccion();
        if(isPintar()) {
            update();
            calcularDiagonal();
        }
        int srcx=getCurrentFrame()*getWidth();
        int srcy=getDireccion()*getHeight();
        Rect src=new Rect(srcx,srcy,srcx+getWidth(),srcy+getHeight());
        Rect dst=new Rect(getCorx(),getCory(),getCorx()+getWidth(),getCory()+getHeight());
        canvas.drawBitmap(getBmp(),src,dst,null);

        //Temporal para ver la velocidad y la pos en pantalla
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(80);
        canvas.drawText(String.format("posX: %d\tposY: %d",getPosX(),getPosY()),(float)(getWidth()*0.8),(float)(getHeight()*1.2),paint);
        canvas.drawText(String.format("corX: %d\tcorY: %d",getCorx(),getCory()),(float)(getWidth()*0.8),(float)(getHeight()*1.7),paint);
        canvas.drawText(String.format("xSpeed: %d\tySpeed: %d",getxSpeed(),getySpeed()),(float)(getWidth()*0.5),(float)(getHeight()*2.2),paint);
        canvas.drawText(String.format("coins: %d\t",getGameView().getMonedas().size()),(float)(getWidth()*0.8),(float)(getHeight()*2.7),paint);
    }

    //Devuelvo la posición donde quiero que se mueva el personaje al hacer touch desde GameView
    public void touch(int x, int y, boolean pintar){
        setPosX(x-(int)(getWidth()*0.5));
        setPosY(y-(int)(getHeight()*0.5));
        setPintar(pintar);
    }


}
