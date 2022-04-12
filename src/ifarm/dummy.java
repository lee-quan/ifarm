package ifarm;

import java.sql.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dummy {

    Connection conn = DBConnection.ConnectDB();

    class count extends Thread {
        int[] count;
        int index;
        String sql;
        public count(int[] count, String tableName, int index) {
            this.count=count;
            this.sql="SELECT count(_id) as count FROM "+tableName;
            this.index=index;
        }
        
        public void run(){
            try {
                Statement myStmt = conn.createStatement();
                ResultSet rs = myStmt.executeQuery(sql);
                while(rs.next()){
                    count[index]= Integer.parseInt(rs.getString("count"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(dummy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    class generate extends Thread {
        String[] array;
        int num,num1,index;
        
        public generate(String[] array, int num, int num1, int index){
            this.array = array;
            this.num = num;
            this.num1 = num1;
            this.index = index;
        }
        
        public void run() {
            String str = "";
            int[] arr = new int[num1];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = i + 1;
            }
            Random random = new Random();
            
            int count = 0;
            while (true) {
                int index = random.nextInt(num1);
                int temp = arr[index];
                if (temp == -1) {
                    break;
                } else {
                    arr[index] = -1;
                    if (count == 0) {
                        str += temp + "";
                    } else {
                        str += "," + temp;
                    }
                }
                count++;
            }
            str += "";
            array[index] = str;
        }
    }

}
