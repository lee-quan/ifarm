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

    static Connection conn = null;
    static Statement stmt;
    
    public static Connection ConnectDB() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ifarm?serverTimezone=UTC", "root", "");
            System.out.println("Connected!");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return conn;
    }

    public static void insert(String sql) {
        
    }

    public static void truncate(String sql) {

    }

    public static void update(String sql) {

    }
}
