package com.mycompany.argprogramaentrega2intento1;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;
import java.util.*;
import java.sql.*;
import java.lang.*;
import java.io.*;

public class ConexionBBDD
{
    //Class.forName("com.mysql.jdbc.Driver");
    String url;
    String usuario;
    String password;
    Connection miConexion;


    public Connection conectar () throws SQLException, IOException, ParseException
    {
        try (BufferedReader br = new BufferedReader(new FileReader("datosBBDD.json")))
        {
            String linea = br.readLine();
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> map = objectMapper.readValue(linea, new TypeReference<Map<String,String>>(){});
            this.url = map.get("url");
            this.usuario = map.get("usuario");
            this.password = map.get("password");

            miConexion = DriverManager.getConnection(url, usuario, password);
            return miConexion;
        }
    }

    public void cerrar_conexion()
    {
        if (miConexion != null){
            try {
                miConexion.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }




}
