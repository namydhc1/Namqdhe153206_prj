package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Storage;

public class StorageDBContext extends DBContext {

    public ArrayList<Storage> getStorages(String username) {
        ArrayList<Storage> storages = new ArrayList();
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
                Storage s = new Storage();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setDateofWarehousing(rs.getDate("dateofWarehousing"));
                s.setPurchaseMoney(rs.getInt("purchaseMoney"));
                s.setQuantityWarehousing(rs.getInt("quantityWarehousing"));
                s.setStocks(rs.getInt("stocks"));
                s.setTypes(rs.getString("types"));
                s.setUnitprice(rs.getInt("unitprice"));
                storages.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return storages;
    }

    public int count() {
        ArrayList<Storage> storages = new ArrayList<>();
        try {
            String sql = "SELECT COUNT(*) as Total  FROM Storage_HE153206 ";
            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<Storage> getStoragesbyPage(String username, int pageindex, int pagesize) {
        ArrayList<Storage> storages = new ArrayList<>();
        try {
            String sql = "SELECT s.id, s.name, s.dateofWarehousing,s.purchaseMoney,s.quantityWarehousing,s.stocks,s.types,s.unitprice FROM \n"
                    + "(SELECT *, ROW_NUMBER() OVER (ORDER BY dateofWarehousing DESC) as row_index FROM Storage_HE153206) s INNER JOIN Account_HE153206 a\n"
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
                Storage s = new Storage();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setDateofWarehousing(rs.getDate("dateofWarehousing"));
                s.setPurchaseMoney(rs.getInt("purchaseMoney"));
                s.setQuantityWarehousing(rs.getInt("quantityWarehousing"));
                s.setStocks(rs.getInt("stocks"));
                s.setTypes(rs.getString("types"));
                s.setUnitprice(rs.getInt("unitprice"));
                storages.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return storages;
    }

    public void insertItems(Storage s, String username) {
        String sql = "INSERT INTO [dbo].[Storage_HE153206]\n"
                + "           ([name]\n"
                + "           ,[dateofWarehousing]\n"
                + "           ,[purchaseMoney]\n"
                + "           ,[quantityWarehousing]\n"
                + "           ,[stocks]\n"
                + "           ,[types]\n"
                + "           ,[username]\n"
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
            stm.setString(1, s.getName());
            stm.setDate(2, s.getDateofWarehousing());
            stm.setInt(3, s.getPurchaseMoney());
            stm.setInt(4, s.getQuantityWarehousing());
            stm.setInt(5, s.getStocks());
            stm.setString(6, s.getTypes());
            stm.setString(7, username);
            stm.setInt(8, s.getUnitprice());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public Storage getStorage(int id, String username) {
        try {
            String sql = "SELECT * FROM Storage_HE153206\n"
                    + "WHERE username = ?\n"
                    + "AND id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(2, id);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Storage s = new Storage();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setDateofWarehousing(rs.getDate("dateofWarehousing"));
                s.setPurchaseMoney(rs.getInt("purchaseMoney"));
                s.setQuantityWarehousing(rs.getInt("quantityWarehousing"));
                s.setStocks(rs.getInt("stocks"));
                s.setTypes(rs.getString("types"));
                s.setUnitprice(rs.getInt("unitprice"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Storage getOrderStorage(int id) {
        PreparedStatement stm = null;
        try {
            String sql = "SELECT * FROM Storage_HE153206\n"
                    + "WHERE id = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Storage s = new Storage();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setDateofWarehousing(rs.getDate("dateofWarehousing"));
                s.setPurchaseMoney(rs.getInt("purchaseMoney"));
                s.setQuantityWarehousing(rs.getInt("quantityWarehousing"));
                s.setStocks(rs.getInt("stocks"));
                s.setTypes(rs.getString("types"));
                s.setUnitprice(rs.getInt("unitprice"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public void updateItems(Storage s, String username) {
        String sql = "UPDATE [Storage_HE153206]\n"
                + "   SET [name] = ?\n"
                + "      ,[dateofWarehousing] = ?\n"
                + "      ,[purchaseMoney] = ?\n"
                + "      ,[quantityWarehousing] = ?\n"
                + "      ,[stocks] = ?\n"
                + "      ,[types] = ?\n"
                + "      ,[username] = ?\n"
                + "      ,[unitprice] = ?\n"
                + " WHERE [id] = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);

            stm.setInt(9, s.getId());
            stm.setString(1, s.getName());
            stm.setDate(2, s.getDateofWarehousing());
            stm.setInt(3, s.getPurchaseMoney());
            stm.setInt(4, s.getQuantityWarehousing());
            stm.setInt(5, s.getStocks());
            stm.setString(6, s.getTypes());
            stm.setString(7, username);
            stm.setInt(8, s.getUnitprice());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public void deleteStudent(Storage s, String username) {
        String sql = "DELETE FROM [Storage_HE153206]\n"
                + "      WHERE id = ? \n"
                + "	  AND username = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);

            stm.setInt(1, s.getId());
            stm.setString(2, username);

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public void updateStocks(int storageid, int i) {
        String sql = " UPDATE Storage_HE153206\n"
                + " SET stocks = ?\n"
                + " WHERE id = ? ";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, i);
            stm.setInt(2, storageid);

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
