/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ifarm;

import database.DBConnection;
import java.sql.*;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hng
 */
public class Generator {

    Connection conn = DBConnection.ConnectDB();

    class count implements Callable<Integer> {

        private int max;
        private final String sql;

        public count(String key) {
            this.sql = "SELECT count(_id) as count FROM " + key;
        }

        @Override
        public Integer call() {
            try {
                Statement myStmt = conn.createStatement();
                ResultSet rs = myStmt.executeQuery(sql);
                while (rs.next()) {
                    max = Integer.parseInt(rs.getString("count"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
            }
            return max;
        }
    }

    class generateFarmForFarmer implements Callable<String> {

        int numOfFarm;

        public generateFarmForFarmer(int numOfFarm) {
            this.numOfFarm = numOfFarm;
        }

        public String call() {
            int[] arr = new int[numOfFarm];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = i + 1;
            }
            Random random = new Random();
            String str = "";
            boolean first = true;
            while (true) {
                int index = random.nextInt(numOfFarm);
                
                int temp = arr[index];

                if (temp == -1) {
                    break;
                } else {
                    arr[index] = -1;
                    if (first) {
                        first = false;
                        str += temp + "";
                    } else {
                        str += "," + temp;
                    }
                }

            }
            return str;
        }
    }

    class generate implements Runnable {

        Object obj;
        int num;
        String name;

        public generate(Object obj, int num, String name) {
            this.obj = obj;
            this.num = num;
            this.name = name;
        }

        @Override
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

                    if (obj instanceof User && name.endsWith("user")) {
                        ((User) obj).insertFarm((index + 1) + "");
                    } else if (obj instanceof Farm farm) {
                        switch (name) {
                            case "plant" ->
                                farm.insertPlant((index + 1) + "");
                            case "fertiliser" ->
                                farm.insertFertiliser((index + 1) + "");
                            default ->
                                farm.insertPesticide((index + 1) + "");
                        }
                    }
                }
            }
        }
    }
}
