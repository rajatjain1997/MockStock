/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import dataclasses.Player;
import exceptions.PlayerLockedException;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rajat
 */
public class Client {
    
    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private static Client client;
    private Player currentPlayer = null;
    private int currentRound = 0;
    
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
    
    private Player readPlayer(int teamNo) throws PlayerLockedException{
        Player player=null;
        String message="Player," + teamNo;
        String wait = "Waiting for Player";
        try {
            synchronized(wait) {
                out.writeObject(message);
                wait.wait();
                if(currentPlayer!=null) {
                    player = currentPlayer;
                    return player;
                } else {
                    throw new PlayerLockedException("Player is locked");
                }
            }
        } catch(IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return player;
    }
    
    private int readRoundNo() {
        int roundNo = 0;
        String message="Round";
        String wait = "Waiting for Round Info";
        try{
            synchronized(wait) {
                out.writeObject(message);
                wait.wait();
                roundNo = currentRound;
            }
        } catch(IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
        return roundNo;
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
        Player p = null;
        try {
            p = client.readPlayer(1);
        } catch (PlayerLockedException ex) {
            System.out.println(ex.getMessage());
        }
        client.writePlayer(p);
        System.out.println(client.readRoundNo());
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
                    } else if(obj.getClass()==String.class) {
                        if(((String)obj).equals("Error 001 - Player is locked")) {
                            currentPlayer = null;
                            synchronized("Waiting for Player") {
                                "Waiting for Player".notifyAll();
                            }
                        }
                    } else if(obj.getClass()==Integer.class) {
                        currentRound = (Integer)obj;
                        synchronized("Waiting for Round Info") {
                            "Waiting for Round Info".notifyAll();
                        }
                    }
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        
    }
}
