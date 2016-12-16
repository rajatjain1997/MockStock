/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import dataclasses.Player;
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
    private Player currentPlayer = null;
    
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
    
    private Player readPlayer(int teamNo) {
        Player player=null;
        String message="Player," + teamNo;
        String wait = "Waiting for Player";
        try {
            synchronized(wait) {
                out.writeObject(message);
                wait.wait();
                player = currentPlayer;
            }
        } catch(IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return player;
    }
    
    private void writePlayer(Player player) {
        try {
            out.writeObject(player);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String args[]) {
        client = new Client("127.0.0.1");
        System.out.println(client.readPlayer(1).getName());
    }
    
    public class RemoteReader implements Runnable {
        Object obj = null;
        @Override
        public void run() {
            try {
                while((obj=in.readObject())!=null) {
                    System.out.println("Got an object from the server");
                    System.out.println(obj.getClass());
                    if(obj.getClass()==Player.class) {
                        currentPlayer = (Player) obj;
                        synchronized("Waiting for Player") {
                            "Waiting for Player".notifyAll();
                        }
                    }
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        
    }
}
