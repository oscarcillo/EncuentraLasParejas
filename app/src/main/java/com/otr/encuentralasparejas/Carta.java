package com.otr.encuentralasparejas;

import android.widget.ImageView;

public class Carta {

    //VARIABLES
    private ImageView vistaImagen;
    private int nPareja;
    private boolean activa;
    private boolean emparejada;
    private int imagen;
    //

    public Carta(ImageView vistaImagen, int imagen, int nPareja){
        this.vistaImagen = vistaImagen;
        vistaImagen.setImageResource(R.drawable.carta);
        this.nPareja = nPareja;
        this.imagen = imagen;
    }

    public Carta(){ }

    //GETTERS Y SETTERS
    public void setEmparejada(boolean emp){
        this.emparejada = emp;
    }

    public boolean isEmparejada(){
        return emparejada;
    }

    public void setNPareja(int pa){
        this.nPareja = pa;
    }

    public int getNPareja(){
        return nPareja;
    }

    public ImageView getVistaImagen(){return vistaImagen;}

    public boolean isBocaArriba(){return activa;}

    //METODOS

    public void setBocaArriba(){
        activa = true;
        vistaImagen.setImageResource(imagen);
    }

    public void setBocaAbajo(){
        activa = false;
        vistaImagen.setImageResource(R.drawable.carta);
    }

}
