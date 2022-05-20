package ifarm;

import database.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pesticide {

    private String _id, name, unitType;
    DBConnection db = new DBConnection();
    private ResultSet rs;
    private String[] pesticideArr = new String[100];
    
    public Pesticide(){
        try {
            this.rs = db.retrieve("SELECT * FROM pesticide");
            int counter = 0;
            while(rs.next()){
                pesticideArr[counter] = rs.getString(2);
                counter++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pesticide.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(db.ConnectDB() != null){
                try {
                    db.ConnectDB().close();
                } catch (SQLException ex) {
                    Logger.getLogger(Pesticide.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public Pesticide(String name) {
        this.name = name;
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

    public String[] getPesticideArr() {
        return pesticideArr;
    }
}
