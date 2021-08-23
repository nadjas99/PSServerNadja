/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.photographyServices;

import domain.PhotographyServices;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Nadja
 */
public class GetAllServices extends AbstractGenericOperation{

    List<PhotographyServices> servicesList;
   
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        servicesList = repository.getAll((PhotographyServices) param);
  }

    public List<PhotographyServices> getservicesList() {
        return servicesList;
    }
    
    
    
}
