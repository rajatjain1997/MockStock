package client;

import dataclasses.Commodity;
import dataclasses.Player;
import dataclasses.Stock;
import exceptions.CommodityNotFoundException;
import exceptions.IllegalBuyingException;
import exceptions.IllegalSellingException;
import java.util.*;
import java.io.*;

/**
 *
 * @author Rajat
 */
public class Broker {
    private static final ArrayList<Stock> stocks = new ArrayList<>();
    private static long initialBalance;
    private static int brokerName;

    public static ArrayList<Stock> getStocks() {
        return stocks;
    }

    public static long getInitialBalance() {
        return initialBalance;
    }

    public static void setInitialBalance(long initialBalance) {
        Broker.initialBalance = initialBalance;
    }

    public static int getBrokerName() {
        return brokerName;
    }

    public static void setBrokerName(int brokerName) {
        Broker.brokerName = brokerName;
    }
    
    
    //Need to update the current balance for the player in accordance with buying and selling. This will be done by requesting the round number from the server
    //Buy Stock needs to have an IllegalBuyingException
    public static void buyStock(Player player, Stock stock, int quantity, int roundNo) {
        try {
            Commodity c = player.getCommodity(stock.getName());
            long currentBalance = player.getCurrentBalance();
            player.setCurrentBalance(currentBalance-c.getStock().getPrice(roundNo));
            c.addQuantity(quantity);
        } catch(CommodityNotFoundException | IllegalBuyingException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void sellStock(Player player, Stock stock, int quantity, int roundNo) {
        try {
            Commodity c = player.getCommodity(stock.getName());
            long currentBalance = player.getCurrentBalance();
            player.setCurrentBalance(currentBalance+c.getStock().getPrice(roundNo));
            c.removeQuantity(quantity);
        } catch(CommodityNotFoundException | IllegalSellingException | IllegalBuyingException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
}
