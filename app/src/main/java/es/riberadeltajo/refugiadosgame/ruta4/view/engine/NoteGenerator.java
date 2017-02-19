package es.riberadeltajo.refugiadosgame.ruta4.view.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.ruta4.view.models.Nota;
import es.riberadeltajo.refugiadosgame.ruta4.view.models.SpriteNotas;

/**
 * Esta clase recibe el nombre del archivo con la canción, lo lee, lo descompone y va
 * añadiendo las notas al arraylist de sprites a su debido momento.
 * Created by Luismi on 29/01/2017.
 */

public class NoteGenerator extends Thread {
    private Context context;
    private GameView gameview;
    private double tps;     // trastes por segundo
    private String cancion;
    private ArrayList<Nota> notas;
    private boolean running;
    private int dificultad;

    public NoteGenerator(Context context, GameView gameview, String cancion, int dificultad) {
        setNotas(new ArrayList<Nota>());
        setContext(context);
        setGameview(gameview);
        setCancion(cancion);
        setDificultad(dificultad);
        setRunning(true);
        crearNotas();
    }

    private void crearNotas() {
        String line;
        try {
            InputStreamReader is = new InputStreamReader(getContext().getAssets().open(getCancion()));
            BufferedReader reader = new BufferedReader(is);

            line = reader.readLine();   // La primera línea marca los TPS
            setTps(Double.parseDouble(line));

            while((line = reader.readLine()) != null) {
                String splitted[] = line.split(" ");
                getNotas().add(new Nota(Float.parseFloat(splitted[0]),Integer.parseInt(splitted[1]),Float.parseFloat(splitted[2])));
            }
        } catch (FileNotFoundException e) {
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public void run() {
        int traste = 15;
        int nota = 0;
        Bitmap bmp;

        while(isRunning()) {
            Nota n;

            try {
                // Esperamos 1 traste (1 segundo / trastes por segundo)
                Thread.sleep((long) (1000/getTps()));
                traste++;

                if(getNotas().size() > nota) {      // Toca hasta que se acaban las notas
                    do {
                        n = getNotas().get(nota);

                        if (traste == n.getTraste()) {
                            n = getNotas().get(nota);
                            switch (n.getPosicion()) {
                                case 1:
                                    bmp = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.notegreen);
                                    break;
                                case 2:
                                    bmp = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.notered);
                                    break;
                                case 3:
                                    bmp = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.noteyellow);
                                    break;
                                default:
                                    bmp = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.noteblue);
                            }
                            getGameview().getNotas().add(new SpriteNotas(getGameview(), bmp, n.getPosicion(), n.getDuracion(), getDificultad()));
                            nota++;
                        }
                    } while (traste == n.getTraste());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public GameView getGameview() {
        return gameview;
    }

    public void setGameview(GameView gameview) {
        this.gameview = gameview;
    }

    public double getTps() {
        return tps;
    }

    public void setTps(double tps) {
        this.tps = tps;
    }

    public String getCancion() {
        return cancion;
    }

    public void setCancion(String cancion) {
        this.cancion = cancion;
    }

    public ArrayList<Nota> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<Nota> notas) {
        this.notas = notas;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }
}
