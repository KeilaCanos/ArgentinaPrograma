package com.mycompany.argprogramaentrega2intento1;

public class Pronostico
{
    private int idPronostico;
    private int idPersona;
    private int idPartido;
    private String resultPro;

    /*private Partido partido;
    private ResultadoEnum resultadoPronostico;
    private int idRonda;
    private Persona participante;*/
    public Pronostico(int idpro, int idper, int idpar, String repro)
    {
        this.idPronostico = idpro;
        this.idPersona = idper;
        this.idPartido = idpar;
        this.resultPro = repro;
    }

    public int get_idPronostico()
    {
        return idPronostico;
    }
    public int get_idPersona()
    {
        return idPersona;
    }
    public int get_idPartido()
    {
        return idPartido;
    }
    public String get_resultPro()
    {
        return resultPro;
    }

    
    
    
}
