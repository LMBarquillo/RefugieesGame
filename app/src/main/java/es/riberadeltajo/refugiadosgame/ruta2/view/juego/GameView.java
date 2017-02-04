package es.riberadeltajo.refugiadosgame.ruta2.view.juego;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;


import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.common.view.MainActivity;


public class GameView extends SurfaceView {

    private int requescode=1;


    private SurfaceHolder holder;
    private GameLoopThread loop;
    private Boton boton;
    private ArrayList<Boton> botones;
    private Bitmap fondo;
    private milan_juego actividad;


    public GameView(Context context) {
        super(context);
        setBotones(new ArrayList<Boton>());
        setLoop(new GameLoopThread(this));
        setFondo(BitmapFactory.decodeResource(getResources(), R.drawable.milan_primerfondo));
        setActividad((milan_juego)context);


        holder=getHolder();

        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                createBotones();
                loop.setRunning(true);
                loop.start();





            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                boolean volver=true;
                loop.setRunning(false);
                while (volver){
                    try {
                        loop.join();
                        volver=false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });


    }

    public Activity getActividad() {
        return actividad;
    }

    public void setActividad(milan_juego actividad) {
        this.actividad = actividad;
    }

    public Boton getBoton() {
        return boton;
    }

    public void setBoton(Boton boton) {
        this.boton = boton;
    }

    public ArrayList<Boton> getBotones() {
        return botones;
    }

    public void setBotones(ArrayList<Boton> botones) {
        this.botones = botones;
    }

    public Bitmap getFondo() {
        return fondo;
    }

    public void setFondo(Bitmap fondo) {
        this.fondo = fondo;
    }

    public GameLoopThread getLoop() {
        return loop;
    }

    public void setLoop(GameLoopThread loop) {
        this.loop = loop;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()== MotionEvent.ACTION_DOWN){
            synchronized ((getHolder())){
                for (Boton p:botones){
                    if (p.isCollition(event.getX(), event.getY())){
                        if(p.getAccion().equals(Boton.ACTION_HELP)){
                            ayuda();
                        }
                        else if(p.getAccion().equals(Boton.ACTION_EXIT)){
                            salir();
                        }
                        else if(p.getAccion().equals(Boton.ACTION_PLAY)){
                            jugar();
                        }
                        break;
                    }
                }
            }
        }


        return true;
    }
    public void ayuda(){

    }
    public void salir(){
        Intent i=new Intent();
        i.putExtra("resultado", false);
        actividad.setResult(Activity.RESULT_OK, i);
        actividad.finish();

    }
    public void jugar(){

        Intent i=new Intent(getActividad(),milan_juego2.class);
        getActividad().startActivityForResult(i, requescode);



    }




    public void draw(Canvas canvas){

        Bitmap nuevoFondo= Bitmap.createScaledBitmap(getFondo(), getWidth(), getHeight(), false);


        canvas.drawBitmap(nuevoFondo, 0, 0, null);



            for (Boton p : getBotones())
                p.onDraw(canvas);








    }



    public void createBotones(){
        Bitmap imagen=BitmapFactory.decodeResource(getResources(), R.drawable.milan_button_play);
        boton=new Boton(imagen, Boton.ACTION_PLAY, (int)(getWidth()*0.20),(int) (getHeight()*0.2),(int)(getHeight()*0.15),(int) (getWidth()*0.6) );
        botones.add(boton);
        imagen=BitmapFactory.decodeResource(getResources(), R.drawable.milan_button_help);
        boton=new Boton(imagen, Boton.ACTION_HELP, (int)(getWidth()*0.20),(int) (getHeight()*0.5), (int)(getHeight()*0.15),(int) (getWidth()*0.6));
        botones.add(boton);
        imagen=BitmapFactory.decodeResource(getResources(), R.drawable.milan_button_exit);
        boton=new Boton( imagen, Boton.ACTION_EXIT, (int)(getWidth()*0.20),(int) (getHeight()*0.8), (int)(getHeight()*0.15),(int) (getWidth()*0.6));
        botones.add(boton);



    }

}
