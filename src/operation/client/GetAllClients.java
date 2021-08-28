/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.client;

import domain.Client;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Nadja
 */
public class GetAllClients extends AbstractGenericOperation{
    List<Client> clients;
    @Override
    protected void preconditions(Object param) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
         clients=repository.getAll((Client)param); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Client> getClients() {
        return clients;
    }

   
    
    
}
