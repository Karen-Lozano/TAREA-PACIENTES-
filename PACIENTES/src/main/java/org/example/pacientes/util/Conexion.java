package org.example.pacientes.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    // Cambia estos datos por los de tu PostgreSQL
    private static final String URL = "jdbc:postgresql://localhost:5432/clinica_db";
    private static final String USUARIO = "postgres";
    private static final String CLAVE = "1234";

    public static Connection getConexion() {

        Connection conexion = null;

        try {

            conexion = DriverManager.getConnection(
                    URL,
                    USUARIO,
                    CLAVE
            );

            System.out.println("Conexión exitosa.");

        } catch (SQLException e) {

            System.out.println("Error de conexión.");
            e.printStackTrace();

        }

        return conexion;
    }

}