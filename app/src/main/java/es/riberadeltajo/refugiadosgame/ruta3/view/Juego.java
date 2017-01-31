package es.riberadeltajo.refugiadosgame.ruta3.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

import es.riberadeltajo.refugiadosgame.R;

/**
 * Created by Profesor on 26/01/2017.
 */

public class Juego {

    private GameView gameView;

    private Bitmap agua;
    private Bitmap terreno;

    private Sprite player;

    public Juego(GameView gameView) {
        setGameView(gameView);
        agua = BitmapFactory.decodeResource(getGameView().getResources(), R.drawable.sarajevoagua);
        agua = Bitmap.createScaledBitmap(agua, getGameView().getWidth(), getGameView().getHeight(), false);
        terreno = BitmapFactory.decodeResource(getGameView().getResources(), R.drawable.sarajevoterreno);
        terreno = Bitmap.createScaledBitmap(terreno, getGameView().getWidth(), getGameView().getHeight(), false);
        player = new Sprite(getGameView(), R.drawable.sarajevodarthvader, 4, 4, 100, 200, 0, 0, 0, 0);
    }

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public Sprite getPlayer() {
        return player;
    }

    public void setPlayer(Sprite player) {
        this.player = player;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(agua, 0, 0, null);
        canvas.drawBitmap(terreno, 0, 0, null);
        player.draw(canvas);
    }

    public void touch(int x, int y) {

    }

}
