
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;


public class TypesDBContext extends DBContext {

    public ArrayList<Category> getTypes() {
        ArrayList<Category> types = new ArrayList();
        try {
            String sql = "SELECT  * from  Category";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Category s = new Category();
                s.setId(rs.getInt("cid"));
                s.setName(rs.getString("cname"));
                
                types.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypesDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return types;
    }
    
}

