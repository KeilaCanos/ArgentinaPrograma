
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author camii
 */
public class Pronostico {
    private Partido partido;
    private ResultadoEnum resultadoPronostico;
    private int idRonda;
    
    public Pronostico(Partido partido, ResultadoEnum resultadoPronostico, int idRonda) {
        this.partido = partido;
        this.resultadoPronostico = resultadoPronostico;
        this.idRonda = idRonda;
    }
    
    public int puntos(){
        int puntos = 0;
        if(this.partido.resultadoPartido() == this.resultadoPronostico){
            puntos = 1;
        }
        return puntos;
    }

    public Partido getPartido()
    {
        return partido;
    }

    public int getIdRonda()
    {
        return idRonda;
    }
    
    
    
}
