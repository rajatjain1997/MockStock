import java.util.*;

public class Player {
	private String name;
	private int teamID;
	private long currentBalance;
	private ArrayList<Commodity> portfolio;
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
			if(i.getName().isEqual(stockName)) {
				return i;
			}
		}
		throw CommodityNotFoundException;
	}

	public Player(String name, long initialBalance) {
		this.name = name;
		numberOfTeams++;
		teamID=numberOfTeams;
		currentBalance = initialBalance;
		portfolio= new ArrayList<Commodity>();
		for (Stock i: Game.getStocks()) {
			portfolio.add(new Commodity(i));
		}
	}
}