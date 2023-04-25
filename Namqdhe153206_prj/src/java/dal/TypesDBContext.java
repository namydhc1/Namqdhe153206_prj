
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ItemTypes;
import model.Storage;


public class TypesDBContext extends DBContext {

    public ArrayList<ItemTypes> getTypes() {
        ArrayList<ItemTypes> types = new ArrayList();
        try {
            String sql = "SELECT  * from  Types_HE153206";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ItemTypes s = new ItemTypes();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("types"));
                
                types.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypesDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return types;
    }
    
}
