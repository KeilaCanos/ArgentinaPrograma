package com.mycompany.argprogramaentrega2intento1;

public class Resultado
{
    private int idRonda;
    private String resultadoPronostico;
    private String resultadoPartido;
    private int idPartido;

    public Resultado(int idron, String resulpro, String resulpart, int idPartido)
    {
        this.idRonda = idron;
        this.resultadoPronostico = resulpro;
        this.resultadoPartido = resulpart;
        this.idPartido = idPartido;
    }

    public int get_IdRonda() {
        return idRonda;
    }
    public String get_ResultadoPronostico(){
        return resultadoPronostico;
    }
    public String get_ResultadoPartido(){
        return resultadoPartido;
    }

    public int get_idPartido(){
        return idPartido;
    }

    public boolean check_resultado(){
        return (this.resultadoPronostico.equals(this.resultadoPartido));
    }

}
