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
import model.Storage;


public class OrderDBContext extends DBContext {

    public void insert(Order o) {
        String sql_insert_order = "INSERT INTO [Order_HE153206]\n"
                + "           ([orderdate]\n"
                + "           ,[profit])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?)";
        String sql_insert_details = "INSERT INTO [OrderDetail_HE153206]\n"
                + "           ([sid]\n"
                + "           ,[orderdate]\n"
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
            stm_insert_order.setDate(1, o.getOrderdate());
            stm_insert_order.setFloat(2, o.getProfit());
            stm_insert_order.executeUpdate();

            for (OrderDetail od : o.getDetails()) {
                PreparedStatement stm_insert_details = connection.prepareStatement(sql_insert_details);
                stm_insert_details.setInt(1, od.getStorage().getId());
                stm_insert_details.setDate(2, o.getOrderdate());
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
                    + "FROM OrderDetail_HE153206 od INNER JOIN Storage_HE153206 s \n"
                    + "ON od.sid = s.id INNER JOIN [Order_HE153206] o\n"
                    + "ON od.orderdate = o.orderdate\n"
                    + "Where od.orderdate = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, date);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                Storage s = new Storage();
                Order order = new Order();

                s.setId(rs.getInt("sid"));
                s.setName(rs.getString("name"));
                s.setDateofWarehousing(rs.getDate("dateofWarehousing"));
                s.setQuantityWarehousing(rs.getInt("quantityWarehousing"));
                s.setQuantitysell(rs.getInt("quantity"));
                s.setPurchaseMoney(rs.getInt("purchaseMoney"));
                s.setStocks(rs.getInt("stocks"));
                s.setTypes(rs.getString("types"));
                s.setUnitprice(rs.getInt("unitprice"));

                order.setOrderdate(rs.getDate("orderdate"));
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
                Storage s = new Storage();
                Order order = new Order();

                s.setId(rs.getInt("sid"));
                s.setName(rs.getString("name"));
                s.setDateofWarehousing(rs.getDate("dateofWarehousing"));
                s.setQuantityWarehousing(rs.getInt("quantityWarehousing"));
                s.setQuantitysell(rs.getInt("quantity"));
                s.setPurchaseMoney(rs.getInt("purchaseMoney"));
                s.setStocks(rs.getInt("stocks"));
                s.setTypes(rs.getString("types"));
                s.setUnitprice(rs.getInt("unitprice"));

                order.setOrderdate(rs.getDate("orderdate"));
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
