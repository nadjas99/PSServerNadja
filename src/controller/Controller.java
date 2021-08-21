/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Photographer;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import operation.photographer.LogInPhotographer;
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
        for(String email : activeClients.keySet()){
            System.out.println(email);
            try{
                activeClients.get(email).close();
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
}
