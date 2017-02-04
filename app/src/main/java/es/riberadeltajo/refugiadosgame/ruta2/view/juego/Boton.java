package es.riberadeltajo.refugiadosgame.ruta2.view.juego;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Boton {

    public static final String ACTION_HELP="HELP";
    public static final String ACTION_EXIT="EXIT";
    public static final String ACTION_PLAY="PLAY";
    public static final String ACTION_UP="ARRIBA";
    public static final String ACTION_AVANCE="ADELANTE";
    public static final String ACTION_ATRAS="ATRAS";



    private int corx, cory; //Coordenadas
    private GameView gameview; //Dónde aparece
    private Bitmap bmp; //Imagen de las figuritas
    private String accion;




    public Boton( Bitmap bmp, String accion, int corx, int cory, int height, int width) {


        setCory(cory);
        setCorx(corx);
        setAccion(accion);
        Bitmap imagen= Bitmap.createScaledBitmap(bmp, width, height, false);
        setBmp(imagen);




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



    public Bitmap getBmp() {
        return bmp;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public void onDraw(Canvas canvas){

        canvas.drawBitmap(bmp, corx,cory, null);
    }
    public boolean isCollition(float x2, float y2){
        //Comprueba si está en las coordenadas que se le pasan, que son las del dedo pulsado
boolean comprobar=false;
        if(x2>corx && x2<(corx+bmp.getWidth()) && y2>cory && y2<(cory+bmp.getHeight())){
comprobar=true;


        }
        return comprobar;

    }
    }


