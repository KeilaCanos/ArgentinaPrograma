package com.mycompany.argprogramaentrega2intento1;

import org.json.simple.parser.ParseException;
import java.sql.Connection;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Consultas
{
    ConexionBBDD conexion = new ConexionBBDD();
    public ArrayList<Persona> crear_listaPersonas()
    {
        String COLUMNAS_PERSONAS = "SELECT * FROM PERSONA";
        ResultSet resSet;
        Statement state;
        ArrayList<Persona> listaPersonas = new ArrayList<>();
        try {
            state = conexion.conectar().createStatement();
            resSet = state.executeQuery(COLUMNAS_PERSONAS);
            while (resSet.next()) {
                Persona per = new Persona(resSet.getInt("IDPERSONA"), resSet.getString("NOMBREPER"), resSet.getInt("PUNTOS"));
                listaPersonas.add(per);
            }
            state.close();
            resSet.close();
            conexion.cerrar_conexion();
        } catch (SQLException | IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        return listaPersonas;
    }

    public ArrayList<Ronda> crear_listaRondas()
    {
        String CANT_RONDAS = "SELECT * FROM RONDA";
        ArrayList<Ronda> listaRondas = new ArrayList<>();
        ResultSet resSet;
        Statement state;
        try {
            state = conexion.conectar().createStatement();
            resSet = state.executeQuery(CANT_RONDAS);
            while(resSet.next())
            {
                Ronda ron = new Ronda(resSet.getInt("ID_RONDA"), resSet.getInt("FK_FASE"));
                listaRondas.add(ron);
            }
            state.close();
            resSet.close();
            conexion.cerrar_conexion();

        } catch (SQLException | IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        return listaRondas;
    }

    public ArrayList<Fase> crear_listaFases()
    {
        String DATOS_FASE = "SELECT * FROM FASE";
        ResultSet resSet;
        Statement state;
        ArrayList<Fase> listaFases = new ArrayList<>();
        try {
            state = conexion.conectar().createStatement();
            resSet = state.executeQuery(DATOS_FASE);
            while(resSet.next())
            {
                Fase fase = new Fase(resSet.getInt("ID_FASE"));
                listaFases.add(fase);
            }
            resSet.close();
            state.close();
            conexion.cerrar_conexion();
        } catch (SQLException | IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        return listaFases;
    }

    public ArrayList<Ronda> crear_listaRondasxFase(int id_fase)
    {
        String RONDAS_FASE = "SELECT * FROM RONDA WHERE FK_FASE = ?";
        ResultSet resSet;
        ArrayList<Ronda> listaRondasxFase = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.conectar().prepareStatement(RONDAS_FASE);
            ps.setInt(1, id_fase);
            resSet = ps.executeQuery();
            while(resSet.next()){
                Ronda ron = new Ronda(resSet.getInt("ID_RONDA"), resSet.getInt("FK_FASE"));
                listaRondasxFase.add(ron);
            }
            resSet.close();
            ps.close();
            conexion.cerrar_conexion();
        } catch (SQLException | IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        return listaRondasxFase;
    }

    public ArrayList<Resultado> crear_listaResultados(int idpersona)
    {
        String VERIF_ACIERTOS_RONDA = "SELECT PRO.EQUIPO_RESULTADO AS RESULTADO_PRONOSTICO, PAR.EQUIPO_GANADOR AS RESULTADO_PARTIDO, PAR.RONDA, PAR.ID_PARTIDO FROM PRONOSTICO PRO INNER JOIN PARTIDO PAR ON PAR.ID_PARTIDO = PRO.ID_PARTIDO WHERE IDPERSONA = ?";
        ResultSet resSet;
        ArrayList<Resultado> listaResultados = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.conectar().prepareStatement(VERIF_ACIERTOS_RONDA);
            ps.setInt(1, idpersona);
            resSet = ps.executeQuery();

            while(resSet.next())
            {
                Resultado result = new Resultado(resSet.getInt("RONDA"), resSet.getString("RESULTADO_PRONOSTICO"), resSet.getString("RESULTADO_PARTIDO"), resSet.getInt("ID_PARTIDO"));
                listaResultados.add(result);
            }
            resSet.close();
            ps.close();
            conexion.cerrar_conexion();
        } catch (SQLException | IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        return listaResultados;
    }

    public int cantPronosticosxPersona(int per_idper, int ron_idron)
    {
        String CANT_PRO_PERSONA = "SELECT COUNT(*) AS CANT FROM PRONOSTICO PRO INNER JOIN PARTIDO PAR ON PAR.ID_PARTIDO = PRO.ID_PARTIDO WHERE PRO.IDPERSONA=? AND PAR.RONDA = ?";
        ResultSet resSet;
        int cant_pronos = 0;
        try {
            PreparedStatement ps = conexion.conectar().prepareStatement(CANT_PRO_PERSONA);
            ps.setInt(1, per_idper);
            ps.setInt(2, ron_idron);
            resSet = ps.executeQuery();
            while (resSet.next()) {
                cant_pronos = resSet.getInt("CANT");
            }
            resSet.close();
            ps.close();
            conexion.cerrar_conexion();
        } catch (SQLException | IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        return cant_pronos;
    }

    public int cantPartidosxRonda(int ron_idron)
    {
        String CANT_PAR = "SELECT COUNT(*) AS CANT FROM PARTIDO WHERE RONDA=?";
        ResultSet resSet;
        int cant_parxRonda = 0;
        try {
            PreparedStatement ps = conexion.conectar().prepareStatement(CANT_PAR);
            ps.setInt(1, ron_idron);
            resSet = ps.executeQuery();
            while (resSet.next()) {
                cant_parxRonda = resSet.getInt("CANT");
            }
            resSet.close();
            ps.close();
            conexion.cerrar_conexion();

        } catch (SQLException | IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        return cant_parxRonda;
    }

    public int nroRondasxFase(int ron_idfase)
    {
        String NRO_RONDAS_FASE = "SELECT COUNT(*) AS CANT_RON FROM RONDA WHERE FK_FASE = ?";
        ResultSet resSet;
        int cant_ron = 0;
        try {
            PreparedStatement ps = conexion.conectar().prepareStatement(NRO_RONDAS_FASE);
            ps.setInt(1, ron_idfase);
            resSet = ps.executeQuery();
            while (resSet.next()){
                cant_ron = resSet.getInt("CANT_RON");
            }
            resSet.close();
            ps.close();
            conexion.cerrar_conexion();

        } catch (SQLException | IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        return cant_ron;
    }

    public int nroFase(int f_idfase)
    {
        String FASE_PRESENTE = "SELECT ID_RONDA AS RONDA_CORRESP, FK_FASE AS NRO_FASE FROM RONDA WHERE FK_FASE = ?";
        ResultSet resSet;
        int nro_fase = 0;
        try {
            PreparedStatement ps = conexion.conectar().prepareStatement(FASE_PRESENTE);
            ps.setInt(1, f_idfase);
            resSet = ps.executeQuery();
            while (resSet.next()){
                nro_fase = resSet.getInt("NRO_FASE");
            }
            resSet.close();
            ps.close();
            conexion.cerrar_conexion();

        } catch (SQLException | IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        return nro_fase;
    }

   public void suma_puntos_ronda(int per_idper)
   {
       String SUMAR_PUNTOS_RONDA = ("UPDATE PERSONA SET PUNTOS = PUNTOS+2 WHERE IDPERSONA = ?");
       try {
           PreparedStatement ps = conexion.conectar().prepareStatement(SUMAR_PUNTOS_RONDA);
           ps.setInt(1, per_idper);
           ps.executeUpdate();

           ps.close();
           conexion.cerrar_conexion();

       } catch (SQLException | IOException | ParseException e) {
           throw new RuntimeException(e);
       }
   }

   public void suma_puntos_fase(int per_idper)
   {
       String SUMAR_PUNTOS_FASE = ("UPDATE PERSONA SET PUNTOS = PUNTOS+5 WHERE IDPERSONA = ?");
       try {
           PreparedStatement ps = conexion.conectar().prepareStatement(SUMAR_PUNTOS_FASE);
           ps.setInt(1, per_idper);
           ps.executeUpdate();

           ps.close();
           conexion.cerrar_conexion();

       } catch (SQLException | IOException | ParseException e) {
           throw new RuntimeException(e);
       }
   }

   public int puntaje(int per_idper){
       String PUNTAJE = ("SELECT PUNTOS FROM PERSONA WHERE IDPERSONA = ?");
       int puntos = 0;
       ResultSet resSet;
       try {
           PreparedStatement ps = conexion.conectar().prepareStatement(PUNTAJE);
           ps.setInt(1, per_idper);
           resSet = ps.executeQuery();
           while (resSet.next()){
               puntos = resSet.getInt("PUNTOS");
           }
           resSet.close();
           ps.close();
           conexion.cerrar_conexion();

       } catch (SQLException | IOException | ParseException e) {
           throw new RuntimeException(e);
       }
       return puntos;
   }
}
