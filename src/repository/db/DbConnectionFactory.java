/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.TimeZone;

/**
 *
 * @author FON
 */
public class DbConnectionFactory {
    private static DbConnectionFactory instance;
    private Connection conn;
    private DbConnectionFactory(){
        
    }
    
    public static DbConnectionFactory getInstance(){
        if(instance==null){
            instance = new DbConnectionFactory();
        }
        return instance;
    }
    
    public Connection getConnection() throws Exception{
        if(conn==null || conn.isClosed()){
          
            //String url = "jdbc:mysql://localhost:3306/psprojekat";
            String url = "jdbc:mysql://localhost:3306/psprojekat?serverTimezone=" + TimeZone.getDefault().getID();
            String username = "root";
            String password = "root";
            conn = DriverManager.getConnection(url,username,password);
            conn.setAutoCommit(false);
        }
        return conn;
    }
}
