package dataclasses;

import java.util.*;
import java.io.*;

public class Stock implements Serializable{
	private String name;
	private ArrayList<Long> prices;

	public String getName() {
		return name;
	}

	public long getPrice(int roundNo) {
		return prices.get(roundNo-1);
	}

	public Stock(String name, ArrayList<Long> prices) {
		this.name=name;
		this.prices=(ArrayList<Long>)prices.clone();
	}

        @Override
        public String toString() {
            return name;
        }
        
        
}