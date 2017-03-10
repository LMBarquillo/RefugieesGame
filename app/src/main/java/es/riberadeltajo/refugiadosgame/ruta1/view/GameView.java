package es.riberadeltajo.refugiadosgame.ruta1.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Collections;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.common.models.PlayerStatus;

/**
 * Created by Adri on 03/02/2017.
 */

public class GameView extends SurfaceView {
    private final int TIEMPO_MAX_EASY=90;
    private final int TIEMPO_MAX_MEDIUM=90;
    private final int TIEMPO_MAX_HARD=120;
    private final int TIEMPO_MAX_EXTREME=150;
    private final int MAX_POINTS_EASY=150;
    private final int MAX_POINTS_MEDIUM=250;
    private final int MAX_POINTS_HARD=350;
    private final int MAX_POINTS_EXTREME=450;
    private Bitmap player,coin1,coin2,coin5,coin10,fondo,ticket,life1,life2,life3,
            moneyBag,sandClock,bombaArriba,bombaAbajo,bombaDrcha,bombaIzqda,fuegoArriba,fuegoAbajo,fuegoDrcha,fuegoIzqda;
    private SurfaceHolder holder;
    private ArrayList<Disparo> disparos;
    private ArrayList<Disparo>misiles;
    private GameLoop loop;
    private int corx,cory;
    private int xSpeed,ySpeed;
    private ArrayList<Sprite> sprites;
    private ArrayList<Monedas> monedas;
    private ArrayList<Ticket> tickets;
    private int puntuacion;
    private long crono,inicio;
    private Sprite jug;
    private Madrid_Arcade contexto;
    private int vidas;
    private Typeface font;
    private int ran;
    private int ran2;
    private MediaPlayer musica;
    private SoundPool soundPool;
    private int idDisparoBomba,idDisparoMisil,idGrito,idCaeMoneda,
            idCoin1,idCoin2,idCoin5,idCoin10,idGameOver,idWin;

