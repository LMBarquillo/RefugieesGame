package es.riberadeltajo.refugiadosgame.ruta5.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Profesor on 09/02/2017.
 */

public class Player {

    private final int[] DIRECCION={0,1,2,3};
    private final int BMP_COLUMNS=4;
    private final int BMP_ROWS=4;
    private int corx,cory;
    private int xSpeed;
    private GameView gameView;
    private Bitmap bmp;
    private int currentFrame;
    private int width;
    private int height;
    private boolean mover;
    private int posx;

    public Player(GameView gameView, Bitmap bmp){
        setBmp(bmp);
        setWidth(bmp.getWidth()/BMP_COLUMNS);
        setHeight(bmp.getHeight()/BMP_ROWS);
        setGameView(gameView);
        currentFrame=0;
        setxSpeed(60);
        setCorx((int) (getGameView().getWidth()*0.45));
        setCory((int) (getGameView().getHeight()-(getHeight()*1.2)));
        setMover(false);
    }

    private int getDireccion(){
        int direccion=3;
        if(getPosx()<getCorx()){
            direccion=1;
        }
        else if(getPosx()>getCorx()){
            direccion=2;
        }
        else if(getPosx()==getCorx()){
            direccion=3;
        }
        return DIRECCION[direccion];
    }

    private void update(){
        if(getCorx()>=getGameView().getWidth()-getWidth()-getxSpeed() || getCorx()+getxSpeed()<=0){
            if(getCorx()<getPosx()){
                setCorx(getCorx()+getxSpeed());
                if(getCorx()>=getGameView().getWidth()-getWidth()-getxSpeed()){
                    setCorx(getGameView().getWidth()-getWidth()-getxSpeed());
                    setPosx(getCorx()+getxSpeed());
                }
            }
            else if(getCorx()>getPosx()){
                setCorx(getCorx()-getxSpeed());
                if(getCorx()-getPosx()<60){
                    setCorx(getPosx());
                }
            }
            else if(getCorx()==getPosx()){
                setCorx(getPosx());
            }
        }
        else{
            if(getCorx()<getPosx()){
                setCorx(getCorx()+getxSpeed());
                if(getPosx()-getCorx()<50){
                    setCorx(getPosx());
                }
            }
            else if(getCorx()>getPosx()){
                setCorx(getCorx()-getxSpeed());
                if(getCorx()-getPosx()<50){
                    setCorx(getPosx());
                }
            }
            else if(getCorx()==getPosx()){
                setCorx(getPosx());
            }
        }
        if(getCorx()==getPosx()){
            setMover(false);
        }
        currentFrame=++currentFrame%BMP_COLUMNS;
    }

    public void draw(Canvas canvas){
        if(isMover()) {
            update();
        }
        int srcx=currentFrame*getWidth();
        int srcy=getDireccion()*getHeight();
        Rect src=new Rect(srcx,srcy,srcx+getWidth(),srcy+getHeight());
        Rect dst=new Rect(getCorx(),getCory(),getCorx()+getWidth(),getCory()+getHeight());
        canvas.drawBitmap(getBmp(),src,dst,null);
    }

    public void touch(int cx,boolean mov){
        setPosx((int) (cx-(getWidth()*0.5)));
        setMover(mov);
    }


    public boolean isMover() {
        return mover;
    }

    public void setMover(boolean mover) {
        this.mover = mover;
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

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }
}
