package ifarm;

import database.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Plant {

    private String _id, name, unitType;

    public Plant(String id, String name, String unitType) {
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
