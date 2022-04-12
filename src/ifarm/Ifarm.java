/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
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
public class Ifarm {

    public static void main(String[] args) {
        //        0: users, 1:farms, 2:plant, 3:fertiliser, 4:pesticide
        int[] count = new int[5];
        String[] tableName = {"users", "farm", "plant", "fertiliser", "pesticide"};

        dummy.count[] totalRow = new dummy.count[5];
        dummy.generate[] farmThreads = new dummy.generate[10];
        dummy.generate[] plantThreads = new dummy.generate[10];
        dummy.generate[] pesticideThreads = new dummy.generate[10];
        dummy.generate[] fertiliserThreads = new dummy.generate[10];
        dummy d = new dummy();

        for (int i = 0; i < 5; i++) {
            totalRow[i] = d.new count(count, tableName[i], i);
        }
        try {
            for (dummy.count thread : totalRow) {
                thread.run();
            }

            for (dummy.count thread : totalRow) {
                thread.join();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Ifarm.class.getName()).log(Level.SEVERE, null, ex);
        }

        int numOfUser = count[0], numOfFarm = count[1], numOfPlant = count[2], numOfFertiliser = count[3], numOfPesticide = count[4];
        String[] userFarm = new String[count[0]];
        String[] farmPlant = new String[count[1]];
        String[] farmFertiliser = new String[count[1]];
        String[] farmPesticides = new String[count[1]];

        for (int i = 0; i < numOfUser; i++) {
            //randomly assign farms to each user
            farmThreads[i] = d.new generate(userFarm, numOfUser, numOfFarm, i);
        }

        for (int i = 0; i < numOfFarm; i++) {
            //randomly assign plants to each farm
            plantThreads[i] = d.new generate(farmPlant, numOfFarm, numOfPlant, i);

            //randomly assign fertilisers to each farm
            fertiliserThreads[i] = d.new generate(farmFertiliser, numOfFarm, numOfFertiliser, i);

            //randomly assign pesticides to each farm
            pesticideThreads[i] = d.new generate(farmPesticides, numOfFarm, numOfPesticide, i);
        }

        try {
            for (int i = 0; i < numOfUser; i++) {
                farmThreads[i].start();
                farmThreads[i].join();
            }
            for (int i = 0; i < numOfFarm; i++) {
                plantThreads[i].start();
                fertiliserThreads[i].start();
                pesticideThreads[i].start();
                plantThreads[i].join();
                fertiliserThreads[i].join();
                pesticideThreads[i].join();
            }
//            for (int i = 0; i < numOfUser; i++) {
//                farmThreads[i].join();
//
//            }
//            for (int i = 0; i < numOfFarm; i++) {
//                plantThreads[i].join();
//                fertiliserThreads[i].join();
//                pesticideThreads[i].join();
//            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Ifarm.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("User");
        for (int i = 0; i < numOfUser; i++) {
            System.out.println("User " + (i + 1) + "'s farm = [" + userFarm[i]+"]");
        }
        
        for (int i = 0; i < numOfFarm; i++) {
            System.out.println("\nFarm " + (i + 1) + ":");
            System.out.println("Plant = [" + farmPlant[i]+"]");
            System.out.println("Fertilizer = [" + farmFertiliser[i]+"]");
            System.out.println("Pesticide = [" + farmPesticides[i]+"]");

        }

    }
}
