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
//        static Connection conn=null ;
//    public static Connection ConnectDB(){
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ifarm?serverTimezone=UTC","root","1234");
//            return conn;
//        } catch (Exception e){
//            JOptionPane.showMessageDialog(null,e);
//        }
//        return conn;
//    }

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ifarm?serverTimezone=UTC","root","1111");
//            Statement myStmt = conn.createStatement();
//            ResultSet rs = myStmt.executeQuery("SELECT * FROM farm");
//            
//            
//            while (rs.next()){
//                System.out.println(rs.getString("name") + " is name, and address is " + rs.getString("address"));
//            }
//        try {
//            conn = DriverManager.getConnection("jdbc:mysql://localhost/ifarm?"
//                    + "user=root&password=1111");
//
//                // Do something with the Connection
//        } catch (SQLException ex) {
//            // handle any errors
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());
//        }

        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
