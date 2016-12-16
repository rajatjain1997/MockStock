/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import dataclasses.Player;
import java.util.*;
import java.net.*;
import java.io.*;

/**
 *
 * @author Rajat
 */
public class Server {
    private ArrayList<ObjectOutputStream> clientOutputStreams;
    private static Server server;
    
    private Server() {
        initializeServer();
    }
    
    public void initializeServer() {
        clientOutputStreams = new ArrayList<>();
        try {
            ServerSocket serverSock = new ServerSocket(4242);
            while(true) {
                Socket clientSocket = serverSock.accept();
                ObjectOutputStream out  = new ObjectOutputStream(clientSocket.getOutputStream());
                clientOutputStreams.add(out);
                
                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
                System.out.println("A broker connected!");
            } 
        } catch (Exception ex) {
            ex.printStackTrace();
            }
    }
    
    public static void main(String args[]) {
        Game.readStocks();
        server = new Server();
    }

    public class ClientHandler implements Runnable {

        ObjectInputStream in;
        Socket sock;
        int brokerName;
        
        public ClientHandler(Socket clientSocket) {
            try {
                sock = clientSocket;
                in = new ObjectInputStream(sock.getInputStream());
                brokerName=Game.getNoOfBrokers()+1;
                Game.setNoOfBrokers(Game.getNoOfBrokers()+1);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        @Override
        public void run() {
            String message;
            try {
                while((message=(String)in.readObject())!=null) {
                    StringTokenizer st = new StringTokenizer(message, ",");
                    if(st.nextToken().equals("Player")) {
                        clientOutputStreams.get(brokerName-1).writeObject(Game.getPlayer(Integer.parseInt(st.nextToken())));
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        
    }
}
