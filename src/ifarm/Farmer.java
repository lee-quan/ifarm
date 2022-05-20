package ifarm;

import database.DBConnection;
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

    public Farmer(String _id) {
        farms = new LinkedList<>();
        this._id = _id;
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

    public LinkedList getFarmList() {
        return farms;
    }

    @Override
    public void run() {
        // Generate Activities
//        try {
//            Statement myStmt = conn.createStatement();
//            ResultSet rs = myStmt.executeQuery("INSERT INTO ");
//            while (rs.next()) {
//
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

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

}
