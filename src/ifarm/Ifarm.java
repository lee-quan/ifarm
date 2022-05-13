/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ifarm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lee Quan
 */
public class Ifarm {

    public static void main(String[] args) {
        //Introduction of thread pool
        ExecutorService executorservice = Executors.newFixedThreadPool(10);

        String[] tableName = {"users", "farm", "plant", "fertiliser", "pesticide"};

        testfordummy d = new testfordummy();
        HashMap<String, Integer> MaxData = new HashMap<>();
        List<testfordummy.count> taskList = new ArrayList<>();

        // creating callable task
        for (String tableName1 : tableName) {
            testfordummy.count run = d.new count(tableName1);
            taskList.add(run);
        }
        // create a list of future object to store data
        List<Future<Integer>> resultList;

        try {
            // submit the collection of task for thread to run
            resultList = executorservice.invokeAll(taskList);
            
            // get the data from future list and put it into hashmap
            for (int i = 0; i < resultList.size(); i++) {
                Future<Integer> future = resultList.get(i);
                MaxData.put(tableName[i], future.get());
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(Ifarm.class.getName()).log(Level.SEVERE, null, ex);
        }

        //set all the maximum value into a constant variable
        final int numOfUser = MaxData.get("users"),
                numOfFarm = MaxData.get("farm"),
                numOfPlant = MaxData.get("plant"),
                numOfFertiliser = MaxData.get("fertiliser"),
                numOfPesticide = MaxData.get("pesticide");

        //create 2 arrays to store all the user and farms
        User[] users = new User[numOfUser];
        Farm[] farms = new Farm[numOfFarm];

        // execute user task
        for (int i = 0; i < numOfUser; i++) {
            users[i] = new User((i + 1) + "");
            Runnable user = d.new generate(users[i], numOfFarm, "user");
            executorservice.execute(user);
        }

        //execute farm task
        for (int i = 0; i < numOfFarm; i++) {
            //randomly assign plants to each farm
            farms[i] = new Farm((i + 1) + "");
            Runnable plant = d.new generate(farms[i], numOfPlant, "plant");
            Runnable fertiliser = d.new generate(farms[i], numOfFertiliser, "fertiliser");
            Runnable pesticide = d.new generate(farms[i], numOfPesticide, "pesticide");
            executorservice.execute(plant);
            executorservice.execute(fertiliser);
            executorservice.execute(pesticide);
        }

        System.out.println("User");
        for (int i = 0; i < numOfUser; i++) {
            System.out.println("User's " + (i + 1) + ": " + users[i].getFarm());
        }

        for (int i = 0; i < numOfFarm; i++) {
            System.out.println("\nFarm " + (i + 1) + ":");
            System.out.println("Plant = " + farms[i].getPlant());
            System.out.println("Fertilizer = " + farms[i].getFertiliser());
            System.out.println("Pesticide = " + farms[i].getPesticide());

        }

        try {
            executorservice.shutdown();
            executorservice.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            Logger.getLogger(Ifarm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
