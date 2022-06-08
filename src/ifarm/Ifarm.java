/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ifarm;

import database.DBConnection;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lee Quan
 */
public class Ifarm {

    private static final DBConnection db = new DBConnection();

    private static Callable<Void> toCallable(final Runnable runnable) {
        return () -> {
            runnable.run();
            return null;
        };
    }

    public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {
        try {
            Plant[] plantArr = db.generatePlantList();
            Fertilizer[] fertilizerArr = db.generateFertiliserList();
            Pesticide[] pesticideArr = db.generatePesticideList();                        

            //Introduction of thread pool
            ExecutorService executorservice = Executors.newFixedThreadPool(20);

            String[] tableName = {"farm", "plant", "fertiliser", "pesticide"};

            Generator d = new Generator();
            HashMap<String, Integer> MaxData = new HashMap<>();
            List<Generator.count> taskList = new ArrayList<>();

            // creating callable task
            for (String tableName1 : tableName) {
                Generator.count run = d.new count(tableName1);
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
            final int numOfFarm = MaxData.get("farm"),
                    numOfPlant = MaxData.get("plant"),
                    numOfFertiliser = MaxData.get("fertiliser"),
                    numOfPesticide = MaxData.get("pesticide");

            //create 2 arrays to store all farms
            Farm[] farms = new Farm[numOfFarm];

            List<Callable<Void>> callables = new ArrayList<>();

            //execute farm task
            for (int i = 0; i < numOfFarm; i++) {
                //randomly assign plants to each farm
                farms[i] = new Farm((i + 1) + "");
                Runnable plant = d.new generate(farms[i], numOfPlant, "plant");
                Runnable fertiliser = d.new generate(farms[i], numOfFertiliser, "fertiliser");
                Runnable pesticide = d.new generate(farms[i], numOfPesticide, "pesticide");
//            executorservice.execute(plant);
//            executorservice.execute(fertiliser);
//            executorservice.execute(pesticide);
                callables.add(toCallable(plant));
                callables.add(toCallable(fertiliser));
                callables.add(toCallable(pesticide));
            }

            try {
                executorservice.invokeAll(callables);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ifarm.class.getName()).log(Level.SEVERE, null, ex);
            }
            // start generate Farmer
            final int NumOfFarmer = 5;
            FarmerSimulator simulator = new FarmerSimulator("SELECT * FROM usersList ORDER BY CAST(_id as unsigned)");
            Farmer[] farmer = simulator.generateFarmers(NumOfFarmer);
            
            for (int i = 0; i < NumOfFarmer; i++) {
                System.out.println("Farmers: " + farmer[i].getId() + " Farm : " + farmer[i].getFarm());
            }

            for (int i = 0; i < numOfFarm; i++) {
            System.out.println("\nFarm " + (i + 1) + ":");
            System.out.println("Plant = " + farms[i].getPlant());
            System.out.println("Fertilizer = " + farms[i].getFertiliser());
            System.out.println("Pesticide = " + farms[i].getPesticide());
                String updateSql = "UPDATE farm "
                        + "SET plants=\"" + farms[i].getPlant() + "\","
                        + "fertilizers=\"" + farms[i].getFertiliser() + "\","
                        + "pesticides=\"" + farms[i].getPesticide() + "\" WHERE _id=\"" + farms[i].getId() + "\"";
                db.update(updateSql);
            }
            
            

            Files.deleteIfExists(Paths.get("log.txt"));
            Files.deleteIfExists(Paths.get("log1.txt"));

            PrintWriter pwS = new PrintWriter(new FileOutputStream("log1.txt", true));
            PrintWriter pwC = new PrintWriter(new FileOutputStream("log.txt", true));
            Counter countS = new Counter(1);
            Counter countC = new Counter(1);
            // generate activities
            List<Callable<Void>> FarmerCallables = new ArrayList<>();

            for (Farmer i : farmer) {
                i.setCounter(countS);
                i.setFarm(farms);
                i.setPlantArr(plantArr);
                i.setPesticideArr(pesticideArr);
                i.setFertilizerArr(fertilizerArr);
                FarmerCallables.add(toCallable(i));
            }

            long sequential_starttime = System.currentTimeMillis();
            for (Farmer i : farmer) {
                i.setPrintWriter(pwS);
                i.run();
            }
            long sequential_endtime = System.currentTimeMillis();
            pwS.close();
            System.out.println("\nTime consumed for generating 1000 activites for 100 farmers by using sequential programming is " + (sequential_endtime - sequential_starttime));

            for(Farmer i : farmer){
                i.setCounter(countC);
                i.setPrintWriter(pwC);
            }
            
            long starttime = System.currentTimeMillis();
            executorservice.invokeAll(FarmerCallables);
            long endtime = System.currentTimeMillis();
            pwC.close();
            executorservice.shutdown();
            
            System.out.println("\nTime consumed for generating 1000 activites for 100 farmers by using concurrent programming is " + (endtime - starttime));
            
            System.out.println();
            for(Farmer i : farmer){
                i.getActivityList();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Ifarm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
