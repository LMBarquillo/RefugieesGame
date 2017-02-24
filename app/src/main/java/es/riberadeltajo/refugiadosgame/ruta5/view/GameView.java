package es.riberadeltajo.refugiadosgame.ruta5.view;

import android.annotation.SuppressLint;
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
import java.util.Observable;
import java.util.Observer;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.common.models.PlayerStatus;

/**
 * Created by Profesor on 26/01/2017.
 */

public class GameView extends SurfaceView implements Observer {

    private Tehran principal;
    private SurfaceHolder holder;
    private int corx,cory;
    private int ySpeed;
    private Bitmap fondo;
    private Bitmap jugbmp;
    private Bitmap jabon1, jabon2, jabon3, jabon4;
    private Bitmap aspiradora, basura, escoba, fregona, limpiador;
    private boolean cojidoJabon1, cojidoJabon2, cojidoJabon3, cojidoJabon4;
    private boolean cojidoAspiradora, cojidoBasura, cojidoEscoba, cojidoFregona, cojidoLimpiador;
    private Player jugador;
    private ArrayList<Objetos> objetos;
    private GameLoop loop;
    private boolean pasaObjeto;
    private Cronometro cronometro;
    private int segundos;
    private int cojidos;
    private boolean fabrica;
    private MediaPlayer lost;
    private MediaPlayer win;
    private int contadorJabon1;
    private Tehran th;



    public GameView(Context context, boolean fab) {
        super(context);
        setTh((Tehran)context);
        principal=(Tehran)context;
        cronometro=new Cronometro();
        cronometro.addObserver(this);
        new Thread(cronometro).start();
        holder=getHolder();
        objetos=new ArrayList<Objetos>();
        setPasaObjeto(false);
        setFabrica(fab);
        setSegundos(-1);
        setCojidos(5);
        setCojidoJabon1(false);
        setCojidoJabon2(false);
        setCojidoJabon3(false);
        setCojidoJabon4(false);
        setLost(MediaPlayer.create(getContext(),R.raw.tehranlost));
        setWin(MediaPlayer.create(getContext(),R.raw.tehranwin));
        setContadorJabon1(2);
        loop=new GameLoop(this);
        jugbmp=BitmapFactory.decodeResource(getResources(), R.drawable.playertehran);
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
        setJabon1(BitmapFactory.decodeResource(getResources(), R.drawable.tehranjabon1));
        setJabon2(BitmapFactory.decodeResource(getResources(), R.drawable.tehranjabon2));
        setJabon3(BitmapFactory.decodeResource(getResources(), R.drawable.tehranjabon3));
        setJabon4(BitmapFactory.decodeResource(getResources(), R.drawable.tehranjabon4));
        setAspiradora(BitmapFactory.decodeResource(getResources(), R.drawable.aspiradoratehran));
        setBasura(BitmapFactory.decodeResource(getResources(), R.drawable.basuratehran));
        setEscoba(BitmapFactory.decodeResource(getResources(), R.drawable.escobatehran));
        setFregona(BitmapFactory.decodeResource(getResources(), R.drawable.fregonatehran));
        setLimpiador(BitmapFactory.decodeResource(getResources(), R.drawable.limpiadortehran));
    }

