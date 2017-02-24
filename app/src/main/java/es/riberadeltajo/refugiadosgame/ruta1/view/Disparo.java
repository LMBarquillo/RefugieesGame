package es.riberadeltajo.refugiadosgame.ruta1.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by jose on 13/02/2017.
 */

public class Disparo extends Sprite {
    private Bitmap bmp;
    private int puntos;
    
    public Disparo(GameView gameView, Bitmap bmp, int vida, float ran,int puntos) {
        super(gameView, bmp, vida);
        setPuntos(puntos);
        Random rn=new Random();
        this.bmp=bmp;
        setPintar(true);
        if(ran==1){
            setCorx(gameView.getWidth());
            setCory((int) (Math.random()*(gameView.getHeight()-(4*getHeight()+(gameView.getHeight()*0.1))))+(int)(gameView.getHeight()*0.1));
            setxSpeed(-6);
            setySpeed(0);
        }else if(ran==2){
            setCorx((int) (Math.random()*(gameView.getWidth()-(4*getWidth()+(gameView.getWidth()*0.1))))+(int)(gameView.getWidth()*0.1));
            setCory(0);
            setxSpeed(0);
            setySpeed(6);
        }else if(ran==3){
            setCorx(0);
            setCory((int) (Math.random()*(gameView.getHeight()-(4*getHeight()+(gameView.getHeight()*0.1))))+(int)(gameView.getHeight()*0.1));
            setxSpeed(6);
            setySpeed(0);
        }else if(ran==4){
            setCorx((int) (Math.random()*(gameView.getWidth()-(4*getWidth()+(gameView.getWidth()*0.1))))+(int)(gameView.getWidth()*0.1));
            setCory(gameView.getHeight());
            setxSpeed(0);
            setySpeed(-6);
        }

    }

    private void update(){
        float newposx=getCorx()+getxSpeed();
        float newposy=getCory()+getySpeed();

        setCorx(newposx);
        setCory(newposy);

    }

    public void draw(Canvas canvas){

        if(isPintar()) {
            update();

        }
        int corx=Math.round(getCorx());
        int cory=Math.round( getCory());

        Rect src=new Rect(0,0,bmp.getWidth(),bmp.getHeight());
        Rect dst=new Rect(corx,cory,corx+bmp.getWidth(),cory+bmp.getHeight());
        canvas.drawBitmap(getBmp(),src,dst,null);
    }

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

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

}
