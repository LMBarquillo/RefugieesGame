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
import java.util.Observable;
import java.util.Observer;

import es.riberadeltajo.refugiadosgame.R;

/**
 * Created by Profesor on 26/01/2017.
 */

public class GameView extends SurfaceView implements Observer {

    private Tehran principal;
    private SurfaceHolder holder;
    private int corx,cory;
    private int ySpeed;
    private Bitmap fondo;
    private Bitmap cesta;       //O el objeto con el que chocan/recoje
    private ArrayList<Objetos> objetos;
    private GameLoop loop;
    private boolean pasaObjeto;
    private Cronometro cronometro;
    private int segundos;



    public GameView(Context context) {
        super(context);
        principal=(Tehran)context;
        cronometro=new Cronometro();
        cronometro.addObserver(this);
        new Thread(cronometro).start();
        holder=getHolder();
        objetos=new ArrayList<Objetos>();
        setPasaObjeto(false);
        setSegundos(0);
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
        objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.guitarratehran),(int)(Math.random()*20)+10,false,(int)(Math.random()*5)+1));
        objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.pizzatehran),(int)(Math.random()*20)+10,false,(int)(Math.random()*6)+1));
        objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.hielotehran),(int)(Math.random()*20)+10,true,(int)(Math.random()*7)+2));
        objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.bebidatehran),(int)(Math.random()*20)+10,true,(int)(Math.random()*5)+5));
        objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.pajitatehran),(int)(Math.random()*20)+10,true,(int)(Math.random()*5)+6));
        objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.vasotehran),(int)(Math.random()*20)+10,true,(int)(Math.random()*5)+7));
    }


    public void draw(Canvas canvas){
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(70);
        //canvas.drawColor(Color.WHITE);      //Dibuja Fondo Blanco
        canvas.drawBitmap(Bitmap.createScaledBitmap(fondo,getWidth(),getHeight(),false),0,0,null);      //Dibuja imagen fondo
        canvas.drawText(String.format("%d",getSegundos()),(float)(getWidth()*0.05),(float)(getHeight()*0.05),paint);
        if(!isPasaObjeto() || objetos.size()==0){
            for(int i=0;i<objetos.size();i++){      //Dibuja los objetos
                if(objetos.get(i).getSegundo()<getSegundos()){          //Si el segundo de aparicion es menor, los sigue dibujando
                    objetos.get(i).draw(canvas);
                }
                else if(objetos.get(i).getSegundo()==getSegundos()){     //Si coinciden los segundos lo dibuja  //NO FUNCIONA
                    objetos.get(i).draw(canvas);
                }
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
            canvas.drawText(String.format("GAME OVER"),(float)(getWidth()*0.35),(float)(getHeight()*0.5),paint);
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
            Thread.sleep(2000);
        }
        catch(InterruptedException ie){

        }
        principal.fin();
    }

    @Override
    public void update(Observable o, Object arg) {
        setSegundos(cronometro.getSegundos());
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

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }
}
