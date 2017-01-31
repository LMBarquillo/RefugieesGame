package es.riberadeltajo.refugiadosgame.ruta5.view;

import android.graphics.Canvas;

/**
 * Created by Profesor on 27/01/2017.
 */

public class GameLoop extends Thread{

    private final long FPS=10;
    private GameView gameView;
    private boolean running;

    public GameLoop(GameView gameView){
        setGameView(gameView);
        setRunning(false);
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public GameView getGameView() {

        return gameView;
    }

    public boolean isRunning() {
        return running;
    }

    public void run(){
        long tick=1000/FPS;
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
