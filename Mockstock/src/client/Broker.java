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
    
    private static ArrayList<Stock> stocks;

    public static ArrayList<Stock> getStocks() {
        return stocks;
    }

    public static void setStocks(ArrayList<Stock> stocks) {
        Broker.stocks = stocks;
    }
    
    

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
