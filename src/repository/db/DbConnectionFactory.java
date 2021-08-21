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
            Properties properties = new Properties();
            properties.load(new FileInputStream("config/dbconfig.properties"));
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            conn = DriverManager.getConnection(url,username,password);
            conn.setAutoCommit(false);
        }
        return conn;
    }
}
