/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ifarm;

import java.sql.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Lee Quan
 */
public class Ifarm {

    public static void main(String[] args) {

        String[] tableName = {"users", "farm", "plant", "fertiliser", "pesticide"};
        
        testfordummy d = new testfordummy();
        testfordummy.count[] dcthread = new testfordummy.count[tableName.length];        
        HashMap<String,Integer> MaxData = new HashMap<>();
        
        // creating threads
        for (int i=0;i<tableName.length;i++){            
            dcthread[i] = d.new count(tableName[i]);
        }
        
        //starting all threads and make sure all the threads finished execution
        try {
            for (testfordummy.count thread : dcthread) {
                thread.start();
            }

            for (testfordummy.count thread : dcthread) {
                thread.join();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Ifarm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //put the data calculated into HashMap
        for (int i=0;i<tableName.length;i++){
            MaxData.put(tableName[i], dcthread[i].getMax());            
        }
        
        //set all the maximum value into a constant variable
        final int numOfUser = dcthread[0].getMax(),
                numOfFarm = dcthread[1].getMax(),
                numOfPlant = dcthread[2].getMax(),
                numOfFertiliser = dcthread[3].getMax(),
                numOfPesticide = dcthread[4].getMax();
        
        // create threads for generating farm, plant, pesticide and fertiliser
        testfordummy.generate[] farmThreads = new testfordummy.generate[numOfUser];
        testfordummy.generate[] plantThreads = new testfordummy.generate[numOfUser];
        testfordummy.generate[] pesticideThreads = new testfordummy.generate[numOfUser];
        testfordummy.generate[] fertiliserThreads = new testfordummy.generate[numOfUser];

        //create 2 arrays to store all the user and farms
        User[] users = new User[numOfUser];
        Farm[] farms = new Farm[numOfFarm];
        
        
        for (int i = 0; i < numOfUser; i++) {
            //randomly assign farms to each user
            users[i] = new User((i+1)+"");
            farmThreads[i] = d.new generate(users[i], numOfFarm, "user");
        }

        for (int i = 0; i < numOfFarm; i++) {
            //randomly assign plants to each farm
            farms[i] = new Farm((i+1)+"");
            plantThreads[i] = d.new generate(farms[i], numOfPlant, "plant");

            //randomly assign fertilisers to each farm
            fertiliserThreads[i] = d.new generate(farms[i], numOfFertiliser, "fertiliser");

            //randomly assign pesticides to each farm
            pesticideThreads[i] = d.new generate(farms[i], numOfPesticide, "pesticide");
        }

        try {
            for (int i = 0; i < numOfUser; i++) {
                farmThreads[i].start();
            }
            for (int i = 0; i < numOfFarm; i++) {
                plantThreads[i].start();
                fertiliserThreads[i].start();
                pesticideThreads[i].start();
            }
            for (int i = 0; i < numOfUser; i++) {
                farmThreads[i].join();
            }
            for (int i = 0; i < numOfFarm; i++) {
                plantThreads[i].join();
                fertiliserThreads[i].join();
                pesticideThreads[i].join();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Ifarm.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("User");
        for (int i = 0; i < numOfUser; i++) {
            System.out.println("User's "+(i+1)+": "+users[i].getFarm());
        }

        for (int i = 0; i < numOfFarm; i++) {
            System.out.println("\nFarm " + (i + 1) + ":");
            System.out.println("Plant = "+farms[i].getPlant());
            System.out.println("Fertilizer = "+farms[i].getFertiliser());
            System.out.println("Pesticide = "+farms[i].getPesticide());

        }

    }
}
