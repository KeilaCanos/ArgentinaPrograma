package com.mycompany.argprogramaentrega2intento1;
public class Partido
{
    private Equipo eq1;
    private Equipo eq2;
    private int golesEq1;
    private int golesEq2;

    public Partido(){}
    public Partido(Equipo eq1, Equipo eq2, int golesEq1, int golesEq2)
    {
        this.eq1 = eq1;
        this.eq2 = eq2;
        this.golesEq1 = golesEq1;
        this.golesEq2 = golesEq2;
    }


    public Equipo getEquipo1()
    {
        return eq1;
    }

    public Equipo getEquipo2()
    {
        return eq2;
    }

    
}
