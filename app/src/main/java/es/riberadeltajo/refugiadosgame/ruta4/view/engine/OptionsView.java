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
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.ruta4.view.GameMenu;

/**
 * Gameview.
 */
public class OptionsView extends SurfaceView implements GameSurface, SurfaceHolder.Callback {
    // Constantes
    public static final int SONG_SOTW = 1;
    public static final int SONG_SCOM = 2;
    public static final int SONG_TEST = 3;  // La canción de test es para probar el final
    public static final int DIF_EASY = 2;
    public static final int DIF_MEDIUM = 3;
    public static final int DIF_HARD = 4;
    public static final int PLACE_AZADI = 1;
    public static final int PLACE_GOLESTAN = 2;
    public static final int PLACE_ABDOL = 3;

    public enum Fase {INICIO,TEMA,CREDITOS,DIFICULTAD,LUGAR,INSTRUCCIONES};   // Las fases por las que pasará el menú

    private GameMenu menu;
    private GameLoopThread loop;
    private SurfaceHolder holder;
    private Bitmap fondo,logo;
    private Typeface tipografia;
    private Fase fase;
    private SoundPool chord;
    private int idChord,idButton;
    // Destinos de los botones
    private RectF oneof3Dst, twoof3Dst, threeof3Dst, nextDst, backDst, letsrockDst;
    private int selectedSong, selectedDifficulty, selectedPlace;

