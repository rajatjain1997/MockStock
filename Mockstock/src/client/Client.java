/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.net.*;
import java.io.*;

/**
 *
 * @author Rajat
 */
public class Client {
    
    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private static Client client;
    
    private Client(String address) {
        initializeServerConnection(address);
    }
    
    private void initializeServerConnection(String address) {
        try{
            Socket sock = new Socket(address, 4242);
            out = new ObjectOutputStream(sock.getOutputStream());
            in = new ObjectInputStream(sock.getInputStream());
            Thread remote = new Thread(new RemoteReader());
            remote.start();
        } catch(Exception ex) {
            System.out.println("Couldn't connect to server!");
        }
    }
    
    public static void main(String args[]) {
        client = new Client("127.0.0.1");
    }
    
    public class RemoteReader implements Runnable {
        Object obj = null;
        @Override
        public void run() {
            try {
                while((obj=in.readObject())!=null) {
                    System.out.println("Got an object from the server");
                    System.out.println(obj.getClass());
                    
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        
    }
}
