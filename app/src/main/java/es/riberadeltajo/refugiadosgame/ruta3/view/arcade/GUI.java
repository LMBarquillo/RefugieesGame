package es.riberadeltajo.refugiadosgame.ruta3.view.arcade;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

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
    private float posXScale = .045f;
    private float posYScale = .025f;
    private int arribaPosX;
    private int arribaPosY;
    private int arribaWidth;
    private int arribaHeight;
    private int abajoPosX;
    private int abajoPosY;
    private int abajoWidth;
    private int abajoHeight;

    private float textSizeScale = .092f; //sobre 100
    private Rect bordesText;
    private Paint txtPaint;
    private Paint borderPaint;
    private String tiempo;
    private int contador;
    private int minutos;
    private int segundos;

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

        arribaPosX = (int)(getGameView().getWidth() - arriba.getWidth() - getGameView().getWidth() * posXScale);
        arribaPosY = (int)(getGameView().getHeight() - arriba.getHeight() - getGameView().getHeight() * posYScale);
        abajoPosX = (int)(getGameView().getWidth() * posXScale);
        abajoPosY = (int)(getGameView().getHeight() - abajo.getHeight() - getGameView().getHeight() * posYScale);

        bordesText = new Rect();
        txtPaint = new Paint();
        txtPaint.setColor(Color.WHITE);
        txtPaint.setTextSize(getGameView().getWidth() * textSizeScale);

        borderPaint = new Paint();
        borderPaint.setColor(Color.WHITE);
        borderPaint.setTextSize(getGameView().getWidth() * textSizeScale);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(15);
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
        update();
        txtPaint.getTextBounds(tiempo, 0, tiempo.length(), bordesText);
        canvas.drawText(tiempo, 0, bordesText.height(), txtPaint);
        //canvas.drawText(String.format("%02d:%02d", minutos, segundos), 100, 100, borderPaint);
        //canvas.drawText(String.format("%02d:%02d", minutos, segundos), 100, 100, txtPaint);
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
    }

    private void update() {
        if(++contador == GameLoop.FPS) {
            contador = 0;
            if(--segundos < 0) {
                segundos = 59;
                minutos--;
            }
            tiempo = String.format("%02d:%02d", minutos, segundos);
        }
        if(minutos == 0 && segundos == 0) {
            getGameView().setFin(true);
            getGameView().getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    /*AlertDialog.Builder dialog = new AlertDialog.Builder(getGameSurface().getActivity());
                    dialog.setTitle("You Lost");
                    dialog.setMessage("You lost because time is up");
                    dialog.setPositiveButton("Reintentar", null);
                    dialog.setNegativeButton("Salir", null);
                    dialog.create();
                    dialog.show();*/
                    new LoseDialog(getGameView().getActivity()).show();
                }
            });
        }
    }

    public void start() {
        contador = 0;
        minutos = 1;
        segundos = 15;
        tiempo = String.format("%02d:%02d", minutos, segundos);
    }

    public void touch(int x, int y) {
        if(x >= arribaPosX && x <= arribaPosX + arribaWidth && y >= arribaPosY && y <= arribaPosY + arribaHeight) {
            getJuego().andarUp();
        } else
        if(x >= abajoPosX && x <= abajoPosX + abajoWidth && y >= abajoPosY && y <= abajoPosY + abajoHeight) {
            getJuego().andarDown();
        }
    }

    public void unTouch(int x, int y) {
        getJuego().andarStop();
    }

}
