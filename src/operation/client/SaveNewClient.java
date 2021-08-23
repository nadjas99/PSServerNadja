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
public class SaveNewClient extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param==null || !(param instanceof Client)) {
            throw new Exception("Invalid data for type Client");
        }
        List<Client> clients=repository.getAll(param);
        for (Client client : clients) {
            if(client.getEmail().equalsIgnoreCase(((Client) param).getEmail())){
                throw new Exception("This client already exists in our system");
            }
        }
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Client)param);
    }
    
    
    
    
}
