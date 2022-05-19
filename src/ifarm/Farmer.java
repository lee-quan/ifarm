package ifarm;

import database.DBConnection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class Farmer implements Runnable {

    private String _id, name, email, password, phoneNumber, farm;
    private Connection conn = DBConnection.ConnectDB();

    public Farmer(String _id, String name, String email, String password, String phoneNumber, String farm) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.farm = farm;
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
    
    public String getFarm() {
        return farm;
    }

    @Override
    public void run() {
        try {
            Statement myStmt = conn.createStatement();
            ResultSet rs = myStmt.executeQuery("INSERT INTO ");
            while (rs.next()) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
