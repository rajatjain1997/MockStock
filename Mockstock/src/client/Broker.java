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
    
    
    /**Buys a particular amount of a stock for a player, and accordingly deducts balance and increases quantity.
     * 
     * @param player
     * @param stock
     * @param quantity
     * @param roundNo 
     */
    public static void buyStock(Player player, Stock stock, int quantity, int roundNo) {
        try {
            Commodity c = player.getCommodity(stock.getName());
            long currentBalance = player.getCurrentBalance();
            player.setCurrentBalance(currentBalance-c.getStock().getPrice(roundNo)*quantity);
            c.addQuantity(quantity);
        } catch(CommodityNotFoundException | IllegalBuyingException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**Sells a particular amount of a stock for a player, and accordingly increases balance and deducts quantity.
     * 
     * @param player
     * @param stock
     * @param quantity
     * @param roundNo 
     */
    public static void sellStock(Player player, Stock stock, int quantity, int roundNo) {
        try {
            Commodity c = player.getCommodity(stock.getName());
            long currentBalance = player.getCurrentBalance();
            c.removeQuantity(quantity);
            player.setCurrentBalance(currentBalance+c.getStock().getPrice(roundNo)*quantity);
        } catch(CommodityNotFoundException | IllegalSellingException | IllegalBuyingException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
}
