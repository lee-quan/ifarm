package ifarm;

import database.DBConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Fertilizer {
    private String _id, name, unitType;

    public Fertilizer(String id, String name, String unitType) {
        this._id = id;
        this.name = name;
        this.unitType = unitType;
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
