package ifarm;

import java.sql.SQLException;

public class Activity {
    
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

}
