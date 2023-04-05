/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException
    {

    	ArrayList<Ronda> listaRondas = new ArrayList<>();
        Ronda ronda1 = new Ronda(1);
        listaRondas.add(ronda1);
        ArrayList<Pronostico> listaPronosticos = new ArrayList<>();
        ArrayList<Persona> listaPersonas = new ArrayList<>();
        
    //resultados: 

        BufferedReader br = new BufferedReader(new FileReader("resultados.csv"));
        String line = br.readLine();
        line = br.readLine();

        //Este while lo que hace es crear el partido y se lo asigna
        // a la ronda correspondiente
        while(line != null)
        {
            String[] linea = line.split(",");
            //Este if lo que hace es verificar que en el archivo no hayan mas o menos columnas de las
            // que correspondan. Si la verificacion no esta bien, directamente no crea el partido
            if(linea.length == 5)
            {
                Equipo eq1 = new Equipo(linea[0]);
                Equipo eq2 = new Equipo(linea[3]);
                int idRondaArchivo = Integer.parseInt(linea[4]);
                try{
                    Partido partido = new Partido(eq1, eq2, Integer.parseInt(linea[1]), Integer.parseInt(linea[2]));
                    boolean rondaEncontrada = false;
                    for(Ronda ronda : listaRondas)
                    {
                        if(ronda.getIdRonda() == idRondaArchivo)
                        {
                            rondaEncontrada = true;
                            ronda.agregarPartido(partido);
                            break;
                        }
                    }
                    if(rondaEncontrada == false)
                    {
                        Ronda rondaNueva = new Ronda(idRondaArchivo);
                        rondaNueva.agregarPartido(partido);
                        listaRondas.add(rondaNueva);
                    }

                }
                catch(NumberFormatException e){
                    System.out.println("El partido de " + eq1.getNombre() + " - " + eq2.getNombre() + " de la Ronda " +
                     idRondaArchivo + " no pudo ser guardado correctamente");
                }
            }else {
                System.out.println("No se pudo crear el partido");
            }
            line = br.readLine();
        }
    
    //Pronosticos:

        br = new BufferedReader(new FileReader("pronosticos.csv"));
        line = br.readLine();
        line = br.readLine();
        while(line != null)
        {
            String[] linea = line.split(",");
            //Este if lo que hace es verificar que en el archivo no hayan mas o menos columnas de las
            // que correspondan. Si la verificacion no esta bien, no crea el pronostico y no
            // se toma en cuenta para imprimir los puntos de cada persona
            if (linea.length == 7)
            {
                ResultadoEnum resultadoPronostico = null;
                int columna=1;
                boolean encontrado = false;
                while(columna<=3 & encontrado == false)
                {
                    if(linea[columna].equals("X"))
                    {
                        encontrado = true;
                        switch (columna)
                        {
                            case 1 -> resultadoPronostico = ResultadoEnum.GANADOR_EQ1;
                            case 2 -> resultadoPronostico = ResultadoEnum.EMPATE;
                            case 3 -> resultadoPronostico = ResultadoEnum.GANADOR_EQ2;
                            default -> {
                            }
                        }
                    }
                    columna++;
                }

                int idRonda = Integer.parseInt(linea[5]);

                //Este for recorre todas las rondas en la lista rondas
                //y guarda cada pronostico en la lista de pronosticos
                for(Ronda ronda : listaRondas)
                {
                    if(ronda.getIdRonda() == idRonda)
                    {
                        //en base a los nombres de los equipos de esta linea del archivo pronosticos, devuelve
                        // el objeto partido correspondiente a esta ronda
                        Partido partidoEncontrado = ronda.buscarPartido(linea[0], linea[4]);
                        if(partidoEncontrado != null & resultadoPronostico != null)
                        {
                            String nomParticipante = new String(linea[6]);
                            if (listaPersonas.isEmpty()){
                                Persona participante = new Persona(nomParticipante);
                                listaPersonas.add(participante);
                                Pronostico pronostico = new Pronostico(partidoEncontrado, resultadoPronostico, idRonda, participante);
                                listaPronosticos.add(pronostico);
                            }
                            else {
                                Optional<Persona> personaEncontrada = listaPersonas.stream()
                                        .filter(persona -> persona.getNombre().equals(nomParticipante))
                                        .findFirst();

                                if (personaEncontrada.isPresent()) {
                                    Persona per = personaEncontrada.get();
                                    //crea un pronostico con el partido encontrado, con el resultado del pronostico
                                    // y el id de ronda y el participante correspondiente. Y lo agrega a la lista de pronosticos
                                    Pronostico pronostico = new Pronostico(partidoEncontrado, resultadoPronostico, idRonda, per);
                                    listaPronosticos.add(pronostico);
                                } else {
                                    Persona participante = new Persona(nomParticipante);
                                    listaPersonas.add(participante);
                                    Pronostico pronostico = new Pronostico(partidoEncontrado, resultadoPronostico, idRonda, participante);
                                    listaPronosticos.add(pronostico);
                                }

                            }


                        }
                    }
                }

            }else {
                System.out.println("No se cargo el pronostico");
            }
            line = br.readLine();

        }

        for(Pronostico pro : listaPronosticos){
            pro.puntos();
        }
        for (Persona per : listaPersonas){
            System.out.println("Los puntos de "+ per.getNombre() +" son: "+per.getPuntos());
        }

    }
}
