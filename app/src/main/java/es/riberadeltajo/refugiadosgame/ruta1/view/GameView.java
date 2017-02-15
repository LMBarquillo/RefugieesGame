package es.riberadeltajo.refugiadosgame.ruta1.view;

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
import java.util.Collections;

import es.riberadeltajo.refugiadosgame.R;

/**
 * Created by Adri on 03/02/2017.
 */

public class GameView extends SurfaceView {
    private final int TIEMPO_MAX=12;
    private final int MAX_POINTS=100;
    private Bitmap player,coins,fondo,ticket;
    private SurfaceHolder holder;
    private GameLoop loop;
    private int corx,cory;
    private int xSpeed,ySpeed;
    private ArrayList<Sprite> sprites;
    private ArrayList<Monedas> monedas;
    private ArrayList<Ticket> tickets;
    private int puntuacion;
    private long crono,inicio;
    private Sprite jug;
    private Madrid contexto;
    private float vidas;

    public GameView(Context context){
        super(context);
        setContexto((Madrid)context);
        loop=new GameLoop(this);
        setCorx(0);
        setCory(0);
        setxSpeed(10);
        setySpeed(10);
        setInicio(System.currentTimeMillis());
        setPuntuacion(0);
        setVidas(5);
        sprites=new ArrayList<Sprite>();
        monedas=new ArrayList<Monedas>();
        tickets=new ArrayList<Ticket>();
        holder=getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                stop();
            }
        });
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

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Madrid getContexto() {
        return contexto;
    }

    public void setContexto(Madrid contexto) {
        this.contexto = contexto;
    }

    public Sprite getJug() {
        return jug;
    }

    public void setJug(Sprite jug) {
        this.jug = jug;
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

    public ArrayList<Monedas> getMonedas() {
        return monedas;
    }

    public void setMonedas(ArrayList<Monedas> monedas) {
        this.monedas = monedas;
    }

    public float getVidas() {
        return vidas;
    }

    public void setVidas(float vidas) {
        this.vidas = vidas;
    }

    //Creo el Sprite y le hago un setJug() para pasarle después la posición en el onTouchEvent
    //Creo las monedas de distinto valor y las barajeo para que se pinten posteriormente de forma aleatoria
    //Creo el ticket que aparecerá cuando obtenga la puntuación requerida
    public void createSprite(){
        player= BitmapFactory.decodeResource(getResources(),R.drawable.pruebamadrid);
        setJug(new Sprite(this,player,5));
        sprites.add(getJug());
        for(int i=0;i<250;i++) {
            coins = BitmapFactory.decodeResource(getResources(), R.drawable.madridcoin1);
            monedas.add(new Monedas(monedas, this, coins, 1));
        }
        for(int i=0;i<100;i++) {
            coins = BitmapFactory.decodeResource(getResources(), R.drawable.madridcoin2);
            monedas.add(new Monedas(monedas, this, coins, 2));
        }
        for(int i=0;i<45;i++) {
            coins = BitmapFactory.decodeResource(getResources(), R.drawable.madridcoin5);
            monedas.add(new Monedas(monedas, this, coins, 5));
        }
        for(int i=0;i<5;i++) {
            coins = BitmapFactory.decodeResource(getResources(), R.drawable.madridcoin10);
            monedas.add(new Monedas(monedas, this, coins, 10));
        }
        Collections.shuffle(getMonedas());
        ticket=BitmapFactory.decodeResource(getResources(), R.drawable.madridticket);
        tickets.add(new Ticket(tickets, this, ticket));
    }

    public void draw(Canvas canvas){
        Paint paint=new Paint();
        long actual;
        boolean conseguido=false, finalizar=false;
        paint.setColor(Color.RED);
        paint.setTextSize((float) (getWidth() * 0.1));
        canvas.drawBitmap(fondo,0,0,null);
        if((sprites.size()!=0) && getCrono()<TIEMPO_MAX && getVidas()>0) {
            actual=System.currentTimeMillis();
            for (Sprite miSprite : sprites) {
                miSprite.draw(canvas);
            }
            if(getPuntuacion()<MAX_POINTS) {
                for (int i = 0; i < monedas.size(); i++) {
                    if (i <= 5) {
                        monedas.get(i).draw(canvas);
                        if (monedas.get(i).isCollition(getJug())) {
                            sumarPuntos(monedas.get(i).getPuntos());
                            monedas.remove(monedas.get(i));
                        }
                    }
                }
            }else{
                for(int i=0;i<tickets.size();i++){
                    tickets.get(i).draw(canvas);
                    if(tickets.get(i).isCollition(getJug())) {
                        conseguido=true;
                        tickets.remove(tickets.get(i));
                    }
                }
            }
            if (sprites.size() > 0) {
                setCrono((actual - inicio) / 1000);
            }
            canvas.drawText(String.format("%s",pasarSeg(TIEMPO_MAX-getCrono())),(float)(getWidth()*0.1),(float)(getHeight()*0.08),paint);
            canvas.drawText(String.format("%.1f",getVidas()),(float)(getWidth()*0.4),(float)(getHeight()*0.08),paint);
            canvas.drawText(String.format("%02d/%02d", getPuntuacion(),MAX_POINTS), (float) (getWidth() * 0.60), (float) (getHeight() * 0.08), paint);
        }
        else{
            conseguido=false;
            finalizar=true;
        }
        if(conseguido){
            getContexto().runOnUiThread(new Runnable() {
                public void run() {
                    new DialogFin(getContexto(), DialogFin.Tipo.WIN).show();
                }
            });
            stop();
        }
        else if(!conseguido && finalizar) {
            getContexto().runOnUiThread(new Runnable() {
                public void run() {
                    new DialogFin(getContexto(), DialogFin.Tipo.LOSE).show();
                }
            });
            stop();
        }
    }

    //Pasar a segundos el tiempo
    private String pasarSeg(float time){
        String cad="";
        String txt=String.valueOf(time);
        int pos=0;
        while(pos<txt.length()){
            if(txt.charAt(pos)=='.' || txt.charAt(pos)==','){
                if(cad.length()>0){
                    return cad;
                }
                else{
                    return String.format("0%s",cad);
                }
            }
            else{
                cad=String.format("%s%c",cad,txt.charAt(pos));
                pos++;
            }
        }
        return "";
    }

    private void start(){
        fondo=BitmapFactory.decodeResource(getResources(), R.drawable.madridfondo);
        fondo = Bitmap.createScaledBitmap(fondo, getWidth(), getHeight(), false);
        createSprite();
        loop.setRunning(true);
        loop.start();
    }

    private void stop(){
        loop.setRunning(false);
        try{
            Thread.sleep(1000);
        }catch(InterruptedException ie){}
    }

    private void sumarPuntos(int puntos){
        setPuntuacion(getPuntuacion()+puntos);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_MOVE){
            getJug().touch(event.getRawX(),event.getRawY(),true);
        }
        else if(event.getAction()==MotionEvent.ACTION_UP){
            getJug().touch(event.getRawX(),event.getRawY(),false);
        }
        else if(event.getAction()==MotionEvent.ACTION_DOWN){
            getJug().touch(event.getRawX(),event.getRawY(),true);
        }
        return true;
    }
}
