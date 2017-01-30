package es.riberadeltajo.refugiadosgame.ruta5.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

import es.riberadeltajo.refugiadosgame.R;

/**
 * Created by Profesor on 26/01/2017.
 */

public class GameView extends SurfaceView {

    private SurfaceHolder holder;
    private int corx,cory;
    private int ySpeed;
    private Bitmap fondo;
    private Bitmap cesta;       //O el objeto con el que chocan/recoje
    //private Bitmap explosion;
    //private Bitmap bomba;
    private ArrayList<Objetos> objetos;
    private GameLoop loop;


    public GameView(Context context) {
        super(context);
        holder=getHolder();
        objetos=new ArrayList<Objetos>();
        loop=new GameLoop(this);

        holder.addCallback(new SurfaceHolder.Callback() {

            @SuppressLint("WrongCall")
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                //crearObjetos();
                loop.setRunning(true);
                loop.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

        fondo= BitmapFactory.decodeResource(getResources(), R.drawable.fondoteheran);

    }


    public void draw(Canvas canvas){
        Paint paint=new Paint();
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(Bitmap.createScaledBitmap(fondo,getWidth(),getHeight(),false),0,0,null);
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

    //public Bitmap getExplosion() {
    //    return explosion;
    //}

    //public void setExplosion(Bitmap explosion) {
    //    this.explosion = explosion;
   // }
}