    public OptionsView(Context context) {
        super(context);

        this.menu = (GameMenu)context;
        fase = Fase.INICIO;
        fondo = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.streetguitarfondo);
        logo = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.streetguitarlogo);
        tipografia = Typeface.createFromAsset(context.getAssets(),"tipografias/aaaiight.ttf");
        chord = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        idChord = chord.load(context, R.raw.selectchord, 0);
        idButton = chord.load(context, R.raw.buttonsound, 0);
        // Valores por defecto
        selectedSong = SONG_SOTW;
        selectedDifficulty = DIF_EASY;
        selectedPlace = PLACE_AZADI;

        holder=getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        loop=new GameLoopThread(this,20);
        loop.setRunning(true);
        loop.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean volver = false;
        loop.setRunning(false);

        while(volver){
            try{
                loop.join();
                volver=false;
            }catch(InterruptedException ie){}
        }
    }

    public void actualizar() {

    }

    public void dibujar(Canvas canvas){
        Bitmap bback, bnext;
        int pc3 = (int) (canvas.getWidth()*0.03);
        int pc10 = (int) (canvas.getWidth()*0.1);
        int pc15 = (int) (canvas.getWidth()*0.15);
        int pc20 = (int) (canvas.getWidth()*0.2);
        // Dibujamos fondo
        canvas.drawBitmap(fondo, null, new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), null);

        bback = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.buttonback);
        bnext = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.buttonnext);
        backDst = new RectF(canvas.getWidth()/16,canvas.getHeight() - (canvas.getWidth()/16) - (canvas.getWidth()/8),(canvas.getWidth()/16) + (canvas.getWidth()/8),canvas.getHeight() - (canvas.getWidth()/16));
        nextDst = new RectF(canvas.getWidth() - (canvas.getWidth()/16) - (canvas.getWidth()/8), canvas.getHeight() - (canvas.getWidth()/16) - (canvas.getWidth()/8), canvas.getWidth() - (canvas.getWidth()/16), canvas.getHeight() - (canvas.getWidth()/16));

        Rect logoSrc = new Rect(0,0,logo.getWidth(),logo.getHeight());
        RectF logoDst = new RectF(pc10, pc10, canvas.getWidth()-pc10, pc10+(logo.getHeight() * (canvas.getWidth()-(pc10*2)) / logo.getWidth()));
        canvas.drawBitmap(logo,logoSrc,logoDst,null);

        float firstY = logoDst.bottom + pc20;
        int buttonHeight = 0;
        // Declaramos nuestro play fuera del switch para usar el botón en el cálculo de las alturas comunes
        Bitmap play = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.buttonplayred);
        buttonHeight = play.getHeight() * (canvas.getWidth()-(pc15*2)) / play.getWidth();

        switch (fase){
            case INICIO:
                Bitmap credits = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.buttoncreditsred);
                Bitmap quit = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.buttonquitred);
                Rect playSrc = new Rect(0,0,play.getWidth(),play.getHeight());
                oneof3Dst = new RectF(pc15, firstY, canvas.getWidth()-pc15,firstY+buttonHeight);
                twoof3Dst = new RectF(pc15, oneof3Dst.bottom + pc3, canvas.getWidth()-pc15, oneof3Dst.bottom + pc3 + buttonHeight);
                threeof3Dst = new RectF(pc15, twoof3Dst.bottom + pc3, canvas.getWidth()-pc15, twoof3Dst.bottom + pc3 + buttonHeight);
                canvas.drawBitmap(play, playSrc, oneof3Dst, null);
                canvas.drawBitmap(credits, playSrc, twoof3Dst, null);
                canvas.drawBitmap(quit, playSrc, threeof3Dst, null);
                break;
            case TEMA:
                Bitmap sotw,scom;
                if(selectedSong == SONG_SOTW) {
                    sotw = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.bsongsotwgreen);
                    scom = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.bsongscomred);
                } else {
                    sotw = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.bsongsotwred);
                    scom = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.bsongscomgreen);
                }
                escribeTexto(canvas,getContext().getString(R.string.selectsong),logoDst.bottom + pc10, Color.YELLOW, Color.BLACK, tipografia);
                Rect songSrc = new Rect(0,0,sotw.getWidth(),sotw.getHeight()); // Como tienen el mismo tamaño, se usa igual para ambos
                canvas.drawBitmap(sotw,songSrc,oneof3Dst,null);
                canvas.drawBitmap(scom,songSrc,twoof3Dst,null);
                canvas.drawBitmap(bback,new Rect(0,0,bback.getWidth(),bback.getHeight()),backDst,null);
                canvas.drawBitmap(bnext,new Rect(0,0,bback.getWidth(),bback.getHeight()),nextDst,null);
                break;
            case CREDITOS:
                Bitmap creds = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.streetguitarcredits);
                canvas.drawBitmap(creds,new Rect(0,0,creds.getWidth(),creds.getHeight()),new Rect(0,0,canvas.getWidth(),canvas.getHeight()),null);
                break;
            case DIFICULTAD:
                Bitmap beasy,bmedium,bhard;
                if(selectedDifficulty == DIF_EASY) {
                    beasy = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.bleveleasygreen);
                    bmedium = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.blevelmediumred);
                    bhard = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.blevelhardred);
                } else if(selectedDifficulty == DIF_MEDIUM) {
                    beasy = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.bleveleasyred);
                    bmedium = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.blevelmediumgreen);
                    bhard = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.blevelhardred);
                } else {
                    beasy = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.bleveleasyred);
                    bmedium = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.blevelmediumred);
                    bhard = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.blevelhardgreen);
                }
                escribeTexto(canvas,getContext().getString(R.string.selectdiff),logoDst.bottom + pc10, Color.YELLOW, Color.BLACK, tipografia);
                Rect levelSrc = new Rect(0,0,beasy.getWidth(),beasy.getHeight());
                oneof3Dst = new RectF(pc15, firstY, canvas.getWidth()-pc15,firstY+buttonHeight);
                twoof3Dst = new RectF(pc15, oneof3Dst.bottom + pc3, canvas.getWidth()-pc15,oneof3Dst.bottom + pc3 + buttonHeight);
                threeof3Dst = new RectF(pc15, twoof3Dst.bottom + pc3, canvas.getWidth()-pc15,twoof3Dst.bottom + pc3 + buttonHeight);
                canvas.drawBitmap(beasy, levelSrc, oneof3Dst, null);
                canvas.drawBitmap(bmedium, levelSrc, twoof3Dst, null);
                canvas.drawBitmap(bhard, levelSrc, threeof3Dst, null);
                canvas.drawBitmap(bback,new Rect(0,0,bback.getWidth(),bback.getHeight()),backDst,null);
                canvas.drawBitmap(bnext,new Rect(0,0,bback.getWidth(),bback.getHeight()),nextDst,null);
                break;
            case LUGAR:
                Bitmap bazadi, bgolestan, babdol;
                if(selectedPlace == PLACE_AZADI) {
                    bazadi = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.bplaceazadigreen);
                    bgolestan = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.bplacegolestanred);
                    babdol = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.bplaceabdolred);
                } else if(selectedPlace == PLACE_GOLESTAN) {
                    bazadi = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.bplaceazadired);
                    bgolestan = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.bplacegolestangreen);
                    babdol = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.bplaceabdolred);
                } else {
                    bazadi = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.bplaceazadired);
                    bgolestan = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.bplacegolestanred);
                    babdol = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.bplaceabdolgreen);
                }
                escribeTexto(canvas,getContext().getString(R.string.selectplace),logoDst.bottom + pc10, Color.YELLOW, Color.BLACK, tipografia);

                oneof3Dst = new RectF(pc15, firstY, canvas.getWidth()-pc15,firstY+buttonHeight);
                twoof3Dst = new RectF(pc15, oneof3Dst.bottom + pc3, canvas.getWidth()-pc15,oneof3Dst.bottom + pc3 + buttonHeight);
                threeof3Dst = new RectF(pc15, twoof3Dst.bottom + pc3, canvas.getWidth()-pc15,twoof3Dst.bottom + pc3 + buttonHeight);
                Rect placeSrc = new Rect(0,0,bazadi.getWidth(),bazadi.getHeight());
                canvas.drawBitmap(bazadi, placeSrc, oneof3Dst, null);
                canvas.drawBitmap(bgolestan, placeSrc, twoof3Dst, null);
                canvas.drawBitmap(babdol, placeSrc, threeof3Dst, null);
                //canvas.drawBitmap(bletsrock, placeSrc, letsrockDst, null);
                canvas.drawBitmap(bback,new Rect(0,0,bback.getWidth(),bback.getHeight()),backDst,null);
                canvas.drawBitmap(bnext,new Rect(0,0,bback.getWidth(),bback.getHeight()),nextDst,null);
                break;
            case INSTRUCCIONES:
                Bitmap bletsrock, demoScreen;
                bletsrock = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.buttonletsrock);
                demoScreen = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.streetguitarinstructions);

                Rect instSrc = new Rect(0,0,bletsrock.getWidth(),bletsrock.getHeight());
                letsrockDst = new RectF(pc15, canvas.getHeight()-buttonHeight-pc10, canvas.getWidth()-pc15, canvas.getHeight()-pc10);
                RectF demoScreenDST = new RectF(pc15,pc10,canvas.getWidth()-pc15, letsrockDst.top - pc10);

                canvas.drawBitmap(demoScreen,new Rect(0,0,demoScreen.getWidth(),demoScreen.getHeight()),demoScreenDST,null);
                canvas.drawBitmap(bletsrock, instSrc, letsrockDst, null);
                canvas.drawBitmap(logo,logoSrc,logoDst,null);   // Plantamos de nuevo el logo encima del demo
                canvas.drawBitmap(bback,new Rect(0,0,bback.getWidth(),bback.getHeight()),backDst,null);
                break;
        }
    }

    @Override
    public void detener() {
        // En teoría no hace falta, porque ya se detiene en el holder destroyed
        getLoop().setRunning(false);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            synchronized(getHolder()){
                switch (fase){
                    case INICIO:
                        // Play
                        if(event.getX() >= oneof3Dst.left && event.getX() <= oneof3Dst.right && event.getY() >= oneof3Dst.top && event.getY() <= oneof3Dst.bottom) {
                            chord.play(idChord, 1, 1, 1, 0, 1);
                            fase = Fase.TEMA;
                        }
                        // Credits
                        if(event.getX() >= twoof3Dst.left && event.getX() <= twoof3Dst.right && event.getY() >= twoof3Dst.top && event.getY() <= twoof3Dst.bottom) {
                            chord.play(idChord, 1, 1, 1, 0, 1);
                            fase = Fase.CREDITOS;
                        }
                        // Quit
                        if(event.getX() >= threeof3Dst.left && event.getX() <= threeof3Dst.right && event.getY() >= threeof3Dst.top && event.getY() <= threeof3Dst.bottom) {
                            chord.play(idChord, 1, 1, 1, 0, 1);
                            getLoop().setRunning(false);
                            menu.onBackPressed();
                        }
                        break;
                    case TEMA:
                        // Smoke on the Water
                        if(event.getX() >= oneof3Dst.left && event.getX() <= oneof3Dst.right && event.getY() >= oneof3Dst.top && event.getY() <= oneof3Dst.bottom && selectedSong != SONG_SOTW) {
                            chord.play(idButton, 1, 1, 1, 0, 1);
                            selectedSong = SONG_SOTW;
                        }
                        // Sweet Child of Mine
                        if(event.getX() >= twoof3Dst.left && event.getX() <= twoof3Dst.right && event.getY() >= twoof3Dst.top && event.getY() <= twoof3Dst.bottom && selectedSong != SONG_SCOM) {
                            chord.play(idButton, 1, 1, 1, 0, 1);
                            selectedSong = SONG_SCOM;
                        }
                        // NEXT
                        if(event.getX() >= nextDst.left && event.getX() <= nextDst.right && event.getY() >= nextDst.top && event.getY() <= nextDst.bottom) {
                            chord.play(idChord, 1, 1, 1, 0, 1);
                            fase = Fase.DIFICULTAD;
                        }
                        // BACK
                        if(event.getX() >= backDst.left && event.getX() <= backDst.right && event.getY() >= backDst.top && event.getY() <= backDst.bottom) {
                            chord.play(idChord, 1, 1, 1, 0, 1);
                            fase = Fase.INICIO;
                        }
                        break;
                    case CREDITOS:
                        chord.play(idButton, 1, 1, 1, 0, 1);
                        fase = Fase.INICIO;
                        break;
                    case DIFICULTAD:
                        // Easy
                        if(event.getX() >= oneof3Dst.left && event.getX() <= oneof3Dst.right && event.getY() >= oneof3Dst.top && event.getY() <= oneof3Dst.bottom && selectedDifficulty != DIF_EASY) {
                            chord.play(idButton, 1, 1, 1, 0, 1);
                            selectedDifficulty = DIF_EASY;
                        }
                        // Medium
                        if(event.getX() >= twoof3Dst.left && event.getX() <= twoof3Dst.right && event.getY() >= twoof3Dst.top && event.getY() <= twoof3Dst.bottom && selectedDifficulty != DIF_MEDIUM) {
                            chord.play(idButton, 1, 1, 1, 0, 1);
                            selectedDifficulty = DIF_MEDIUM;
                        }
                        // Hard
                        if(event.getX() >= threeof3Dst.left && event.getX() <= threeof3Dst.right && event.getY() >= threeof3Dst.top && event.getY() <= threeof3Dst.bottom && selectedDifficulty != DIF_HARD) {
                            chord.play(idButton, 1, 1, 1, 0, 1);
                            selectedDifficulty = DIF_HARD;
                        }
                        // NEXT
                        if(event.getX() >= nextDst.left && event.getX() <= nextDst.right && event.getY() >= nextDst.top && event.getY() <= nextDst.bottom) {
                            chord.play(idChord, 1, 1, 1, 0, 1);
                            fase = Fase.LUGAR;
                        }
                        // BACK
                        if(event.getX() >= backDst.left && event.getX() <= backDst.right && event.getY() >= backDst.top && event.getY() <= backDst.bottom) {
                            chord.play(idChord, 1, 1, 1, 0, 1);
                            fase = Fase.TEMA;
                        }
                        break;
                    case LUGAR:
                        // Azadi Tower
                        if(event.getX() >= oneof3Dst.left && event.getX() <= oneof3Dst.right && event.getY() >= oneof3Dst.top && event.getY() <= oneof3Dst.bottom && selectedPlace != PLACE_AZADI) {
                            chord.play(idButton, 1, 1, 1, 0, 1);
                            selectedPlace = PLACE_AZADI;
                        }
                        // Golestan Palace
                        if(event.getX() >= twoof3Dst.left && event.getX() <= twoof3Dst.right && event.getY() >= twoof3Dst.top && event.getY() <= twoof3Dst.bottom && selectedPlace != PLACE_GOLESTAN) {
                            chord.play(idButton, 1, 1, 1, 0, 1);
                            selectedPlace = PLACE_GOLESTAN;
                        }
                        // Shah Abdol Azim Shrine
                        if(event.getX() >= threeof3Dst.left && event.getX() <= threeof3Dst.right && event.getY() >= threeof3Dst.top && event.getY() <= threeof3Dst.bottom && selectedPlace != PLACE_ABDOL) {
                            chord.play(idButton, 1, 1, 1, 0, 1);
                            selectedPlace = PLACE_ABDOL;
                        }
                        // NEXT
                        if(event.getX() >= nextDst.left && event.getX() <= nextDst.right && event.getY() >= nextDst.top && event.getY() <= nextDst.bottom) {
                            chord.play(idChord, 1, 1, 1, 0, 1);
                            fase = Fase.INSTRUCCIONES;
                        }
                        // BACK
                        if(event.getX() >= backDst.left && event.getX() <= backDst.right && event.getY() >= backDst.top && event.getY() <= backDst.bottom) {
                            chord.play(idChord, 1, 1, 1, 0, 1);
                            fase = Fase.DIFICULTAD;
                        }
                        break;
                    case INSTRUCCIONES:
                        // LET'S PLAY !!
                        if(letsrockDst != null && event.getX() >= letsrockDst.left && event.getX() <= letsrockDst.right && event.getY() >= letsrockDst.top && event.getY() <= letsrockDst.bottom) {
                            chord.play(idChord, 1, 1, 1, 0, 1);
                            this.menu.jugar(selectedSong,selectedDifficulty,selectedPlace);
                        }
                        // BACK
                        if(event.getX() >= backDst.left && event.getX() <= backDst.right && event.getY() >= backDst.top && event.getY() <= backDst.bottom) {
                            chord.play(idChord, 1, 1, 1, 0, 1);
                            fase = Fase.LUGAR;
                        }
                        break;
                }
            }
        }
        return true;
    }

    /* Texto con sombra */
    private void escribeTexto(Canvas canvas, String texto, float y, int color, int sombra, Typeface fuente) {
        float x, size = 100;
        Paint paint = new Paint();
        paint.setColor(sombra);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(size);
        paint.setStrokeWidth(size/8);
        if(fuente != null) paint.setTypeface(fuente);
        /* Vamos reduciendo el tamaño del texto hasta que nos ajuste bien a la pantalla */
        while((x = (canvas.getWidth() - paint.measureText(texto)) / 2) < 0) {
            size-=10;
            paint.setTextSize(size);
            paint.setStrokeWidth(size/8);
        }
        canvas.drawText(texto,x,y,paint);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(texto,x,y,paint);
    }

    public GameLoopThread getLoop() {
        return loop;
    }

    public void setLoop(GameLoopThread loop) {
        this.loop = loop;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }
}
