/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.Repository;

/**
 *
 * @author FON
 */
public interface DbRepository<T> extends Repository<T>{
    default void commit() throws Exception{
            DbConnectionFactory.getInstance().getConnection().commit();
        
    }
    
    default void disconnect() throws Exception{
            DbConnectionFactory.getInstance().getConnection().close();
        
    }
    
    default void rollback() throws Exception{
            DbConnectionFactory.getInstance().getConnection().rollback();
        
    }
    
    default void connect() throws Exception{
            DbConnectionFactory.getInstance().getConnection();
        
    }
    
    
    
}