    private void cargarObjetos(){
        jugador=new Player(this,Bitmap.createScaledBitmap(jugbmp,(int)(jugbmp.getWidth()*1.7),(int)(jugbmp.getHeight()*1.5),false));
        if(isFabrica()){
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.guitarratehran),(int)(Math.random()*10)+10,false,(int)(Math.random()*15)+1));
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.pizzatehran),(int)(Math.random()*10)+10,false,(int)(Math.random()*5)+4));
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.tehranjabon1),(int)(Math.random()*10)+10,true,(int)(Math.random()*5)+6));
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.tehranjabon2),(int)(Math.random()*10)+10,true,(int)(Math.random()*5)+10));
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.monedatehran),(int)(Math.random()*10)+10,false,(int)(Math.random()*5)+14));
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.alfombratehran),(int)(Math.random()*10)+10,false,(int)(Math.random()*5)+18));
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.tehranjabon4),(int)(Math.random()*10)+10,true,(int)(Math.random()*5)+22));
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.saxofontehran),(int)(Math.random()*10)+10,false,(int)(Math.random()*5)+25));
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.tehranjabon1),(int)(Math.random()*10)+10,true,(int)(Math.random()*5)+27));
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.tehranjabon3),(int)(Math.random()*10)+10,true,(int)(Math.random()*5)+30));
        }
        else{
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.guitarratehran),(int)(Math.random()*10)+10,false,(int)(Math.random()*15)+1));
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.pizzatehran),(int)(Math.random()*10)+10,false,(int)(Math.random()*5)+4));
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.escobatehran),(int)(Math.random()*10)+10,true,(int)(Math.random()*5)+6));
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.fregonatehran),(int)(Math.random()*10)+10,true,(int)(Math.random()*5)+10));
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.monedatehran),(int)(Math.random()*10)+10,false,(int)(Math.random()*5)+14));
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.alfombratehran),(int)(Math.random()*10)+10,false,(int)(Math.random()*5)+18));
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.basuratehran),(int)(Math.random()*10)+10,true,(int)(Math.random()*5)+22));
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.saxofontehran),(int)(Math.random()*10)+10,false,(int)(Math.random()*5)+25));
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.limpiadortehran),(int)(Math.random()*10)+10,true,(int)(Math.random()*5)+27));
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.aspiradoratehran),(int)(Math.random()*10)+10,true,(int)(Math.random()*5)+30));
        }

    }


    public void draw(Canvas canvas){
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(70);
        //canvas.drawColor(Color.WHITE);      //Dibuja Fondo Blanco
        canvas.drawBitmap(Bitmap.createScaledBitmap(fondo,getWidth(),getHeight(),false),0,0,null);      //Dibuja imagen fondo
        canvas.drawText(String.format("%d",getSegundos()),(float)(getWidth()*0.05),(float)(getHeight()*0.05),paint);
        //canvas.drawText(String.format("Objetos por Coger: %d",getCojidos()),(float)(getWidth()*0.4),(float)(getHeight()*0.05),paint);
        jugador.draw(canvas);
        if(isFabrica()) {       //Dibuja Objetos a coger en la esquina derecha segun trabajo escogido
            if (!isCojidoJabon1()) {
                canvas.drawBitmap(Bitmap.createScaledBitmap(getJabon1(), (int) (getWidth() * 0.05), (int) (getHeight() * 0.05), false), (float) (getWidth() * 0.9), (float) (getHeight() * 0.07), null);
            }
            if (!isCojidoJabon2()) {
                canvas.drawBitmap(Bitmap.createScaledBitmap(getJabon2(), (int) (getWidth() * 0.05), (int) (getHeight() * 0.05), false), (float) (getWidth() * 0.9), (float) (getHeight() * 0.14), null);
            }
            if (!isCojidoJabon3()) {
                canvas.drawBitmap(Bitmap.createScaledBitmap(getJabon3(), (int) (getWidth() * 0.05), (int) (getHeight() * 0.05), false), (float) (getWidth() * 0.9), (float) (getHeight() * 0.21), null);
            }
            if (!isCojidoJabon4()) {
                canvas.drawBitmap(Bitmap.createScaledBitmap(getJabon4(), (int) (getWidth() * 0.05), (int) (getHeight() * 0.05), false), (float) (getWidth() * 0.9), (float) (getHeight() * 0.27), null);
            }
        }
        else {
            if (!isCojidoAspiradora()) {
                canvas.drawBitmap(Bitmap.createScaledBitmap(getAspiradora(), (int) (getWidth() * 0.05), (int) (getHeight() * 0.05), false), (float) (getWidth() * 0.9), (float) (getHeight() * 0.07), null);
            }
            if (!isCojidoBasura()) {
                canvas.drawBitmap(Bitmap.createScaledBitmap(getBasura(), (int) (getWidth() * 0.05), (int) (getHeight() * 0.05), false), (float) (getWidth() * 0.9), (float) (getHeight() * 0.14), null);
            }
            if (!isCojidoEscoba()) {
                canvas.drawBitmap(Bitmap.createScaledBitmap(getEscoba(), (int) (getWidth() * 0.05), (int) (getHeight() * 0.05), false), (float) (getWidth() * 0.9), (float) (getHeight() * 0.21), null);
            }
            if (!isCojidoFregona()) {
                canvas.drawBitmap(Bitmap.createScaledBitmap(getFregona(), (int) (getWidth() * 0.05), (int) (getHeight() * 0.05), false), (float) (getWidth() * 0.9), (float) (getHeight() * 0.27), null);
            }
            if (!isCojidoLimpiador()) {
                canvas.drawBitmap(Bitmap.createScaledBitmap(getLimpiador(), (int) (getWidth() * 0.05), (int) (getHeight() * 0.05), false), (float) (getWidth() * 0.9), (float) (getHeight() * 0.33), null);
            }
        }
        if(!isPasaObjeto() && objetos.size()>0 && getCojidos()>0){
            for(int i=0;i<objetos.size();i++){      //Dibuja los objetos
                if(objetos.get(i).getSegundo()<getSegundos()){          //Si el segundo de aparicion es menor, los sigue dibujando
                    objetos.get(i).draw(canvas);
                }
                else if(objetos.get(i).getSegundo()==getSegundos()){     //Si coinciden los segundos lo dibuja
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
                else if(objetos.get(i).choqueJugador(jugador)){     //COMPRUEBA SI EL OBJETO CHOCA CON EL JUGADOR
                    if(objetos.get(i).isCoger()){
                        setCojidos(getCojidos()-1);
                        if(objetos.get(i).getBmp().sameAs(getJabon1())){         //COMPRUEBA QUE OBJETO HA COJIDO PARA BORRARLO DE PANTALLA
                            setContadorJabon1(getContadorJabon1()-1);
                            if(getContadorJabon1()==0){
                                setCojidoJabon1(true);
                            }
                        }
                        else if(objetos.get(i).getBmp().sameAs(getJabon2())){
                            setCojidoJabon2(true);
                        }
                        else if(objetos.get(i).getBmp().sameAs(getJabon3())){
                            setCojidoJabon3(true);
                        }
                        else if(objetos.get(i).getBmp().sameAs(getJabon4())){
                            setCojidoJabon4(true);
                        }
                        else if(objetos.get(i).getBmp().sameAs(getAspiradora())){
                            setCojidoAspiradora(true);
                        }
                        else if(objetos.get(i).getBmp().sameAs(getBasura())){
                            setCojidoBasura(true);
                        }
                        else if(objetos.get(i).getBmp().sameAs(getEscoba())){
                            setCojidoEscoba(true);
                        }
                        else if(objetos.get(i).getBmp().sameAs(getFregona())){
                            setCojidoFregona(true);
                        }
                        else if(objetos.get(i).getBmp().sameAs(getLimpiador())){
                            setCojidoLimpiador(true);
                        }
                        objetos.remove(i);
                    }
                    else{
                        setPasaObjeto(true);
                    }

                }
            }
        }
        else{
            if(isPasaObjeto()){         //GAME OVER
                getTh().stopMusic();
                getLost().start();
                canvas.drawText(String.format("%s",getContext().getString(R.string.game_over_tehran)),(float)(getWidth()*0.32),(float)(getHeight()*0.45),paint);
                finalizar();
            }
            else{                       //WINNER
                getTh().stopMusic();
                getWin().start();
                canvas.drawText(String.format("%s",getContext().getString(R.string.congratulations_tehran)),(float)(getWidth()*0.25),(float)(getHeight()*0.45),paint);
                finalizar();
            }
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
            Thread.sleep(2500);
        }
        catch(InterruptedException ie){

        }
        if(isPasaObjeto()){         //Para la musica, segun ganes o pierdas
            getLost().stop();
            getLost().release();
        }
        else{
            getWin().stop();
            getWin().release();
        }
        principal.fin();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()== MotionEvent.ACTION_MOVE){
            jugador.touch((int) event.getRawX(),true);
        }
        else if(event.getAction()==MotionEvent.ACTION_UP){
            jugador.touch((int) event.getRawX(),false);
        }
        else if(event.getAction()==MotionEvent.ACTION_DOWN){
            jugador.touch((int) event.getRawX(),true);
        }
        return true;
    }


    //GETTER-SETTERS
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

    public int getCojidos() {
        return cojidos;
    }

    public void setCojidos(int cojidos) {
        this.cojidos = cojidos;
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

    public boolean isCojidoJabon1() {
        return cojidoJabon1;
    }

    public void setCojidoJabon1(boolean cojidoJabon1) {
        this.cojidoJabon1 = cojidoJabon1;
    }

    public boolean isCojidoJabon2() {
        return cojidoJabon2;
    }

    public void setCojidoJabon2(boolean cojidoJabon2) {
        this.cojidoJabon2 = cojidoJabon2;
    }

    public boolean isCojidoJabon3() {
        return cojidoJabon3;
    }

    public void setCojidoJabon3(boolean cojidoJabon3) {
        this.cojidoJabon3 = cojidoJabon3;
    }

    public boolean isCojidoJabon4() {
        return cojidoJabon4;
    }

    public void setCojidoJabon4(boolean cojidoJabon4) {
        this.cojidoJabon4 = cojidoJabon4;
    }

    public Bitmap getJabon1() {
        return jabon1;
    }

    public void setJabon1(Bitmap jabon1) {
        this.jabon1 = jabon1;
    }

    public Bitmap getJabon2() {
        return jabon2;
    }

    public void setJabon2(Bitmap jabon2) {
        this.jabon2 = jabon2;
    }

    public Bitmap getJabon3() {
        return jabon3;
    }

    public void setJabon3(Bitmap jabon3) {
        this.jabon3 = jabon3;
    }

    public Bitmap getJabon4() {
        return jabon4;
    }

    public void setJabon4(Bitmap jabon4) {
        this.jabon4 = jabon4;
    }

    public boolean isFabrica() {
        return fabrica;
    }

    public void setFabrica(boolean camarero) {
        this.fabrica = camarero;
    }

    public MediaPlayer getLost() {
        return lost;
    }

    public void setLost(MediaPlayer lost) {
        this.lost = lost;
    }

    public MediaPlayer getWin() {
        return win;
    }

    public void setWin(MediaPlayer win) {
        this.win = win;
    }

    public int getContadorJabon1() {
        return contadorJabon1;
    }

    public void setContadorJabon1(int contadorJabon1) {
        this.contadorJabon1 = contadorJabon1;
    }

    public Bitmap getAspiradora() {
        return aspiradora;
    }

    public void setAspiradora(Bitmap aspiradora) {
        this.aspiradora = aspiradora;
    }

    public Bitmap getBasura() {
        return basura;
    }

    public void setBasura(Bitmap basura) {
        this.basura = basura;
    }

    public Bitmap getEscoba() {
        return escoba;
    }

    public void setEscoba(Bitmap escoba) {
        this.escoba = escoba;
    }

    public boolean isCojidoBasura() {
        return cojidoBasura;
    }

    public void setCojidoBasura(boolean cojidoBasura) {
        this.cojidoBasura = cojidoBasura;
    }

    public boolean isCojidoEscoba() {
        return cojidoEscoba;
    }

    public void setCojidoEscoba(boolean cojidoEscoba) {
        this.cojidoEscoba = cojidoEscoba;
    }

    public boolean isCojidoAspiradora() {
        return cojidoAspiradora;
    }

    public void setCojidoAspiradora(boolean cojidoAspiradora) {
        this.cojidoAspiradora = cojidoAspiradora;
    }

    public Bitmap getLimpiador() {
        return limpiador;
    }

    public void setLimpiador(Bitmap limpiador) {
        this.limpiador = limpiador;
    }

    public Bitmap getFregona() {
        return fregona;
    }

    public void setFregona(Bitmap fregona) {
        this.fregona = fregona;
    }

    public boolean isCojidoFregona() {
        return cojidoFregona;
    }

    public void setCojidoFregona(boolean cojidoFregona) {
        this.cojidoFregona = cojidoFregona;
    }

    public boolean isCojidoLimpiador() {
        return cojidoLimpiador;
    }

    public void setCojidoLimpiador(boolean cojidoLimpiador) {
        this.cojidoLimpiador = cojidoLimpiador;
    }

    public Tehran getTh() {
        return th;
    }

    public void setTh(Tehran th) {
        this.th = th;
    }
}
