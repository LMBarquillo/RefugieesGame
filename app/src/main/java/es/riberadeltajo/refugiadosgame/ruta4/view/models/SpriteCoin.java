package es.riberadeltajo.refugiadosgame.ruta4.view.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.ruta4.view.engine.GameView;

/**
 * Sprite de la monedica bailona
  */
public class SpriteCoin {
    private final int FRAMESX = 24;
    private GameView gameView;
    private int cutx;
    private Rect src;
    private Rect dst;
    private Bitmap bmp;

    public SpriteCoin(GameView gameView, Rect dst){
        setGameView(gameView);
        bmp = BitmapFactory.decodeResource(getGameView().getResources(),R.drawable.monedica);
        setCutx(0);
        src = new Rect(getCutx(),0,bmp.getWidth()/FRAMESX + getCutx(), bmp.getHeight());
        this.dst = dst;
    }

    public synchronized void draw(Canvas canvas){
        update();
        canvas.drawBitmap(bmp,src,dst,null);
    }

    private void update() {
        if(getCutx() < bmp.getWidth()/FRAMESX*(FRAMESX-1)) {
            setCutx(getCutx() + bmp.getWidth()/FRAMESX);
        } else {
            setCutx(0);
        }
        src.set(getCutx(),0,bmp.getWidth()/FRAMESX + getCutx(), bmp.getHeight());
    }

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public int getCutx() {
        return cutx;
    }

    public void setCutx(int cutx) {
        this.cutx = cutx;
    }

    public Rect getDst() {
        return dst;
    }

    public void setDst(Rect dst) {
        this.dst = dst;
    }
}
