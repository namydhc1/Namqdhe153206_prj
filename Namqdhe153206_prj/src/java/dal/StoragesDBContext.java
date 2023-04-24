package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

public class StoragesDBContext extends DBContext {

    public ArrayList<Product> getStorages(String username) {
        ArrayList<Product> storages = new ArrayList();
        try {
            String sql = "SELECT p.pid, p.pname, p.dateofWarehousing,p.purchaseMoney,p.quantityWarehousing, p.inventory, c.cname,p.unitprice\n"
                    + "   FROM Account a INNER JOIN Product p\n"
                    + "   ON p.username = a.username \n"
                    + "	  INNER JOIN Category c \n"
                    + "	  ON c.cid = p.cid \n" 
                    + "	  WHERE p.username = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            if (username != null) {
                stm.setString(1, username);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product s = new Product();
                s.setId(rs.getInt("pid"));
                s.setPname(rs.getString("pname"));
                s.setDateofWarehousing(rs.getDate("dateofWarehousing"));
                s.setPurchaseMoney(rs.getInt("purchaseMoney"));
                s.setQuantityWarehousing(rs.getInt("quantityWarehousing"));
                s.setInventory(rs.getInt("inventory"));
                s.setCid(rs.getInt("cid"));
                s.setUnitprice(rs.getInt("unitprice"));
                storages.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StoragesDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return storages;
    }

    public int count() {
        ArrayList<Product> storages = new ArrayList<>();
        try {
            String sql = "SELECT COUNT(*) as Total  FROM Product ";
            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StoragesDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<Product> getStoragesbyPage(String username, int pageindex, int pagesize) {
        ArrayList<Product> storages = new ArrayList<>();
        try {
            String sql = "SELECT s.id, s.name, s.dateofWarehousing,s.purchaseMoney,s.quantityWarehousing,s.stocks,s.types,s.unitprice FROM \n"
                    + "(SELECT *, ROW_NUMBER() OVER (ORDER BY dateofWarehousing DESC) as row_index FROM Product) s INNER JOIN Account a\n"
                    + "ON s.username = a.username \n"
                    + "	WHERE row_index >= (?-1)*?+ 1\n"
                    + "	AND row_index <= ? * ?\n"
                    + "AND s.username = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageindex);
            stm.setInt(2, pagesize);
            stm.setInt(3, pageindex);
            stm.setInt(4, pagesize);
            stm.setString(5, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product s = new Product();
                s.setId(rs.getInt("pid"));
                s.setPname(rs.getString("pname"));
                s.setDateofWarehousing(rs.getDate("dateofWarehousing"));
                s.setPurchaseMoney(rs.getInt("purchaseMoney"));
                s.setQuantityWarehousing(rs.getInt("quantityWarehousing"));
                s.setInventory(rs.getInt("inventory"));
                s.setCid(rs.getInt("cid"));
                s.setUnitprice(rs.getInt("unitprice"));
                storages.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StoragesDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return storages;
    }

    public void insertItems(Product p, String username) {
        String sql = "INSERT INTO [dbo].[Product]\n"
                + "           ([pname]\n"
                + "           ,[dateofWarehousing]\n"
                + "           ,[purchaseMoney]\n"
                + "           ,[quantityWarehousing]\n"
                + "           ,[inventory]\n"
                + "           ,[types]\n"
                + "           ,[cid]\n"
                + "           ,[unitprice])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, p.getPname());
            stm.setDate(2, p.getDateofWarehousing());
            stm.setInt(3, p.getPurchaseMoney());
            stm.setInt(4, p.getQuantityWarehousing());
            stm.setInt(5, p.getInventory());
            stm.setInt(6, p.getCid());
            stm.setString(7, username);
            stm.setInt(8, p.getUnitprice());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StoragesDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StoragesDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StoragesDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public Product getStorage(int id, String username) {
        try {
            String sql = "SELECT * FROM Product\n"
                    + "WHERE username = ?\n"
                    + "AND pid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(2, id);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product s = new Product();
                s.setId(rs.getInt("pid"));
                s.setPname(rs.getString("pname"));
                s.setDateofWarehousing(rs.getDate("dateofWarehousing"));
                s.setPurchaseMoney(rs.getInt("purchaseMoney"));
                s.setQuantityWarehousing(rs.getInt("quantityWarehousing"));
                s.setInventory(rs.getInt("inventory"));
                s.setCid(rs.getInt("cid"));
                s.setUnitprice(rs.getInt("unitprice"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StoragesDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Product getOrderStorage(int id) {
        PreparedStatement stm = null;
        try {
            String sql = "SELECT * FROM Product\n"
                    + "WHERE pid = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Product s = new Product();
                s.setId(rs.getInt("pid"));
                s.setPname(rs.getString("pname"));
                s.setDateofWarehousing(rs.getDate("dateofWarehousing"));
                s.setPurchaseMoney(rs.getInt("purchaseMoney"));
                s.setQuantityWarehousing(rs.getInt("quantityWarehousing"));
                s.setInventory(rs.getInt("inventory"));
                s.setCid(rs.getInt("cid"));
                s.setUnitprice(rs.getInt("unitprice"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StoragesDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StoragesDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StoragesDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public void updateItems(Product s, String username) {
        String sql = "UPDATE [Product]\n"
                + "   SET [pname] = ?\n"
                + "      ,[dateofWarehousing] = ?\n"
                + "      ,[purchaseMoney] = ?\n"
                + "      ,[quantityWarehousing] = ?\n"
                + "      ,[inventory] = ?\n"
                + "      ,[cid] = ?\n"
                + "      ,[username] = ?\n"
                + "      ,[unitprice] = ?\n"
                + " WHERE [pid] = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);

            stm.setInt(9, s.getId());
            stm.setString(1, s.getPname());
            stm.setDate(2, s.getDateofWarehousing());
            stm.setInt(3, s.getPurchaseMoney());
            stm.setInt(4, s.getQuantityWarehousing());
            stm.setInt(5, s.getInventory());
            stm.setInt(6, s.getCid());
            stm.setString(7, username);
            stm.setInt(8, s.getUnitprice());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StoragesDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StoragesDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StoragesDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public void deleteStudent(Product s, String username) {
        String sql = "DELETE FROM [Product]\n"
                + "      WHERE pid = ? \n"
                + "	  AND username = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);

            stm.setInt(1, s.getId());
            stm.setString(2, username);

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StoragesDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StoragesDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StoragesDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public void updateStocks(int storageid, int i) {
        String sql = " UPDATE Product\n"
                + " SET inventory = ?\n"
                + " WHERE pid = ? ";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, i);
            stm.setInt(2, storageid);

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StoragesDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StoragesDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StoragesDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}

