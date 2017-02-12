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
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.ruta4.view.models.SpriteMensajes;
import es.riberadeltajo.refugiadosgame.ruta4.view.models.SpriteNotas;

/**
 * Gameview.
 */
public class GameView extends SurfaceView {
    private final int FPS = 60;
    private NoteGenerator generador;
    private ArrayList<SpriteNotas> notas;
    private ArrayList<SpriteMensajes> mensajes;
    private GameLoopThread loop;
    private SurfaceHolder holder;
    private MediaPlayer musica, error;
    private Bitmap fondo, pickups, cuerda;
    private Typeface tipoPuntos;
    private int puntuacion, seguidas, testing;
    //private double proporcion;

    public GameView(Context context) {
        super(context);
        loop=new GameLoopThread(this);
        notas = new ArrayList<SpriteNotas>();
        mensajes = new ArrayList<SpriteMensajes>();
        generador = new NoteGenerator(context,this,"songs/smokeonthewater.txt");
        // Inicializamos bitmaps
        fondo = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.tehranazaditower);
        pickups = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.whitepickups);
        cuerda = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.guitarstring);
        // Fuentes
        tipoPuntos = Typeface.createFromAsset(context.getAssets(),"tipografias/aaaiight.ttf");
        puntuacion = 0;
        seguidas = 0;
        testing = 0;

        setMusica(new MediaPlayer().create(context,R.raw.smokeonthewater));
        setError(new MediaPlayer().create(context,R.raw.guitarerror));

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

    // MIRAR LA LETRA STREET GATHERING

    public void draw(Canvas canvas){
        // Dibujamos fondo
        canvas.drawBitmap(fondo, null, new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), null);

        // Pastillas
        //proporcion = pickups.getWidth()/canvas.getWidth();
        canvas.drawBitmap(pickups,
                new Rect(0,0,pickups.getWidth(),pickups.getHeight()),
                new RectF(0, canvas.getHeight()-pickups.getHeight()/(pickups.getWidth()/canvas.getWidth()), canvas.getWidth(), canvas.getHeight()),
                null);

        // Cuerdas
        for(int pos=1; pos<=4; pos++) {
            int cx = (canvas.getWidth()/5*pos) - (cuerda.getWidth()/2);
            canvas.drawBitmap(cuerda,null, new RectF(cx,0,cx+cuerda.getWidth(),canvas.getHeight()),null);
        }

        // Dibujamos cada nota que tengamos en pantalla
        for(SpriteNotas n : getNotas()) {
            n.draw(canvas);
        }

        // Dibujamos los mensajes de ánimo
        for(SpriteMensajes m : getMensajes()) {
            m.draw(canvas);
        }

        // Monitores y/o contadores
        escribeTexto(canvas,String.valueOf(puntuacion),50,100,canvas.getWidth()/10,Color.YELLOW,Color.BLACK,tipoPuntos);
        //escribeTexto(canvas,String.valueOf(notas.size()),50,100,80,Color.WHITE,Color.BLACK,tipografia);

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
        // Reiniciamos el contador de notas seguidas
        if(outNotes>0) seguidas = 0;
        testing ++;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            synchronized(getHolder()){
                for(SpriteNotas n : notas){
                    // Si pulsamos una nota...
                    if(n.isCollition(event.getX(),event.getY())) {
                        // Comprobamos si está dentro del traste
                        if(event.getY() > (getHeight()-pickups.getHeight()) && event.getY() < getHeight()) {
                            // Está dentro (mola). Lo primero es subir la puntuación
                            puntuacion++;
                            // Ahora nos cargamos la nota
                            notas.remove(n);
                            // Y mostramos un bonito efecto visual

                        } else {
                            // Está fuera (la cagaste). Reiniciamos el contador de notas seguidas
                            seguidas = 0;
                            // Y escuchamos un desagradable sonido de guitarra desafinando.
                            getError().start();
                        }
                        break;
                    }
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

    /* Texto con sombra */
    private void escribeTexto(Canvas canvas, String texto, float x, float y, float size, int color, int sombra, Typeface fuente) {
        Paint paint = new Paint();
        paint.setColor(sombra);
        paint.setStrokeWidth(size/8);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(size);
        if(fuente != null) paint.setTypeface(fuente);
        canvas.drawText(texto,x,y,paint);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(texto,x,y,paint);

    }

    /* Sin sombra */
    private void escribeTexto(Canvas canvas, String texto, float x, float y, float size, int color, Typeface fuente) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(size);
        if(fuente != null) paint.setTypeface(fuente);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(texto,x,y,paint);
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

    public ArrayList<SpriteMensajes> getMensajes() {
        return mensajes;
    }

    public void setMensajes(ArrayList<SpriteMensajes> mensajes) {
        this.mensajes = mensajes;
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

    public MediaPlayer getError() {
        return error;
    }

    public void setError(MediaPlayer error) {
        this.error = error;
    }
}
