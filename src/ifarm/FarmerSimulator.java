package ifarm;

import database.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FarmerSimulator implements FarmerSimulatorInterface {

    DBConnection db = new DBConnection();
    private String sql;
    private Generator gen = new Generator();
    private volatile Farmer[] farmers;

    public FarmerSimulator(String sql) {
        this.sql = sql;
    }

    @Override
    public Farmer[] generateFarmers(int numberOfFarmers) {
        sql += " LIMIT " + numberOfFarmers;
        // create fixed thread poo;
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Generator.count getTotalNumberOfFarm = gen.new count("farm");
        Future<Integer> count = executorService.submit(getTotalNumberOfFarm);

        List<Generator.generateFarmForFarmer> taskList = new ArrayList<>();
        List<Future<String>> resultList;

        //Farmer array to store all the farmer class
        farmers = new Farmer[numberOfFarmers];

        try {
            for (int i = 0; i < numberOfFarmers; i++) {
                farmers[i] = new Farmer((i + 1) + "");
                Generator.generateFarmForFarmer farmGenerator = gen.new generateFarmForFarmer(farmers[i], count.get());
                taskList.add(farmGenerator);
            }

            // execute generate farm task and wait till finish completion 
            resultList = executorService.invokeAll(taskList);            
            // execute mysql to insert details of farmer
            ResultSet rs = db.retrieve(sql);

            int counter = 0;
            while (rs.next()) {
                Future<String> future = resultList.get(counter);
                String _id = rs.getString(1),
                        name = rs.getString(2),
                        email = rs.getString(3),
                        password = rs.getString(4),
                        phoneNumber = rs.getString(5);
                farmers[counter].setDetails(name, email, password, phoneNumber);
                counter++;
            }
        } catch (InterruptedException | ExecutionException | SQLException ex) {
            Logger.getLogger(FarmerSimulator.class.getName()).log(Level.SEVERE, null, ex);
        }
        executorService.shutdown();
        return farmers;
    }
    
    
    
    
    
    
    
}
