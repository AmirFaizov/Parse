package ru.kpfu.itis.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    private static ConnectionProvider _instance;

    public static ConnectionProvider getInstance() throws DbException {
        if(_instance == null){
            _instance = new ConnectionProvider();
        }
        return _instance;
    }

    private Connection con;



    private ConnectionProvider() throws DbException {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost/1_books","postgres","postgres");
        } catch (SQLException | ClassNotFoundException e) {
            throw new DbException("Can't connect to DB.", e);
        }
    }

    public Connection getCon() {
        return con;
    }
}
