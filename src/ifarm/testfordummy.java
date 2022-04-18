/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ifarm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hng
 */
public class testfordummy {
    
    Connection conn = DBConnection.ConnectDB();
    
    class count extends Thread{
        private int max;
        private String sql;
        
        public count(String key){
            this.sql = "SELECT count(_id) as count FROM "+key;
        }
        
        
        public void run(){
            try {
                Statement myStmt = conn.createStatement();
                ResultSet rs = myStmt.executeQuery(sql);
                while(rs.next()){
                    max= Integer.parseInt(rs.getString("count"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(testfordummy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public int getMax(){
            return max;
        }
    }
    
    class generate extends Thread {
        Object obj;
        int num;
        String name;
        
        public generate(Object obj, int num, String name){
            this.obj = obj;
            this.num = num;

            this.name = name;
        }
        
        public void run() {            
            int[] arr = new int[num];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = i + 1; 
            }
            
            Random random = new Random();
                        
            while (true) {
                int index = random.nextInt(num);
                int temp = arr[index];
                if (temp == -1) {
                    break;
                } else {
                    arr[index] = -1;                    
                                        
                    if (obj instanceof User && name.endsWith("user")){
                        ((User) obj).insertFarm(index+"");
                    }
                    else if (obj instanceof Farm){
                        if (name.equals("plant")){
                            ((Farm) obj).insertPlant(index+"");                            
                        }
                        else if (name.equals("fertiliser")){
                            ((Farm) obj).insertFertiliser(index+"");
                        }
                        else{
                            ((Farm) obj).insertPesticide(index+"");
                        }
                    }                    
                }                
            }                        
        }
    }
}
