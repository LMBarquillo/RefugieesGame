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
    private ArrayList<Objetos> objetos;
    private GameLoop loop;
    private boolean pasaObjeto;



    public GameView(Context context) {
        super(context);
        holder=getHolder();
        objetos=new ArrayList<Objetos>();
        setPasaObjeto(false);
        loop=new GameLoop(this);
        holder.addCallback(new SurfaceHolder.Callback() {

            @SuppressLint("WrongCall")
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                cargarObjetos();
                getLoop().setRunning(true);
                getLoop().start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                getLoop().setRunning(false);
            }
        });

        setFondo(BitmapFactory.decodeResource(getResources(), R.drawable.fondoteheran));

    }

    private void cargarObjetos(){
        objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.guitarra),(int)(Math.random()*20)+10,false));
        objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.pizza),(int)(Math.random()*20)+10,true));
    }


    public void draw(Canvas canvas){
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(60);
        //canvas.drawColor(Color.WHITE);      //Dibuja Fondo Blanco
        canvas.drawBitmap(Bitmap.createScaledBitmap(fondo,getWidth(),getHeight(),false),0,0,null);      //Dibuja imagen fondo
        if(!isPasaObjeto()){
            for(int i=0;i<objetos.size();i++){      //Dibuja los objetos
                objetos.get(i).draw(canvas);
            }
            for(int i=0;i<objetos.size();i++){
                if(objetos.get(i).finalPantalla()){       //Si el objeto llega al final de pantalla lo destruye
                    if(objetos.get(i).isCoger()){
                        setPasaObjeto(true);            //COMPRUEBA QUE NO SEA UN OBJETO DE LOS QUE HAY QUE COJER, SINO GAME OVER
                    }
                    objetos.remove(i);
                }
            }
        }
        else{
            canvas.drawText(String.format("GAME OVER"),(float)(getWidth()*0.4),(float)(getHeight()*0.49),paint);
            finalizar();
        }

    }


    public GameLoop getLoop() {
        return loop;
    }

    public void setLoop(GameLoop loop) {
        this.loop = loop;
    }

    //@Override
   // public SurfaceHolder getHolder() {
   //     return holder;
    //}

    public void finalizar(){
        loop.setRunning(false);

        try{
            Thread.sleep(1500);
        }
        catch(InterruptedException ie){

        }
        //actividad.fin();
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

    public boolean isPasaObjeto() {
        return pasaObjeto;
    }

    public void setPasaObjeto(boolean pasaObjeto) {
        this.pasaObjeto = pasaObjeto;
    }
}
