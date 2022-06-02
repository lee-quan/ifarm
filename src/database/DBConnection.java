/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.*;


/**
 *
 * @author Lee Quan
 */
public class DBConnection {
        
    private ResultSet rs;    

    Connection conn = ConnectDB();

    public Connection ConnectDB() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ifarm?serverTimezone=UTC", "root", "");

            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return conn;
    }

    public ResultSet retrieve(String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(sql);
    }

    public void insert(String sql) throws SQLException {

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.execute();
    }

    public void truncate(String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }

    public void update(String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }
    
    public String[] generateFertiliserList(){
        String[] fertilizerArr = new String[100];
        try {            
            this.rs = retrieve("SELECT * FROM fertiliser");
            int counter = 0;
            while(rs.next()){
                fertilizerArr[counter] = rs.getString(2);
                counter++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fertilizerArr;
    }
    
    public String[] generatePlantList(){
        String[] plantArr = new String[100];
        try {
            this.rs = retrieve("SELECT * FROM plant");
            int counter = 0;
            while (rs.next()) {
                plantArr[counter] = rs.getString(2);
                counter++;
            }
          } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return plantArr;
    }
    
    
    public String[] generatePesticideList(){
        String[] pesticideArr = new String[100];
        try {
            this.rs = retrieve("SELECT * FROM pesticide");
            int counter = 0;
            while(rs.next()){
                pesticideArr[counter] = rs.getString(2);
                counter++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pesticideArr;
    }
}
