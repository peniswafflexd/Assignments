// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP112 assignment.
// You may not distribute it in any other way without permission.
package chatclient;
/* Code for COMP112 - 2017T1, Assignment 6
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.io.*;
import java.net.Socket;
import java.util.*;

/**
 * Basic IRC Chat Client
 */
public class ChatClient {

    
    BufferedReader input;
    BufferedWriter output;
    private String server = "irc.ecs.vuw.ac.nz";  // default IRC server for testing.
    private static final int IRC_PORT = 6667;     // The standard IRC port number.
    Socket Server_Socket ;
    /*# YOUR CODE HERE */
    /**
     * main: construct a new ChatClient
     */
    public static void main(String[] args) {
        new ChatClient();
    }

    /* 
     * Sets up the user interface.
     */
    public ChatClient() {
        UI.addButton("Connect", this::connect);
        UI.addButton("Disconnect", this::closeConnection);
        /*# YOUR CODE HERE */
        try{
         Server_Socket = new Socket(server, IRC_PORT);
            input = new BufferedReader(new InputStreamReader(Server_Socket.getInputStream()));
            output = new BufferedWriter(new OutputStreamWriter(Server_Socket.getOutputStream()));
        }catch(Exception e){
            e.toString();
        }
    }

    /**
     * If there is currently an active socket, it should close the connection
     * and set the socket to null. Creates a socket connected to the server.
     * Creates a Scanner on the input stream of the socket, and a PrintStream on
     * the output stream of the socket. Logs in to the server (calling the
     * loginToServer Message) Once login is successful, starts a separate thread
     * to listen to the server and process the messages from it.
     */
    public void connect() {
        /*# YOUR CODE HERE */
        try {
           
            if (login() == true) {
                UI.clearText();
                UI.println("Welcome");

            } else {
                UI.clearText();
                UI.println("Login Failed - that username is already in use");
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    /*
     * Attempt to log in to the Server and return true if successful, false if not.
     *  Ask user for username and real name
     *  Send info to server (NICK command and USER command)
     *  Read lines from server until get a message containing 004 (success) or
     *   a message containing 433 (failure - nickname in use)
     *  (For debugging, at least, print out all lines from the server)
     */
    private boolean login() {
        String nickname = UI.askToken("Enter your username: ");
        String realname = UI.askString("Enter your real name: ");
        /*# YOUR CODE HERE */
        try {
            output.write("NICK " + nickname + " \r\n");
            output.write("USER " + nickname + " 0 " + "unused :" + realname + "\r\n");
            output.flush();
            while (true) {
                String Login_Reply = input.readLine();
                UI.println("connecting...");
                UI.sleep(1000);
                //UI.println(Login_Reply);
                if (Login_Reply.equals("004") ){
                    return true;
                    
                } else if (Login_Reply.equals("433")) {
                    return false;
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }

    /**
     * Send a message to the current server: - check that the socket and the
     * serverOut are not null - print the message with a \r\n at the end to
     * serverOut - flush serverOut (to ensure the message is sent)
     */
    private void send(String msg) {
        /*# YOUR CODE HERE */

    }

    /**
     * Method run in the the thread that is listening to the server. Loop as
     * long as there is anything in the serverIn scanner: Get and process the
     * next line of input from the scanner Simple version: prints the line out
     * for the user Checks if the line contains "SQUIT", if so, close the
     * socket, set serverIn and serverOut set the quit the program. if the line
     * contains "PING", send a PONG message back (must be identical to the line,
     * but with "PING" replaced by "PONG") Better version parses the line into
     * source, command, params, finalParam (where the source and finalParam are
     * optional, and params may be empty) Then deals with the message
     * appropriately.
     */
    private void listenToServer() {
        /*# YOUR CODE HERE */

    }

    /**
     * Close the connection: - close the socket, - set the serverIn and
     * serverOut to null - print a message to the user
     */
    public void closeConnection() {
        /*# YOUR CODE HERE */
        try {
        output.write("QUIT\r\n");
        UI.clearText();
        UI.println("You have Disconnected from the server");
        
        Server_Socket.close();
        output.close();
        input.close();        
        }
        catch(Exception e){
        UI.println(e.toString());    
        
        }
    }

    // Other methods 
}
