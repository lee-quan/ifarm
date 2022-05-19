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

    private Connection conn = DBConnection.ConnectDB();
    private String sql;
    private Generator gen = new Generator();
    private volatile Farmer[] farmers;

    public FarmerSimulator(String sql) {
        this.sql = sql;
    }

    public Callable<Void> toCallable(final Runnable runnable) {
        return new Callable<Void>() {
            @Override
            public Void call() {
                runnable.run();
                return null;
            }
        };
    }

    @Override
    public Farmer[] generateFarmers(int numberOfFarmers) {
        sql += " LIMIT " + numberOfFarmers;
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Generator.count getTotalNumberOfFarm = gen.new count("farm");
        Future<Integer> count = executorService.submit(getTotalNumberOfFarm);

        List<Generator.generateFarmForUser> taskList = new ArrayList<>();
        List<Future<String>> resultList;
        farmers = new Farmer[numberOfFarmers];

        try {
            for (int i = 0; i < numberOfFarmers; i++) {
                Generator.generateFarmForUser farmGenerator = gen.new generateFarmForUser(count.get());
                taskList.add(farmGenerator);
            }

            resultList = executorService.invokeAll(taskList);
            Statement myStmt = conn.createStatement();

            myStmt.executeUpdate("TRUNCATE `ifarm`.`users`;");
            ResultSet rs = myStmt.executeQuery(sql);

            int counter = 0;
            while (rs.next()) {
                Future<String> future = resultList.get(counter);
                String _id = rs.getString(1),
                        name = rs.getString(2),
                        email = rs.getString(3),
                        password = rs.getString(4),
                        phoneNumber = rs.getString(5),
                        insertSql = "INSERT INTO users (_id, name,email,password,phoneNumber,farm) VALUES ("
                        + "\"" + _id + "\","
                        + "\"" + name + "\","
                        + "\"" + email + "\","
                        + "\"" + password + "\","
                        + "\"" + phoneNumber + "\","
                        + "\"" + future.get() + "\""
                        + ")";
                        System.out.println(insertSql);
                PreparedStatement pstmt = conn.prepareStatement(insertSql);
                pstmt.execute();
                farmers[Integer.parseInt(_id) - 1] = new Farmer(_id, name,email,password,phoneNumber, future.get());


                counter++;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(FarmerSimulator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(FarmerSimulator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FarmerSimulator.class.getName()).log(Level.SEVERE, null, ex);
        }
        executorService.shutdown();
        return farmers;
    }

}
