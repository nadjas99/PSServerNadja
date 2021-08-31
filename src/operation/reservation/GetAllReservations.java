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
public class GetAllReservations extends AbstractGenericOperation{
    List<Reservation> reservations;
    @Override
    protected void preconditions(Object param) throws Exception {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        reservations=repository.getAll((Reservation)param);
        for (Reservation reservation : reservations) {
            reservation.setReservationDetails(repository.getAllBy(new ReservationDetail(), "idReservation", reservation.getId().toString()));
            for (ReservationDetail rd: reservation.getReservationDetails()) {
                rd.setReservation(reservation);
                
            }
        }
        
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
    
}
