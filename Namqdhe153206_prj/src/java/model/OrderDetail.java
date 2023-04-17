
package model;

public class OrderDetail {
    private Order order;
    private Product storage;
    private int quantity;
    private int unitprice;
    
    public float getProfit() {
        float purchasemoney = (float)storage.getPurchaseMoney() / storage.getQuantityWarehousing();
        float profit = quantity*purchasemoney;
        return getTotal() - profit;
    }
    
    
    public int getTotal() {
        return quantity*unitprice;
    }
    
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getStorage() {
        return storage;
    }

    public void setStorage(Product storage) {
        this.storage = storage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(int unitprice) {
        this.unitprice = unitprice;
    }
    
}
