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
import java.nio.file.*;

class Farmer implements Runnable {

    DBConnection db = new DBConnection();
    private String _id, name, email, password, phoneNumber;
    private String[] plantArr, fertilizerArr, pesticideArr, nameArr = new String[100];
    volatile int activityNum;
    private LinkedList<String> farms;
    private Farm[] farm;
    private PrintWriter pw;

    public Farmer(String _id) {
        this.activityNum = 1;
        farms = new LinkedList<>();
        this._id = _id;
    }

    public void setFarm(Farm[] farm) {
        this.farm = farm;
    }

    public void setPlantArr(String[] plantArr) {
        this.plantArr = plantArr;
    }

    public void setFertilizerArr(String[] fertilizerArr) {
        this.fertilizerArr = fertilizerArr;
    }

    public void setPesticideArr(String[] pesticideArr) {
        this.pesticideArr = pesticideArr;
    }

    public void setNameArr(String[] nameArr) {
        this.nameArr = nameArr;
    }

    public void setPrintWriter(PrintWriter pw) {
        this.pw = pw;
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
    // Generate Activities and write into log files (Concurrent)
    public void run() {
            String[] ActivityName = {"Sowing", "Fertilizers", "Pesticides", "Harvest", "Sales"};
            String[] UnitType = {"mass", "pack", "volume"};
            Random r = new Random();

            for (int i = 0; i < farms.size(); i++) {
                activityNum = 1;
//                System.out.println("Farmer " + this._id + " on Farm " + farms.get(i) + "\n");
//                writeLogFile(pwC, "Farmer " + this._id + " on Farm " + farms.get(i) + "\n");
                while (true) {
//                    System.out.println(farms.get(i));
                    // check at least 1000 activities

                    if (activityNum >= 1000) {
                        if (r.nextInt(2) == 0) {
                            break;
                        }
                    }
                    // random for activity id, date, action, type, unit, quantity, field, row, farmid, userid
                    String id = activityNum + "";
                    String date = r.nextInt(2020) + 2000 + "-" + r.nextInt(12) + 1 + "-" + r.nextInt(30) + 1;
                    String action = ActivityName[r.nextInt(ActivityName.length)];
                    int farmid = Integer.parseInt(farms.get(i));
                    String userid = this._id;
                    String type;
                    if (action.equals("Sowing") || action.equals("Harvest") || action.equals("Sales")) {
                        //store plants name
                        String plantid = farm[farmid - 1].getPlantlist().get(r.nextInt(farm[farmid - 1].getPlantlist().size()));
                        type = plantid;

                    } else if (action.equals("Fertilizers")) {
                        //store fertilizers name
                        String fertilizerid = farm[farmid - 1].getFertiliserlist().get(r.nextInt(farm[farmid - 1].getFertiliserlist().size()));
                        type = fertilizerid;
                    } else {
                        //store pesticide name
                        String pesticideid = farm[farmid - 1].getPesticidelist().get(r.nextInt(farm[farmid - 1].getPesticidelist().size()));
                        type = pesticideid;
                    }
                    String unit = UnitType[r.nextInt(UnitType.length)];
                    Double quantity = r.nextDouble(5);
                    quantity = Math.rint(quantity * 100) / 100;
                    int field = r.nextInt(3) + 1;
                    int row = r.nextInt(3) + 1;

                    activityNum++;
                    //Create new Activity
                    Activity act = new Activity(id, date, action, type, unit, quantity, field, row, farmid + "", userid,plantArr, fertilizerArr, pesticideArr);
                    // write the log file
                    // Exp log file: Sowing Broccol  Field 1 Row 1 1 kg 2022-03-03
                    writeLogFile(pw, "Farmer " + this._id + " on Farm " + farms.get(i) + " " + act.toLogFile() + "\n");
                }

            }

    }

    public synchronized void writeLogFile(PrintWriter pw, String str) {
        pw.write(str);

    }

    // Generate Activities and write into log files (Sequential)
//    public void sequantialRun() {
//        PrintWriter pwS = null;
////        System.out.println("Farmer " + getId());
//            String[] ActivityName = {"Sowing", "Fertilizers", "Pesticides", "Harvest", "Sales"};
//            String[] UnitType = {"mass", "pack", "volume"};
//            Random r = new Random();
//            int activityNum = 1;
//
//            for (int i = 0; i < farms.size(); i++) {
////            writeLogFile(pwS, "Farmer " + this._id + " on Farm " + farms.get(i) + "\n");
//                while (true) {
//                    // check at least 1000 activities 
//
//                    if (activityNum >= 1000) {
//                        if (r.nextInt(2) == 0) {
//                            break;
//                        }
//                    }
//                    // random for activity id, date, action, type, unit, quantity, field, row, farmid, userid
//                    String id = activityNum + "";
//                    String date = r.nextInt(2020) + 2000 + "-" + r.nextInt(12) + 1 + "-" + r.nextInt(30) + 1;
//                    String action = ActivityName[r.nextInt(ActivityName.length)];
//                    int farmid = Integer.parseInt(farms.get(i));
//                    String userid = this._id;
//                    String type;
//                    if (action.equals("Sowing") || action.equals("Harvest") || action.equals("Sales")) {
//                        //store plants name
//                        String plantid = farm[farmid - 1].getPlantlist().get(r.nextInt(farm[farmid - 1].getPlantlist().size()));
//                        type = plantid;
//
//                    } else if (action.equals("Fertilizers")) {
//                        //store fertilizers name
//                        String fertilizerid = farm[farmid - 1].getFertiliserlist().get(r.nextInt(farm[farmid - 1].getFertiliserlist().size()));
//                        type = fertilizerid;
//                    } else {
//                        //store pesticide name
//                        String pesticideid = farm[farmid - 1].getPesticidelist().get(r.nextInt(farm[farmid - 1].getPesticidelist().size()));
//                        type = pesticideid;
//                    }
//                    String unit = UnitType[r.nextInt(UnitType.length)];
//                    Double quantity = r.nextDouble(5);
//                    quantity = Math.rint(quantity * 100) / 100;
//                    int field = r.nextInt(3) + 1;
//                    int row = r.nextInt(3) + 1;
//
//                    //Create new Activity
//                    Activity act = new Activity(id, date, action, type, unit, quantity, field, row, farmid + "", userid,plantArr, fertilizerArr, pesticideArr);
////                     write the log file
////                     Exp log file: Sowing Broccol  Field 1 Row 1 1 kg 2022-03-03
//                    writeLogFile(pwS, "Farmer " + this._id + " on Farm " + farms.get(i) + " " + act.toLogFile() + "\n");
//                    activityNum++;
//
//                }
//
//            }
//
//    }
}
