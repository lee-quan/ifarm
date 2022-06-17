package ifarm;

public class Plant {

   
    private String _id, name, unitType;

    public Plant(String id, String name, String unitType) {
        this._id = id;
        this.name = name;
        this.unitType = unitType;
    }

    public String getUnitType() {
        return unitType;
    }
    
    public String getId() {
        return _id;
    }

    public String getName() {
        return name;
    }

    

}
