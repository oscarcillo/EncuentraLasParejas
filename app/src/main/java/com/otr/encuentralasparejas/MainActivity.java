package com.otr.encuentralasparejas;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //variables
    private Carta cartas[];
    private int cartaEmparejada1;
    private int cartaEmparejada2;
    private int modo;
    //
    private int turno;
    private TextView titulo;
    private int retardo;
    private boolean perdedor = false;
    //strings
    private String correcto;
    private String incorrecto;
    private String ganador;
    //vistas
    public static TextView vistaTemporizador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //inicializamos las variables necesarias
        turno = 1;
        titulo = findViewById(R.id.titulo);
        retardo = 1000;
        //
        correcto = getText(R.string.titulo_correcto).toString();
        incorrecto = getText(R.string.titulo_incorrecto).toString();
        ganador = getText(R.string.titulo_ganador).toString();
        //recogemos el modo de juego
        Intent in = getIntent();
        modo = in.getIntExtra("modo", 1);

        //crear el array de vistas
        ImageView vistas[] = {findViewById(R.id.cartaa1), findViewById(R.id.cartaa2), findViewById(R.id.cartaa3),
                              findViewById(R.id.cartab1), findViewById(R.id.cartab2), findViewById(R.id.cartab3),
                              findViewById(R.id.cartac1), findViewById(R.id.cartac2), findViewById(R.id.cartac3),
                              findViewById(R.id.cartad1), findViewById(R.id.cartad2), findViewById(R.id.cartad3)};
        //crear el array de imagenes
        int imagenes[] = {R.drawable.arcticmonkeys, R.drawable.wolfmother,
                          R.drawable.imagenwideawake, R.drawable.muse, R.drawable.fuzz,
                          R.drawable.elcaminoblackkeys};

        //
        //generar el tablero
        cartas = new Carta[vistas.length];
        situarTablero(vistas, imagenes);

        //mostrar todas las cartas al principio
        for(int i = 0; i < cartas.length; i++)
            cartas[i].setBocaArriba();

        if(modo==3) {
            vistaTemporizador = findViewById(R.id.temporizador);
            vistaTemporizador.setText("00:10");
        }

        (new Handler()).postDelayed(this::iniciarPartida, 5000);
    }

    @Override
    public void onClick(View v) {

        ImageView im = (ImageView)v;
        Carta carta = new Carta();

        //comprobar que carta ha sido pulsada
        for(int i = 0; i < cartas.length; i++)
            if(cartas[i].getVistaImagen() == im)
                carta = cartas[i];

        if(carta.isBocaArriba() == false && !titulo.getText().equals(incorrecto)){
            switch(turno) {
                //hace algo cuando todas las cartas están ocultas
                case 1: {
                    for (int i = 0; i < cartas.length; i++) {
                        if (v == cartas[i].getVistaImagen()) {
                            cartas[i].setBocaArriba();
                            cartaEmparejada1 = i;
                        }
                        //pasar turno
                        turno = 2;
                    }
                }
                break;
                case 2: {
                    for (int i = 0; i < cartas.length; i++) {
                        if (v == cartas[i].getVistaImagen()) {
                            cartas[i].setBocaArriba();
                            cartaEmparejada2 = i;
                        }
                    }
                    //comprobar si ha emparejado bien las dos cartas
                    if (cartas[cartaEmparejada1].getNPareja() == cartas[cartaEmparejada2].getNPareja()) {
                        cartas[cartaEmparejada1].setEmparejada(true);
                        cartas[cartaEmparejada2].setEmparejada(true);
                        titulo.setText(correcto);
                        (new Handler()).postDelayed(this::quitarTitulo, retardo);
                    } else {
                        quitarListeners();
                        titulo.setText(incorrecto);
                        (new Handler()).postDelayed(this::resetearCartas, retardo);
                    }
                    //pasar turno
                    turno = 1;
                }
                break;
            }
            if(comprobarGanador()){
                titulo.setText(ganador);
                (new Handler()).postDelayed(this::volverMenu, 2500);
            }
        }
    }

    public void resetearCartas(){
        if(modo==2){
            titulo.setText(getText(R.string.titulo_perdedor));
            perdedor = true;
            (new Handler()).postDelayed(this::volverMenu, 2500);
        }
        else{
            cartas[cartaEmparejada1].setBocaAbajo();
            cartas[cartaEmparejada2].setBocaAbajo();
            titulo.setText("");
            establecerListeners();
        }
    }

    public void quitarTitulo(){
        if(!comprobarGanador())
            titulo.setText("");
    }

    public void situarTablero(ImageView[] vistas, int[] img){

        int contadorCartas = 0;
        int contadorImagenes = 0;
        boolean nSeleccionados[] = new boolean [vistas.length];

        while(contadorCartas<vistas.length){
            int numero = (int)(Math.random() * vistas.length);

            if(nSeleccionados[numero]==false){
                cartas[numero] = new Carta(vistas[numero], img[contadorImagenes], contadorImagenes);
                nSeleccionados[numero]=true;
                //
                contadorCartas++;
                if(contadorCartas%2==0 && contadorCartas!=0)
                    contadorImagenes++;
            }
        }
    }

    public void iniciarPartida(){
        for(int i = 0; i < cartas.length; i++)
            cartas[i].setBocaAbajo();
        //establecer los listeners
        establecerListeners();
        titulo.setText("");

        //si el modo es tiempo limitado
        if(modo==3){
            vistaTemporizador = findViewById(R.id.temporizador);
            //clase que genera la cuenta atras
            CountDownTimer temporizador = new CountDownTimer(9000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    //si no hay ganador que el temporizador se ejecute
                    if(!comprobarGanador()){
                        int tiempo = (int)millisUntilFinished/1000+1;
                        if(tiempo>=10)
                            vistaTemporizador.setText("00:"+tiempo);
                        else
                            vistaTemporizador.setText("00:0"+tiempo);
                    }
                }
                @Override
                public void onFinish() {
                    if(!comprobarGanador()){
                        vistaTemporizador.setText("00:00");
                        titulo.setText(getText(R.string.titulo_perdedor));
                        quitarListeners();
                        //volver al menu despues de un tiempo
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                volverMenu();
                            }
                        }, retardo+750);
                    }
                }
            }.start();
        }
        else{
            vistaTemporizador = findViewById(R.id.temporizador);
            //clase que genera el temporizador
            CountUpTimer temporizador = new CountUpTimer(1000) {
                int segundos = 0;
                String mostrarSegundos = "";
                int minutos = 0;
                String mostrarMinutos = "00";
                @Override
                public void onTick(long elapsedTime) {
                    //que el temporizador cuente si no hay ganador
                    if(!comprobarGanador() && !perdedor){
                        if(segundos==59) {
                            segundos = 0;
                            minutos++;
                        }
                        else
                            segundos++;
                        //
                        if(segundos>=10)
                            mostrarSegundos = ""+segundos;
                        else
                            mostrarSegundos = "0"+segundos;
                        if(minutos>=10)
                            mostrarMinutos = ""+minutos;
                        else
                            mostrarMinutos = "0"+minutos;
                        //
                        vistaTemporizador.setText(mostrarMinutos+":"+mostrarSegundos);
                    }
                }
            }.start();
        }
    }

    public boolean comprobarGanador(){
        for(int i = 0; i < cartas.length; i++)
            if(!cartas[i].isEmparejada())
                return false;
        return true;
    }

    public void volverMenu(){
        Intent intent = new Intent(this, Menu.class);
        intent.putExtra("modo", modo);
        startActivity(intent);
        finish();
    }

    public void quitarListeners(){
        for(int i = 0; i < cartas.length; i++)
            cartas[i].getVistaImagen().setOnClickListener(null);
    }

    public void establecerListeners(){
        for(int i = 0; i < cartas.length; i++)
            cartas[i].getVistaImagen().setOnClickListener(this);
    }
}
