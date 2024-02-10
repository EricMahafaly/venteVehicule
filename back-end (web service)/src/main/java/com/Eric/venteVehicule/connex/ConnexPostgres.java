package com.Eric.venteVehicule.connex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexPostgres {
    public Connection getConnex() {
        String url = "jdbc:postgresql://roundhouse.proxy.rlwy.net:22051/railway";
        String username = "postgres";
        String password = "F1-ddde2dfdd6DC1dbe-1dFCf3C*abbB";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}