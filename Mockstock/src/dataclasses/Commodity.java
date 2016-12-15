package dataclasses;

/**
 *
 * @author Rajat
 */
public class Commodity {
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

    public Commodity(Stock stock) {
        this.stock = stock;
        this.quantity = 0;
    }
    
    
}
