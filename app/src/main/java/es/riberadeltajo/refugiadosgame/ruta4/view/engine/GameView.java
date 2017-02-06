package es.riberadeltajo.refugiadosgame.ruta4.view.engine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.ruta4.view.models.SpriteNotas;

/**
 * Gameview.
 */
public class GameView extends SurfaceView {
    private final int FPS = 60;
    private NoteGenerator generador;
    private ArrayList<SpriteNotas> notas;
    private GameLoopThread loop;
    private SurfaceHolder holder;
    private MediaPlayer musica;

    public GameView(Context context) {
        super(context);
        loop=new GameLoopThread(this);
        notas = new ArrayList<SpriteNotas>();
        generador = new NoteGenerator(context,this,"songs/sweetchildofmine.txt");
        setMusica(new MediaPlayer().create(context,R.raw.sweetchildofmine));

        holder=getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {

            @SuppressLint("WrongCall")
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                loop.setRunning(true);
                loop.start();
                generador.start();  // Este se encargará de ir añadiendo las notas al arraylist
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean volver=true;
                loop.setRunning(false);
                generador.setRunning(false);
                musica.stop();
                while(volver){
                    try{
                        loop.join();
                        volver=false;
                    }catch(InterruptedException ie){}
                }
            }
        });
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();  // información en pantalla
        // Dibujamos fondo
        Bitmap fondo = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.tehranazaditower);
        canvas.drawBitmap(fondo, null, new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), null);
        // Pastillas
        Bitmap pickups = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.whitepickups);
        canvas.drawBitmap(pickups, new Rect(0,0,pickups.getWidth(),pickups.getHeight()), new RectF(0, canvas.getHeight()-pickups.getHeight(), canvas.getWidth(), canvas.getHeight()), null);
        // Cuerdas
        Bitmap cuerda = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.guitarstring);
        for(int pos=1; pos<=4; pos++) {
            int cx = (canvas.getWidth()/5*pos) - (cuerda.getWidth()/2);
            canvas.drawBitmap(cuerda,null, new RectF(cx,0,cx+cuerda.getWidth(),canvas.getHeight()),null);
        }
        // Dibujamos cada nota que tengamos en pantalla
        for(SpriteNotas n : getNotas()) {
            n.draw(canvas);
        }
        // Monitores y/o contadores
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(60);
        canvas.drawText("Notas en pantalla: "+String.valueOf(notas.size()),50,100,paint);

        // Eliminamos las notas que salieron fuera
        deleteOutNotes();
        controlMusic(canvas.getHeight()-pickups.getHeight());
    }

    private void controlMusic(int posicionPickups) {
        int retrasoCancion = 430;
        // cuando la primera nota pasa por el traste, arrancamos la música
        if(!musica.isPlaying() && notas.size() > 0) {
            if(notas.get(0).getAltura() >= posicionPickups - retrasoCancion) {
                musica.start();
            }
        }
    }

    private void deleteOutNotes() {
        int outNotes = 0;
        // Analizamos cuántas se fueron fuera (siempre serán las primeras del arraylist)
        for(SpriteNotas n : notas) {
            if(n.getAltura() > getHeight()) outNotes++;
        }
        // Y nos cargamos la posición 0 tantas veces como notas se fueron
        for(int i=0; i<outNotes; i++) notas.remove(0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            synchronized(getHolder()){
                for(SpriteNotas n : notas){
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

    public synchronized void deleteFirstNote() {
        getNotas().remove(0);
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

    public synchronized ArrayList<SpriteNotas> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<SpriteNotas> notas) {
        this.notas = notas;
    }

    public double getTPS() {
        return generador.getTps();
    }

    public int getFPS() {
        return FPS;
    }

    public MediaPlayer getMusica() {
        return musica;
    }

    public void setMusica(MediaPlayer musica) {
        this.musica = musica;
    }
}
