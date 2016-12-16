package dataclasses;

import java.util.*;
import java.io.*;
import exceptions.CommodityNotFoundException;
import exceptions.IllegalBuyingException;
import server.Game;

public class Player implements Serializable{
	private final String name;
	private final int teamID;
	private long currentBalance;
	private final ArrayList<Commodity> portfolio;
	private static int numberOfTeams=0;

	public String getName() {
		return name;
	}

	public int getTeamID() {
		return teamID;
	}

	public long getCurrentBalance() {
		return currentBalance;
	}

	public Commodity getCommodity(String stockName) throws CommodityNotFoundException{
		for(Commodity i:portfolio) {
                    System.out.println(i.getStock().getName().trim().equalsIgnoreCase(stockName.trim()));
                    System.out.println(i.getStock().getName());
                    System.out.println(stockName);
                    char[] chr= i.getStock().getName().toCharArray();
                    for(char j:chr) {
                        System.out.println((int)j);
                    }
			if(i.getStock().getName().trim().equalsIgnoreCase(stockName)) {
				return i;
			}
		}
		throw new CommodityNotFoundException("The stock requested for does not exist in the database, sorry :-(");
	}

        public void setCurrentBalance(long currentBalance) throws IllegalBuyingException {
            if(currentBalance>=0) {
                this.currentBalance = currentBalance;
            } else {
                throw new IllegalBuyingException("You can't sell if you don't have the money.");
            }
        }

	public Player(String name) {
		this.name = name;
		numberOfTeams++;
		teamID=numberOfTeams;
		currentBalance = Game.getInitialBalance();
		portfolio= new ArrayList<>();
		for (Stock i: Game.getStocks()) {
			portfolio.add(new Commodity(i));
		}
        }
}