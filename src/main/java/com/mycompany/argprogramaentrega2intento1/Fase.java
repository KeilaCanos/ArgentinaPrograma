package com.mycompany.argprogramaentrega2intento1;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Fase
{
    private int idFase;
    ArrayList<Ronda> listaRondas = new ArrayList<>();

    public Fase(int idfa)
    {
        this.idFase = idfa;
    }

    public int get_IdFase() {return idFase;}

    public void set_listaRondas(ArrayList<Ronda> listaRon)
    {
        this.listaRondas = listaRon;
    }





}
