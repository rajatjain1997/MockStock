/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import dataclasses.Player;
import dataclasses.Stock;
import exceptions.PlayerLockedException;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rajat
 */
public class Client {
    
    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private static Client client = null;
    private Player currentPlayer = null;
    private int currentRound = 0;
    private int currentTeam = 0;
    
    private Client(String address) throws Exception{
        initializeServerConnection(address);
    }

    public static Client getInstance() {
        return client;
    }
    
    
    
    private void initializeServerConnection(String address) throws Exception {
            Socket sock = new Socket(address, 4242);
            out = new ObjectOutputStream(sock.getOutputStream());
            in = new ObjectInputStream(sock.getInputStream());
            Thread remote = new Thread(new RemoteReader());
            remote.start();
    }
    
    public Player readPlayer(int teamNo) throws PlayerLockedException{
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
    
    public int readRoundNo() {
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
    
    public void writePlayer(Player player) {
        try {
            out.writeObject(player);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public int registerPlayer(String name) {
        String message = "Register,"+name;
        String wait = "Waiting for Team Number";
        try {
            synchronized(wait) {
                out.writeObject(message);
                wait.wait();      
            }
        } catch(IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return currentTeam;
    }
    
    public void readStocks() {
        String message = "Stocks";
        try {
            out.writeObject(message);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void initializeConnection(String address) throws Exception{
        if(client==null) {
            client = new Client(address);
        }
    }
    
    public static void main(String args[]) {
        GUI.buildGUI();
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
                        } else if(((String)obj).startsWith("Team")) {
                            StringTokenizer st = new StringTokenizer((String)obj," ");
                            st.nextToken();
                            currentTeam = Integer.parseInt(st.nextToken());
                            synchronized("Waiting for Team Number") {
                                "Waiting for Team Number".notifyAll();
                            }
                        }
                    } else if(obj.getClass()==Integer.class) {
                        currentRound = (Integer)obj;
                        synchronized("Waiting for Round Info") {
                            "Waiting for Round Info".notifyAll();
                        }
                    } else if(obj.getClass()==ArrayList.class) {
                        Broker.setStocks((ArrayList<Stock>)obj);
                    }
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        
    }
}
