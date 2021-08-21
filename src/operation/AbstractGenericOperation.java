/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation;

import repository.Repository;
import repository.db.DbRepository;
import repository.db.impl.RepositoryDbGeneric;

/**
 *
 * @author FON
 */
public abstract class AbstractGenericOperation {
    //opsta sistemska operacija
    protected final Repository repository;
    

    public AbstractGenericOperation() {
        this.repository = new RepositoryDbGeneric();
    }
    

    public final void execute(Object param) throws Exception{
        try{
            preconditions(param);
            startTransaction();
            executeOperation(param);
            commitTransaction();
        }catch(Exception e){
            e.printStackTrace();
            rollbackTransaction();
            throw e;
        }finally{
            disconnect();
        }
    }

    protected abstract void preconditions(Object param) throws Exception;
    
    private void startTransaction() throws Exception {
        ((DbRepository)repository).connect();
    }

    protected abstract void executeOperation(Object param) throws Exception;

    private void commitTransaction() throws Exception {
        ((DbRepository)repository).commit();
    }

    private void rollbackTransaction() throws Exception {
        ((DbRepository)repository).rollback();
    }

    private void disconnect() throws Exception {
        ((DbRepository)repository).disconnect();
    }
    
}
