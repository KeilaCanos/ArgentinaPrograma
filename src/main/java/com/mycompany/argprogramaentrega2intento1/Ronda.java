package com.mycompany.argprogramaentrega2intento1;


import java.util.ArrayList;

public class Ronda {
    //private ArrayList<Partido> partidos = new ArrayList<>();
    private int idRonda;
    private int idFase;

    //CONSTRUCTOR
    public Ronda(int idRonda, int idFase)
    {
        this.idRonda=idRonda;
        this.idFase = idFase;
    }

    public int get_IdFase(){
        return idFase;
    }
    public int getIdRonda()
    {
        return idRonda;
    }
    
    
}
