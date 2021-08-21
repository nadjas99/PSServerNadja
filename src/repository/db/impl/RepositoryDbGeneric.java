/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import domain.GenericEntity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import repository.db.DbConnectionFactory;
import repository.db.DbRepository;


/**
 *
 * @author FON
 */
public class RepositoryDbGeneric implements DbRepository<GenericEntity>{

    @Override
    public void add(GenericEntity entity) throws Exception {
       try{
           Connection connection = DbConnectionFactory.getInstance().getConnection();
           StringBuilder sb = new StringBuilder();
           sb.append("INSERT INTO ").append(entity.getTableName())
                   .append(" (")
                   .append(entity.getColumnNamesForInsert())
                   .append(") ")
                   .append(" VALUES (")
                   .append(entity.getInsertValues())
                   .append(") ");
           String query = sb.toString();
           System.out.println(query);
           Statement statement = connection.createStatement();
           statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
           ResultSet rsKey = statement.getGeneratedKeys();
           if(rsKey.next()){
               Long id = rsKey.getLong(1);
               entity.setId(id);
           }
           statement.close();
           rsKey.close();
           
    
                   
       }catch(Exception e){
           throw e;
       }
    }
    
    @Override
    public List<GenericEntity> getAll(GenericEntity entity) throws Exception {
        try{
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * from ")
                .append(entity.getTableName()).append(" ").append(entity.getJoinCondition());
        Statement statement = connection.createStatement();
        return entity.getList(statement.executeQuery(sb.toString()));
        }catch(Exception e){
            throw e;
            
        }
        
    }

    @Override
    public void edit(GenericEntity entity) throws Exception {
        try{
           Connection connection = DbConnectionFactory.getInstance().getConnection();

            String query = new StringBuilder()
                    .append("UPDATE ")
                    .append(entity.getTableName())
                    .append(" SET ")
                    .append(entity.getUpdateValues())
                    .append(" WHERE ")
                    .append(entity.getObjectCase())
                    .toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            
            statement.close();
            
            
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public List<GenericEntity> getAllBy(GenericEntity param, String field, String value) throws Exception {
        try{
        Connection connection = DbConnectionFactory.getInstance().getConnection();

        StringBuilder sb = new StringBuilder();
                sb.append("SELECT * FROM ")
                .append(param.getTableName())
                .append(" ").append(param.getJoinCondition()).append(" ")
                .append(" WHERE ").append(field).append(" = '").append(value).append("'");
        String query = sb.toString();
            System.out.println(query);
        
        Statement s = connection.createStatement();

        return param.getList(s.executeQuery(query));
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public void delete(GenericEntity entity) throws Exception {
        try{
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        
        String query = new StringBuilder()
                .append("DELETE FROM ")
                .append(entity.getTableName())
                .append(" WHERE ")
                .append(entity.getObjectCase())
                .toString();
        System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            
            statement.close();
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public List<GenericEntity> search(GenericEntity param) throws Exception {
        try{
        Connection connection = DbConnectionFactory.getInstance().getConnection();

        StringBuilder sb = new StringBuilder();
                sb.append("SELECT * FROM ")
                .append(param.getTableName())
                .append(" ").append(param.getJoinCondition()).append(" ")
                .append(" WHERE ").append(param.getSearchCase());
        String query = sb.toString();
            System.out.println(query);
        
        Statement s = connection.createStatement();

        return param.getList(s.executeQuery(query));
        }catch(Exception e){
            throw e;
        }
    }
    
}
