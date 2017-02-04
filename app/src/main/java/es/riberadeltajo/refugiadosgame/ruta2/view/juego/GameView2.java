package es.riberadeltajo.refugiadosgame.ruta2.view.juego;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

import es.riberadeltajo.refugiadosgame.R;


public class GameView2 extends SurfaceView {


    private final int TIEMPO_MAX = 30;
    private Bitmap player;
    private SurfaceHolder holder;
    private GameLoopThread2 loop;
    private int puntuacion;
    private Activity actividad;
    private long crono;
    private long inicio;
    private Jugador jugador;
    private ArrayList<Boton> botones;
    private boolean enMarcha;
    private ScrollingBackground fondo;


    public GameView2(Context context) {
        super(context);
        loop = new GameLoopThread2(this);
        puntuacion = 0;
        actividad = (Activity) context;
        this.inicio = System.currentTimeMillis();
        botones = new ArrayList<Boton>();




        holder = getHolder();

        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                crear();
                loop.setRunning(true);
                loop.start();



            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                boolean volver = true;
                loop.setRunning(false);
                while (volver) {
                    try {
                        loop.join();
                        volver = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });


    }

    public ScrollingBackground getFondo() {
        return fondo;
    }

    public void setFondo(ScrollingBackground fondo) {
        this.fondo = fondo;
    }

    public Activity getActividad() {
        return actividad;
    }

    public void setActividad(Activity actividad) {
        this.actividad = actividad;
    }

    private void sumarPuntos(int puntos) {
        puntuacion = puntuacion + puntos;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }



    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        long actual;
        float trans;
        paint.setColor(Color.RED);
        paint.setTextSize(60);
        canvas.drawText(String.valueOf(getFondo().getTopealto()), 300, 300, paint);
        getFondo().onDraw(canvas);
        for (Boton p : botones) {
            p.onDraw(canvas);
        }

            jugador.onDraw(canvas);

        // canvas.drawText(String.valueOf(jugador.getWidth()+" "+jugador.getHeight()), 300, 300, paint);


    }

    private void finalizar() {
        loop.setRunning(false);

        fin();

    }

    public void fin() {
        actividad.finish();

    }

    private String pasarSeg(float time) {
        String cad = "";
        String txt = String.valueOf(time);
        int pos = 0;
        while (pos < txt.length()) {
            if (txt.charAt(pos) == '.' || txt.charAt(pos) == ',') {
                if (cad.length() > 1)
                    return cad;
                else return String.format("0%s", cad);
            } else {
                cad = String.format("%s%c", cad, txt.charAt(pos));
                pos++;
            }
        }
        return "";
    }

    public void crear() {
        Bitmap imagen = BitmapFactory.decodeResource(getResources(), R.drawable.milan_personajeuno);
        Bitmap redimension = Bitmap.createScaledBitmap(imagen, (getWidth()), (getHeight()), false);
        int alto = redimension.getHeight() / 4;
        int ancho = redimension.getWidth() / 4;
        setJugador(new Jugador(this, redimension, getHeight() - alto*2, (int) ((getWidth() * 0.5) - (ancho * 0.5))));
        getJugador().setEnMarcha(false);
        Bitmap bot = BitmapFactory.decodeResource(getResources(), R.drawable.milan_flecha_delante);
        double alt = getHeight() * 0.10;
        double anch = getWidth() * 0.20;
        Boton boton = new Boton( bot, Boton.ACTION_AVANCE, (int) (getWidth() - anch), (int) (getHeight() - alt), (int) alt, (int) anch);
        botones.add(boton);
        bot = BitmapFactory.decodeResource(getResources(), R.drawable.milan_flecha_atras);
        boton = new Boton( bot, Boton.ACTION_ATRAS, 0, (int) (getHeight() - alt), (int) alt, (int) anch);
        botones.add(boton);
        bot = BitmapFactory.decodeResource(getResources(), R.drawable.milan_flecha_arriba);
        boton = new Boton(bot, Boton.ACTION_UP, (int) ((getWidth() * 0.5) - (anch * 0.5)), (int) (getHeight() - alt), (int) alt, (int) anch);
        botones.add(boton);
        Bitmap fon=BitmapFactory.decodeResource(getResources(), R.drawable.milan_fondo);
      //  int anchoactual=fon.getWidth();
       // int altoactual=fon.getHeight();
       // int altonuevo=getHeight();
        int anchonuevo=(fon.getHeight()*getWidth())/getHeight();


        setFondo(new ScrollingBackground(fon, anchonuevo, fon.getHeight(), getJugador(), getWidth(), getHeight()));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            synchronized ((getHolder())) {
                for (Boton p:botones){
                    if (p.isCollition(event.getX(), event.getY())){
                        if(p.getAccion().equals(Boton.ACTION_UP)){
                            getJugador().saltar();

                        }
                        else if(p.getAccion().equals(Boton.ACTION_AVANCE)){
                            getJugador().avanzar();
                            getFondo().avanzarX();



                        }
                        else if(p.getAccion().equals(Boton.ACTION_ATRAS)){
                            getJugador().atras();
                            getFondo().retrocederX();


                        }
                        break;
                    }

                }


            }


        }if(event.getAction() == MotionEvent.ACTION_UP){
            getJugador().parar();
        }

        return true;
    }

}






