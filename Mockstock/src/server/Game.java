/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import dataclasses.Player;
import dataclasses.Stock;
import java.util.*;
import java.io.*;

/**
 *
 * @author Rajat
 */
public class Game {
    private static final ArrayList<Player> players = new ArrayList<>();
    private static final ArrayList<Stock> stocks = new ArrayList<>();
    private static final ArrayList<Boolean> playerLocks = new ArrayList<>();
    private static long initialBalance;
    private static int noOfBrokers=0;
    
    public static void registerPlayer(String name) {
        players.add(new Player(name));
        System.out.println("Added "+ name);
    }
    
    public static void readStocks() {
        File file = GUI.getFile();
        String line = "";
        String name;
        ArrayList<Long> prices;
        StringTokenizer st;
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            while((line=in.readLine())!=null) {
                st = new StringTokenizer(line,",");
                prices = new ArrayList<>();
                name = st.nextToken();
                char[] replacer = {(char)65279};
                name = name.replace(new String(replacer), "");
                while(st.hasMoreTokens()) {
                    prices.add(Long.parseLong(st.nextToken()));
                }
                stocks.add(new Stock(name, prices));
                System.out.println("Read" + name);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static Player getPlayer(int teamNo) {
        return players.get(teamNo-1);
    }

    public static ArrayList<Stock> getStocks() {
        return stocks;
    }

    public static long getInitialBalance() {
        return initialBalance;
    }

    public static void setInitialBalance(long initialBalance) {
        Game.initialBalance = initialBalance;
    }

    public static int getNoOfBrokers() {
        return noOfBrokers;
    }

    public static void setNoOfBrokers(int noOfBrokers) {
        Game.noOfBrokers = noOfBrokers;
    }
    
    
}
