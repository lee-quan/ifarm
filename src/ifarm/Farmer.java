package ifarm;

import database.DBConnection;
import java.io.PrintWriter;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

class Farmer implements Runnable {

    DBConnection db = new DBConnection();
    private String _id, name, email, password, phoneNumber;
    private Plant[] plantArr;
    private Fertilizer[] fertilizerArr;
    private Pesticide[] pesticideArr;
    private int activityNum;
    private LinkedList<String> farms;
    private Farm[] farm;
    private PrintWriter pw;
    private HashMap<String, Integer> activities;
    private Counter count;

    public Farmer(String _id) {
        activities = new HashMap<>();
        farms = new LinkedList<>();
        this._id = _id;
    }

    public void setFarm(Farm[] farm) {
        this.farm = farm;
    }

    public void setPlantArr(Plant[] plantArr) {
        this.plantArr = plantArr;
    }

    public void setFertilizerArr(Fertilizer[] fertilizerArr) {
        this.fertilizerArr = fertilizerArr;
    }

    public void setPesticideArr(Pesticide[] pesticideArr) {
        this.pesticideArr = pesticideArr;
    }

    public void setCounter(Counter c) {
        count = c;
    }

    public void setPrintWriter(PrintWriter pw) {
        this.pw = pw;
    }

    public void setDetails(String name, String email, String password, String phoneNumber) throws SQLException {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        String updateSql = "UPDATE users SET farm=\"" + getFarm() + "\" WHERE _id=\"" + _id + "\"";
        db.update(updateSql);
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

    public void getActivityList() {
        System.out.println("Farmers: " + this._id);
        activities.entrySet().forEach(entry -> {
            System.out.println("Farm: " + entry.getKey() + " with activities: " + entry.getValue());
        });
        System.out.println();
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
    // Generate Activities and write into log files (Concurrent)
    public void run() {
        String[] ActivityName = {"Sowing", "Fertilizers", "Pesticides", "Harvest", "Sales"};
//        String[] UnitType = {"mass", "pack", "volume"};
        String[] Unit = {"kg", "g", "pack (1000g)", "pack (500g)", "l", "ml"};
        Random r = new Random();
        for (int i = 0; i < farms.size(); i++) {
            activityNum = 1;
            while (true) {
                // check at least 1000 activities

                if (activityNum > 5) {
                    if (r.nextInt(2) == 0) {
                        break;
                    }
                }
                // random for activity id, date, action, type, unit, quantity, field, row, farmid, userid
//                String id = "F" + this._id + "F" + farms.get(i) + " " + activityNum;
                String date = r.nextInt(21) + 2000 + "-" + (r.nextInt(12) + 1) + "-" + (r.nextInt(30) + 1);
                int action = r.nextInt(ActivityName.length);
                int farmid = Integer.parseInt(farms.get(i));
                String userid = this._id;
                String type, typeId, quantity="";
                Integer unit;
                switch (action) {
                    case 1, 3, 5 -> {
                        unit = r.nextInt(2); // kg,g
                        if(unit==0) quantity=(r.nextInt(9)+1)+"."+(r.nextInt(100));
                        else if(unit==1) quantity=(r.nextInt(9+1)*100)+(r.nextInt(100))+"";
                        
                        //store plants name
                        typeId = farm[farmid - 1].getPlantlist().get(r.nextInt(farm[farmid - 1].getPlantlist().size()));
                        type = plantArr[Integer.parseInt(typeId) - 1].getName();
                    }
                    case 0 -> { //Fertilizer
                        unit = r.nextInt(2) + 2;// pack 1000,500
                        quantity=(r.nextInt(9)+1)+"";
                        
                        //store fertilizers name
                        typeId = farm[farmid - 1].getFertiliserlist().get(r.nextInt(farm[farmid - 1].getFertiliserlist().size()));
                        type = fertilizerArr[Integer.parseInt(typeId) - 1].getName();
                    }
                    default -> {
                        unit = r.nextInt(2) + 4;
                        if(unit==4) quantity=(r.nextInt(9)+1)+"."+(r.nextInt(100));
                        else if(unit==5) quantity=(r.nextInt(9+1)*100)+(r.nextInt(100))+"";
                        
                        typeId = farm[farmid - 1].getPesticidelist().get(r.nextInt(farm[farmid - 1].getPesticidelist().size()));
                        type = pesticideArr[Integer.parseInt(typeId) - 1].getName();
                    }
                }
//                String unit = UnitType[r.nextInt(UnitType.length)];
//                Double quantity = r.nextDouble(5);
//                quantity = Math.rint(quantity * 100) / 100;
                int field = r.nextInt(3) + 1;
                int row = r.nextInt(3) + 1;
                Integer id = count.getAndIncrease();
                try {
                    //Create new Activity
                    Activity act = new Activity(id, date, action + 1, typeId+1, unit+1, quantity, field, row, farmid + "", userid);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Farmer.class.getName()).log(Level.SEVERE, null, ex);
                }
        // write the log file
                    // Exp log file: Sowing Broccol  Field 1 Row 1 1 kg 2022-03-03
                writeLogFile(pw, id + "- Farmer " + this._id + " on Farm " + farms.get(i) + " " +
                        ActivityName[action]+" "+type+" Field "+field+" Row " + row + " ("+quantity+""+Unit[unit] +") "+date+"\n");

                // increment activity number
                activityNum++;
            }
            activities.put(farms.get(i), activityNum - 1);

        }

    }

    public synchronized void writeLogFile(PrintWriter pw, String str) {
        pw.write(str);

    }
}
