/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.reservation;

import domain.Reservation;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Nadja
 */
public class UpdateReservation extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null){
            throw new Exception("Invalid reservation parameters");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
       
        List<Reservation> reservations=repository.getAll(new Reservation());
        
    }
    
}
