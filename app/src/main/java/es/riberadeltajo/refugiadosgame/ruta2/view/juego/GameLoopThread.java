package es.riberadeltajo.refugiadosgame.ruta2.view.juego;
import android.graphics.Canvas;


public class GameLoopThread extends Thread {
    private final long FPS=10;
    private GameView gameView;
    private boolean running;
    public GameLoopThread(GameView gameView){
        setGameView(gameView);
        setRunning(false);
    }

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
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
                canvas=getGameView().getHolder().lockCanvas();
                synchronized (getGameView()){
                    getGameView().draw(canvas);
                }
            }finally {
                if(canvas!=null){
                    getGameView().getHolder().unlockCanvasAndPost(canvas);
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
