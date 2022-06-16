/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import ifarm.Fertilizer;
import ifarm.Pesticide;
import ifarm.Plant;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
        
    static PreparedStatement pstmt;               
    public void setSQL(String sql) throws SQLException{
        pstmt = conn.prepareStatement(sql);                      
    }
    
    private static int batch=0;
    public synchronized void insertFast(Integer _id, String date, Integer action, String type,
            Integer unit, Double quantity, Integer field, Integer row, String farmId,
            String userId) throws SQLException{
        conn.setAutoCommit(false);
        batch++;
        pstmt.setString(1, Integer.toString(_id));
        pstmt.setString(2, date);
        pstmt.setInt(3, action);
        pstmt.setString(4, type);
        pstmt.setInt(5, unit);
        pstmt.setDouble(6, quantity);
        pstmt.setInt(7, field);
        pstmt.setInt(8, row);
        pstmt.setString(9, farmId);
        pstmt.setString(10, userId);
        
        pstmt.addBatch();
        if(batch%100==0){
            System.out.println("Data insert "+batch);
            pstmt.executeBatch();            
        }
        conn.commit();
    }

    public void truncate(String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }

    public void update(String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }

    public Fertilizer[] generateFertiliserList() {
        Fertilizer[] fertilizerArr = new Fertilizer[100];
        try {
            this.rs = retrieve("SELECT * FROM fertiliser");
            int counter = 0;
            while (rs.next()) {
                int index = Integer.parseInt(rs.getString(1)) - 1;
                fertilizerArr[index] = new Fertilizer(rs.getString(1), rs.getString(2), rs.getString(3));
                counter++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fertilizerArr;
    }

    public Plant[] generatePlantList() {
        Plant[] plantArr = new Plant[100];
        try {
            this.rs = retrieve("SELECT * FROM plant");
            int counter = 0;
            while (rs.next()) {
                int index = Integer.parseInt(rs.getString(1)) - 1;
                plantArr[index] = new Plant(rs.getString(1), rs.getString(2), rs.getString(3));
                counter++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plantArr;
    }

    public Pesticide[] generatePesticideList() {
        Pesticide[] pesticideArr = new Pesticide[100];
        try {
            this.rs = retrieve("SELECT * FROM pesticide");
            int counter = 0;
            while (rs.next()) {
                int index = Integer.parseInt(rs.getString(1)) - 1;
                pesticideArr[index] = new Pesticide(rs.getString(1), rs.getString(2), rs.getString(3));
                counter++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pesticideArr;
    }
}
