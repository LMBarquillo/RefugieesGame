package es.riberadeltajo.refugiadosgame.ruta3.view;

import android.graphics.Canvas;

/**
 * Created by Profesor on 26/01/2017.
 */

public class GameLoop extends Thread {

    private final int FPS = 20;

    private boolean running;
    private GameView gameView;

    public GameLoop(GameView gameView) {
        setRunning(false);
        setGameView(gameView);
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
        if(running) this.start();
    }

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    @Override
    public void run() {
        int tick = 1000/FPS;
        long startTime;
        long sleepTime;
        Canvas canvas = null;
        while(isRunning()) {
            startTime = System.currentTimeMillis();
            try {
                canvas = getGameView().getHolder().lockCanvas();
                synchronized (getGameView()) {
                    getGameView().draw(canvas);
                }
            } finally {
                if(canvas != null) {
                    getGameView().getHolder().unlockCanvasAndPost(canvas);
                }
            }
            sleepTime = tick - (System.currentTimeMillis() - startTime);
            try {
                if(sleepTime > 0) {
                    sleep(sleepTime);
                } else {
                    sleep(10);
                }
            } catch (InterruptedException e) {}
        }
    }

}
