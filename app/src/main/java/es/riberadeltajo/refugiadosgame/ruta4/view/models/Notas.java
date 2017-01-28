package es.riberadeltajo.refugiadosgame.ruta4.view.models;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import es.riberadeltajo.refugiadosgame.ruta4.view.engine.GameView;

/**
 * Created by Profesor on 18/01/2017.
 */

public class Notas {
    private GameView gameView;
    private Bitmap bmp;
    private int currentFrame;
    private int width;
    private int height;

    public Notas(GameView gameView, Bitmap bmp){
        this.gameView=gameView;
        this.bmp=bmp;
        currentFrame=0;
    }

    private void update(){
        // Aquí actualizamos las posiciones y datos del sprite
    }

    public void draw(Canvas canvas){
        update();
        //
        /*Rect src=new Rect(srcx,srcy,srcx+width,srcy+height);
        Rect dst=new Rect(corx,cory,corx+width,cory+height);
        canvas.drawBitmap(bmp,src,dst,null); */
    }
}
