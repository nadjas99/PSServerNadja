/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.List;

/**
 *
 * @author FON
 */
public interface Repository<T> {
    void add(T param) throws Exception;
    List<T> getAll(T param) throws Exception;
    List<T> getAllBy(T param, String field, String value) throws Exception;
    void edit(T param) throws Exception;
    void delete(T param) throws Exception;
    List<T> search(T param) throws Exception;
    
    
}
