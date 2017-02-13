package es.riberadeltajo.refugiadosgame.ruta4.view.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import java.lang.reflect.Array;
import java.util.ArrayList;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.ruta4.view.engine.GameView;

/**
 * Sprite de las explosiones de las notas cuando las tocas
  */
public class SpriteXplosion {
    private final int FRAMESX = 5;
    private final int FRAMESY = 3;
    private GameView gameView;
    private int posx, posy;
    private int cutx, cuty;
    private Rect src;
    private Rect dst;
    private Bitmap bmp;
    private boolean finished;

    public SpriteXplosion(GameView gameView, Rect dst){
        setGameView(gameView);
        bmp = BitmapFactory.decodeResource(getGameView().getResources(),R.drawable.notexplode);
        setCutx(0);
        setCuty(0);
        src = new Rect(getCutx(),getCuty(),bmp.getWidth()/FRAMESX + getCutx(), bmp.getHeight()/FRAMESY + getCuty());
        this.dst = dst;
        setFinished(false);
    }

    public void draw(Canvas canvas){
        update();
        canvas.drawBitmap(bmp,src,dst,null);
    }

    private void update() {
        if(getCutx() < bmp.getWidth()/FRAMESX*(FRAMESX-1)) {
            setCutx(getCutx() + bmp.getWidth()/FRAMESX);
        } else {
            setCutx(0);
            setCuty(getCuty() + bmp.getHeight()/FRAMESY);
        }
        // Actualizamos el recorte con los nuevos datos
        src.set(getCutx(),getCuty(),bmp.getWidth()/FRAMESX + getCutx(), bmp.getHeight()/FRAMESY + getCuty());
        // Si ya hemos recorrido el bitmap entero, nos autoerradicamos
        if(getCuty() > bmp.getHeight()) setFinished(true);
    }

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public int getCuty() {
        return cuty;
    }

    public void setCuty(int cuty) {
        this.cuty = cuty;
    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public int getCutx() {
        return cutx;
    }

    public void setCutx(int cutx) {
        this.cutx = cutx;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
