package es.riberadeltajo.refugiadosgame.ruta2.view.juego;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Boton {

    public static final String ACTION_HELP="HELP";
    public static final String ACTION_EXIT="EXIT";
    public static final String ACTION_PLAY="PLAY";



    private int corx, cory; //Coordenadas
    private GameView gameview; //Dónde aparece
    private Bitmap bmp; //Imagen de las figuritas
    private String accion;




    public Boton(GameView gameview, Bitmap bmp, String accion, int corx, int cory) {

        this.gameview = gameview;
        this.bmp = bmp;
        this.corx = corx;
        this.cory = cory;
        setAccion(accion);


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


