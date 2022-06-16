/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ifarm;

import java.util.List;

/**
 *
 * @author hng
 */
public class Log {
    
    private String actionName,typeName,date,unitType;
    private int typeID,field,row,unit,_id;
    private double quantity;

    public Log(int _id,String actionName, String typeName, String date, String unitType, int typeID, int field, int row, double quantity,int unit) {
        this._id = _id;
        this.actionName = actionName;
        this.typeName = typeName;
        this.date = date;
        this.unitType = unitType;
        this.typeID = typeID;
        this.field = field;
        this.row = row;
        this.quantity = quantity;
        this.unit = unit;
    }
    
    public Log(int _id,String actionName, String typeName, int field, int row, double quantity, int unit, String unitType){
        this._id = _id;
        this.actionName = actionName;
        this.typeName = typeName;                     
        this.field = field;
        this.row = row;
        this.quantity = quantity; 
        this.unit = unit;
        this.unitType = unitType;
        setUnit(unit);
    }

    public String getActionName() {
        return actionName;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getDate() {
        return date;
    }

    public String getUnitType() {
        return unitType;
    }

    public int getTypeID() {
        return typeID;
    }

    public int getField() {
        return field;
    }

    public int getRow() {
        return row;
    }

    public double getQuantity() {
        return quantity;
    }

    public int getId() {
        return _id;
    }    

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
        setUnitType(unit);
    }
    
    public void setUnitType(int unit){
        switch(unit){
            case 1 -> this.unitType ="kg";            
            case 3 -> this.unitType ="pack (1000g)";            
            case 5 -> this.unitType ="l";            
        }
    }        
    
    public void toLog(){
        //output format: Sowing Broccoli Field 1 Row 1 2.5 kg
        System.out.printf("%-1d %-12s %-30s Field %d Row %d %-7.2f %-5s%n",_id,actionName,typeName,field,row,quantity,unitType);        
    }
    
    
    
    
}
