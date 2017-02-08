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

import es.riberadeltajo.refugiadosgame.R;

/**
 * Created by Adri on 03/02/2017.
 */

public class GameView extends SurfaceView {
    private final int TIEMPO_MAX=300;
    private Bitmap player,fondo;
    private SurfaceHolder holder;
    private GameLoop loop;
    private int corx,cory;
    private int xSpeed,ySpeed;
    private ArrayList<Sprite> sprites;
    private int puntuacion;
    private long crono,inicio;
    private Sprite jug;
    private Madrid contexto;
    private ArrayList<Bitmap> drawgif;
    private int cont=0;

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
        sprites=new ArrayList<Sprite>();
        drawgif=new ArrayList<>();
        holder=getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                createSprite();
                loop.setRunning(true);
                loop.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean volver=true;
                loop.setRunning(false);
                while(volver){
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

    //Prueba fondo animado. Cambio el fondo añadiendo al array, ya vacío, el siguiente
    public Bitmap cambiarFondo(int cont){
        Bitmap background=null;
        if(cont==0){
            background= drawgif.get(0);
        }
        else if(cont==1){
            background= drawgif.get(0);
        }
        else if(cont==2){
            background= drawgif.get(0);
        }
        else if(cont==3){
            background= drawgif.get(0);
        }
        else if(cont==4){
            background= drawgif.get(0);
        }
        else if(cont==5){
            background= drawgif.get(0);
        }
        else if(cont==6){
            background= drawgif.get(0);
        }
        else if(cont==7){
            background= drawgif.get(0);
        }
        else if(cont==8){
            background= drawgif.get(0);
        }
        else if(cont==9){
            background= drawgif.get(0);
        }
        else if(cont==10){
            background= drawgif.get(0);
        }
        else if(cont==11){
            background= drawgif.get(0);
        }
        else if(cont==12){
            background= drawgif.get(0);
        }
        else if(cont==13){
            background= drawgif.get(0);
        }
        else if(cont==14){
            background= drawgif.get(0);
        }
        else if(cont==15){
            background= drawgif.get(0);
        }
        else if(cont==16){
            background= drawgif.get(0);
        }
        else if(cont==17){
            background= drawgif.get(0);
        }
        else if(cont==18){
            background= drawgif.get(0);
        }
        else if(cont==19){
            background= drawgif.get(0);
        }
        else if(cont==20){
            background= drawgif.get(0);
        }
        else if(cont==21){
            background= drawgif.get(0);
        }
        else if(cont==22){
            background= drawgif.get(0);
        }
        else if(cont==23){
            background= drawgif.get(0);
        }

        return background;
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

    //Creo el Sprite y le hago un setJug() para pasarle después la posición en el onTouchEvent
    //Inicializo el fondo temporalmente aquí porque daba Null en el constructor
    public void createSprite(){
        player= BitmapFactory.decodeResource(getResources(),R.drawable.pruebamadrid);
        setJug(new Sprite(this,player,10));
        sprites.add(getJug());
        fondo=BitmapFactory.decodeResource(getResources(), R.drawable.frame1);
        fondo = Bitmap.createScaledBitmap(fondo, getWidth(), getHeight(), false);
    }

    //Prueba fondo animado. Crea el fondo antes de ponerle
    public void createFondo(int cont){
        Bitmap fond;
        if (cont == 0) {
            fond=BitmapFactory.decodeResource(getResources(), R.drawable.frame1);
            fond = Bitmap.createScaledBitmap(fond, getWidth(), getHeight(), false);
            drawgif.add(fond);
        }
        if (cont == 1) {
            fond = BitmapFactory.decodeResource(getResources(), R.drawable.frame2);
            fond = Bitmap.createScaledBitmap(fond, getWidth(), getHeight(), false);
            drawgif.add(fond);
        }
        if (cont == 2) {
            fond = BitmapFactory.decodeResource(getResources(), R.drawable.frame3);
            fond = Bitmap.createScaledBitmap(fond, getWidth(), getHeight(), false);
            drawgif.add(fond);
        }
        if (cont == 3) {
            fond = BitmapFactory.decodeResource(getResources(), R.drawable.frame4);
            fond = Bitmap.createScaledBitmap(fond, getWidth(), getHeight(), false);
            drawgif.add(fond);
        }
        if (cont == 4) {
            fond = BitmapFactory.decodeResource(getResources(), R.drawable.frame5);
            fond = Bitmap.createScaledBitmap(fond, getWidth(), getHeight(), false);
            drawgif.add(fond);
        }
        if (cont == 5) {
            fond = BitmapFactory.decodeResource(getResources(), R.drawable.frame6);
            fond = Bitmap.createScaledBitmap(fond, getWidth(), getHeight(), false);
            drawgif.add(fond);
        }
        if (cont == 6) {
            fond = BitmapFactory.decodeResource(getResources(), R.drawable.frame7);
            fond = Bitmap.createScaledBitmap(fond, getWidth(), getHeight(), false);
            drawgif.add(fond);
        }
        if (cont == 7) {
            fond = BitmapFactory.decodeResource(getResources(), R.drawable.frame8);
            fond = Bitmap.createScaledBitmap(fond, getWidth(), getHeight(), false);
            drawgif.add(fond);
        }
        if (cont == 8) {
            fond = BitmapFactory.decodeResource(getResources(), R.drawable.frame9);
            fond = Bitmap.createScaledBitmap(fond, getWidth(), getHeight(), false);
            drawgif.add(fond);
        }
        if (cont == 9) {
            fond = BitmapFactory.decodeResource(getResources(), R.drawable.frame10);
            fond = Bitmap.createScaledBitmap(fond, getWidth(), getHeight(), false);
            drawgif.add(fond);
        }
        if (cont == 10) {
            fond = BitmapFactory.decodeResource(getResources(), R.drawable.frame11);
            fond = Bitmap.createScaledBitmap(fond, getWidth(), getHeight(), false);
            drawgif.add(fond);
        }
        if (cont == 11) {
            fond = BitmapFactory.decodeResource(getResources(), R.drawable.frame12);
            fond = Bitmap.createScaledBitmap(fond, getWidth(), getHeight(), false);
            drawgif.add(fond);
        }
        if (cont == 12) {
            fond = BitmapFactory.decodeResource(getResources(), R.drawable.frame13);
            fond = Bitmap.createScaledBitmap(fond, getWidth(), getHeight(), false);
            drawgif.add(fond);
        }
        if (cont == 13) {
            fond = BitmapFactory.decodeResource(getResources(), R.drawable.frame14);
            fond = Bitmap.createScaledBitmap(fond, getWidth(), getHeight(), false);
            drawgif.add(fond);
        }
        if (cont == 14) {
            fond = BitmapFactory.decodeResource(getResources(), R.drawable.frame15);
            fond = Bitmap.createScaledBitmap(fond, getWidth(), getHeight(), false);
            drawgif.add(fond);
        }
        if (cont == 15) {
            fond = BitmapFactory.decodeResource(getResources(), R.drawable.frame16);
            fond = Bitmap.createScaledBitmap(fond, getWidth(), getHeight(), false);
            drawgif.add(fond);
        }
        if (cont == 16) {
            fond = BitmapFactory.decodeResource(getResources(), R.drawable.frame17);
            fond = Bitmap.createScaledBitmap(fond, getWidth(), getHeight(), false);
            drawgif.add(fond);
        }
        if (cont == 17) {
            fond = BitmapFactory.decodeResource(getResources(), R.drawable.frame18);
            fond = Bitmap.createScaledBitmap(fond, getWidth(), getHeight(), false);
            drawgif.add(fond);
        }
        if (cont == 18) {
            fond = BitmapFactory.decodeResource(getResources(), R.drawable.frame19);
            fond = Bitmap.createScaledBitmap(fond, getWidth(), getHeight(), false);
            drawgif.add(fond);
        }
        if (cont == 19) {
            fond = BitmapFactory.decodeResource(getResources(), R.drawable.frame20);
            fond = Bitmap.createScaledBitmap(fond, getWidth(), getHeight(), false);
            drawgif.add(fond);
        }
        if (cont == 20) {
            fond = BitmapFactory.decodeResource(getResources(), R.drawable.frame21);
            fond = Bitmap.createScaledBitmap(fond, getWidth(), getHeight(), false);
            drawgif.add(fond);
        }
        if (cont == 21) {
            fond = BitmapFactory.decodeResource(getResources(), R.drawable.frame22);
            fond = Bitmap.createScaledBitmap(fond, getWidth(), getHeight(), false);
            drawgif.add(fond);
        }
        if (cont == 22) {
            fond = BitmapFactory.decodeResource(getResources(), R.drawable.frame23);
            fond = Bitmap.createScaledBitmap(fond, getWidth(), getHeight(), false);
            drawgif.add(fond);
        }
        if (cont == 23) {
            fond = BitmapFactory.decodeResource(getResources(), R.drawable.frame24);
            fond = Bitmap.createScaledBitmap(fond, getWidth(), getHeight(), false);
            drawgif.add(fond);
        }
    }

    public void draw(Canvas canvas){
        Paint paint=new Paint();
        long actual;
        paint.setColor(Color.RED);
        paint.setTextSize((float) (getWidth() * 0.1));
        canvas.drawBitmap(fondo,0,0,null);
        /*createFondo(cont); //Creo el fondo, le guardo para luego borrarle y le pongo
        Bitmap temp;
        temp=cambiarFondo(cont);
        canvas.drawBitmap(temp, 0, 0, null);
        if(cont<22) {
            cont++;
            drawgif.remove(temp);
        }
        else{
            cont=0;
            drawgif.remove(temp);
        }*/
        if((sprites.size()!=0) && getCrono()<TIEMPO_MAX) {
            actual=System.currentTimeMillis();
            for (Sprite miSprite : sprites) {
                miSprite.draw(canvas);
            }
            if(sprites.size()>0) {
                setCrono((actual - inicio) / 1000);
            }
            //Tiempo hacia arriba
            //canvas.drawText(String.format("%s",pasarSeg(crono)),(float)(getWidth()*0.1),(float)(getHeight()*0.08),paint);
            canvas.drawText(String.format("%s",pasarSeg(TIEMPO_MAX-getCrono())),(float)(getWidth()*0.1),(float)(getHeight()*0.08),paint);
            canvas.drawText(String.format("%02d", getPuntuacion()), (float) (getWidth() * 0.80), (float) (getHeight() * 0.08), paint);
        }
        else{
            if(getCrono()<TIEMPO_MAX) {
                canvas.drawText("VICTORIA",(float)(getWidth()*0.25),(float)(getHeight()*0.49),paint);
            }
            else{
                canvas.drawText("GAME OVER",(float)(getWidth()*0.25),(float)(getHeight()*0.49),paint);
            }
            finalizar();
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

    private void finalizar(){
        loop.setRunning(false);
        try{
            Thread.sleep(1000);
        }catch(InterruptedException ie){}
        getContexto().finish();
    }

    private void sumarPuntos(int puntos){
        setPuntuacion(getPuntuacion()+puntos);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        setCorx((int)event.getRawX());
        setCory((int)event.getRawY());
        if(event.getAction()==MotionEvent.ACTION_MOVE){
            getJug().touch(getCorx(),getCory(),true);
        }
        else if(event.getAction()==MotionEvent.ACTION_UP){
            getJug().touch(getCorx(),getCory(),false);
        }
        else if(event.getAction()==MotionEvent.ACTION_DOWN){
            getJug().touch(getCorx(),getCory(),true);
        }
        return true;
    }
}
