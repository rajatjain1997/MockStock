package dataclasses;

import exceptions.CommodityNotFoundException;
import exceptions.IllegalSellingException;
import java.util.*;

/**
 *
 * @author Rajat
 */
public class Broker {
    private static final ArrayList<Player> players =new ArrayList<>();
    private static final ArrayList<Stock> stocks = new ArrayList<>();
    private static long initialBalance;

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static ArrayList<Stock> getStocks() {
        return stocks;
    }

    public static long getInitialBalance() {
        return initialBalance;
    }

    public static void setInitialBalance(long initialBalance) {
        Broker.initialBalance = initialBalance;
    }
    
    public static void buyStock(Player player, Stock stock, int quantity) {
        try {
            Commodity c = player.getCommodity(stock.getName());
            c.addQuantity(quantity);
        } catch(CommodityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void sellStock(Player player, Stock stock, int quantity) {
        try {
            Commodity c = player.getCommodity(stock.getName());
            c.removeQuantity(quantity);
        } catch(CommodityNotFoundException | IllegalSellingException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
}
