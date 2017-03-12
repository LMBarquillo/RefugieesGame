package es.riberadeltajo.refugiadosgame.ruta4.view.engine;

import android.graphics.Canvas;

/**
 * Game Loop Thread
 * @author Luismi
 */

public class GameLoopThread extends Thread {
    private int fps;
    private GameSurface gameSurface;
    private boolean running;

    public GameLoopThread(GameSurface gameSurface, int fps){
        setGameSurface(gameSurface);
        setRunning(false);
        setFps(fps);
    }

    public void run(){
        long tick=1000/getFps();
        long startTime;
        long sleepTime;

        while(isRunning()){
            Canvas canvas=null;
            startTime=System.currentTimeMillis();
            try{
                canvas= getGameSurface().getHolder().lockCanvas();
                synchronized(getGameSurface()){
                    getGameSurface().actualizar();
                    getGameSurface().dibujar(canvas);
                }
            }finally{
                if(canvas!=null){
                    getGameSurface().getHolder().unlockCanvasAndPost(canvas);
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

    public void setGameSurface(GameSurface gameSurface) {
        this.gameSurface = gameSurface;
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

    public GameSurface getGameSurface() {
        return gameSurface;
    }

    public boolean isRunning() {
        return running;
    }
}
