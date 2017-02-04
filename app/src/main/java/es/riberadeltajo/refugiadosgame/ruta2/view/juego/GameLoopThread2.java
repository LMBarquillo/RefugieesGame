package es.riberadeltajo.refugiadosgame.ruta2.view.juego;

import android.graphics.Canvas;

public class GameLoopThread2 extends Thread {
    private final long FPS=10;
    private GameView2 gameView2;
    private boolean running;

    public GameLoopThread2(GameView2 gameView2){
        setGameView2(gameView2);
        setRunning(false);
    }
    public GameView2 getGameView2() {
        return gameView2;
    }

    public void setGameView2(GameView2 gameView2) {
        this.gameView2 = gameView2;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
    public void run(){
        long click=1000/FPS;  //Fotogramas por segundo (En este caso 10)
        long timeStart;
        long sleepTime;
        while(isRunning()){
            Canvas canvas=null;
            timeStart= System.currentTimeMillis(); //Tiempo de comienzo
            try{
                canvas=getGameView2().getHolder().lockCanvas();
                synchronized (getGameView2()){
                    getGameView2().draw(canvas);
                }
            }finally {
                if(canvas!=null){
                    getGameView2().getHolder().unlockCanvasAndPost(canvas);
                }
            }
            sleepTime=click-(System.currentTimeMillis()-timeStart); //Tiempo que tiene que estar dormido
            try{
                if(sleepTime>0){
                    sleep(sleepTime);
                }else{
                    sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



