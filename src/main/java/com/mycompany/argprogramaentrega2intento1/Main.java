package com.mycompany.argprogramaentrega2intento1;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        ArrayList<Persona> listaPersonas = new ArrayList<>();
        ArrayList<Ronda> listaRondas = new ArrayList<>();
        ArrayList<Fase> listaFases = new ArrayList<>();
        Consultas consul = new Consultas();

        listaPersonas = consul.crear_listaPersonas();
        listaRondas = consul.crear_listaRondas();
        listaFases = consul.crear_listaFases();

        for(Fase fase : listaFases){

            fase.set_listaRondas(consul.crear_listaRondasxFase(fase.get_IdFase()));
        }
        int contadorRondasAcertadas = 0;
        System.out.println("LAS ESTADÍSTICAS SON LAS SIGUIENTES: \n");
        for (Persona per : listaPersonas){
            System.out.println("----------------- "+per.getNombrePer()+" -----------------");

            //ESTO DEVUELVE LOS RESULTADOS DE LOS PRONOSTICOS Y LOS RESULTADOS DE LOS PARTIDOS DE ESTA PERSONA EN TODAS LAS RONDAS
            ArrayList<Resultado> listaResultados = consul.crear_listaResultados(per.get_IdPersona());

            if(listaResultados.stream().anyMatch(Resultado::check_resultado)){
                System.out.println("Acertó los partidos: ");
            }else {
                System.out.println("No acertó ningun partido");
            }

            for(Ronda ron : listaRondas) {

                //ESTO DEVUELVE LA CANTIDAD DE PRONOSTICOS QUE TIENE UNA PERSONA EN UNA RONDA
                int cant_pronos = consul.cantPronosticosxPersona(per.get_IdPersona(), ron.getIdRonda());

                //ESTO DEVUELVE LA CANTIDAD DE PARTIDOS QUE HAY EN UNA RONDA
                int cant_parxRonda = consul.cantPartidosxRonda(ron.getIdRonda());

                if (cant_pronos == cant_parxRonda)
                {
                    int contadorPartidosAcertadosxRonda = 0;
                    int nro_fase = 0;
                    for (Resultado resul : listaResultados)
                    {
                        if (resul.get_IdRonda() == ron.getIdRonda())
                        {
                            if (resul.check_resultado()) {
                                System.out.println(resul.get_idPartido() + " de la ronda " + ron.getIdRonda());
                                contadorPartidosAcertadosxRonda++;
                            }
                        }

                        //SI ESTA CONDICION ES IGUAL, SE REFIERE A UNA RONDA
                        if (contadorPartidosAcertadosxRonda == cant_parxRonda)
                        {
                            //ESTO ME MODIFICA LOS PUNTOS DE LA PERSONA SI ES QUE ACERTO TODOS LOS PRONOSTICOS EN UNA RONDA
                            consul.suma_puntos_ronda(per.get_IdPersona());
                            System.out.println("Recibió PUNTOS EXTRA por haber acertado en todos los " +
                                    "pronósticos de la ronda " + ron.getIdRonda());

                            //NECESITO QUE EL CONTADOR DE RONDAS ACERTADAS SEA DE LAS RONDAS DE LA FASE DE RON.GETIDFASE
                            //YO TENGO EL ID FASE EN LAS RONDAS
                            for (Fase fa : listaFases){
                                if (ron.get_IdFase() == fa.get_IdFase()){
                                    contadorRondasAcertadas++;
                                }
                            }

                            //ESTO ME DEVUELVE LA CANTIDAD DE RONDAS DE UNA FASE
                            int cant_ron = consul.nroRondasxFase(ron.get_IdFase());

                            for(Fase f : listaFases)
                            {
                                if (cant_ron == contadorRondasAcertadas && ron.get_IdFase() == f.get_IdFase())
                                {
                                    //ESTO ME DEVUELVE LA FASE DE LA RONDA QUE SEA IGUAL A LA FASE QUE ESTAMOS PRESENTES
                                    nro_fase = consul.nroFase(f.get_IdFase());

                                    //YO NECESITO QUE LA FASE DE LA RONDA SEA LA MISMA QUE LA FASE DEL FOR
                                    if (nro_fase == f.get_IdFase())
                                    {
                                        consul.suma_puntos_fase(per.get_IdPersona());
                                        System.out.println("Recibió PUNTOS EXTRA por haber acertado todas las rondas de la fase " +
                                                f.get_IdFase());
                                    }
                                }

                            }
                            contadorPartidosAcertadosxRonda = 0;
                            break;
                        }

                    }

                }else {
                    for (Resultado resul : listaResultados) {
                        if (resul.get_IdRonda() == ron.getIdRonda()) {
                            if (resul.get_ResultadoPronostico().equalsIgnoreCase(resul.get_ResultadoPartido())) {
                                System.out.println(resul.get_idPartido() + " de la ronda " + ron.getIdRonda());

                            }
                        }
                    }
                }
            }
            int puntos = consul.puntaje(per.get_IdPersona());
            System.out.println("\nEl puntaje total es: " + puntos);

        }


    }
}
