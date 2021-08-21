/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import controller.Controller;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author FON
 */
public class Server extends Thread{
    private ServerSocket ss;
    public Server() {
    }

    @Override
    public void run() {
        try{
        ss = new ServerSocket(9000);
            System.out.println("Waiting for clients...");
        while(!ss.isClosed()){
            Socket socket = ss.accept();
            System.out.println("Client connected...");
            handleClient(socket);
            
        }
        }catch(SocketException se){
            System.out.println(se.getMessage());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void handleClient(Socket socket) {
        HandleClient hc = new HandleClient(socket);
        hc.start();
    }

    public void stopServer() {
        try{
            ss.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    
}
