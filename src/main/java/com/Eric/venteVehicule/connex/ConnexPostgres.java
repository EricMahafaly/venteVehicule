package com.Eric.venteVehicule.connex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexPostgres {
    public Connection getConnex() {
        String url = "jdbc:postgresql://localhost:5432/vente_vehicule";
        String username = "postgres";
        String password = "root";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}