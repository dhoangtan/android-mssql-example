package com.example.dbconnection.services;

import android.os.StrictMode;

import net.sourceforge.jtds.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MsSqlConnector {

    private String ip = "192.168.74.1";
    private String port = "1433";
    private String database = "TodoDB";
    private String username = "sa";
    private String password = "1234";
    private String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;

    private Connection connection = null;

    private   MsSqlConnector() {
        connect();
    }

    private MsSqlConnector(String ip, String port, String database, String username, String password) {
        this.ip = ip;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;

        this.url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;
        connect();
    }

    private static MsSqlConnector instance;

    public static synchronized MsSqlConnector getInstance() {
        if(instance == null)
            instance = new MsSqlConnector();
        return instance;
    }

    public static synchronized MsSqlConnector getInstance(String ip, String port, String database, String username, String password) {
        if (instance == null)
            instance = new MsSqlConnector(ip, port, database, username, password);
        return instance;
    }

    private void connect() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(url, username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet query(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int execute(String command)  {
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(command);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
