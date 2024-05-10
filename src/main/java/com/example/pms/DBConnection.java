package com.example.pms;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private Connection cnx;

    String url = "jdbc:mysql://localhost:3306/pms";

    String user = "root";
    String pwd = "root";
    public static DBConnection ct;

    private DBConnection() {
        try {
            cnx = DriverManager.getConnection(url,user,pwd);
            System.out.println("Cnx BD etablie avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static DBConnection getInstance(){
        if(ct ==null)
            ct= new DBConnection();
        return ct;
    }

    public Connection getCnx() {
        return cnx;
    }
}
