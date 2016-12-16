package dataclasses;

import client.Broker;
import java.util.*;
import java.io.*;
import exceptions.CommodityNotFoundException;
import exceptions.IllegalBuyingException;

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
        
        //Change TeamID mechanism and shift it to server

	public Player(String name) {
		this.name = name;
		numberOfTeams++;
		teamID=numberOfTeams;
		currentBalance = Broker.getInitialBalance();
		portfolio= new ArrayList<>();
		for (Stock i: Broker.getStocks()) {
			portfolio.add(new Commodity(i));
		}
        }
}