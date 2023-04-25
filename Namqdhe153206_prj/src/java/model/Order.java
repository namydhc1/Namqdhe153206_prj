
package model;

import java.sql.Date;
import java.util.ArrayList;


public class Order {
    private ArrayList<OrderDetail> details = new ArrayList<>();

    private Date orderdate;
    private float profit;
    
    public int getSize() {
        return details.size();
    }
    
    public float getTotalProfit() {
        float sum =0 ;
        for (OrderDetail detail : details) {
            sum += detail.getProfit();
        }
        return sum;
    
    }
    
    public int getTotal() {
        int sum =0 ;
        for (OrderDetail detail : details) {
            sum += detail.getTotal();
        }
        return sum;
    }
    public ArrayList<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<OrderDetail> details) {
        this.details = details;
    }

   

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }
    
}
