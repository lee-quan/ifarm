package ifarm;

public class Activity {
    private String _id;
    private String date;
    private String action;
    private String type;
    private String unit;
    private Integer quantity;
    private Integer field;
    private Integer row;
    private Integer farmId;
    private Integer userId;

    
    public Activity(String _id, String date, String action, String type, String unit, Integer quantity, Integer field, Integer row, Integer farmId, Integer userId) {
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

    public String get_id() {
        return _id;
    }

    public String getDate() {
        return date;
    }
    
    public String getAction() {
        return action;
    }

    public String getType() {
        return type;
    }

    public String getUnit() {
        return unit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getField() {
        return field;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getFarmId() {
        return farmId;
    }

    public Integer getUserId() {
        return userId;
    }
}