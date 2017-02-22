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
        this.puntos=puntos;
        Random rn=new Random();
        this.bmp=bmp;
        setPintar(true);
        if(ran>1){
            setCorx((int) (Math.random()*(gameView.getWidth()-(2*getWidth()+(gameView.getWidth()*0.1))))+(int)(gameView.getWidth()*0.1));
            setCory(bmp.getHeight());
            setxSpeed(0);
            setySpeed(5);
        }else{
            setCorx(gameView.getWidth()-bmp.getWidth());
            setCory((int) (Math.random()*(gameView.getHeight()-(2*getHeight()+(gameView.getHeight()*0.1))))+(int)(gameView.getHeight()*0.1));
            setxSpeed(-5);
            setySpeed(0);
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
}
