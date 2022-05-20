package ifarm;

import database.DBConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Fertilizer {
    private String _id, name, unitType;
    DBConnection db = new DBConnection();
    private ResultSet rs;
    private String[] fertilizerArr = new String[100];
    
    public Fertilizer(){
        try {
            this.rs = db.retrieve("SELECT * FROM fertiliser");
            int counter = 0;
            while(rs.next()){
                fertilizerArr[counter] = rs.getString(2);
                counter++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Fertilizer.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(db.ConnectDB() != null){
                try {
                    db.ConnectDB().close();
                } catch (SQLException ex) {
                    Logger.getLogger(Fertilizer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public Fertilizer(String name) {
        this.name = name;
    }

    public String[] getFertilizerArr() {
        return fertilizerArr;
    }
    
    
    public String getId() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getUnitType() {
        return unitType;
    }
   
}
