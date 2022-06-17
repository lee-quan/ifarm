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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author Lee Quan
 */
public class Ifarm {

    private static final DBConnection db = new DBConnection();
    private static final int NumOfFarmer = 100;

    private static Callable<Void> toCallable(final Runnable runnable) {
        return () -> {
            runnable.run();
            return null;
        };
    }

    public static boolean validDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        boolean valid;
        try {
            format.parse(date);
            format.setLenient(false);
            valid = true;

        } catch (ParseException e) {
            valid = false;
        }

        return valid;
    }

    public static void main(String[] args) {

        try {
            Plant[] plantArr = db.generatePlantList();
            Fertilizer[] fertilizerArr = db.generateFertiliserList();
            Pesticide[] pesticideArr = db.generatePesticideList();

            //Introduction of thread pool
            ExecutorService executorservice = new MyThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS,
                   new LinkedBlockingQueue<>());
            //Disaster Simulator
            
            // Generate maximum value for each data
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

            //Part B
            // start generate Farmer
            FarmerSimulator simulator = new FarmerSimulator("SELECT * FROM users ORDER BY CAST(_id as unsigned)");
            Farmer[] farmer = simulator.generateFarmers(NumOfFarmer);

            System.out.println("Farmers with their farm list: \n");
            for (int i = 0; i < NumOfFarmer; i++) {
                System.out.println("Farmers: " + farmer[i].getId() + " Farm : " + farmer[i].getFarm() + "\n");
            }
            System.out.println("Farm with its Plant, Fertilizer and Pesticide list: ");
            for (int i = 0; i < numOfFarm; i++) {
                System.out.println("\nFarm " + (i + 1) + ":");
                System.out.println("\nPlant = " + farms[i].getPlant());
                System.out.println("\nFertilizer = " + farms[i].getFertiliser());
                System.out.println("\nPesticide = " + farms[i].getPesticide());
                String updateSql = "UPDATE farm "
                        + "SET plants=\"" + farms[i].getPlant() + "\","
                        + "fertilizers=\"" + farms[i].getFertiliser() + "\","
                        + "pesticides=\"" + farms[i].getPesticide() + "\" WHERE _id=\"" + farms[i].getId() + "\"";
                db.update(updateSql);
            }
//
//            // Part C
            Files.deleteIfExists(Paths.get("sequential.txt"));
            Files.deleteIfExists(Paths.get("concurrent.txt"));

            PrintWriter pwS = new PrintWriter(new FileOutputStream("sequential.txt", true));
            PrintWriter pwC = new PrintWriter(new FileOutputStream("concurrent.txt", true));
            Counter countS = new Counter(1);
            Counter countC = new Counter(1);
            // generate activities
            List<Callable<Void>> FarmerCallables = new ArrayList<>();
//
            for (Farmer i : farmer) {
                i.setCounter(countS);
                i.setFarm(farms);
                i.setPlantArr(plantArr);
                i.setPesticideArr(pesticideArr);
                i.setFertilizerArr(fertilizerArr);
                i.setPrintWriter(pwS);
                FarmerCallables.add(toCallable(i));
            }
//
            db.truncate("truncate activity");
            long sequential_starttime = System.currentTimeMillis();
            for (Farmer i : farmer) {
                i.run();
            }
            long sequential_endtime = System.currentTimeMillis();
            pwS.close();

            System.out.println("\nSequential Programming: ");
            System.out.println("\n\nTime consumed for generating 1000 activites for 100 farmers is " + (sequential_endtime - sequential_starttime));
            db.truncate("truncate activity");
            for (Farmer i : farmer) {
                i.setIsDisaster(true);
                i.setCounter(countC);
                i.setPrintWriter(pwC);

            }
            long starttime = System.currentTimeMillis();
            //Disaster Simulator
//            for(Farmer i : farmer){
//                threadPool.execute(i);
//            }
            executorservice.invokeAll(FarmerCallables);
            long endtime = System.currentTimeMillis();
            pwC.close();

            System.out.println("\n\nConcurrent Programming: ");
            System.out.println("\n\nTime consumed for generating 1000 activites for 100 farmers is " + (endtime - starttime));
            System.out.println("\n\nFarmer Activity List Numer");
            for (Farmer i : farmer) {
                System.out.println(i.getActivityList());
                System.out.println("\n");
            }
            executorservice.shutdown();
//
//            //part E: Data Visualization
            Scanner sc = new Scanner(System.in);
            DataVisualizer dv = new DataVisualizer();
            String fromto = dv.getMinAndMaxDate();
            while (true) {
                System.out.println("\nThere are five methods to display the activity logs: ");
                System.out.println("1. Display all activity logs for a target farm");
                System.out.println("2. Display all activity logs for a target farmer");
                System.out.println("3. Display all activity logs for a target farm and plant / fertilizer / pesticide");
                System.out.println("4. Display all activity logs for a target farm and plant / fertilizer / pesticide between date A and date B (inclusive)");
                System.out.println("5. Display summarized logs by plants, fertilizers and pesticides for a target farm and plant / fertilizer / pesticide between date A and date B (inclusive) for selected field and row number.");
                System.out.println("0 - Quit\n");
                System.out.print("Enter number of method to display: ");
                int opt = sc.nextInt();
                if (opt == 0) {
                    break;
                }
                switch (opt) {
                    case 1 -> {
                        while (true) {
                            System.out.print("(0 - Back to main menu)\nEnter farm id to be displayed (1-" + farms.length + "): ");
                            int displayFarm = sc.nextInt();
                            if (displayFarm > 0 || displayFarm <= farms.length) {
                                dv.getChoice(opt + "," + displayFarm);
                                break;
                            } else if (displayFarm == 0) {
                                break;
                            } else {
                                System.out.println("Farm " + displayFarm + " does not exist! Please try again.");
                            }
                        }
                    }
                    case 2 -> {
                        while (true) {
                            System.out.print("(0 - Back to main menu)\nEnter farmer id to be displayed (1-" + farmer.length + "): ");
                            int displayFarmer = sc.nextInt();
                            if (displayFarmer > 0 || displayFarmer <= farms.length) {
                                dv.getChoice(opt + "," + displayFarmer);
                                break;
                            } else if (displayFarmer == 0) {
                                break;
                            } else {
                                System.out.println("Farmer " + displayFarmer + " does not exist! Please try again.");
                            }
                        }
                    }
                    case 3 -> {
                        System.out.print("(0 - Back to main menu)\nChoose 1 for plant, 2 for fertiliser, 3 for pesticide: ");
                        while (true) {
                            int displayType = sc.nextInt();
                            if (displayType > 3 || displayType < 1) {
                                System.out.println("Please enter 1,2,3 only");
                            } else if (displayType == 0) {
                                break;
                            } else {
                                String str;
                                str = switch (displayType) {
                                    case 1 ->
                                        "Plant ID ";
                                    case 2 ->
                                        "Fertilizer ID ";
                                    default ->
                                        "Pesticide ID ";
                                };
                                while (true) {
                                    System.out.print("Choose " + str + " (1-100): ");
                                    int typeId = sc.nextInt();
                                    if (typeId > 100 || typeId < 0) {
                                        System.out.println("1 to 100 only. ");
                                    } else {
                                        dv.getChoice(opt + "," + displayType + "," + typeId);
                                        break;
                                    }
                                }
                                
                                break;
                            }
                        }
                    }
                    case 4 -> {
                        while (true) {
                            System.out.print("(0 - Back to main menu)\nChoose 1 for plant, 2 for fertiliser, 3 for pesticide: ");
                            int displayType = sc.nextInt();
                            if (displayType > 3 || displayType < 1) {
                                System.out.println("Please enter 1,2,3 only");
                            } else if (displayType == 0) {
                                break;
                            } else {
                                String str = "";
                                str = switch (displayType) {
                                    case 1 ->
                                        "Plant ID ";
                                    case 2 ->
                                        "Fertilizer ID ";
                                    default ->
                                        "Pesticide ID ";
                                };
                                int typeId;
                                while (true) {
                                    System.out.print("Choose " + str + " (1-100): ");
                                    typeId = sc.nextInt();
                                    if (typeId > 100 || typeId < 0) {
                                        System.out.println("1 to 100 only. ");
                                    } else {
                                        break;
                                    }
                                }
                                while (true) {
                                    System.out.println("Between " + fromto.split(",")[0] + " and " + fromto.split(",")[1] + " ");
                                    System.out.print("From (yyyy-MM-dd: ");
                                    String from = sc.next();
                                    System.out.print("To (yyyy-MM-dd: ");
                                    String to = sc.next();
                                    if (!validDate(from) && validDate(to)) {
                                        System.out.println("Please enter date with correct format!");
                                    } else {
                                        dv.getChoice(opt + "," + displayType + "," + typeId + "," + from + "," + to);
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                    case 5 -> {
                        while (true) {
                            int displayFarm;
                            while (true) {
                                System.out.print("Enter farm id to be displayed (1-" + farms.length + "): \n");
                                displayFarm = sc.nextInt();
                                if (displayFarm > 0 || displayFarm <= farms.length) {
                                    break;
                                } else {
                                    System.out.println("Farm " + displayFarm + " does not exist! Please try again.");
                                }
                            }

                            int field, row;
                            while (true) {
                                System.out.print("Which field and row (1-5)?");
                                field = sc.nextInt();
                                row = sc.nextInt();
                                if (field > 5 || row > 5 || field < 1 || row < 1) {
                                    System.out.println("Please choose 1 to 5 only.");
                                } else {
                                    break;
                                }
                            }
                            while (true) {
                                System.out.println("Between " + fromto.split(",")[0] + " and " + fromto.split(",")[1] + " ");
                                System.out.print("From (yyyy-MM-dd): ");
                                String from = sc.next();
                                System.out.print("To (yyyy-MM-dd): ");
                                String to = sc.next();
                                if (!validDate(from) && validDate(to)) {
                                    System.out.println("Please enter date with correct format!");
                                } else {
                                    dv.getChoice(opt + "," + displayFarm + "," + field + "," + row + "," + from + "," + to);
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }

            }
        } catch (Exception ex) {
            Logger.getLogger(Ifarm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
