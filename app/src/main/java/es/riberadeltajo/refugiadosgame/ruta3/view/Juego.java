package es.riberadeltajo.refugiadosgame.ruta3.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Profesor on 26/01/2017.
 */

public class Juego {

    private GameView gameView;

    public Juego(GameView gameView) {
        setGameView(gameView);
    }

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(80);

        canvas.drawColor(Color.CYAN);
        canvas.drawText("PRUEBA", canvas.getWidth()* 0.4f, canvas.getHeight() * 0.4f, paint);
    }

    public void touch(int x, int y) {

    }

}
