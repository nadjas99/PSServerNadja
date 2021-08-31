/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.photographer;

import domain.Photographer;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Nadja
 */
public class LogInPhotographer extends AbstractGenericOperation{

    
    Photographer photographer;
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        List<Photographer> photographersList = repository.getAll((Photographer) param);
        Photographer ph = (Photographer) param;
        for(Photographer photographer1 : photographersList){
            if(photographer1.getUsername().equals(ph.getUsername()) && photographer1.getPassword().equals(ph.getPassword())){
                photographer = photographer1;
                return;
            }
        }
        throw new Exception("Photographer with given credentials is not registered in our system!");
    }

    public Photographer getPhotographer() {
        System.out.println(photographer.getId());
        return photographer;
    }
    
    

    
    
    
    
}
