/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ifarm;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Lee Quan
 */
public class DBConnection {
    static Connection conn=null;
    
    public static Connection ConnectDB(){
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ifarm?serverTimezone=UTC","root","1111");
            return conn;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        return conn;
    }
}
