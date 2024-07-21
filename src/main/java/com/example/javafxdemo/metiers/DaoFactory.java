package com.example.javafxdemo.metiers;

import java.sql.*;

public class DaoFactory {

    private static Connection connexion = null ;
    private static void toConnect()   {
        try {
            System.out.println("Download Driver ....");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println(" Driver OK");
            String url ="jdbc:mysql://localhost:3306/ItemsDB";
            connexion = DriverManager.getConnection(url,"root","");
            System.out.println("success Connection ");
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("Download driver Failed !!!");
        }
        catch(SQLException ex)
        {
            System.out.println(" Pb with your connection url , login, Passwd"+ex.getMessage());
        }
    }
    public static Connection getConnection()
    {
        if(connexion == null) toConnect();

        return connexion;
    }
}
