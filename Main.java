/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author camii
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException  {

    	ArrayList<Ronda> rondas = new ArrayList<>();
        Ronda ronda1 = new Ronda(1);
        rondas.add(ronda1);
        ArrayList<Pronostico> pronosticos = new ArrayList<>();
        
    //resultados: 

        BufferedReader br = new BufferedReader(new FileReader("resultados.csv"));
        String line = br.readLine();
        line = br.readLine();

        //Este while lo que hace es crear el partido y se lo asigna
        // a la ronda correspondiente
        while(line != null)
        {
            String[] linea = line.split(",");
            Equipo eq1 = new Equipo(linea[0]);
            Equipo eq2 = new Equipo(linea[3]);
            Partido partido = new Partido(eq1, eq2, Integer.parseInt(linea[1]), Integer.parseInt(linea[2]));
            boolean rondaEncontrada = false;
            for(Ronda ronda : rondas)
            {
                if(ronda.getIdRonda() == Integer.parseInt(linea[4]))
                {
                    rondaEncontrada = true;
                    ronda.agregarPartido(partido);
                    break;
                }
            }
            if(rondaEncontrada == false)
            {
                Ronda rondaNueva = new Ronda(Integer.parseInt(linea[4]));
                rondaNueva.agregarPartido(partido);
                rondas.add(rondaNueva);
            }
            /*while(i<rondas.length & rondaEncontrada == false & rondas[i] != null){
                if(rondas[i].getIdRonda() == Integer.parseInt(linea[4])){
                    rondaEncontrada = true;
                    rondas[i].agregarPartido(partido);
                }
                i++;
            }
            if(!rondaEncontrada & rondasAgregadas<rondas.length){
                Ronda r = new Ronda(Integer.parseInt(linea[4]));
                rondas[rondasAgregadas] = r;
                System.out.println(rondas);
                rondas[i].agregarPartido(partido);
                System.out.println(rondas);
                rondasAgregadas++;
            }*/
            line = br.readLine();
        }
    
    //Pronosticos:

        br = new BufferedReader(new FileReader("pronosticos.csv"));
        line = br.readLine();
        line = br.readLine();
        while(line != null)
        {
            String[] linea = line.split(",");
            ResultadoEnum resultado = null;
            int columna=1;
            boolean encontrado = false;
            while(columna<=3 & encontrado == false){
                if(linea[columna].equals("X")){
                    encontrado = true;
                    switch (columna) {
                        case 1 -> resultado = ResultadoEnum.GANADOR_EQ1;
                        case 2 -> resultado = ResultadoEnum.EMPATE;
                        case 3 -> resultado = ResultadoEnum.GANADOR_EQ2;
                        default -> {
                        }
                    }
                }
                columna++;
            }

            int idRonda = Integer.parseInt(linea[5]);
            for(Ronda ronda : rondas)
            {
                if(ronda.getIdRonda() == idRonda)
                {
                    Partido partido = ronda.buscarPartido(linea[0], linea[4]);
                    if(partido != null & resultado != null){
                        Pronostico pronostico = new Pronostico(partido, resultado, idRonda);
                        pronosticos.add(pronostico);
                    }
                }
            }
        line = br.readLine();
        }
        
        
        int puntosRonda = 0;
        for(Ronda r : rondas)
        {
            if(r != null)
            {
                puntosRonda = r.puntosDePronosticosPorRonda(pronosticos);
                System.out.println("puntos de la ronda " + r.getIdRonda() +": "+puntosRonda);
            }
        }
    }
}
