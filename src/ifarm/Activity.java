package ifarm;

public class Activity {
    private String _id;
    private String date;
    private String action;
    private String type;
    private String unit;
    private Double quantity;
    private Integer field;
    private Integer row;
    private String farmId;
    private String userId;

    
    public Activity(String _id, String date, String action, String type, String unit, Double quantity, Integer field, Integer row, String farmId, String userId) {
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
    
    public String toLogFile(){
        // Exp log file: Sowing Broccoli Field 1 Row 1 1 kg 2022-03-03
        String str = action + " " + type + " Field " + field + " Row " + row + " " + quantity + " " + unit + " " + date;
        return str;
    }
}