package es.riberadeltajo.refugiadosgame.ruta5.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Profesor on 26/01/2017.
 */

public class GameView extends SurfaceView {

    private SurfaceHolder holder;
    private int corx,cory;
    private int xSpeed,ySpeed;
    private Bitmap fondo;
    private Bitmap cesta;
    private Bitmap explosion;
    private Bitmap bomba;


    public GameView(Context context) {
        super(context);
        holder=getHolder();

        holder.addCallback(new SurfaceHolder.Callback() {

            @SuppressLint("WrongCall")
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });



    }

    @Override
    public SurfaceHolder getHolder() {
        return holder;
    }

    public void setHolder(SurfaceHolder holder) {
        this.holder = holder;
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

    public Bitmap getFondo() {
        return fondo;
    }

    public void setFondo(Bitmap fondo) {
        this.fondo = fondo;
    }

    public Bitmap getCesta() {
        return cesta;
    }

    public void setCesta(Bitmap cesta) {
        this.cesta = cesta;
    }

    public Bitmap getExplosion() {
        return explosion;
    }

    public void setExplosion(Bitmap explosion) {
        this.explosion = explosion;
    }
}
