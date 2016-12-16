package dataclasses;

import client.Broker;
import java.util.*;
import exceptions.CommodityNotFoundException;
import exceptions.IllegalBuyingException;

public class Player {
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
			if(i.getStock().getName().equals(stockName)) {
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
        
        

	public Player(String name, long initialBalance) {
		this.name = name;
		numberOfTeams++;
		teamID=numberOfTeams;
		currentBalance = initialBalance;
		portfolio= new ArrayList<>();
		for (Stock i: Broker.getStocks()) {
			portfolio.add(new Commodity(i));
		}
        }
}