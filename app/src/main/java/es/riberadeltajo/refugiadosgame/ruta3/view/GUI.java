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

    private Bitmap arriba;
    private Bitmap abajo;

    private GameView gameView;

    public GUI(GameView gameView) {
        setGameView(gameView);
        arriba = BitmapFactory.decodeResource(getGameView().getResources(), R.drawable.sarajevoarrowup);
        arriba = Bitmap.createScaledBitmap(arriba, (int)(getGameView().getWidth() * 0.15), (int)(getGameView().getHeight() * 0.08), false);
        abajo = BitmapFactory.decodeResource(getGameView().getResources(), R.drawable.sarajevoarrowdown);
        abajo = Bitmap.createScaledBitmap(abajo, (int)(getGameView().getWidth() * 0.15), (int)(getGameView().getHeight() * 0.08), false);
    }

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(arriba, getGameView().getWidth() - arriba.getWidth() - 50, getGameView().getHeight() - arriba.getHeight() - 50, null);
        canvas.drawBitmap(abajo, 50, getGameView().getHeight() - abajo.getHeight() - 50, null);
        Paint paint = new Paint();
        paint.setTextSize(50);
        paint.setColor(Color.RED);
        canvas.drawText(String.format("%d x %d", getGameView().getWidth(), getGameView().getHeight()), 200, 200, paint);
    }

    public void touch(int x, int y) {

    }

}