    public GameView(Context context){
        super(context);
        setContexto((Madrid_Arcade)context);
        loop=new GameLoop(this);
        setCorx(0);
        setCory(0);
        setxSpeed(0);
        setySpeed(0);
        setInicio(System.currentTimeMillis());
        setPuntuacion(0);
        setVidas(6);
        misiles=new ArrayList<>();
        disparos=new ArrayList<Disparo>();
        sprites=new ArrayList<Sprite>();
        monedas=new ArrayList<Monedas>();
        tickets=new ArrayList<Ticket>();
        font = Typeface.createFromAsset(context.getAssets(),"tipografias/madrid_font.ttf");
        soundPool = new SoundPool(32, AudioManager.STREAM_MUSIC , 0);
        idDisparoBomba = soundPool.load(context, R.raw.madrid_misil, 4);
        idDisparoMisil = soundPool.load(context, R.raw.madrid_shot, 4);
        idGrito = soundPool.load(context, R.raw.madrid_pain_loud, 2);
        idCaeMoneda = soundPool.load(context, R.raw.madrid_coins_falling, 3);
        idCoin1 = soundPool.load(context, R.raw.madrid_coin1_effect, 1);
        idCoin2 = soundPool.load(context, R.raw.madrid_coin2_effect, 1);
        idCoin5 = soundPool.load(context, R.raw.madrid_coin5_effect, 1);
        idCoin10 = soundPool.load(context, R.raw.madrid_coin10_effect, 1);
        idWin = soundPool.load(context, R.raw.madrid_win_effect, 0);
        idGameOver = soundPool.load(context, R.raw.madrid_gameover_effect, 0);
        holder=getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                stop();
            }
        });
    }

    public int getCorx() {
        return corx;
    }

    public void setCorx(int corx) {
        this.corx = corx;
    }

    public int getCory() {
        return cory;
    }

    public void setCory(int cory) {
        this.cory = cory;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Madrid_Arcade getContexto() {
        return contexto;
    }

    public void setContexto(Madrid_Arcade contexto) {
        this.contexto = contexto;
    }

    public Sprite getJug() {
        return jug;
    }

    public void setJug(Sprite jug) {
        this.jug = jug;
    }

    public long getCrono() {
        return crono;
    }

    public void setCrono(long crono) {
        this.crono = crono;
    }

    public long getInicio() {
        return inicio;
    }

    public void setInicio(long inicio) {
        this.inicio = inicio;
    }

    public ArrayList<Monedas> getMonedas() {
        return monedas;
    }

    public void setMonedas(ArrayList<Monedas> monedas) {
        this.monedas = monedas;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public GameLoop getLoop() {
        return loop;
    }

    public void setLoop(GameLoop loop) {
        this.loop = loop;
    }

    //Creo el Sprite y le hago un setJug() para pasarle después la posición en el onTouchEvent
    //Creo las monedas de distinto valor y las barajeo para que se pinten posteriormente de forma aleatoria
    //Creo el ticket que aparecerá cuando obtenga la puntuación requerida
    public void createSprite(){
        player = BitmapFactory.decodeResource(getResources(), R.drawable.madrid_sprite);
        player = Bitmap.createScaledBitmap(player, (int)(getWidth()*1.45), (int)(getHeight()*0.6), false);
        coin1 = BitmapFactory.decodeResource(getResources(), R.drawable.madrid_coin1);
        coin1 = Bitmap.createScaledBitmap(coin1, (int)(getWidth()*0.35), (int)(getHeight()*0.035), false);
        coin2 = BitmapFactory.decodeResource(getResources(), R.drawable.madrid_coin2);
        coin2 = Bitmap.createScaledBitmap(coin2, (int)(getWidth()*0.35), (int)(getHeight()*0.035), false);
        coin5 = BitmapFactory.decodeResource(getResources(), R.drawable.madrid_coin5);
        coin5 = Bitmap.createScaledBitmap(coin5, (int)(getWidth()*0.35), (int)(getHeight()*0.035), false);
        coin10 = BitmapFactory.decodeResource(getResources(), R.drawable.madrid_coin10);
        coin10 = Bitmap.createScaledBitmap(coin10, (int)(getWidth()*0.35), (int)(getHeight()*0.035), false);
        bombaArriba = BitmapFactory.decodeResource(getResources(), R.drawable.madrid_bomba_up);
        bombaArriba = Bitmap.createScaledBitmap(bombaArriba, (int)(getWidth()*0.05), (int)(getHeight()*0.06), false);
        bombaAbajo = BitmapFactory.decodeResource(getResources(), R.drawable.madrid_bomba_down);
        bombaAbajo = Bitmap.createScaledBitmap(bombaAbajo, (int)(getWidth()*0.05), (int)(getHeight()*0.06), false);
        bombaDrcha = BitmapFactory.decodeResource(getResources(), R.drawable.madrid_bomba_right);
        bombaDrcha = Bitmap.createScaledBitmap(bombaDrcha, (int)(getWidth()*0.1), (int)(getHeight()*0.03), false);
        bombaIzqda = BitmapFactory.decodeResource(getResources(), R.drawable.madrid_bomba_left);
        bombaIzqda = Bitmap.createScaledBitmap(bombaIzqda, (int)(getWidth()*0.1), (int)(getHeight()*0.03), false);
        fuegoArriba = BitmapFactory.decodeResource(getResources(), R.drawable.madrid_fire_up);
        fuegoArriba = Bitmap.createScaledBitmap(fuegoArriba, (int)(getWidth()*0.02), (int)(getHeight()*0.03), false);
        fuegoAbajo = BitmapFactory.decodeResource(getResources(), R.drawable.madrid_fire_down);
        fuegoAbajo = Bitmap.createScaledBitmap(fuegoAbajo, (int)(getWidth()*0.02), (int)(getHeight()*0.03), false);
        fuegoDrcha = BitmapFactory.decodeResource(getResources(), R.drawable.madrid_fire_right);
        fuegoDrcha = Bitmap.createScaledBitmap(fuegoDrcha, (int)(getWidth()*0.06), (int)(getHeight()*0.01), false);
        fuegoIzqda = BitmapFactory.decodeResource(getResources(), R.drawable.madrid_fire_left);
        fuegoIzqda = Bitmap.createScaledBitmap(fuegoIzqda, (int)(getWidth()*0.06), (int)(getHeight()*0.01), false);
        ticket = BitmapFactory.decodeResource(getResources(), R.drawable.madrid_ticket);
        ticket = Bitmap.createScaledBitmap(ticket, (int)(getWidth()*0.15), (int)(getHeight()*0.05), false);
        setJug(new Sprite(this,player,getVidas()));
        sprites.add(getJug());
        crearMonedasYDisparosPorDificultad();
        Collections.shuffle(getMonedas());
        tickets.add(new Ticket(tickets, this, ticket));
    }

    public void crearMonedasYDisparosPorDificultad(){
        //NIVEL FÁCIL
        if(PlayerStatus.getInstancia(getContexto()).getDinero()==350){
            musica= MediaPlayer.create(getContexto(),R.raw.madrid_easy);
            musica.setVolume(0.5f,0.5f);
            musica.setLooping(true);
            musica.start();
            for(int i=0;i<5;i++){
                disparos.add(getDisparos());
            }
            for(int i=0;i<263;i++) {
                monedas.add(new Monedas(monedas, this, coin1, 1));
            }
            for(int i=0;i<100;i++) {
                monedas.add(new Monedas(monedas, this, coin2, 2));
            }
            for(int i=0;i<30;i++) {
                monedas.add(new Monedas(monedas, this, coin5, 5));
            }
            for(int i=0;i<7;i++) {
                monedas.add(new Monedas(monedas, this, coin10, 10));
            }
        }
        //NIVEL MEDIO
        else if(PlayerStatus.getInstancia(getContexto()).getDinero()==250){
            musica= MediaPlayer.create(getContexto(),R.raw.madrid_medium);
            musica.setVolume(0.5f,0.5f);
            musica.setLooping(true);
            musica.start();
            for(int i=0;i<5;i++){
                disparos.add(getDisparos());
            }
            for(int i=0;i<213;i++) {
                monedas.add(new Monedas(monedas, this, coin1, 1));
            }
            for(int i=0;i<125;i++) {
                monedas.add(new Monedas(monedas, this, coin2, 2));
            }
            for(int i=0;i<50;i++) {
                monedas.add(new Monedas(monedas, this, coin5, 5));
            }
            for(int i=0;i<12;i++) {
                monedas.add(new Monedas(monedas, this, coin10, 10));
            }
        }
        //NIVEL DIFÍCIL
        else if(PlayerStatus.getInstancia(getContexto()).getDinero()==150){
            musica= MediaPlayer.create(getContexto(),R.raw.madrid_hard);
            musica.setVolume(0.5f,0.5f);
            musica.setLooping(true);
            musica.start();
            for(int i=0;i<7;i++){
                disparos.add(getDisparos());
            }
            for(int i=0;i<163;i++) {
                monedas.add(new Monedas(monedas, this, coin1, 1));
            }
            for(int i=0;i<150;i++) {
                monedas.add(new Monedas(monedas, this, coin2, 2));
            }
            for(int i=0;i<70;i++) {
                monedas.add(new Monedas(monedas, this, coin5, 5));
            }
            for(int i=0;i<17;i++) {
                monedas.add(new Monedas(monedas, this, coin10, 10));
            }
        }
        //NIVEL EXTREMO
        else if(PlayerStatus.getInstancia(getContexto()).getDinero()==50){
            musica= MediaPlayer.create(getContexto(),R.raw.madrid_extreme);
            musica.setVolume(0.5f,0.5f);
            musica.setLooping(true);
            musica.start();
            for(int i=0;i<8;i++){
                disparos.add(getDisparos());
            }
            for(int i=0;i<88;i++) {
                monedas.add(new Monedas(monedas, this, coin1, 1));
            }
            for(int i=0;i<200;i++) {
                monedas.add(new Monedas(monedas, this, coin2, 2));
            }
            for(int i=0;i<90;i++) {
                monedas.add(new Monedas(monedas, this, coin5, 5));
            }
            for(int i=0;i<22;i++) {
                monedas.add(new Monedas(monedas, this, coin10, 10));
            }
        }
    }

    private Disparo getDisparos() {
        ran=(int)(Math.random()*4)+1;
        ran2=(int)(Math.random()*2)+1;
        Disparo disp;
        if(ran==1){
            if(ran2==1){
                disp=new Disparo(this,bombaIzqda,2,ran,10);
                soundPool.play(idDisparoBomba,0,(float)0.09,4,0,1);
                //NIVEL FÁCIL
                if(PlayerStatus.getInstancia(getContexto()).getDinero()==350) {
                    disp.setxSpeed((int)(disp.getxSpeed()*1.2));
                    disp.setySpeed((int)(disp.getySpeed()*1.2));
                }
                //NIVEL MEDIO
                else if(PlayerStatus.getInstancia(getContexto()).getDinero()==250) {
                    disp.setxSpeed((int)(disp.getxSpeed()*1.5));
                    disp.setySpeed((int)(disp.getySpeed()*1.5));
                    disp.setPuntos((int)(disp.getPuntos()*1.2));
                }
                //NIVEL DIFICIL
                else if(PlayerStatus.getInstancia(getContexto()).getDinero()==150) {
                    disp.setxSpeed((int)(disp.getxSpeed()*2));
                    disp.setySpeed((int)(disp.getySpeed()*2));
                    disp.setPuntos((int)(disp.getPuntos()*1.5));
                }
                //NIVEL EXTREMO
                else if(PlayerStatus.getInstancia(getContexto()).getDinero()==50) {
                    disp.setxSpeed((int)(disp.getxSpeed()*2));
                    disp.setySpeed((int)(disp.getySpeed()*2));
                    disp.setPuntos(disp.getPuntos()*2);
                }
            }else {
                disp = new Disparo(this, fuegoIzqda, 1, ran, 5);
                //NIVEL FÁCIL
                if(PlayerStatus.getInstancia(getContexto()).getDinero()==350) {
                    disp.setxSpeed((int)(disp.getxSpeed()*2.2));
                }
                //NIVEL MEDIO
                else if(PlayerStatus.getInstancia(getContexto()).getDinero()==250) {
                    disp.setxSpeed((int)(disp.getxSpeed()*2.5));
                    disp.setPuntos((int)(disp.getPuntos()*1.2));
                }
                //NIVEL DIFICIL
                else if(PlayerStatus.getInstancia(getContexto()).getDinero()==150) {
                    disp.setxSpeed((int)(disp.getxSpeed()*4));
                    disp.setPuntos((int)(disp.getPuntos()*1.5));
                }
                //NIVEL EXTREMO
                else if(PlayerStatus.getInstancia(getContexto()).getDinero()==50) {
                    disp.setxSpeed((int)(disp.getxSpeed()*4));
                    disp.setPuntos(disp.getPuntos()*2);
                }
                soundPool.play(idDisparoMisil,0,(float)0.09,4,0,1);
            }
        }else if(ran==2) {
            if(ran2==1){
                disp=new Disparo(this,bombaArriba,2,ran,10);
                soundPool.play(idDisparoBomba,(float)0.07,(float)0.07,4,0,1);
                //NIVEL FÁCIL
                if(PlayerStatus.getInstancia(getContexto()).getDinero()==350) {
                    disp.setxSpeed((int)(disp.getxSpeed()*1.2));
                    disp.setySpeed((int)(disp.getySpeed()*1.2));
                }
                //NIVEL MEDIO
                else if(PlayerStatus.getInstancia(getContexto()).getDinero()==250) {
                    disp.setxSpeed((int)(disp.getxSpeed()*1.5));
                    disp.setySpeed((int)(disp.getySpeed()*1.5));
                    disp.setPuntos((int)(disp.getPuntos()*1.2));
                }
                //NIVEL DIFICIL
                else if(PlayerStatus.getInstancia(getContexto()).getDinero()==150) {
                    disp.setxSpeed((int)(disp.getxSpeed()*2));
                    disp.setySpeed((int)(disp.getySpeed()*2));
                    disp.setPuntos((int)(disp.getPuntos()*1.5));
                }
                //NIVEL EXTREMO
                else if(PlayerStatus.getInstancia(getContexto()).getDinero()==50) {
                    disp.setxSpeed((int)(disp.getxSpeed()*2));
                    disp.setySpeed((int)(disp.getySpeed()*2));
                    disp.setPuntos(disp.getPuntos()*2);
                }
            }else {
                disp = new Disparo(this, fuegoArriba, 1, ran, 5);
                //NIVEL FÁCIL
                if(PlayerStatus.getInstancia(getContexto()).getDinero()==350) {
                    disp.setySpeed((int)(disp.getySpeed()*2.2));
                }
                //NIVEL MEDIO
                else if(PlayerStatus.getInstancia(getContexto()).getDinero()==250) {
                    disp.setySpeed((int)(disp.getySpeed()*2.5));
                    disp.setPuntos((int)(disp.getPuntos()*1.2));
                }
                //NIVEL DIFICIL
                else if(PlayerStatus.getInstancia(getContexto()).getDinero()==150) {
                    disp.setySpeed((int)(disp.getySpeed()*4));
                    disp.setPuntos((int)(disp.getPuntos()*1.5));
                }
                //NIVEL EXTREMO
                else if(PlayerStatus.getInstancia(getContexto()).getDinero()==50) {
                    disp.setySpeed((int)(disp.getySpeed()*4));
                    disp.setPuntos(disp.getPuntos()*2);
                }
                soundPool.play(idDisparoMisil,(float)0.07,(float)0.07,4,0,1);
            }
        }else if(ran==3){
            if(ran2==1){
                disp=new Disparo(this,bombaDrcha,2,ran,10);
                soundPool.play(idDisparoBomba,(float)0.09,0,4,0,1);
                //NIVEL FÁCIL
                if(PlayerStatus.getInstancia(getContexto()).getDinero()==350) {
                    disp.setxSpeed((int)(disp.getxSpeed()*1.2));
                    disp.setySpeed((int)(disp.getySpeed()*1.2));
                }
                //NIVEL MEDIO
                else if(PlayerStatus.getInstancia(getContexto()).getDinero()==250) {
                    disp.setxSpeed((int)(disp.getxSpeed()*1.5));
                    disp.setySpeed((int)(disp.getySpeed()*1.5));
                    disp.setPuntos((int)(disp.getPuntos()*1.2));
                }
                //NIVEL DIFICIL
                else if(PlayerStatus.getInstancia(getContexto()).getDinero()==150) {
                    disp.setxSpeed((int)(disp.getxSpeed()*2));
                    disp.setySpeed((int)(disp.getySpeed()*2));
                    disp.setPuntos((int)(disp.getPuntos()*1.5));
                }
                //NIVEL EXTREMO
                else if(PlayerStatus.getInstancia(getContexto()).getDinero()==50) {
                    disp.setxSpeed((int)(disp.getxSpeed()*2));
                    disp.setySpeed((int)(disp.getySpeed()*2));
                    disp.setPuntos(disp.getPuntos()*2);
                }
            }else {
                disp = new Disparo(this, fuegoDrcha, 1, ran, 5);
                //NIVEL FÁCIL
                if(PlayerStatus.getInstancia(getContexto()).getDinero()==350) {
                    disp.setxSpeed((int)(disp.getxSpeed()*2.2));
                }
                //NIVEL MEDIO
                else if(PlayerStatus.getInstancia(getContexto()).getDinero()==250) {
                    disp.setxSpeed((int)(disp.getxSpeed()*2.5));
                    disp.setPuntos((int)(disp.getPuntos()*1.2));
                }
                //NIVEL DIFICIL
                else if(PlayerStatus.getInstancia(getContexto()).getDinero()==150) {
                    disp.setxSpeed((int)(disp.getxSpeed()*4));
                    disp.setPuntos((int)(disp.getPuntos()*1.5));
                }
                //NIVEL EXTREMO
                else if(PlayerStatus.getInstancia(getContexto()).getDinero()==50) {
                    disp.setxSpeed((int)(disp.getxSpeed()*4));
                    disp.setPuntos(disp.getPuntos()*2);
                }
                soundPool.play(idDisparoMisil,(float)0.09,0,4,0,1);
            }
        }else if(ran==4) {
            if(ran2==1){
                disp=new Disparo(this,bombaAbajo,2,ran,10);
                soundPool.play(idDisparoBomba,(float)0.07,(float)0.07,4,0,1);
                //NIVEL FÁCIL
                if(PlayerStatus.getInstancia(getContexto()).getDinero()==350) {
                    disp.setxSpeed((int)(disp.getxSpeed()*1.2));
                    disp.setySpeed((int)(disp.getySpeed()*1.2));
                }
                //NIVEL MEDIO
                else if(PlayerStatus.getInstancia(getContexto()).getDinero()==250) {
                    disp.setxSpeed((int)(disp.getxSpeed()*1.5));
                    disp.setySpeed((int)(disp.getySpeed()*1.5));
                    disp.setPuntos((int)(disp.getPuntos()*1.2));
                }
                //NIVEL DIFICIL
                else if(PlayerStatus.getInstancia(getContexto()).getDinero()==150) {
                    disp.setxSpeed((int)(disp.getxSpeed()*2));
                    disp.setySpeed((int)(disp.getySpeed()*2));
                    disp.setPuntos((int)(disp.getPuntos()*1.5));
                }
                //NIVEL EXTREMO
                else if(PlayerStatus.getInstancia(getContexto()).getDinero()==50) {
                    disp.setxSpeed((int)(disp.getxSpeed()*2));
                    disp.setySpeed((int)(disp.getySpeed()*2));
                    disp.setPuntos(disp.getPuntos()*2);
                }
            }else {
                disp = new Disparo(this, fuegoAbajo, 1, ran, 5);
                //NIVEL FÁCIL
                if(PlayerStatus.getInstancia(getContexto()).getDinero()==350) {
                    disp.setySpeed((int)(disp.getySpeed()*2.2));
                }
                //NIVEL MEDIO
                else if(PlayerStatus.getInstancia(getContexto()).getDinero()==250) {
                    disp.setySpeed((int)(disp.getySpeed()*2.5));
                    disp.setPuntos((int)(disp.getPuntos()*1.2));
                }
                //NIVEL DIFICIL
                else if(PlayerStatus.getInstancia(getContexto()).getDinero()==150) {
                    disp.setySpeed((int)(disp.getySpeed()*4));
                    disp.setPuntos((int)(disp.getPuntos()*1.5));
                }
                //NIVEL EXTREMO
                else if(PlayerStatus.getInstancia(getContexto()).getDinero()==50) {
                    disp.setySpeed((int)(disp.getySpeed()*4));
                    disp.setPuntos(disp.getPuntos()*2);
                }
                soundPool.play(idDisparoMisil,(float)0.07,(float)0.07,4,0,1);
            }
        }else{
            disp=null;
        }

        return disp;
    }
    //Pinto interfaz, creo sprite y monedas y compruebo si hay colisiones y abro dialogo al finalizar
    public void draw(Canvas canvas){
        long actual;
        boolean conseguido=false, finalizar=false;
        canvas.drawBitmap(fondo, 0, 0, null);
        Paint paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth((float)((getWidth()*0.1)/12));
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize((float) (getWidth() * 0.08));
        paint.setTypeface(font);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawBitmap(sandClock,(float)(getWidth()*0.05),(float)(getHeight()*0.035),null);
        //NIVEL FÁCIL
        if(PlayerStatus.getInstancia(getContexto()).getDinero()==350) {
            canvas.drawText(String.format("%s",pasarSeg(TIEMPO_MAX_EASY-getCrono())),(float)(getWidth()*0.05+sandClock.getWidth()*1.45),(float)(getHeight()*0.084),paint);
        }
        //NIVEL MEDIO
        else if(PlayerStatus.getInstancia(getContexto()).getDinero()==250) {
            canvas.drawText(String.format("%s",pasarSeg(TIEMPO_MAX_MEDIUM-getCrono())),(float)(getWidth()*0.05+sandClock.getWidth()*1.45),(float)(getHeight()*0.084),paint);
        }
        //NIVEL DIFICIL
        else if(PlayerStatus.getInstancia(getContexto()).getDinero()==150) {
            canvas.drawText(String.format("%s",pasarSeg(TIEMPO_MAX_HARD-getCrono())),(float)(getWidth()*0.05+sandClock.getWidth()*1.45),(float)(getHeight()*0.084),paint);
        }
        //NIVEL EXTREMO
        else if(PlayerStatus.getInstancia(getContexto()).getDinero()==50) {
            canvas.drawText(String.format("%s",pasarSeg(TIEMPO_MAX_EXTREME-getCrono())),(float)(getWidth()*0.05+sandClock.getWidth()*1.45),(float)(getHeight()*0.084),paint);
        }
        canvas.drawBitmap(life1,(float)(getWidth()*0.3),(float)(getHeight()*0.045),null);
        canvas.drawBitmap(life2,(float)(getWidth()*0.3+life1.getWidth()*1.05),(float)(getHeight()*0.045),null);
        canvas.drawBitmap(life3,(float)(getWidth()*0.3+life1.getWidth()*1.05+life2.getWidth()*1.05),(float)(getHeight()*0.045),null);
        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawBitmap(moneyBag,(float)(getWidth()*0.57),(float)(getHeight()*0.035),null);
        //NIVEL FÁCIL
        if(PlayerStatus.getInstancia(getContexto()).getDinero()==350) {
            canvas.drawText(String.format("%03d/%03d", getPuntuacion(), MAX_POINTS_EASY), (float) (getWidth() - (getWidth() * 0.05) + moneyBag.getWidth() * 0.4), (float) (getHeight() * 0.084), paint);
        }
        //NIVEL MEDIO
        else if(PlayerStatus.getInstancia(getContexto()).getDinero()==250) {
            canvas.drawText(String.format("%03d/%03d", getPuntuacion(), MAX_POINTS_MEDIUM), (float) (getWidth() - (getWidth() * 0.05) + moneyBag.getWidth() * 0.4), (float) (getHeight() * 0.084), paint);
        }
        //NIVEL DIFICIL
        else if(PlayerStatus.getInstancia(getContexto()).getDinero()==150) {
            canvas.drawText(String.format("%03d/%03d", getPuntuacion(), MAX_POINTS_HARD), (float) (getWidth() - (getWidth() * 0.05) + moneyBag.getWidth() * 0.4), (float) (getHeight() * 0.084), paint);
        }
        //NIVEL EXTREMO
        else if(PlayerStatus.getInstancia(getContexto()).getDinero()==50) {
            canvas.drawText(String.format("%03d/%03d", getPuntuacion(), MAX_POINTS_EXTREME), (float) (getWidth() - (getWidth() * 0.05) + moneyBag.getWidth() * 0.4), (float) (getHeight() * 0.084), paint);
        }
        paint.setColor(Color.rgb(255,255,0));
        paint.setStyle(Paint.Style.FILL);

        if((sprites.size()!=0) && getVidas()>0 && ((getCrono()<TIEMPO_MAX_EASY && PlayerStatus.getInstancia(getContexto()).getDinero()==350)
                || (getCrono()<TIEMPO_MAX_MEDIUM && PlayerStatus.getInstancia(getContexto()).getDinero()==250)
                || (getCrono()<TIEMPO_MAX_HARD&& PlayerStatus.getInstancia(getContexto()).getDinero()==150)
                || (getCrono()<TIEMPO_MAX_EXTREME && PlayerStatus.getInstancia(getContexto()).getDinero()==50))) {
            actual=System.currentTimeMillis();
            for (Sprite miSprite : sprites) {
                miSprite.draw(canvas);
            }
            if((getPuntuacion()<MAX_POINTS_EASY && PlayerStatus.getInstancia(getContexto()).getDinero()==350)
                    || (getPuntuacion()<MAX_POINTS_MEDIUM && PlayerStatus.getInstancia(getContexto()).getDinero()==250)
                    || (getPuntuacion()<MAX_POINTS_HARD && PlayerStatus.getInstancia(getContexto()).getDinero()==150)
                    || (getPuntuacion()<MAX_POINTS_EXTREME && PlayerStatus.getInstancia(getContexto()).getDinero()==50)) {
                for (int i = 0; i < monedas.size(); i++) {
                    if (i < 5) {
                        monedas.get(i).draw(canvas);
                        if (monedas.get(i).isCollition(getJug())) {
                            sumarPuntos(monedas.get(i).getPuntos());
                            if(monedas.get(i).getPuntos()==1){
                                soundPool.play(idCoin1,(float)0.4,(float)0.4,1,0,1);
                            }
                            else if(monedas.get(i).getPuntos()==2){
                                soundPool.play(idCoin2,(float)0.4,(float)0.4,1,0,1);
                            }
                            else if(monedas.get(i).getPuntos()==5){
                                soundPool.play(idCoin5,1,1,1,0,1);
                            }
                            else if(monedas.get(i).getPuntos()==10){
                                soundPool.play(idCoin10,1,1,1,0,1);
                            }
                            monedas.remove(monedas.get(i));
                        }
                    }
                }
            }else{
                for(int i=0;i<tickets.size();i++){
                    tickets.get(i).draw(canvas);
                    if(tickets.get(i).isCollition(getJug())) {
                        //oundPool.play(idWin,(float)0.5,(float)0.5,0,0,1);
                        tickets.remove(tickets.get(i));
                        conseguido=true;
                    }
                }
            }
            for (int i = 0; i < disparos.size(); i++) {
                    disparos.get(i).draw(canvas);
                    if (disparos.get(i).isCollition(getJug())) {
                        soundPool.play(idCaeMoneda,(float)0.4,(float)0.4,2,0,1);
                        soundPool.play(idGrito,(float)0.5,(float)0.5,1,0,1);
                        restarVida(disparos.get(i).getVida());
                        restaPuntos(disparos.get(i).getPuntos());
                        disparos.remove(disparos.get(i));
                        disparos.add(getDisparos());
                    }
                    else if(disparos.get(i).getCorx()<=0){
                        disparos.remove(disparos.get(i));
                        disparos.add(getDisparos());
                    }
                    else if(disparos.get(i).getCory()>=getHeight()){
                        disparos.remove(disparos.get(i));
                        disparos.add(getDisparos());
                    }
                    else if(disparos.get(i).getCorx()>=getWidth()){
                        disparos.remove(disparos.get(i));
                        disparos.add(getDisparos());
                    }
                    else if(disparos.get(i).getCory()<=0){
                        disparos.remove(disparos.get(i));
                        disparos.add(getDisparos());
                    }
            }
            if (sprites.size() > 0) {
                setCrono((actual - inicio) / 1000);
            }
            paint.setTextAlign(Paint.Align.LEFT);
            //NIVEL FÁCIL
            if(PlayerStatus.getInstancia(getContexto()).getDinero()==350) {
                canvas.drawText(String.format("%s",pasarSeg(TIEMPO_MAX_EASY-getCrono())),(float)(getWidth()*0.05+sandClock.getWidth()*1.25),(float)(getHeight()*0.08),paint);
            }
            //NIVEL MEDIO
            else if(PlayerStatus.getInstancia(getContexto()).getDinero()==250) {
                canvas.drawText(String.format("%s",pasarSeg(TIEMPO_MAX_MEDIUM-getCrono())),(float)(getWidth()*0.05+sandClock.getWidth()*1.25),(float)(getHeight()*0.08),paint);
            }
            //NIVEL DIFICIL
            else if(PlayerStatus.getInstancia(getContexto()).getDinero()==150) {
                canvas.drawText(String.format("%s",pasarSeg(TIEMPO_MAX_HARD-getCrono())),(float)(getWidth()*0.05+sandClock.getWidth()*1.25),(float)(getHeight()*0.08),paint);
            }
            //NIVEL EXTREMO
            else if(PlayerStatus.getInstancia(getContexto()).getDinero()==50) {
                canvas.drawText(String.format("%s",pasarSeg(TIEMPO_MAX_EXTREME-getCrono())),(float)(getWidth()*0.05+sandClock.getWidth()*1.25),(float)(getHeight()*0.08),paint);
            }
            paint.setTextAlign(Paint.Align.RIGHT);
            //NIVEL FÁCIL
            if(PlayerStatus.getInstancia(getContexto()).getDinero()==350) {
                canvas.drawText(String.format("%03d/%03d", getPuntuacion(), MAX_POINTS_EASY), (float) (getWidth() - (getWidth() * 0.05) + moneyBag.getWidth() * 0.2), (float) (getHeight() * 0.08), paint);
            }
            //NIVEL MEDIO
            else if(PlayerStatus.getInstancia(getContexto()).getDinero()==250) {
                canvas.drawText(String.format("%03d/%03d", getPuntuacion(), MAX_POINTS_MEDIUM), (float) (getWidth() - (getWidth() * 0.05) + moneyBag.getWidth() * 0.2), (float) (getHeight() * 0.08), paint);
            }
            //NIVEL DIFICIL
            else if(PlayerStatus.getInstancia(getContexto()).getDinero()==150) {
                canvas.drawText(String.format("%03d/%03d", getPuntuacion(), MAX_POINTS_HARD), (float) (getWidth() - (getWidth() * 0.05) + moneyBag.getWidth() * 0.2), (float) (getHeight() * 0.08), paint);
            }
            //NIVEL EXTREMO
            else if(PlayerStatus.getInstancia(getContexto()).getDinero()==50) {
                canvas.drawText(String.format("%03d/%03d", getPuntuacion(), MAX_POINTS_EXTREME), (float) (getWidth() - (getWidth() * 0.05) + moneyBag.getWidth() * 0.2), (float) (getHeight() * 0.08), paint);
            }
        }
        else{
            conseguido=false;
            finalizar=true;
            //soundPool.play(idGameOver,1,1,0,0,1);
        }
        if(conseguido){
            stop();
            musica= MediaPlayer.create(getContexto(),R.raw.madrid_win_effect);
            musica.setLooping(false);
            musica.start();
            getContexto().runOnUiThread(new Runnable() {
                public void run() {
                    new DialogFin(getContexto(), DialogFin.Tipo.WIN).show();
                }
            });
        }
        else if(!conseguido && finalizar) {
            stop();
            musica= MediaPlayer.create(getContexto(),R.raw.madrid_gameover_effect);
            musica.setLooping(false);
            musica.start();
            getContexto().runOnUiThread(new Runnable() {
                public void run() {
                    new DialogFin(getContexto(), DialogFin.Tipo.LOSE).show();
                }
            });
        }
    }

    private void restaPuntos(int puntos) {
        if (puntos > getPuntuacion()) {
            setPuntuacion(0);
        } else {
            setPuntuacion(getPuntuacion() - puntos);
        }
    }


    private void restarVida(int vida){
        getJug().setVida(getVidas()-vida);
        setVidas(getJug().getVida());

        if(getJug().getVida()==5){
            life1=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_fullheart);
            life1 = Bitmap.createScaledBitmap(life1, (int)(getWidth()*0.08), (int)(getHeight()*0.045), false);
            life2=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_fullheart);
            life2 = Bitmap.createScaledBitmap(life2, (int)(getWidth()*0.08), (int)(getHeight()*0.045), false);
            life3=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_halfheart);
            life3 = Bitmap.createScaledBitmap(life3, (int)(getWidth()*0.08), (int)(getHeight()*0.045), false);
        }
        if(getJug().getVida()==4){
            life1=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_fullheart);
            life1 = Bitmap.createScaledBitmap(life1, (int)(getWidth()*0.08), (int)(getHeight()*0.045), false);
            life2=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_fullheart);
            life2 = Bitmap.createScaledBitmap(life2, (int)(getWidth()*0.08), (int)(getHeight()*0.045), false);
            life3=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_empyheart);
            life3 = Bitmap.createScaledBitmap(life3, (int)(getWidth()*0.08), (int)(getHeight()*0.045), false);
        }
        if(getJug().getVida()==3){
            life1=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_fullheart);
            life1 = Bitmap.createScaledBitmap(life1, (int)(getWidth()*0.08), (int)(getHeight()*0.045), false);
            life2=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_halfheart);
            life2 = Bitmap.createScaledBitmap(life2, (int)(getWidth()*0.08), (int)(getHeight()*0.045), false);
            life3=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_empyheart);
            life3 = Bitmap.createScaledBitmap(life3, (int)(getWidth()*0.08), (int)(getHeight()*0.045), false);
        }
        if(getJug().getVida()==2){
            life1=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_fullheart);
            life1 = Bitmap.createScaledBitmap(life1, (int)(getWidth()*0.08), (int)(getHeight()*0.045), false);
            life2=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_empyheart);
            life2 = Bitmap.createScaledBitmap(life2, (int)(getWidth()*0.08), (int)(getHeight()*0.045), false);
            life3=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_empyheart);
            life3 = Bitmap.createScaledBitmap(life3, (int)(getWidth()*0.08), (int)(getHeight()*0.045), false);
        }
        if(getJug().getVida()==1){
            life1=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_halfheart);
            life1 = Bitmap.createScaledBitmap(life1, (int)(getWidth()*0.08), (int)(getHeight()*0.045), false);
            life2=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_empyheart);
            life2 = Bitmap.createScaledBitmap(life2, (int)(getWidth()*0.08), (int)(getHeight()*0.045), false);
            life3=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_empyheart);
            life3 = Bitmap.createScaledBitmap(life3, (int)(getWidth()*0.08), (int)(getHeight()*0.045), false);
        }
        if(getJug().getVida()<=0){
            life1=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_empyheart);
            life1 = Bitmap.createScaledBitmap(life1, (int)(getWidth()*0.08), (int)(getHeight()*0.045), false);
            life2=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_empyheart);
            life2 = Bitmap.createScaledBitmap(life2, (int)(getWidth()*0.08), (int)(getHeight()*0.045), false);
            life3=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_empyheart);
            life3 = Bitmap.createScaledBitmap(life3, (int)(getWidth()*0.08), (int)(getHeight()*0.045), false);
        }
    }

    //Pasar a segundos el tiempo
    private String pasarSeg(float time){
        String cad="";
        String txt=String.valueOf(time);
        int pos=0;
        while(pos<txt.length()){
            if(txt.charAt(pos)=='.' || txt.charAt(pos)==','){
                if(cad.length()>0){
                    return cad;
                }
                else{
                    return String.format("0%s",cad);
                }
            }
            else{
                cad=String.format("%s%c",cad,txt.charAt(pos));
                pos++;
            }
        }
        return "";
    }

    //Inicializo el fondo y los bitmap de la interfaz, creo el sprite e inicio el bucle
    private void start(){
        fondo=BitmapFactory.decodeResource(getResources(), R.drawable.madridfondo);
        fondo = Bitmap.createScaledBitmap(fondo, getWidth(), getHeight(), false);
        life1=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_fullheart);
        life1 = Bitmap.createScaledBitmap(life1, (int)(getWidth()*0.08), (int)(getHeight()*0.045), false);
        life2=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_fullheart);
        life2 = Bitmap.createScaledBitmap(life2, (int)(getWidth()*0.08), (int)(getHeight()*0.045), false);
        life3=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_fullheart);
        life3 = Bitmap.createScaledBitmap(life3, (int)(getWidth()*0.08), (int)(getHeight()*0.045), false);
        moneyBag=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_money);
        moneyBag = Bitmap.createScaledBitmap(moneyBag, (int)(getWidth()*0.08), (int)(getHeight()*0.06), false);
        sandClock=BitmapFactory.decodeResource(getResources(),R.drawable.madrid_clock);
        sandClock = Bitmap.createScaledBitmap(sandClock, (int)(getWidth()*0.065), (int)(getHeight()*0.06), false);
        createSprite();
        if (loop.getState() == Thread.State.NEW) {
            loop.start();
        }
        loop.setRunning(true);
    }

    private void stop(){
        loop.setRunning(false);
        musica.stop();
        musica.release();
    }

    private void sumarPuntos(int puntos){
        setPuntuacion(getPuntuacion()+puntos);
    }

    //Mando al Sprite la posición de la pantalla donde quiero que se mueva el personaje
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_MOVE){
            getJug().touch(event.getRawX(),event.getRawY(),true);
        }
        else if(event.getAction()==MotionEvent.ACTION_UP){
            getJug().touch(event.getRawX(),event.getRawY(),false);
        }
        else if(event.getAction()==MotionEvent.ACTION_DOWN){
            getJug().touch(event.getRawX(),event.getRawY(),true);
        }
        return true;
    }

    public ArrayList<Disparo> getMisiles() {
        return misiles;
    }
}
