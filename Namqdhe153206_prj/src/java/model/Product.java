
package model;

import java.sql.Date;


public class Product {

    private int id;
    private String pname;
    private Date dateofWarehousing;
    private int quantityWarehousing;
    private int purchaseMoney;
    private int inventory;
    private int cid;
    private int unitprice;   
    private int quantitysell;

    public Product() {
    }

    public Product(int id, String pname, Date dateofWarehousing, int quantityWarehousing, int purchaseMoney, int inventory, int cid, int unitprice, int quantitysell) {
        this.id = id;
        this.pname = pname;
        this.dateofWarehousing = dateofWarehousing;
        this.quantityWarehousing = quantityWarehousing;
        this.purchaseMoney = purchaseMoney;
        this.inventory = inventory;
        this.cid = cid;
        this.unitprice = unitprice;
        this.quantitysell = quantitysell;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Date getDateofWarehousing() {
        return dateofWarehousing;
    }

    public void setDateofWarehousing(Date dateofWarehousing) {
        this.dateofWarehousing = dateofWarehousing;
    }

    public int getQuantityWarehousing() {
        return quantityWarehousing;
    }

    public void setQuantityWarehousing(int quantityWarehousing) {
        this.quantityWarehousing = quantityWarehousing;
    }

    public int getPurchaseMoney() {
        return purchaseMoney;
    }

    public void setPurchaseMoney(int purchaseMoney) {
        this.purchaseMoney = purchaseMoney;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(int unitprice) {
        this.unitprice = unitprice;
    }

    public int getQuantitysell() {
        return quantitysell;
    }

    public void setQuantitysell(int quantitysell) {
        this.quantitysell = quantitysell;
    }

    
    
}
