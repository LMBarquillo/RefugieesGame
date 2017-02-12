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
    private final int TEXT_SIZE = 150;
    private final int SHADOW_SIZE = 10;
    private GameView gameView;
    private int posx, posy;
    private String texto;
    private Typeface tipografia;
    private Paint paint;
    private long vida;

    public SpriteMensajes(GameView gameView, long duracion, String texto){
        setGameView(gameView);
        setTexto(texto);
        setVida(duracion * gameView.getFPS() / 1000);

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
        if(--this.vida <= 0) {
            getGameView().setMensaje(null);
        } else {
            // Borde blanco y relleno rojo
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawText(getTexto(),getPosx(),getPosy(),paint);
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawText(getTexto(),getPosx(),getPosy(),paint);
        }
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

    public void setVida(long vida) {
        this.vida = vida;
    }
}
