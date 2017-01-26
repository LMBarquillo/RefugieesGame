package es.riberadeltajo.refugiadosgame.ruta3.view;

import android.graphics.Canvas;

/**
 * Created by Profesor on 26/01/2017.
 */

public class GUI {

    private GameView gameView;

    public GUI(GameView gameView) {
        setGameView(gameView);
    }

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public void draw(Canvas canvas) {

    }

    public void touch(int x, int y) {

    }

}
