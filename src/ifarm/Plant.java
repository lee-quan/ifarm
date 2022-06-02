package ifarm;

import database.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Plant {

    private String _id, name, unitType;

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
