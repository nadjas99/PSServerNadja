/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Client;
import domain.Photographer;
import domain.PhotographyServices;
import domain.Reservation;
import domain.ReservationDetail;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import operation.AbstractGenericOperation;
import operation.client.DeleteClient;
import operation.client.EditClient;
import operation.client.GetAllClients;
import operation.client.SaveNewClient;
import operation.photographer.LogInPhotographer;
import operation.photographyServices.GetAllServices;
import operation.reservation.GetAllReservations;
import operation.reservation.SaveNewReservation;
import operation.reservation.UpdateReservation;
import threads.Server;



/**
 *
 * @author Nadja
 */
public class Controller {
    private static Controller instance;
    private Server server;
    private Map<String,Socket> activeClients;
    
    private Controller(){
        activeClients = new HashMap<>();
    }
    
    
    
    public static Controller getInstance(){
        if(instance==null){
            instance = new Controller();
        }
        return instance;
    }
    public void startServer(){
        server = new Server();
        server.start();
    }
    public void stopServer(){
        for(String username : activeClients.keySet()){
            System.out.println(username);
            try{
                activeClients.get(username).close();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        activeClients.clear();
        server.stopServer();
    }
    public Photographer login(String username, String password, Socket socket) throws Exception{
        LogInPhotographer operation = new LogInPhotographer();
        Photographer admin = new Photographer();
        admin.setUsername(username);
        admin.setPassword(password);
        operation.execute(admin);
        admin = operation.getPhotographer();
        if(activeClients.containsKey(admin.getUsername())){
            throw new Exception("This admin/photographer is already logged in!");
        }
        activeClients.put(admin.getUsername(), socket);
       return admin;
    }
    //test da li radi commit na github
    //ne radi

    public void addClient(Client client) throws Exception {
        
        AbstractGenericOperation operation = new SaveNewClient();
        operation.execute(client);
    
    }

    public List<Client> getAllClients() throws Exception {
        GetAllClients operation=new GetAllClients();
        operation.execute(new Client());
        return operation.getClients();
       
        
    }

    public void saveNewReservation(Reservation reservation) throws Exception {
        SaveNewReservation operation=new SaveNewReservation();
        operation.execute(reservation);
        
        
    }

    public Object getAllReservations() throws Exception {
        GetAllReservations operation=new GetAllReservations();
        operation.execute(new Reservation());
        return operation.getReservations();
        
    }

    public void editClient(Client clientEdit) throws Exception {
        EditClient operation = new EditClient();
        operation.execute(clientEdit); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteClient(Client clientRemove) throws Exception {
        DeleteClient operation= new DeleteClient();
        operation.execute(clientRemove);
    }

    public List<PhotographyServices> getAllServices() throws Exception {
        GetAllServices operation=new GetAllServices();
        operation.execute(new PhotographyServices());
        return operation.getservicesList();
        
    }

    public void logOutUser(Photographer admin1) {
        activeClients.remove(admin1.getUsername()); 
    }

    public void updateReservation(Reservation reservation1) throws Exception {
        UpdateReservation operation=new UpdateReservation();
        operation.execute(reservation1);
        
    }

   
}
