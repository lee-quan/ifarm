package ifarm;

import database.DBConnection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

class Farmer implements Runnable {

    DBConnection db = new DBConnection();
    private String _id, name, email, password, phoneNumber;
//    private Connection conn = DBConnection.ConnectDB();
    private LinkedList<String> farms;
    private Farm[] farm;
    
    public Farmer(String _id) {
        farms = new LinkedList<>();
        this._id = _id;
    }
    
    public void setFarm(Farm[] farm){
        this.farm = farm;
    }

    public void setDetails(String name, String email, String password, String phoneNumber) throws SQLException {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        String insertSql = "INSERT INTO users (_id, name,email,password,phoneNumber,farm) VALUES ("
                + "\"" + _id + "\","
                + "\"" + name + "\","
                + "\"" + email + "\","
                + "\"" + password + "\","
                + "\"" + phoneNumber + "\","
                + "\"" + getFarm() + "\""
                + ")";
        db.insert(insertSql);
    }

    public String getId() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void insertFarm(String farmid) {
        farms.add(farmid);
    }

    public String getFarm() {
        String str = "";

        for (int i = 0; i < farms.size(); i++) {
            if (i == farms.size() - 1) {
                str += farms.get(i);
            } else {
                str += farms.get(i) + ",";
            }
        }

        return str;
    }

    @Override
    // Generate Activities and write into log files
    public void run() {
        String[] ActivityName = {"Sowing", "Fertilizers", "Pesticides", "Harvest", "Sales"};
        String[] UnitType = {"mass", "pack", "volume"};
        Random r = new Random();        
        int activityNum=1;
        try{
            PrintWriter pw = pw = new PrintWriter(new FileOutputStream("log.txt", true));
            
        
        for (int i = 0; i < farm.length; i++) {
            writeLogFile(pw, "Farmer "+this._id + " on Farm "+farm[i].getId()+"\n");            
            while (true){
                // check at least 1000 activities 
                
                if (activityNum>=1000){                    
                    if (r.nextInt(2) == 0)
                        break;                
                }
                // random for activity id, date, action, type, unit, quantity, field, row, farmid, userid 
                String id = activityNum+"";
                String date = r.nextInt(2020)+2000+"-"+r.nextInt(12)+1+"-"+r.nextInt(30)+1;
                String action = ActivityName[r.nextInt(ActivityName.length)];
                String farmid = farm[i].getId();
                String userid = this._id;    
                String type;
                if (action.equals("Sowing") || action.equals("Harvest") || action.equals("Sales")){
                    //store plants name
                    String plantid = farm[i].getPlantlist().get(r.nextInt(farm[i].getPlantlist().size()));
                    type = plantid;
                    
                }
                else if (action.equals("Fertilizers")){
                    //store fertilizers name
                    String fertilizerid = farm[i].getFertiliserlist().get(r.nextInt(farm[i].getFertiliserlist().size()));
                    type = fertilizerid;
                }
                else{
                    //store pesticide name
                    String pesticideid = farm[i].getPesticidelist().get(r.nextInt(farm[i].getPesticidelist().size()));
                    type = pesticideid;
                }
                String unit = UnitType[r.nextInt(UnitType.length)];
                Double quantity = r.nextDouble(5);
                quantity = Math.rint(quantity*100)/100;
                int field = r.nextInt(3)+1;
                int row = r.nextInt(3)+1;
                
                //Create new Activity
                Activity act = new Activity(id,date,action,type,unit,quantity,field,row,farmid,userid);
                
                // write the log file
                // Exp log file: Sowing Broccol  Field 1 Row 1 1 kg 2022-03-03
                writeLogFile(pw, act.toLogFile()+"\n");                
                activityNum++;
                
            }
            
        }
        pw.close(); 
        }catch (FileNotFoundException ex){
            System.out.println("File is not found");
        }
    }
    
    public synchronized void writeLogFile(PrintWriter pw, String str){
        pw.write(str);
        
    }

}
