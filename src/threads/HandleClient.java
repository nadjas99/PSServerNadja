/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import communication.Operation;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import controller.Controller;
import domain.Client;
import domain.Photographer;
import domain.PhotographyServices;
import domain.Reservation;

import java.net.Socket;
import java.util.List;

/**
 *
 * @author FON
 */
public class HandleClient extends Thread{
    private Socket socket;
    private boolean end=false;

    public HandleClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            Receiver receiver = new Receiver(socket);
            Sender sender = new Sender(socket);
            while(!socket.isClosed() && socket.isConnected()){
                Response response = new Response();
                Request request = (Request) receiver.receive();
                
                try{
                    switch(request.getOperation()){
                        case GET_ALL_SERVICES:
                            List<PhotographyServices> services = Controller.getInstance().getAllServices();
                            response.setResult(services);
                            break;
                        case LOGIN:
                            Photographer admin = (Photographer) request.getArgument();
                            admin = Controller.getInstance().login(admin.getUsername(), admin.getPassword(),socket);
                            response.setResult(admin);
                            response.setOperation(Operation.LOGIN);
                            break;
                        case ADD_NEW_CLIENT:
                            Client client = (Client) request.getArgument();
                            Controller.getInstance().addClient(client);
                            break;
                        case GET_ALL_CLIENTS:
                            response.setResult(Controller.getInstance().getAllClients());
                            break;
                        case ADD_NEW_RESERVATION:
                            Reservation reservation = (Reservation) request.getArgument();
                            Controller.getInstance().saveNewReservation(reservation);
                            break;
                        case GET_ALL_RESERVATIONS:
                            response.setResult(Controller.getInstance().getAllReservations());
                            break;
                        case EDIT_CLIENT:
                            Client clientEdit = (Client) request.getArgument();
                            Controller.getInstance().editClient(clientEdit);
                            break;
                        case DELETE_CLIENT:
                            Client clientRemove = (Client) request.getArgument();
                            Controller.getInstance().deleteClient(clientRemove);
                            break;  
                        
                       
                        case LOGOUT:
                            Photographer admin1 = (Photographer) request.getArgument();
                            Controller.getInstance().logOutUser(admin1);
                            break;
                        case UPDATE_RESERVATION:
                            Reservation reservation1 = (Reservation) request.getArgument();
                            Controller.getInstance().updateReservation(reservation1);
                            break;
                    }
                }catch(Exception e){
                    response.setException(e);
                }
                sender.send(response);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    
            
}
