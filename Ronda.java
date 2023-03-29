
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author camii
 */
public class Ronda {
    private ArrayList<Partido> partidos = new ArrayList<>();
    private int idRonda;

    //CONSTRUCTOR
    public Ronda(int idRonda) {
        this.idRonda=idRonda;
    }
    
    
    
    public Partido buscarPartido(String eq1, String eq2)
    {
        for(Partido p : partidos){
            if(p.getEq1().getNombre().equals(eq1) & p.getEq2().getNombre().equals(eq2)){
                return p;
            }
        }
        return null;
    }

    public int puntosDePronosticosPorRonda(ArrayList<Pronostico> pronosticos){
        int puntos = 0;
        for (Pronostico pro : pronosticos)
        {
           if (pro.getIdRonda() == this.idRonda)
           {
               puntos += pro.puntos();
           }
        }

       /* for(int i=0; i<pronosticos.length; i++){
            if(pronosticos[i] != null){
                if(pronosticos[i].getIdRonda()==this.idRonda){
                    puntos += pronosticos[i].puntos();

                }
            }
        }*/
        return puntos;
    }
    
    public void agregarPartido(Partido p)
    {
        this.partidos.add(p);
    }
    
    
    public int getIdRonda() {
        return idRonda;
    }

    public ArrayList<Partido> getPartidos() {
        return partidos;
    }
    
    
}
