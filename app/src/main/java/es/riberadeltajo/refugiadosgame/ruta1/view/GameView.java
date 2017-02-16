package es.riberadeltajo.refugiadosgame.ruta1.view;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import es.riberadeltajo.refugiadosgame.R;

/**
 * Created by Adri on 03/02/2017.
 */

public class GameView extends SurfaceView {
    private final int TIEMPO_MAX=120;
    private final int MAX_POINTS=100;
    private Bitmap player,coin1,coin2,coin5,coin10,fondo,ticket,life1,life2,life3,moneyBag,sandClock;
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
    private Typeface font;

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
        font = Typeface.createFromAsset(context.getAssets(),"tipografias/madrid_font.ttf");
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
        player=getBitmapFromAssets(getContext(),"madrid_resources/madrid_sprite.png");
        coin1=getBitmapFromAssets(getContext(),"madrid_resources/madrid_coin1.png");
        coin2=getBitmapFromAssets(getContext(),"madrid_resources/madrid_coin2.png");
        coin5=getBitmapFromAssets(getContext(),"madrid_resources/madrid_coin5.png");
        coin10=getBitmapFromAssets(getContext(),"madrid_resources/madrid_coin10.png");
        setJug(new Sprite(this,player,5));
        sprites.add(getJug());
        for(int i=0;i<250;i++) {
            monedas.add(new Monedas(monedas, this, coin1, 1));
        }
        for(int i=0;i<100;i++) {
            monedas.add(new Monedas(monedas, this, coin2, 2));
        }
        for(int i=0;i<45;i++) {
            monedas.add(new Monedas(monedas, this, coin5, 5));
        }
        for(int i=0;i<5;i++) {
            monedas.add(new Monedas(monedas, this, coin10, 10));
        }
        Collections.shuffle(getMonedas());
        ticket=getBitmapFromAssets(getContext(),"madrid_resources/madrid_ticket.png");
        tickets.add(new Ticket(tickets, this, ticket));
    }

    //Obtener bitmap escalado para distintos dispositivos
    public static Bitmap getBitmapFromAssets(Context context, String filePath) {
        AssetManager assetManager = context.getAssets();

        InputStream inStream;
        Bitmap bitmap = null;
        try {
            inStream = assetManager.open(filePath);
            bitmap = BitmapFactory.decodeStream(inStream);
        } catch (IOException e) {
        }

        return bitmap;
    }

    //Pinto interfaz, creo sprite y monedas y compruebo si hay colisiones y abro dialogo al finalizar
    public void draw(Canvas canvas){
        long actual;
        boolean conseguido=false, finalizar=false;
        canvas.drawBitmap(fondo, 0, 0, null);
        Paint paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth((float)((getWidth()*0.1)/12));
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize((float) (getWidth() * 0.08));
        paint.setTypeface(font);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawBitmap(sandClock,(float)(getWidth()*0.05),(float)(getHeight()*0.035),null);
        canvas.drawText(String.format("%s",pasarSeg(TIEMPO_MAX-getCrono())),(float)(getWidth()*0.05+sandClock.getWidth()*1.45),(float)(getHeight()*0.084),paint);
        canvas.drawBitmap(life1,(float)(getWidth()*0.3),(float)(getHeight()*0.045),null);
        canvas.drawBitmap(life2,(float)(getWidth()*0.3+life1.getWidth()*1.05),(float)(getHeight()*0.045),null);
        canvas.drawBitmap(life3,(float)(getWidth()*0.3+life1.getWidth()*1.05+life2.getWidth()*1.05),(float)(getHeight()*0.045),null);
        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawBitmap(moneyBag,(float)(getWidth()*0.57),(float)(getHeight()*0.035),null);
        canvas.drawText(String.format("%03d/%03d", getPuntuacion(),MAX_POINTS), (float)(getWidth()-(getWidth()*0.05)+moneyBag.getWidth()*0.4), (float) (getHeight() * 0.084), paint);
        paint.setColor(Color.rgb(255,255,0));
        paint.setStyle(Paint.Style.FILL);

        if((sprites.size()!=0) && getCrono()<TIEMPO_MAX && getVidas()>0) {
            actual=System.currentTimeMillis();
            for (Sprite miSprite : sprites) {
                miSprite.draw(canvas);
            }
            if(getPuntuacion()<MAX_POINTS) {
                for (int i = 0; i < monedas.size(); i++) {
                    if (i < 5) {
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
            paint.setTextAlign(Paint.Align.LEFT);
            canvas.drawText(String.format("%s",pasarSeg(TIEMPO_MAX-getCrono())),(float)(getWidth()*0.05+sandClock.getWidth()*1.25),(float)(getHeight()*0.08),paint);
            paint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(String.format("%03d/%03d", getPuntuacion(),MAX_POINTS), (float)(getWidth()-(getWidth()*0.05)+moneyBag.getWidth()*0.2), (float) (getHeight() * 0.08), paint);
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

    //Inicializo el fondo y los bitmap de la interfaz, creo el sprite e inicio el bucle
    private void start(){
        fondo=BitmapFactory.decodeResource(getResources(), R.drawable.madridfondo);
        fondo = Bitmap.createScaledBitmap(fondo, getWidth(), getHeight(), false);
        life1=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_fullheart);
        life1 = Bitmap.createScaledBitmap(life1, (int)(getWidth()*0.08), (int)(getHeight()*0.045), false);
        life2=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_fullheart);
        life2 = Bitmap.createScaledBitmap(life1, (int)(getWidth()*0.08), (int)(getHeight()*0.045), false);
        life3=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_fullheart);
        life3 = Bitmap.createScaledBitmap(life1, (int)(getWidth()*0.08), (int)(getHeight()*0.045), false);
        moneyBag=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_money);
        moneyBag = Bitmap.createScaledBitmap(moneyBag, (int)(getWidth()*0.08), (int)(getHeight()*0.06), false);
        sandClock=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_clock);
        sandClock = Bitmap.createScaledBitmap(sandClock, (int)(getWidth()*0.065), (int)(getHeight()*0.06), false);
        createSprite();
        loop.setRunning(true);
        loop.start();
    }

    private void stop(){
        loop.setRunning(false);
        try{
            Thread.sleep(1000);
        }catch(InterruptedException ie){}
        getContexto().onPause();
    }

    private void sumarPuntos(int puntos){
        setPuntuacion(getPuntuacion()+puntos);
    }

    //Mando al Sprite la posición de la pantalla donde quiero que se mueva el personaje
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

    //Habro un diálogo si pulso el botón de ir atrás de android para pedir confirmación
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            //new DialogSalirSiNo().show();
        }
        return super.onKeyDown(keyCode, event);
    }
}
