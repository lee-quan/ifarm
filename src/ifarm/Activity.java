package ifarm;

import database.DBConnection;
import java.sql.SQLException;

public class Activity {

    DBConnection db = new DBConnection();
    private String date, type, farmId, userId, quantity;
    private Integer field, row, action, unit, _id;

    public Activity(Integer _id, String date, Integer action, String type,
            Integer unit, String quantity, Integer field, Integer row, String farmId,
            String userId) throws SQLException {
        this._id = _id;
        this.date = date;
        this.action = action;
        this.type = type;
        this.unit = unit;
        this.quantity = quantity;
        this.field = field;
        this.row = row;
        this.farmId = farmId;
        this.userId = userId;
//        `_id` VARCHAR(45) NOT NULL,
//  `date` DATE NOT NULL,
//  `action` INT NOT NULL,
//  `type` VARCHAR(255) NOT NULL,
//  `unit` INT NOT NULL,
//  `quantity` VARCHAR(255) NOT NULL,
//`field` INT NOT NULL,
//`row` INT NOT NULL,
//`farmId` VARCHAR(255) NOT NULL,
//`userId` VARCHAR(255) NOT NULL,
        String insertSql = "INSERT INTO activity (_id,date,action,type,unit,quantity,field,row,farmId,userId) VALUES ("
                + "\"" + _id + "\","
                + "\"" + date + "\","
                + action + ","
                + "\"" + type + "\","
                + unit + ","
                + "\"" + quantity + "\","
                + field + ","
                + row + ","
                + "\"" + farmId + "\","
                + "\"" + userId + "\""
                + ")";
        db.insert(insertSql);
    }

    public Integer get_id() {
        return _id;
    }

    public String getDate() {
        return date;
    }

    public Integer getAction() {
        return action;
    }

    public String getType() {
        return type;
    }

    public Integer getUnit() {
        return unit;
    }

    public String getQuantity() {
        return quantity;
    }

    public Integer getField() {
        return field;
    }

    public Integer getRow() {
        return row;
    }

    public String getFarmId() {
        return farmId;
    }

    public String getUserId() {
        return userId;
    }

//    public String toLogFile() {
//
//        // Exp log file: Sowing Broccoli Field 1 Row 1 1 kg 2022-03-03
//        String str = action + " " + type + " Field " + field + " Row " + row + " " + quantity + " " + unit + " " + date;
//        return str;
//    }
}
