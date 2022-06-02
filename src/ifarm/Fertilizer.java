package ifarm;

import database.DBConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Fertilizer {
    private String _id, name, unitType;

    public Fertilizer(String name) {
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
