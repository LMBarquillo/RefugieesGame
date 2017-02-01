package es.riberadeltajo.refugiadosgame.ruta3.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import es.riberadeltajo.refugiadosgame.R;

/**
 * Created by Profesor on 26/01/2017.
 */

public class GUI {

    private GameView gameView;
    private Juego juego;

    private Bitmap arriba;
    private Bitmap abajo;
    private float scaleX = .15f;
    private float scaleY = .08f;
    private float ratioX = .045f;
    private float ratioY = .025f;

    private int arribaPosX;
    private int arribaPosY;
    private int arribaWidth;
    private int arribaHeight;
    private int abajoPosX;
    private int abajoPosY;
    private int abajoWidth;
    private int abajoHeight;

    public GUI(GameView gameView, Juego juego) {
        setGameView(gameView);
        setJuego(juego);

        arribaWidth = (int)(getGameView().getWidth() * scaleX);
        arribaHeight = (int)(getGameView().getHeight() * scaleY);
        abajoWidth = (int)(getGameView().getWidth() * scaleX);
        abajoHeight = (int)(getGameView().getHeight() * scaleY);

        arriba = BitmapFactory.decodeResource(getGameView().getResources(), R.drawable.sarajevoarrowup);
        arriba = Bitmap.createScaledBitmap(arriba, arribaWidth, arribaHeight, false);
        abajo = BitmapFactory.decodeResource(getGameView().getResources(), R.drawable.sarajevoarrowdown);
        abajo = Bitmap.createScaledBitmap(abajo, abajoWidth, abajoHeight, false);

        arribaPosX = (int)(getGameView().getWidth() - arriba.getWidth() - getGameView().getWidth() * ratioX);
        arribaPosY = (int)(getGameView().getHeight() - arriba.getHeight() - getGameView().getHeight() * ratioY);
        abajoPosX = (int)(getGameView().getWidth() * ratioX);
        abajoPosY = (int)(getGameView().getHeight() - abajo.getHeight() - getGameView().getHeight() * ratioY);
    }

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(
                arriba,
                arribaPosX,
                arribaPosY,
                null
        );
        canvas.drawBitmap(
                abajo,
                abajoPosX,
                abajoPosY,
                null
        );
        /*Paint paint = new Paint();
        paint.setTextSize(50);
        paint.setColor(Color.RED);
        canvas.drawText(String.format("%d x %d", getGameView().getWidth(), getGameView().getHeight()), 200, 200, paint);*/
    }

    public void touch(int x, int y) {
        if(x >= arribaPosX && x <= arribaPosX + arribaWidth && y >= arribaPosY && y <= arribaPosY + arribaHeight) {
            getJuego().getPlayer().setSpeedY(-8);
        } else
        if(x >= abajoPosX && x <= abajoPosX + abajoWidth && y >= abajoPosY && y <= abajoPosY + abajoHeight) {
            getJuego().getPlayer().setSpeedY(8);
        }
    }

    public void unTouch(int x, int y) {
        getJuego().getPlayer().setSpeedY(0);
    }

}
