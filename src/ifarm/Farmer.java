package ifarm;

import database.DBConnection;
import java.io.PrintWriter;
import java.sql.*;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
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
    private boolean isDisaster;

    public Farmer(String _id) {
        activities = new HashMap<>();
        farms = new LinkedList<>();
        this.isDisaster = false;
        this._id = _id;
    }
    
    public boolean getIsDisaster(){
        return this.isDisaster;
    }
    public void setIsDisaster(boolean bool) {
        this.isDisaster = bool;
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

    public String getActivityList() {
        StringBuilder b = new StringBuilder();
        b.append("Farmers: " + this._id + "\n");
        activities.entrySet().forEach(new Consumer<Map.Entry<String, Integer>>() {
            @Override
            public void accept(Map.Entry<String, Integer> entry) {
                b.append("Farm: " + entry.getKey() + " with activities: " + entry.getValue() + "\n");
            }
        });
        return b.toString();
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

    public String generateDate() {
        long startSeconds = Instant.now().minus(Duration.ofDays(90)).getEpochSecond();
        long endSeconds = Instant.now().getEpochSecond();
        long random = ThreadLocalRandom.current().nextLong(startSeconds, endSeconds);

        return Instant.ofEpochSecond(random).atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    // Generate Activities and write into log files (Concurrent)
    public void run() {
        Random r = new Random();

        if (isDisaster) {
            if (r.nextInt(3) == 0) {
                try {
                    db.update("DELETE from activity WHERE userId=\"" + _id + "\"");
                    throw new RuntimeException(" Opps! Error occured!! Farmer ID =" + _id);
                } catch (SQLException ex) {
                    Logger.getLogger(Farmer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        String[] ActivityName = {"Sowing", "Harvest", "Sales", "Fertilizers", "Pesticides"};
        String[] Unit = {"kg", "g", "pack(1000g)", "pack(500g)", "l", "ml"};
        for (int i = 0; i < farms.size(); i++) {
            activityNum = 1;
            while (true) {
                // check at least 1000 activities

                if (activityNum > 1000) {
                    if (r.nextInt(2) == 0) {
                        break;
                    }
                }
                // random for activity id, date, action, type, unit, quantity, field, row, farmid, userid

                String date = generateDate();
                int action = r.nextInt(ActivityName.length);
                int farmid = Integer.parseInt(farms.get(i));
                String userid = this._id;
                String type, typeId, quantity = "";
                Integer unit;
                switch (action) {

                    case 0, 1, 2 -> {
                        unit = r.nextInt(2); // kg,g
                        if (unit == 0) {
                            quantity = (r.nextInt(9) + 1) + "." + (r.nextInt(100));
                        } else if (unit == 1) {
                            quantity = (r.nextInt(9 + 1) * 100) + (r.nextInt(100)) + "";
                        }

                        //store plants name
                        typeId = farm[farmid - 1].getPlantlist().get(r.nextInt(farm[farmid - 1].getPlantlist().size()));
                        type = plantArr[Integer.parseInt(typeId) - 1].getName();
                        break;
                    }
                    case 3 -> { //Fertilizer
                        unit = r.nextInt(2) + 2;// pack 1000,500
                        quantity = (r.nextInt(9) + 1) + "";

                        //store fertilizers name
                        typeId = farm[farmid - 1].getFertiliserlist().get(r.nextInt(farm[farmid - 1].getFertiliserlist().size()));
                        type = fertilizerArr[Integer.parseInt(typeId) - 1].getName();
                        break;
                    }
                    default -> {
                        unit = r.nextInt(2) + 4;
                        if (unit == 4) {
                            quantity = (r.nextInt(9) + 1) + "." + (r.nextInt(100));
                        } else if (unit == 5) {
                            quantity = (r.nextInt(9 + 1) * 100) + (r.nextInt(100)) + "";
                        }

                        typeId = farm[farmid - 1].getPesticidelist().get(r.nextInt(farm[farmid - 1].getPesticidelist().size()));
                        type = pesticideArr[Integer.parseInt(typeId) - 1].getName();
                        break;
                    }
                }
                int field = r.nextInt(3) + 1;
                int row = r.nextInt(3) + 1;
                Integer id = count.getAndIncrease();
                try {
                    //Create new Activity
                    Activity act = new Activity(id, date, action + 1, Integer.toString(Integer.parseInt(typeId) + 1), unit + 1, quantity, field, row, farmid + "", userid);
                    String insertSql = "INSERT INTO activity (_id,date,action,type,unit,quantity,field,_row,farmId,userId) VALUES ("
                            + "\"" + id + "\","
                            + "\"" + date + "\","
                            + act.getAction() + ","
                            + act.getType() + ","
                            + act.getUnit() + ","
                            + "\"" + quantity + "\","
                            + field + ","
                            + row + ","
                            + "\"" + farmid + "\","
                            + "\"" + userid + "\""
                            + ")";
                    insertDB(insertSql);
                } catch (SQLException ex) {
                    Logger.getLogger(Farmer.class.getName()).log(Level.SEVERE, null, ex);
                }
                // write the log file
                // Exp log file: 1- Farmer 1 on Farm 6 Sowing Ammonium Polyphosphate (POLY11) Field 2 Row 1 (2pack (500g)) 2020-5-29
                writeLogFile(pw, id + " - Farmer " + this._id + " on Farm " + farms.get(i) + " "
                        + ActivityName[action] + " " + type + " Field " + field + " Row " + row + " (" + quantity + "" + Unit[unit] + ") " + date + "\n");

                // increment activity number
                activityNum++;

            }
            activities.put(farms.get(i), activityNum - 1);

        }
        System.out.println("Farmer "+this._id+" is Done!");

    }

    public synchronized void writeLogFile(PrintWriter pw, String str) {
        pw.write(str);

    }

    public void insertDB(String sql) throws SQLException {
        db.insert(sql);
    }
}
