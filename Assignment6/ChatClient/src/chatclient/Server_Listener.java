/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient;
import ecs100.*;
import java.io.*;
import java.net.Socket;
import java.util.*;
/**
 *
 * @author gregordevo
 */
public class Server_Listener extends Thread implements Runnable  {
    Socket Server_Socket;
    BufferedReader input;
    String Server_Reply;
    public void Server_Listener(Socket irc){
        try{
        Server_Socket = irc;
        input = new BufferedReader(new InputStreamReader(Server_Socket.getInputStream()));
        
        }catch(Exception e){
            e.toString();
        }
    }
    
    @Override
    public void run(){
        while(true){
            System.out.println("Listener Running");
        try{
            Server_Reply = input.readLine();
            if (Server_Reply != null){
                UI.println(Server_Reply);
            }
                    
            
        }catch(IOException e){
            e.toString();
        }
        try{
         Thread.sleep(1000);   
        }catch(InterruptedException e){
            System.out.println(e.toString());
        }
        }
    }
    
}
