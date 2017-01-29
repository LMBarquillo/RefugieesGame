package es.riberadeltajo.refugiadosgame.ruta4.view.engine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.ruta4.view.models.Notas;

public class GameView extends SurfaceView {
    private ArrayList<Notas> notas;
    private GameLoopThread loop;
    private SurfaceHolder holder;

    public GameView(Context context) {
        super(context);
        loop=new GameLoopThread(this);
        notas = new ArrayList<Notas>();

        holder=getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {

            @SuppressLint("WrongCall")
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                loop.setRunning(true);
                loop.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean volver=true;
                loop.setRunning(false);
                while(volver){
                    try{
                        loop.join();
                        volver=false;
                    }catch(InterruptedException ie){}
                }
            }
        });
    }

    private void addNote() {


    }

    public void draw(Canvas canvas){
        // Dibujamos fondo
        canvas.drawColor(Color.YELLOW);
        Bitmap fondo = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.tehranazaditower);
        canvas.drawBitmap(fondo, null, new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), null);

        // Dibujamos cada nota que tengamos en pantalla
        for(Notas n : notas) {
            n.draw(canvas);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            synchronized(getHolder()){
                for(Notas n : notas){
                    /*if(miSprite.isCollition(event.getX(),event.getY())){
                        sprites.remove(miSprite);
                        temps.add(new SpriteTemp(temps,this,event.getX(),event.getY(),sangre));
                        break;
                    }*/
                }
            }
        }
        return true;
    }

    public void finish() {
        getLoop().setRunning(false);
    }

    public GameLoopThread getLoop() {
        return loop;
    }

    public void setLoop(GameLoopThread loop) {
        this.loop = loop;
    }

    public ArrayList<Notas> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<Notas> notas) {
        this.notas = notas;
    }

    /*private Bitmap player;
    private ArrayList<Notas> sprites;
    private List<SpriteTemp> temps;
    private SurfaceHolder holder;
    private GameLoopThread loop;
    private int corx,cory;
    private int xSpeed,ySpeed;
    private Bitmap sangre;

    public GameView(Context context) {
        super(context);
        loop=new GameLoopThread(this);
        corx=0;
        cory=0;
        xSpeed=10;
        ySpeed=10;
        sprites=new ArrayList<Sprite>();
        temps=new ArrayList<SpriteTemp>();
        holder=getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {

            @SuppressLint("WrongCall")
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                createSprite();
                loop.setRunning(true);
                loop.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean volver=true;
                loop.setRunning(false);
                while(volver){
                    try{
                        loop.join();
                        volver=false;
                    }catch(InterruptedException ie){}
                }
            }
        });
        sangre= BitmapFactory.decodeResource(getResources(),R.drawable.sangre);
    }

    private void createSprite(){
        player= BitmapFactory.decodeResource(getResources(),R.drawable.darthvader);
        sprites.add(new Notas(this,player));
    }

    public void draw(Canvas canvas){
        canvas.drawColor(Color.YELLOW);
        for(Sprite sprite:sprites)
            sprite.draw(canvas);
        for(int i=temps.size()-1;i>=0;i--)
            temps.get(i).draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            synchronized(getHolder()){
                for(Sprite miSprite:sprites){
                    if(miSprite.isCollition(event.getX(),event.getY())){
                        sprites.remove(miSprite);
                        temps.add(new SpriteTemp(temps,this,event.getX(),event.getY(),sangre));
                        break;
                    }
                }
            }
        }
        return true;
    } */
}
