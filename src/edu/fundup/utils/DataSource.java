package edu.fundup.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author WIKI
 */
public class DataSource {
    String myDriver = "com.mysql.jdbc.Driver";

    String url = "jdbc:mysql://localhost:3306/charity?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";

    String login = "root";
    String password = "";
    private Connection connection;
    private static DataSource instance;

    private DataSource() {
      try{
        connection=DriverManager.getConnection(url,login,password);
        System.out.println("cnx etablie");    
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("erreur");
}
    }

    public Connection getConnection() {
        return connection;
    }
    

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

}