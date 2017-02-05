package es.riberadeltajo.refugiadosgame.ruta4.view.engine;

import android.graphics.Canvas;

/**
 * Game Loop Thread
 * @author Luismi
 */

public class GameLoopThread extends Thread {
    private int fps;
    private GameView gameView;
    private boolean running;

    public GameLoopThread(GameView gameView){
        setGameView(gameView);
        setRunning(false);
        setFps(gameView.getFPS());  // Nos traemos los FPS del GameView. Los tenemos ahí porque los necesitaremos en otras clases para conseguir el sincronismo de la música.
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public GameView getGameView() {
        return gameView;
    }

    public boolean isRunning() {
        return running;
    }

    public void run(){
        long tick=1000/getFps();
        long startTime;
        long sleepTime;

        while(isRunning()){
            Canvas canvas=null;
            startTime=System.currentTimeMillis();
            try{
                canvas=getGameView().getHolder().lockCanvas();
                synchronized(getGameView()){
                    getGameView().draw(canvas);
                }
            }finally{
                if(canvas!=null){
                    getGameView().getHolder().unlockCanvasAndPost(canvas);
                }
            }
            sleepTime=tick-(System.currentTimeMillis()-startTime);
            try{
                if(sleepTime>0)
                    sleep(sleepTime);
                else
                    sleep(10);
            }catch(InterruptedException ie){}
        }
    }
}
