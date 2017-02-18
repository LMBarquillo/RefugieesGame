package es.riberadeltajo.refugiadosgame.ruta1.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.List;

/**
 * Created by alumno on 18/01/2017.
 */

public class Ticket {
    private final int[] DIRECCION={0};
    private final int BMP_COLUMS=1;
    private final int BMP_ROWS=1;
    private int width;
    private int height;
    private float corx;
    private float cory;
    private Bitmap bmp;
    private int life;
    private List<Ticket> billetes;
    private GameView gameView;
    private int xSpeed,ySpeed;
    private int currentFrame;

    public Ticket(List<Ticket> temps, GameView gameview, Bitmap bmp){
        setWidth(bmp.getWidth()/BMP_COLUMS);
        setHeight(bmp.getHeight()/BMP_ROWS);
        life=100;
        setBilletes(temps);
        setBmp(bmp);
        setGameView(gameview);
        setxSpeed(5);
        setySpeed(5);
        setCurrentFrame(0);
        setCorx((int) (Math.random()*(gameView.getWidth()-(2*getWidth()+(gameview.getWidth()*0.1))))+(int)(gameview.getWidth()*0.1));
        setCory((int) (Math.random()*(gameView.getHeight()-(2*getHeight()+(gameview.getHeight()*0.1))))+(int)(gameview.getHeight()*0.1));
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

    public List<Ticket> getBilletes() {
        return billetes;
    }

    public void setBilletes(List<Ticket> billetes) {
        this.billetes = billetes;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    private void update(){
        setCurrentFrame(++currentFrame % BMP_COLUMS);
    }

    public void draw(Canvas canvas){
        update();
        if(--life<1){
            setCorx((int) (Math.random()*(getGameView().getWidth()-getWidth())));
            setCory((int) (Math.random()*(getGameView().getHeight()-getHeight())));
            life=100;
        }
        else{
            int srcx=getCurrentFrame()*getWidth();
            int srcy=getDireccion()*getHeight();
            Rect src=new Rect(srcx,srcy,srcx+getWidth(),srcy+getHeight());
            Rect dst=new Rect((int)getCorx(),(int)getCory(),(int)getCorx()+getWidth(),(int)getCory()+getHeight());
            canvas.drawBitmap(getBmp(),src,dst,null);
        }
    }

    //Método que devuelve true si el personaje colisiona con el ticket
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
