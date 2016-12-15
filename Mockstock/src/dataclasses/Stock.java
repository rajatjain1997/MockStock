package dataclasses;

import java.util.*;

public class Stock{
	private String name;
	private ArrayList<Double> prices;

	public String getName() {
		return name;
	}

	public double getPrice(int roundNo) {
		return prices.get(roundNo-1);
	}

	public Stock(String name, ArrayList<Double> prices) {
		this.name=name;
		this.prices=(ArrayList<Double>)prices.clone();
	}
}