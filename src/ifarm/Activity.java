package ifarm;

import database.DBConnection;
import java.sql.SQLException;

public class Activity {

    DBConnection db = new DBConnection();
    private String date, type, farmId, userId;
    private Integer field, row, action, unit, _id;        
    private Double quantity;

    public Activity(Integer _id, String date, Integer action, String type,
            Integer unit, Double quantity, Integer field, Integer row, String farmId,
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

        String insertSql = "INSERT INTO activity (_id,date,action,type,unit,quantity,field,_row,farmId,userId) VALUES ("
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
        
//        db.insert(insertSql);        
//        System.out.println(_id);
//        db.insertFast(_id,date,action,type,unit,quantity,field,row,farmId,userId);        
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

    public Double getQuantity() {
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
    
}
