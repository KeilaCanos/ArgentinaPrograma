package com.mycompany.argprogramaentrega2intento1;

import java.util.ArrayList;

public class Persona
{
    private int idPersona;
    private String nombrePer;
    private int puntos;


    public Persona(int id, String nombrePer, int puntos)
    {
        this.idPersona = id;
        this.nombrePer = nombrePer;
        this.puntos = puntos;

    }


    public int get_IdPersona()
    {
        return idPersona;
    }

    public String getNombrePer()
    {
        return this.nombrePer;
    }

    public int getPuntos()
    {
        return puntos;
    }
}

