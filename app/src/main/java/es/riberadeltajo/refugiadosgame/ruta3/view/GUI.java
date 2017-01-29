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

    private Bitmap arriba;
    private Bitmap abajo;
    private float scaleX = .15f;
    private float scaleY = .08f;
    private float ratioX = .045f;
    private float ratioY = .025f;

    public GUI(GameView gameView) {
        setGameView(gameView);
        arriba = BitmapFactory.decodeResource(getGameView().getResources(), R.drawable.sarajevoarrowup);
        arriba = Bitmap.createScaledBitmap(arriba, (int)(getGameView().getWidth() * scaleX), (int)(getGameView().getHeight() * scaleY), false);
        abajo = BitmapFactory.decodeResource(getGameView().getResources(), R.drawable.sarajevoarrowdown);
        abajo = Bitmap.createScaledBitmap(abajo, (int)(getGameView().getWidth() * scaleX), (int)(getGameView().getHeight() * scaleY), false);
    }

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(
                arriba,
                getGameView().getWidth() - arriba.getWidth() - getGameView().getWidth() * ratioX,
                getGameView().getHeight() - arriba.getHeight() - getGameView().getHeight() * ratioY,
                null
        );
        canvas.drawBitmap(
                abajo,
                getGameView().getWidth() * ratioX,
                getGameView().getHeight() - abajo.getHeight() - getGameView().getHeight() * ratioY,
                null
        );
        /*Paint paint = new Paint();
        paint.setTextSize(50);
        paint.setColor(Color.RED);
        canvas.drawText(String.format("%d x %d", getGameView().getWidth(), getGameView().getHeight()), 200, 200, paint);*/
    }

    public void touch(int x, int y) {

    }

}
