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
//    private PrintWriter pwC;
//    private Connection conn = DBConnection.ConnectDB();
    private LinkedList<String> farms;
    private Farm[] farm;

    public Farmer(String _id) {
        farms = new LinkedList<>();
        this._id = _id;
    }

    public void setFarm(Farm[] farm) {
        this.farm = farm;
    }

//    public void setPrintWriter(PrintWriter pw) {
//        this.pwC = pw;
//    }
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

    public LinkedList getFarmList() {
        return farms;
    }

    @Override
    // Generate Activities and write into log files (Concurrent)
    public void run() {
        PrintWriter pwC = null;
        try {
            pwC = new PrintWriter(new FileOutputStream("log.txt", true));
            String[] ActivityName = {"Sowing", "Fertilizers", "Pesticides", "Harvest", "Sales"};
            String[] UnitType = {"mass", "pack", "volume"};
            Random r = new Random();
            int activityNum = 1;
            for (int i = 0; i < farms.size(); i++) {
//                writeLogFile(pwC, "Farmer " + this._id + " on Farm " + farms.get(i) + "\n");
                while (true) {
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

                    //Create new Activity
                    Activity act = new Activity(id, date, action, type, unit, quantity, field, row, farmid + "", userid);

                    // write the log file
                    // Exp log file: Sowing Broccol  Field 1 Row 1 1 kg 2022-03-03
                    writeLogFile(pwC, "Farmer " + this._id + " on Farm " + farms.get(i) + " " + act.toLogFile()+"\n");
                    activityNum++;

                }

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Farmer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pwC.close();
        }

    }

<<<<<<< HEAD
    public void sequantialRun(Farm[] farm) {
        String[] ActivityName = {"Sowing", "Fertilizers", "Pesticides", "Harvest", "Sales"};
//        System.out.println("\nFarmer " + getId() + ": Farms " + getFarm());
        int numOfFarm = farms.size();

        //Generate for the first Farm
        String farmId = farm[0].getId();
        int numOfPesticides = farm[0].getPesticideList().size(),
                numOfPlants = farm[0].getPlantList().size(),
                numOfFertiliser = farm[0].getFertiliserList().size();

        LinkedList<String> plant = farm[0].getPlantList(), fertiliser = farm[0].getFertiliserList(), pesticides = farm[0].getPesticideList();

        Random r = new Random();
        int numOfActivity = 0;
        while (true) {
            // Check 
            if (numOfActivity >= 1000) {
                int reach = r.nextInt(2);
                if (reach == 0) {
                    break;
                }
            }

            int unitIndex, materialIndex;
            double amount;
            String unit;
            //five activities
            int index = r.nextInt(5);
            switch (index) {
                case 0,3,4:
                    unitIndex = r.nextInt(2);
                    if (unitIndex == 0) {
                        unit = "kg";
                        amount = r.nextDouble(3);
                    } else {
                        unit = "g";
                        amount = (r.nextInt(9)+1)*100;
                    }
                    

                    materialIndex = r.nextInt(numOfPlants);
                    System.out.println("Farmer" + getId() + " " + ActivityName[index] + " Plant "+plant.get(materialIndex)+" Field 1 Row 1 "+amount + unit + " 2022-5-10"
                    );
                    break;
                case 2:
                    unitIndex = r.nextInt(2);
                    if (unitIndex == 0) {
                        unit = "l";
                        amount = r.nextDouble(3);
                    } else {
                        unit = "ml";
                        amount = (r.nextInt(9)+1)*100;
                    }

                    materialIndex = r.nextInt(numOfPesticides);
                    System.out.println("Farmer" + getId() + " " + ActivityName[index] + " Pesticide "+pesticides.get(materialIndex)+" Field 1 Row 1 " + amount + unit + " 2022-5-10");
                    break;
                default:
                    unitIndex = r.nextInt(2);
                    if (unitIndex == 0) {
                        unit = "pack (500g)";
                        amount = (r.nextInt(9)+1);
                    } else {
                        unit = "pack (1kg)";
                        amount = (r.nextInt(9)+1);
                    }

                    materialIndex = r.nextInt(numOfFertiliser);
                    System.out.println("Farmer" + getId() + " " + ActivityName[index] + " Fertilizer "+fertiliser.get(materialIndex)+" Field 1 Row 1 " + amount + unit + " 2022-5-10");
            }
            
            numOfActivity ++;
        }
    }

=======
    public synchronized void writeLogFile(PrintWriter pw, String str) {
        pw.write(str);

    }

    // Generate Activities and write into log files (Sequential)
    public void sequantialRun(Farm[] farm, PrintWriter pwS) {
//        System.out.println("Farmer " + getId());
        String[] ActivityName = {"Sowing", "Fertilizers", "Pesticides", "Harvest", "Sales"};
        String[] UnitType = {"mass", "pack", "volume"};
        Random r = new Random();
        int activityNum = 1;

        for (int i = 0; i < farms.size(); i++) {
//            writeLogFile(pwS, "Farmer " + this._id + " on Farm " + farms.get(i) + "\n");
            while (true) {
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

                //Create new Activity
                Activity act = new Activity(id, date, action, type, unit, quantity, field, row, farmid + "", userid);

                // write the log file
                // Exp log file: Sowing Broccol  Field 1 Row 1 1 kg 2022-03-03
                writeLogFile(pwS, "Farmer " + this._id + " on Farm " + farms.get(i) + " " + act.toLogFile()+"\n");
                activityNum++;

            }

        }
    }
>>>>>>> activity
}
