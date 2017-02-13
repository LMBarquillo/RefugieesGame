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
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.ruta4.view.models.SpriteMensajes;
import es.riberadeltajo.refugiadosgame.ruta4.view.models.SpriteNotas;
import es.riberadeltajo.refugiadosgame.ruta4.view.models.SpriteXplosion;

/**
 * Gameview.
 */
public class GameView extends SurfaceView {
    private final int FPS = 60;
    private NoteGenerator generador;
    private ArrayList<SpriteNotas> notas;
    private ArrayList<SpriteXplosion> explosiones;
    private SpriteMensajes mensaje;
    private GameLoopThread loop;
    private SurfaceHolder holder;
    private MediaPlayer musica, error;
    private Bitmap fondo, pickups, cuerda;
    private Typeface tipoPuntos;
    private int puntuacion, seguidas;

    public GameView(Context context) {
        super(context);
        loop=new GameLoopThread(this);
        notas = new ArrayList<SpriteNotas>();
        explosiones = new ArrayList<SpriteXplosion>();
        generador = new NoteGenerator(context,this,"songs/smokeonthewater.txt");
        // Inicializamos bitmaps
        fondo = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.tehranazaditower);
        pickups = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.pickupsuhrv60);
        cuerda = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.guitarstring);
        // Fuentes
        tipoPuntos = Typeface.createFromAsset(context.getAssets(),"tipografias/aaaiight.ttf");
        puntuacion = 0;
        seguidas = 0;

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
                musica.release();
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
        // Dibujamos fondo
        canvas.drawBitmap(fondo, null, new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), null);

        // Pastillas (se reducen por proporcionalidad en base a la anchura de la pantalla)
        canvas.drawBitmap(pickups,
                new Rect(0,0,pickups.getWidth(),pickups.getHeight()),
                new RectF(0, (float)(canvas.getHeight()-(pickups.getHeight()*canvas.getHeight()/pickups.getWidth())), canvas.getWidth(), canvas.getHeight()),
                null);

        // Cuerdas
        for(int pos=1; pos<=4; pos++) {
            int cx = (canvas.getWidth()/5*pos) - (cuerda.getWidth()/2);
            canvas.drawBitmap(cuerda,null, new RectF(cx,0,cx+cuerda.getWidth(),canvas.getHeight()),null);
        }

        // Dibujamos cada nota que tengamos en pantalla
        for(SpriteNotas n : getNotas()) n.draw(canvas);

        // Sprites temporales
        for(SpriteXplosion x : getExplosiones()) x.draw(canvas);

        // Dibujamos los mensajes de ánimo
        if(getMensaje() != null) getMensaje().draw(canvas);

        // Monitores y/o contadores
        escribeTexto(canvas,String.valueOf(puntuacion),50,100,canvas.getWidth()/10,Color.YELLOW,Color.BLACK,tipoPuntos);

        // Eliminamos las notas que salieron fuera
        deleteOutNotes();

        // Eliminamos las explosiones finalizadas
        deleteFinishedExplosions();

        // Ponemos mensajes de ánimo (si toca)
        checkSeguidas();

        controlMusic(canvas.getHeight()-pickups.getHeight());
    }

    private void deleteFinishedExplosions() {
        int finished = 0;
        for(SpriteXplosion x : getExplosiones())
            if(x.isFinished()) finished++;
        for(int i=0; i<finished; i++) getExplosiones().remove(0);
    }

    private void controlMusic(int posicionPickups) {
        int retrasoCancion = 430;
        // cuando la primera nota pasa por el traste, arrancamos la música
        if(!getMusica().isPlaying() && getNotas().size() > 0) {
            if(getNotas().get(0).getAltura() >= posicionPickups - retrasoCancion) {
                getMusica().start();
            }
        }
        if(getMusica().getCurrentPosition() >= getMusica().getDuration()) {
            // Canción terminada
            loop.setRunning(false);
            generador.setRunning(false);
            musica.stop();
            musica.release();
        }
    }

    // Si cogemos varias notas seguidas, conseguimos más puntos y un mensaje de ánimo
    private void checkSeguidas() {
        if(getMensaje() == null) {
            switch (getSeguidas()) {
                case 10:
                    setMensaje(new SpriteMensajes(this,500,getContext().getString(R.string.notes10)));
                    setPuntuacion(getPuntuacion()+10);
                    break;
                case 20:
                    setMensaje(new SpriteMensajes(this,500,getContext().getString(R.string.notes20)));
                    setPuntuacion(getPuntuacion()+20);
                    break;
                case 30:
                    setMensaje(new SpriteMensajes(this,500,getContext().getString(R.string.notes30)));
                    setPuntuacion(getPuntuacion()+30);
                    break;
                case 40:
                    setMensaje(new SpriteMensajes(this,500,getContext().getString(R.string.notes40)));
                    setPuntuacion(getPuntuacion()+40);
                    break;
                case 50:
                    setMensaje(new SpriteMensajes(this,500,getContext().getString(R.string.notes50)));
                    setPuntuacion(getPuntuacion()+50);
                    break;
                case 100:
                    setMensaje(new SpriteMensajes(this,500,getContext().getString(R.string.notes100)));
                    setPuntuacion(getPuntuacion()+100);
                    break;
            }
        }
    }

    private void deleteOutNotes() {
        int outNotes = 0;
        // Analizamos cuántas se fueron fuera (siempre serán las primeras del arraylist)
        for(SpriteNotas n : getNotas()) {
            if(n.getAltura() > getHeight()) {
                outNotes++;
            }
        }
        // Y nos cargamos la posición 0 tantas veces como notas se fueron
        for(int i=0; i<outNotes; i++) getNotas().remove(0);
        // Reiniciamos el contador de notas seguidas
        if(outNotes>0) setSeguidas(0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            synchronized(getHolder()){
                for(SpriteNotas n : getNotas()){
                    // Si pulsamos una nota...
                    if(n.isCollition(event.getX(),event.getY())) {
                        // Comprobamos si está dentro del traste
                        if(event.getY() > (getHeight()-pickups.getHeight()) && event.getY() < getHeight()) {
                            // Está dentro (mola). Lo primero es subir la puntuación
                            setPuntuacion(getPuntuacion()+1);
                            // Ahora nos cargamos la nota
                            getNotas().remove(n);
                            // Aumentamos nuestro contador de notas seguidas
                            setSeguidas(getSeguidas()+1);
                            // Y mostramos un bonito efecto visual
                            getExplosiones().add(new SpriteXplosion(this,new Rect(n.getPosx(),n.getAltura(),n.getPosx()+n.getSizeNota(),n.getAltura()+n.getSizeNota())));
                        } else {
                            // Está fuera (la cagaste). Reiniciamos el contador de notas seguidas
                            setSeguidas(0);
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

    public synchronized ArrayList<SpriteXplosion> getExplosiones() {
        return explosiones;
    }

    public void setExplosiones(ArrayList<SpriteXplosion> explosiones) {
        this.explosiones = explosiones;
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

    public SpriteMensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(SpriteMensajes mensaje) {
        this.mensaje = mensaje;
    }

    public int getSeguidas() {
        return seguidas;
    }

    public void setSeguidas(int seguidas) {
        this.seguidas = seguidas;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}
