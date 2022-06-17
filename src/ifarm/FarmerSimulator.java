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

        
        } catch (InterruptedException | ExecutionException | SQLException ex) {
            Logger.getLogger(FarmerSimulator.class.getName()).log(Level.SEVERE, null, ex);
        }
        executorService.shutdown();
        return farmers;
    }
}
