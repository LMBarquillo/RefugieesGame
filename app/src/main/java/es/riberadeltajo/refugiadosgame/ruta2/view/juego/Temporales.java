package es.riberadeltajo.refugiadosgame.ruta2.view.juego;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.List;

/**
 * Created by lagar on 11/02/2017.
 */

public class Temporales {
    private float corx;
    private float cory;
    private Bitmap bmp;
    private int life;
    private List<Temporales> temps;

    public Temporales(List<Temporales> temps, GameView2 gameView2, float x, float y, Bitmap bmp){
        life=20;
        this.temps=temps;
        this.bmp=bmp;
        this.corx=Math.min(Math.max(x-bmp.getWidth()/2, 0),gameView2.getWidth());
        this.cory=Math.min(Math.max(y-bmp.getHeight()/2, 0),gameView2.getHeight());
    }

    public float getCorx() {
        return corx;
    }

    public void setCorx(float corx) {
        this.corx = corx;
    }

    public float getCory() {
        return cory;
    }

    public void setCory(float cory) {
        this.cory = cory;
    }

    public Bitmap getBmp() {
        return bmp;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public List<Temporales> getTemps() {
        return temps;
    }

    public void setTemps(List<Temporales> temps) {
        this.temps = temps;
    }

    public void draw(Canvas canvas){
        if(--life<1){
            temps.remove(this);
        }
        else{
            canvas.drawBitmap(bmp, corx, cory, null);
        }
    }
}


