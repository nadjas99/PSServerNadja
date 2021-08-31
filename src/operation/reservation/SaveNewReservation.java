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
public class SaveNewReservation extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null){
            throw new Exception("Invalid reservation parameters, cannot save in the system");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Reservation r= (Reservation) param;
        repository.add((Reservation)param);
        List<ReservationDetail> detail=r.getReservationDetails();
        for (ReservationDetail reservationDetail : detail) {
            reservationDetail.setReservation(r);
            repository.add(reservationDetail);
        }
        
    }
    
}
