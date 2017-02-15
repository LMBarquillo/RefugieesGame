package es.riberadeltajo.refugiadosgame.ruta5.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
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
    private Bitmap jugbmp;
    private Bitmap hielo, vaso, refresco, pajita;
    private boolean cojidoHielo, cojidoVaso, cojidoRefresco, cojidoPajita;
    private Player jugador;
    private ArrayList<Objetos> objetos;
    private GameLoop loop;
    private boolean pasaObjeto;
    private Cronometro cronometro;
    private int segundos;
    private int cojidos;
    private boolean camarero;



    public GameView(Context context, boolean cam) {
        super(context);
        principal=(Tehran)context;
        cronometro=new Cronometro();
        cronometro.addObserver(this);
        new Thread(cronometro).start();
        holder=getHolder();
        objetos=new ArrayList<Objetos>();
        setPasaObjeto(false);
        setCamarero(cam);
        setSegundos(-1);
        setCojidos(4);
        setCojidoHielo(false);
        setCojidoVaso(false);
        setCojidoRefresco(false);
        setCojidoPajita(false);
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
        setHielo(BitmapFactory.decodeResource(getResources(), R.drawable.hielotehran));
        setVaso(BitmapFactory.decodeResource(getResources(), R.drawable.vasotehran));
        setRefresco(BitmapFactory.decodeResource(getResources(), R.drawable.bebidatehran));
        setPajita(BitmapFactory.decodeResource(getResources(), R.drawable.pajitatehran));
    }

    private void cargarObjetos(){
        jugador=new Player(this,Bitmap.createScaledBitmap(jugbmp,(int)(jugbmp.getWidth()*1.7),(int)(jugbmp.getHeight()*1.5),false));
        if(isCamarero()){
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.guitarratehran),(int)(Math.random()*10)+10,false,(int)(Math.random()*5)+1));
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.pizzatehran),(int)(Math.random()*10)+10,false,(int)(Math.random()*5)+4));
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.hielotehran),(int)(Math.random()*10)+10,true,(int)(Math.random()*5)+6));
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.bebidatehran),(int)(Math.random()*10)+10,true,(int)(Math.random()*5)+10));
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.monedatehran),(int)(Math.random()*10)+10,false,(int)(Math.random()*5)+10));
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.alfombratehran),(int)(Math.random()*10)+10,false,(int)(Math.random()*5)+14));
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.pajitatehran),(int)(Math.random()*10)+10,true,(int)(Math.random()*5)+18));
            objetos.add(new Objetos(this,BitmapFactory.decodeResource(getResources(), R.drawable.vasotehran),(int)(Math.random()*10)+10,true,(int)(Math.random()*5)+22));
        }

    }


    public void draw(Canvas canvas){
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(70);
        //canvas.drawColor(Color.WHITE);      //Dibuja Fondo Blanco
        canvas.drawBitmap(Bitmap.createScaledBitmap(fondo,getWidth(),getHeight(),false),0,0,null);      //Dibuja imagen fondo
        canvas.drawText(String.format("%d",getSegundos()),(float)(getWidth()*0.05),(float)(getHeight()*0.05),paint);
        //canvas.drawText(String.format("Objetos por Cojer: %d",getCojidos()),(float)(getWidth()*0.4),(float)(getHeight()*0.05),paint);
        jugador.draw(canvas);
        if(!isCojidoHielo()){
            canvas.drawBitmap(Bitmap.createScaledBitmap(getHielo(),(int)(getWidth()*0.05),(int)(getHeight()*0.05),false),(float)(getWidth()*0.9),(float)(getHeight()*0.07),null);
        }
        if(!isCojidoVaso()){
            canvas.drawBitmap(Bitmap.createScaledBitmap(getVaso(),(int)(getWidth()*0.05),(int)(getHeight()*0.05),false),(float)(getWidth()*0.9),(float)(getHeight()*0.14),null);
        }
        if(!isCojidoRefresco()){
            canvas.drawBitmap(Bitmap.createScaledBitmap(getRefresco(),(int)(getWidth()*0.05),(int)(getHeight()*0.05),false),(float)(getWidth()*0.9),(float)(getHeight()*0.21),null);
        }
        if(!isCojidoPajita()){
            canvas.drawBitmap(Bitmap.createScaledBitmap(getPajita(),(int)(getWidth()*0.05),(int)(getHeight()*0.05),false),(float)(getWidth()*0.9),(float)(getHeight()*0.27),null);
        }
        if(!isPasaObjeto() && objetos.size()>0 && getCojidos()>0){
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
                else if(objetos.get(i).choqueJugador(jugador)){     //COMPRUEBA SI EL OBJETO CHOCA CON EL JUGADOR
                    if(objetos.get(i).isCoger()){
                        setCojidos(getCojidos()-1);
                        if(objetos.get(i).getBmp().sameAs(getHielo())){         //COMPRUEBA QUE OBJETO HA COJIDO PARA BORRARLO DE PANTALLA
                            setCojidoHielo(true);
                        }
                        else if(objetos.get(i).getBmp().sameAs(getVaso())){
                            setCojidoVaso(true);
                        }
                        else if(objetos.get(i).getBmp().sameAs(getRefresco())){
                            setCojidoRefresco(true);
                        }
                        else if(objetos.get(i).getBmp().sameAs(getPajita())){
                            setCojidoPajita(true);
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
            if(isPasaObjeto()){
                canvas.drawText(String.format("%s",getContext().getString(R.string.game_over_tehran)),(float)(getWidth()*0.32),(float)(getHeight()*0.45),paint);
                finalizar();
            }
            else{
                canvas.drawText(String.format("%s",getContext().getString(R.string.congratulations_tehran)),(float)(getWidth()*0.2),(float)(getHeight()*0.45),paint);
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
            Thread.sleep(2000);
        }
        catch(InterruptedException ie){

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

    public boolean isCojidoHielo() {
        return cojidoHielo;
    }

    public void setCojidoHielo(boolean cojidoHielo) {
        this.cojidoHielo = cojidoHielo;
    }

    public boolean isCojidoVaso() {
        return cojidoVaso;
    }

    public void setCojidoVaso(boolean cojidoVaso) {
        this.cojidoVaso = cojidoVaso;
    }

    public boolean isCojidoRefresco() {
        return cojidoRefresco;
    }

    public void setCojidoRefresco(boolean cojidoRefresco) {
        this.cojidoRefresco = cojidoRefresco;
    }

    public boolean isCojidoPajita() {
        return cojidoPajita;
    }

    public void setCojidoPajita(boolean cojidoPajita) {
        this.cojidoPajita = cojidoPajita;
    }

    public Bitmap getHielo() {
        return hielo;
    }

    public void setHielo(Bitmap hielo) {
        this.hielo = hielo;
    }

    public Bitmap getVaso() {
        return vaso;
    }

    public void setVaso(Bitmap vaso) {
        this.vaso = vaso;
    }

    public Bitmap getRefresco() {
        return refresco;
    }

    public void setRefresco(Bitmap refresco) {
        this.refresco = refresco;
    }

    public Bitmap getPajita() {
        return pajita;
    }

    public void setPajita(Bitmap pajita) {
        this.pajita = pajita;
    }

    public boolean isCamarero() {
        return camarero;
    }

    public void setCamarero(boolean camarero) {
        this.camarero = camarero;
    }
}
