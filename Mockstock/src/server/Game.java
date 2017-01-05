/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import dataclasses.Player;
import dataclasses.Stock;
import exceptions.PlayerLockedException;
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
    private static long initialBalance=100000;
    private static int noOfBrokers=0;
    private static int currentRound=1;
    
    public static int registerPlayer(String name) {
        players.add(new Player(name));
        playerLocks.add(false);
        System.out.println("Added "+ name);
        return Player.getNumberOfTeams();
    }
    
    /**Reads a .csv file and loads the stocks in the program.
     * 
     */
    
    public static void readStocks() {
        stocks.removeAll(stocks);
        File file = GUI.getFile();
        String line = "";
        String name;
        ArrayList<Long> prices;
        StringTokenizer st;
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            while((line=in.readLine())!=null) {
                System.out.println(line);
                st = new StringTokenizer(line,",");
                prices = new ArrayList<>();
                name = st.nextToken();
                char[] replacer = name.toCharArray();
                name="";
                for(char i:replacer) {
                    if(('a'<=i&&i<='z')||('A'<=i&&i<='Z')||i==' '||('0'<=i&&i<='9')||i==',') {
                        name+=i;
                    }
                }
                while(st.hasMoreTokens()) {
                    prices.add(Long.parseLong(st.nextToken()));
                }
                stocks.add(new Stock(name, prices));
                System.out.println("Read " + name + prices);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static Player getPlayer(int teamNo) throws PlayerLockedException {
        if(teamNo<=Player.getNumberOfTeams()&&!playerLocks.get(teamNo-1)) {
            playerLocks.set(teamNo-1, true);
            System.out.println("Locked"+players.get(teamNo-1));
            return players.get(teamNo-1);
        }
        throw new PlayerLockedException("The player is being accessed by another client");
    }
    
    public static void setPlayer(Player player) {
        players.set(player.getTeamID()-1, player);
        playerLocks.set(player.getTeamID()-1, false);
        System.out.println("Unlocked"+player);
        GUI.inform();
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

    public static int getCurrentRound() {
        return currentRound;
    }

    public static void setCurrentRound(int currentRound) {
        Game.currentRound = currentRound;
        System.out.println(currentRound);
    }

    public static ArrayList<Player> getPlayers() {
        return (ArrayList<Player>)players.clone();
    }
    
    
    
}
