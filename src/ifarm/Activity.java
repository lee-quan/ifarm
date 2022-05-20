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
    private String[] name = new String[100];

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
//            String[] ActivityName = {"Sowing", "Fertilizers", "Pesticides", "Harvest", "Sales"};


    public String toLogFileBackUp() {

        // Exp log file: Sowing Broccoli Field 1 Row 1 1 kg 2022-03-03
        String str = action + " " + name[Integer.parseInt(type)-1] + " Field " + field + " Row " + row + " " + quantity + " " + unit + " " + date;
        return str;
    }
    
    public String toLogFile(String[] plantArr, String[] fertilizerArr, String[] pesticideArr) {
        if(action=="Sowing" || action=="Harvest" || action == "Sales") name=plantArr;
        else if(action=="Fertilizers") name = fertilizerArr;
        else name = pesticideArr;
        
        // Exp log file: Sowing Broccoli Field 1 Row 1 1 kg 2022-03-03
        String str = action + " " + name[Integer.parseInt(type)-1] + " Field " + field + " Row " + row + " " + quantity + " " + unit + " " + date;
        return str;
    }
}
