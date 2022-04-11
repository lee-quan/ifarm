package ifarm;

import java.sql.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dummyFarmer {

    static Connection conn = DBConnection.ConnectDB();

    public void test() {
        try {
            Statement myStmt = conn.createStatement();
            ResultSet rs = myStmt.executeQuery("SELECT count(_id) as count FROM users");

            int numOfUsers = 0, numOfFarms = 0;
            while (rs.next()) {
                numOfUsers = Integer.parseInt(rs.getString("count"));

            }
            ResultSet rs1 = myStmt.executeQuery("SELECT count(_id) as count FROM farm");

            while (rs1.next()) {
                numOfFarms = Integer.parseInt(rs1.getString("count"));
            }

            String[] farm = new String[numOfUsers];
            generateFarm[] threads = new generateFarm[10];
            for (int i = 0; i < numOfUsers; i++) {
                threads[i] = new generateFarm(farm, i, numOfFarms);
            }

            for (generateFarm thread : threads) {
                thread.start();
            }
            for (generateFarm thread : threads) {
                thread.join();
            }

            for (String i : farm) {
                System.out.println(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex){
            Logger.getLogger("thread.join").log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {

    }

    class generateFarm extends Thread {

        String[] farm;
        int counter, numOfFarm;

        public generateFarm(String[] farm, int counter, int numOfFarm) {
            this.farm = farm;
            this.counter = counter;
            this.numOfFarm = numOfFarm;
        }

        @Override
        public void run() {
            String str = "[";
            int[] arr = new int[numOfFarm];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = i + 1;
            }
            Random random = new Random();
            int count = 0;

            while (true) {
                int index = random.nextInt(numOfFarm);
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
            str += "]";
            farm[counter] = str;
        }
    }
}
