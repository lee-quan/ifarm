package ifarm;

public class Pesticide {

    
    private String _id,name,unitType;

    public Pesticide(String _id, String name, String unitType) {
        this._id = _id;
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
