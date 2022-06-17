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
    