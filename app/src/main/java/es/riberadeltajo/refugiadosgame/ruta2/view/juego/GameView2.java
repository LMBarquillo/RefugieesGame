package es.riberadeltajo.refugiadosgame.ruta2.view.juego;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;

import es.riberadeltajo.refugiadosgame.R;


public class GameView2 extends SurfaceView implements View.OnTouchListener {

    private final int MARGENES=20;
    private final int TIEMPO_MAX = 45;
    private final int NUM_MONEDAS=15;
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
    private Monedas moneda_arriba;
    private ArrayList<Bitmap> vidas;
    private boolean terminado;
    private boolean ganado;
    private boolean banderaLoop;
    private int coordenadas1[];
    private int coordenadas2[];




    public GameView2(Context context){
        super(context);
        loop = new GameLoopThread2(this);
        puntuacion = 0;
        actividad = (Activity) context;
        this.inicio = System.currentTimeMillis();
        botones = new ArrayList<Boton>();
        Bitmap mon=BitmapFactory.decodeResource(getResources(), R.drawable.milan_moneda);
        moneda_arriba=new Monedas(this,20,(int)(getHeight()*0.5),mon);
        vidas=new ArrayList<Bitmap>();
        terminado=false;
        ganado=false;
        banderaLoop=false;
        coordenadas1=new int[2];
        coordenadas2=new int[2];
        coordenadas1[0]=-1;
        coordenadas1[1]=-1;
        coordenadas2[0]=-1;
        coordenadas2[1]=-1;
        setOnTouchListener(this);









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

    public GameLoopThread2 getLoop() {
        return loop;
    }

    public void setLoop(GameLoopThread2 loop) {
        this.loop = loop;
    }

    public long getCrono() {
        return crono;
    }

    public void setCrono(long crono) {
        this.crono = crono;
    }

    public long getInicio() {
        return inicio;
    }

    public void setInicio(long inicio) {
        this.inicio = inicio;
    }

    public ArrayList<Boton> getBotones() {
        return botones;
    }

    public void setBotones(ArrayList<Boton> botones) {
        this.botones = botones;
    }

    public boolean isEnMarcha() {
        return enMarcha;
    }

    public void setEnMarcha(boolean enMarcha) {
        this.enMarcha = enMarcha;
    }

    public Monedas getMoneda_arriba() {
        return moneda_arriba;
    }

    public void setMoneda_arriba(Monedas moneda_arriba) {
        this.moneda_arriba = moneda_arriba;
    }

    public ArrayList<Bitmap> getVidas() {
        return vidas;
    }

    public void setVidas(ArrayList<Bitmap> vidas) {
        this.vidas = vidas;
    }

    public boolean isTerminado() {
        return terminado;
    }

    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }

    public boolean isGanado() {
        return ganado;
    }

    public void setGanado(boolean ganado) {
        this.ganado = ganado;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public boolean isBanderaLoop() {
        return banderaLoop;
    }

    public void setBanderaLoop(boolean banderaLoop) {
        this.banderaLoop = banderaLoop;
    }

    public void draw(Canvas canvas) {

        Paint paint = new Paint();
        long actual;
        float trans;
        paint.setColor(Color.WHITE);
        paint.setTextSize(80);
        canvas.drawText(String.valueOf(getFondo().getTopealto()), 300, 300, paint);
        getFondo().onDraw(canvas);
        canvas.drawRect(0,0,getWidth(), (int)(getHeight()*0.10), paint);
        paint.setColor(Color.RED);
        canvas.drawText(String.valueOf(getJugador().getMonedas()),MARGENES+moneda_arriba.getWidth(),(int)(getHeight()*0.05),paint);
        moneda_arriba.onDraw(canvas);
        if(!terminado) {
            for (Boton p : botones) {
                p.onDraw(canvas);
            }
            actual=System.currentTimeMillis();
            crono = (actual / 1000) - (inicio / 1000);



            jugador.onDraw(canvas);
        }
        for(int i=1;i<jugador.getVidas()+1;i++) {
            vidas.add(BitmapFactory.decodeResource(getResources(), R.drawable.milan_vidas));

            canvas.drawBitmap(vidas.get(i-1),(float)(MARGENES+moneda_arriba.getWidth()+MARGENES+(vidas.get(i-1).getWidth()*i)),0,null);

        }
        canvas.drawText((String.valueOf(pasarSeg(crono))), (float) (getWidth() * 0.8), (float) (getHeight() * 0.09), paint);



        if(terminado){
            paint.setARGB(150,0,0,0);
            canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),paint);
            paint.setColor(Color.RED);
            if(ganado){
                canvas.drawText(getResources().getString(R.string.milan_ganado),20,500,paint);
                finalizar();
            }
            else{
                canvas.drawText(getResources().getString(R.string.milan_perdido),20,500,paint);
                finalizar();

            }



        }
        comprobarJuego();



    }

    private void comprobarJuego() {
        if((jugador.getVidas()==0 && getJugador().getMonedas()<NUM_MONEDAS) || (getJugador().getMonedas()<NUM_MONEDAS && crono==TIEMPO_MAX)){
            terminado=true;
            ganado=false;


        }
        else if((jugador.getVidas()>0 && getJugador().getMonedas()==NUM_MONEDAS) || (getJugador().getMonedas()==NUM_MONEDAS && crono<TIEMPO_MAX)){
            terminado=true;
            ganado=true;


        }
        else{
            terminado=false;
            ganado=false;
        }
    }


    public void finalizar() {

        banderaLoop=true;
        loop.setRunning(false);
        fin();





    }

    public void fin() {



        Intent i=new Intent();
        i.putExtra("resultado", ganado);
        actividad.setResult(Activity.RESULT_OK, i);

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
        int anchonuevo=(fon.getHeight()*getWidth())/getHeight();
        setFondo(new ScrollingBackground(this,fon, anchonuevo, fon.getHeight(), getJugador(), getWidth(), getHeight()));


    }




    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action= event.getAction() & MotionEvent.ACTION_MASK;
        int pointerIndex=(event.getAction()&MotionEvent.ACTION_POINTER_INDEX_MASK)>>MotionEvent.ACTION_POINTER_INDEX_SHIFT;

        switch(action){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if(pointerIndex==0){
                    coordenadas1[0]=(int)event.getX(pointerIndex);
                    coordenadas1[1]=(int)event.getY(pointerIndex);
                }else{
                    coordenadas2[0]=(int)event.getX(pointerIndex);
                    coordenadas2[1]=(int)event.getY(pointerIndex);

                }

                    for (Boton p:getBotones()){
                        if (p.isCollition(event.getX(pointerIndex), event.getY(pointerIndex))){
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
                        }

                    }




                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if(pointerIndex==0){
                    coordenadas1[0]=-1;
                    coordenadas1[1]=-1;

                }else{
                    coordenadas2[0]=-1;
                    coordenadas2[1]=-1;

                }

                    for (Boton p:getBotones()){
                        if (p.isCollition(event.getX(pointerIndex), event.getY(pointerIndex))){
                            if(p.getAccion().equals(Boton.ACTION_UP)){


                            }
                            else if(p.getAccion().equals(Boton.ACTION_AVANCE)){
                                getJugador().parar();




                            }
                            else if(p.getAccion().equals(Boton.ACTION_ATRAS)){
                                getJugador().parar();



                            }

                        }

                    }





                break;





        }


        return true;

    }
}






