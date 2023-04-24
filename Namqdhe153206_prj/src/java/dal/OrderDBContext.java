/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;
import model.OrderDetail;
import model.Product;


public class OrderDBContext extends DBContext {

    public void insert(Order o) {
        String sql_insert_order = "INSERT INTO [Order]\n"
                + "           ([order_date]\n"
                + "           ,[profit])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?)";
        String sql_insert_details = "INSERT INTO [Order_detail]\n"
                + "           ([pid]\n"
                + "           ,[order_date]\n"
                + "           ,[quantity]\n"
                + "           ,[unitprice])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            connection.setAutoCommit(false);
            PreparedStatement stm_insert_order = connection.prepareStatement(sql_insert_order);
            stm_insert_order.setDate(1, o.getOrder_date());
            stm_insert_order.setFloat(2, o.getProfit());
            stm_insert_order.executeUpdate();

            for (OrderDetail od : o.getDetails()) {
                PreparedStatement stm_insert_details = connection.prepareStatement(sql_insert_details);
                stm_insert_details.setInt(1, od.getStorage().getId());
                stm_insert_details.setDate(2, o.getOrder_date());
                stm_insert_details.setInt(3, od.getQuantity());
                stm_insert_details.setInt(4, od.getStorage().getUnitprice());
                stm_insert_details.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<OrderDetail> getOrderDetails(String date) {
        ArrayList<OrderDetail> orderdetails = new ArrayList<>();
        try {
            String sql = "SELECT *\n"
                    + "FROM Order_detail od INNER JOIN Product p \n"
                    + "ON od.pid = p.pid INNER JOIN [Order] o\n"
                    + "ON od.oid = o.oid\n"
                    + "Where o.order_date = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, date);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                Product s = new Product();
                Order order = new Order();

                s.setId(rs.getInt("pid"));
                s.setPname(rs.getString("pname"));
                s.setDateofWarehousing(rs.getDate("dateofWarehousing"));
                s.setQuantityWarehousing(rs.getInt("quantityWarehousing"));
                s.setQuantitysell(rs.getInt("quantity"));
                s.setPurchaseMoney(rs.getInt("purchaseMoney"));
                s.setInventory(rs.getInt("inventory"));
                s.setCid(rs.getInt("cid"));
                s.setUnitprice(rs.getInt("unitprice"));

                order.setOrder_date(rs.getDate("orderdate"));
                order.setProfit(rs.getFloat("profit"));

                od.setStorage(s);
                od.setOrder(order);
                od.setQuantity(rs.getInt("quantity"));
                od.setUnitprice(rs.getInt("unitprice"));

                orderdetails.add(od);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderdetails;
    }

    public ArrayList<OrderDetail> getViewOrderDetails(String raw_date) {
        ArrayList<OrderDetail> orderdetails = new ArrayList<>();
        try {
            String sql = "  SELECT DISTINCT  od.sid, od.orderdate, od.quantity, s.unitprice, s.id, s.[name], s.[dateofWarehousing], s.purchaseMoney, s.quantityWarehousing, \n"
                    + "  s.stocks, s.[types], \n"
                    + "  s.username, s.unitprice,\n"
                    + " od.quantity*(s.unitprice - (CAST (s.purchaseMoney AS float )/CAST (s.quantityWarehousing  AS float ))) AS profit\n"
                    + "  FROM OrderDetail_HE153206 od INNER JOIN Storage_HE153206 s \n"
                    + " ON od.sid = s.id INNER JOIN [Order_HE153206] o\n"
                    + "ON od.orderdate = o.orderdate\n"
                    + "Where od.orderdate = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, raw_date);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                Product s = new Product();
                Order order = new Order();

                s.setId(rs.getInt("pid"));
                s.setPname(rs.getString("pname"));
                s.setDateofWarehousing(rs.getDate("dateofWarehousing"));
                s.setQuantityWarehousing(rs.getInt("quantityWarehousing"));
                s.setQuantitysell(rs.getInt("quantity"));
                s.setPurchaseMoney(rs.getInt("purchaseMoney"));
                s.setInventory(rs.getInt("inventory"));
                s.setCid(rs.getInt("cid"));
                s.setUnitprice(rs.getInt("unitprice"));

                order.setOrder_date(rs.getDate("orderdate"));
                order.setProfit(rs.getFloat("profit"));

                od.setStorage(s);
                od.setOrder(order);
                od.setQuantity(rs.getInt("quantity"));
                od.setUnitprice(rs.getInt("unitprice"));

                orderdetails.add(od);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderdetails;
    }
}
