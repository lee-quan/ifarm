package ifarm;

import database.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Plant {

    private String _id, name, unitType;
    DBConnection db = new DBConnection();
    private ResultSet rs;
    private String[] plantArr = new String[100];

    public Plant() {
        try {
            this.rs = db.retrieve("SELECT * FROM plant");
            int counter = 0;
            while (rs.next()) {
                plantArr[counter] = rs.getString(2);
                counter++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Plant.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (db.ConnectDB() != null) {
                try {
                    db.ConnectDB().close();
                } catch (SQLException ex) {
                    Logger.getLogger(Plant.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public String[] getPlantArr() {
        return plantArr;
    }

    public Plant(String name) {

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

}
