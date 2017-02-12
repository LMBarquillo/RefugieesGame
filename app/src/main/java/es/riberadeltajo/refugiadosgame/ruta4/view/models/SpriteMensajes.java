package es.riberadeltajo.refugiadosgame.ruta4.view.models;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import es.riberadeltajo.refugiadosgame.ruta4.view.engine.GameView;

/**
 * Sprite de los mensajes de ánimo temporales.
  */

public class SpriteMensajes {
    private final int TEXT_SIZE = 120;
    private final int SHADOW_SIZE = 10;
    private GameView gameView;
    private int posx, posy;
    private float duracion;
    private String texto;
    private Typeface tipografia;
    private Paint paint;

    public SpriteMensajes(GameView gameView, int duracion, String texto){
        setGameView(gameView);
        setDuracion(duracion);
        setTexto(texto);

        setTipografia(Typeface.createFromAsset(gameView.getContext().getAssets(),"tipografias/streetgathering.ttf"));
        setPaint(new Paint());
        getPaint().setStrokeWidth(SHADOW_SIZE);
        getPaint().setTextSize(TEXT_SIZE);
        getPaint().setTypeface(getTipografia());

        // X = centrado, Y = 1/3 pantalla
        setPosx((int)(getGameView().getWidth() - getPaint().measureText(texto)) /2);
        setPosy((int)(getGameView().getHeight()/3));
    }

    public void draw(Canvas canvas){
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);

    }

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
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

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Typeface getTipografia() {
        return tipografia;
    }

    public void setTipografia(Typeface tipografia) {
        this.tipografia = tipografia;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }
}
