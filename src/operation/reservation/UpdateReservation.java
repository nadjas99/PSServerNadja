/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.reservation;

import domain.Reservation;
import domain.ReservationDetail;
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
        Reservation r=(Reservation) param;
        System.out.println(r);
        List<ReservationDetail> detailsNew=r.getReservationDetails();
        List<ReservationDetail> detailsOld=repository.getAllBy(new ReservationDetail(), "idReservation", r.getId().toString());
        
        for (ReservationDetail reservationDetail : detailsOld) {
            if(!detailsNew.contains(reservationDetail)){
                repository.delete(reservationDetail);
            }else{
                detailsNew.remove(reservationDetail);
            }
            
        }
        for (ReservationDetail reservationDetail1 : detailsNew) {
            repository.add(reservationDetail1);
       }
       
        repository.edit(param);
        
        
    }
    
}
