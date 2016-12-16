package dataclasses;

import exceptions.IllegalSellingException;
import java.io.*;

/**
 *
 * @author Rajat
 */
public class Commodity implements Serializable{
    private Stock stock;
    private int quantity;

    public Stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public void addQuantity(int i) {
        quantity+=i;
    }
    
    public void removeQuantity(int i) throws IllegalSellingException {
        if(quantity>=i) {
            quantity-=i;
        } else {
            throw new IllegalSellingException("You can't sell more than what you've bought");
        }
    }

    public Commodity(Stock stock) {
        this.stock = stock;
        this.quantity = 0;
    }
    
    
}
