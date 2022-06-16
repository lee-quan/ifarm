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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
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
    private static Plant[] plantArr;
    private static Fertilizer[] fertilizerArr;
    private static Pesticide[] pesticideArr;
    private static List<Log> loglist;

    private static Callable<Void> toCallable(final Runnable runnable) {
        return () -> {
            runnable.run();
            return null;
        };
    }

    public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {
        try {
            plantArr = db.generatePlantList();
            fertilizerArr = db.generateFertiliserList();
            pesticideArr = db.generatePesticideList();

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
            final int NumOfFarmer = 1;
            FarmerSimulator simulator = new FarmerSimulator("SELECT * FROM users ORDER BY CAST(_id as unsigned)");
            Farmer[] farmer = simulator.generateFarmers(NumOfFarmer);

            for (int i = 0; i < NumOfFarmer; i++) {
//                System.out.println("Farmers: " + farmer[i].getId() + " Farm : " + farmer[i].getFarm());
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
            
//            db.truncate("truncate activity");
//            long sequential_starttime = System.currentTimeMillis();
//            for (Farmer i : farmer) {
//                i.setPrintWriter(pwS);
//                i.run();
//            }
//            long sequential_endtime = System.currentTimeMillis();
//            pwS.close();
//            System.out.println("\nTime consumed for generating 1000 activites for 100 farmers by using sequential programming is " + (sequential_endtime - sequential_starttime));
            db.truncate("truncate activity");
            for (Farmer i : farmer) {
                i.setCounter(countC);
                i.setPrintWriter(pwC);
            }

            long starttime = System.currentTimeMillis();
            executorservice.invokeAll(FarmerCallables);
            long endtime = System.currentTimeMillis();
            pwC.close();            

            System.out.println("\nTime consumed for generating 1000 activites for 100 farmers by using concurrent programming is " + (endtime - starttime));
            
            System.out.println();
            for (Farmer i : farmer) {
                i.getActivityList();
            }
            
            //part E: Data Visualization
            Scanner sc = new Scanner(System.in);
            System.out.println("5 methods to display activities log: ");
            System.out.println("1. Display all activity logs for a target farm");
            System.out.println("2. Display all activity logs for a target farmer");
            System.out.println("3. Display all activity logs for a target farm and plant / fertilizer / pesticide");
            System.out.println("4. Display all activity logs for a target farm and plant / fertilizer / pesticide between date A and date B (inclusive)");
            System.out.println("5. Display summarized logs by plants, fertilizers and pesticides for a target farm and plant / fertilizer / pesticide between date A and date B (inclusive) for selected field and row number.");
            
                
            
            System.out.print("\nEnter number of method to display: ");           
            int num = sc.nextInt();
            String sql = "SELECT * from activity WHERE";
            int activityLog=0;
            switch (num){
                case 1 -> {
                    //display target farm activities log
                    int farmid = input(sc,"farm",0,farms,0);          
                    activityLog = display(sql+" farmid="+farmid,false);
                    System.out.println("Total activities had done on farm: "+activityLog);
                    break;
                }
                case 2 -> {
                    //display target farmer activities log
                    int userid = input(sc,"user",0,farms,0);                    
                    activityLog = display(sql+" userid="+userid,false);
                    System.out.println("Total activities had done on user: "+activityLog);
                    break;
                }
                case 3 -> {     
                    //display target farm AND plant activities log
                    int farmid = input(sc,"farm",0,farms,0);
                    
                    System.out.print("Choose 1 for plant, 2 for fertiliser, 3 for pesticide: ");
                    int choice = sc.nextInt();
                    while(choice >3 || choice<1){
                        System.out.println("Please enter 1,2,3 only");
                        choice = sc.nextInt();
                    }
                    String method;
                    int id;
                    switch(choice){
                        case 1 -> {
                            //input plant
                            method = "plant";
                            id = input(sc,method,choice,farms,farmid);
                            break;
                        }
                        case 2 -> {
                            //input fertilizer
                            method = "fertilizer";
                            id = input(sc,method,choice,farms,farmid);
                            break;
                        }
                        default -> {
                            //input pesticide
                            method = "pesticide";
                            id = input(sc,method,choice,farms,farmid);
                            break;
                        }
                    }
                    System.out.println("Activity Log for Farm "+farmid+" and "+method+": ");
                    sql = displayFarmAndOther(farmid,method,id); 
                    activityLog = display(sql,false);
                    System.out.println("Total activities had done on farm "+farmid+" "+method+" "+id+": "+activityLog);
                }
                case 4 -> {
                    // display case 3 method but with date
                    int farmid = input(sc,"farm",0,farms,0);
                    
                    System.out.print("Choose 1 for plant, 2 for fertiliser, 3 for pesticide: ");
                    int choice = sc.nextInt();
                    while(choice >3 || choice<1){                        
                        System.out.println("Please enter 1,2,3 only");
                        choice = sc.nextInt();
                    }
                    String method;
                    int id;
                    switch(choice){
                        case 1 -> {
                            //input plant
                            method = "plant";
                            id = input(sc,method,choice,farms,farmid);                            
                            break;
                        }
                        case 2 -> {
                            //input fertilizer
                            method = "fertilizer";
                            id = input(sc,method,choice,farms,farmid);                            
                            break;
                        }
                        default -> {
                            //input pesticide
                            method = "pesticide";
                            id = input(sc,method,choice,farms,farmid);
                            break;
                        }
                    }
                    System.out.println();
                    String startdate =inputDate(sc);
                    String enddate =inputDate(sc);
                    System.out.println("Activity Log for Farm "+farmid+" and "+method+" between "+startdate+" and "+enddate);
                    sql = displayFarmAndOther(farmid,method,id) + " and date between '"+startdate+"' and '"+enddate+"'";
                    activityLog = display(sql,false);
                    System.out.println("Total activities had done on farm "+farmid+" "+method+" "+id+" between "+startdate+" and "+enddate+": "+activityLog);
                    
                }
                case 5 ->{
                    // display case 3 method but with date and summarize record for selected field and row number
                    int farmid = input(sc,"farm",0,farms,0);
                    
                    System.out.print("Choose 1 for plant, 2 for fertiliser, 3 for pesticide: ");
                    int choice = sc.nextInt();
                    while(choice >3 || choice<1){                        
                        System.out.println("Please enter 1,2,3 only");
                        choice = sc.nextInt();
                    }
                    String method;
                    int id;
                    switch(choice){
                        case 1 -> {
                            //input plant
                            method = "plant";
                            id = input(sc,method,choice,farms,farmid);                            
                            break;
                        }
                        case 2 -> {
                            //input fertilizer
                            method = "fertilizer";
                            id = input(sc,method,choice,farms,farmid);                            
                            break;
                        }
                        default -> {
                            //input pesticide
                            method = "pesticide";
                            id = input(sc,method,choice,farms,farmid);
                            break;
                        }
                    }
                    System.out.println();
                    String startdate =inputDate(sc);
                    System.out.println();
                    String enddate =inputDate(sc);
                    
                    loglist = new ArrayList<>();
                    System.out.println("Activity Log for Farm "+farmid+" and "+method+" between "+startdate+" and "+enddate);
                    sql = displayFarmAndOther(farmid,method,id) + " and date between '"+startdate+"' and '"+enddate+"'";
                    activityLog = display(sql,true); 
                    System.out.println("Total activities before summarized on farm "+farmid+" "+method+" "+id+" between "+startdate+" and "+enddate+": "+activityLog);
                    List<Log> result = summarizedLog();
                    System.out.println("Total activities after summarized: "+result.size());
                    for(Log result1: result){
                        result1.toLog();
                    }
                    
                }
                
            }
            
            
            executorservice.shutdown();
            
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Ifarm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        

    }
    
    public static List<Log> summarizedLog(){
        List<Log> resultlist = new ArrayList<>();
        List<Integer> idlist = new ArrayList<>();
        int newUnit=0;
        for (Log log1: loglist) {             
            double newQuantity=0;
            for (Log log2 : loglist) {
                if (log1.getId() == log2.getId() || idlist.contains(log1.getId())) continue;
                
                if (log1.getActionName().equals(log2.getActionName()) &&
                        log1.getTypeName().equals(log2.getTypeName()) && 
                        log1.getField()==log2.getField() &&
                        log1.getRow()==log2.getRow()){ // compare action, name, field and row
                    // check unittype
                    if(log1.getUnit()==1 || log1.getUnit()==5){ // kg and l
                        if(log2.getUnit()==1 || log2.getUnit()==5){
                            // kg vs kg or l vs l
                            newQuantity = log1.getQuantity() + log2.getQuantity();

                        }
                        else{
                            // kg vs g or l vs ml
                            newQuantity = log1.getQuantity()*1000 +log2.getQuantity();
                        }
                    }
                    else if(log2.getUnit()==1 || log2.getUnit()==5){
                        //g vs kg or ml vs l
                        newQuantity = log1.getQuantity() + log2.getQuantity()*1000;

                    }
                    else if ((log1.getUnit()==6 || log1.getUnit()==2) &&(log2.getUnit()==6 || log2.getUnit()==2)){
                        // g vs g and ml vs ml
                        newQuantity = log1.getQuantity() + log2.getQuantity();
                    }
                    //for pack (1000g) and pack (500g)
                    else if (log1.getUnit()==3 && log2.getUnit()==3){
                        // kg pack vs kg pack
                        newQuantity = log1.getQuantity() +log2.getQuantity();
                    }
                    else if(log1.getUnit()==3 || log2.getUnit()==4){
                        // kg pack vs g pack
                        newQuantity = log1.getQuantity()*1000 +log2.getQuantity()*500;                        
                    }
                    else if(log1.getUnit()==4 || log2.getUnit()==3){
                        // g pack vs kg pack
                        newQuantity = log1.getQuantity()*500 +log2.getQuantity()*1000;
                    }
                    else if(log1.getUnit()==4 && log2.getUnit()==4){
                        newQuantity = log1.getQuantity()*500 +log2.getQuantity()*500;
                    }
                    
                    //finish adding quantity adding id of log 2 done
                    idlist.add(log2.getId());
                }
                
                
            }
            
            //check new quantity more than 1000
            if (newQuantity >=1000){
                //set new unit 
                newQuantity /= 1000;
                switch(log1.getUnit()){                    
                    case 2 -> newUnit = 1;
                    case 4 -> newUnit = 3;
                    case 6 -> newUnit = 5;
                }                
                //output format: Sowing Broccoli Field 1 Row 1 2.5 kg
                resultlist.add(new Log(log1.getId(),log1.getActionName(),log1.getTypeName(),log1.getField(),log1.getRow(),newQuantity,newUnit,log1.getUnitType()));
            }
            else if (newQuantity == 0){
                newQuantity = log1.getQuantity();
                if (!idlist.contains(log1.getId())){
                    resultlist.add(new Log(log1.getId(),log1.getActionName(),log1.getTypeName(),log1.getField(),log1.getRow(),newQuantity,newUnit,log1.getUnitType()));
                    
                }
                
            }
            
            
        }
        return resultlist;
    }
    
    public static int display(String sql,boolean summarized){        
        //retrieve data from database        
        int count=0;            
        try {             
            ResultSet rs = db.retrieve(sql+" ORDER BY date");            
            while (rs.next()) {   
                count++;
                //result obtain:                                 
                String date = rs.getString("date");
                int actionid = rs.getInt("action");                
                ResultSet actionRS = db.retrieve("SELECT action from action WHERE _id="+actionid);
                String actionName = "";
                if (actionRS.next()){
                    actionName = actionRS.getString("action");
                }
                String name;
                String type;
                switch (actionid){
                    case 1,2,3 ->{
                         type = rs.getString("type");
                        name = plantArr[Integer.parseInt(type)-1].getName();                                                
                        break;
                    }
                    case 4 ->{
                         type = rs.getString("type");
                        name = fertilizerArr[Integer.parseInt(type)-1].getName(); 
                        break;
                    }
                    default ->{
                         type = rs.getString("type");
                        name = pesticideArr[Integer.parseInt(type)-1].getName(); 
                        break;
                    }
                }
                int field = rs.getInt("field");
                int row = rs.getInt("_row");
                double quantity = rs.getDouble("quantity");
                int unit = rs.getInt("unit");     
                ResultSet unitTypeRS = db.retrieve("SELECT unit FROM unit WHERE _id="+unit);
                String unitType="";
                if (unitTypeRS.next()){
                    unitType = unitTypeRS.getString("unit");
                }
                if(summarized){
                    Log log = new Log(count,actionName,name,date,unitType,Integer.parseInt(type),field,row,quantity,unit);
                    loglist.add(log);
                    
                }
                //display format: Sowing Ammonium Polyphosphate (POLY11) Field 2 Row 1 (2pack (500g)) 2020-5-29
                System.out.printf("%-3d %-12s %-30s Field %d Row %d %-7.2f %-5s %s%n",count,actionName,name,field,row,quantity,unitType,date);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
        
    }
    
    public static boolean checkID(int id, String method) throws SQLException{
        String sql = "SELECT * from activity WHERE "+method+"id="+id;
        ResultSet rs = db.retrieve(sql);
        return !rs.next();
    }
    
    public static boolean checkOthers(int id, int choice, Farm[] farms,int farmid){        
        switch (choice){
            case 1 -> {
                return farms[farmid-1].getPlantlist().indexOf(id+"")==-1;                    
            }
            case 2 -> {
                return farms[farmid-1].getFertiliserlist().indexOf(id+"")==-1;                    
            }
            default -> {
                return farms[farmid-1].getPesticidelist().indexOf(id+"")==-1;                    
            }            
        
        }                
    }
    
    public static int input(Scanner sc, String method,int choice,Farm[]farms,int farmid) throws SQLException{
        // check id null
        int id;
        switch (method){
            case "farm","user" ->{
                System.out.print("Enter "+method+" id to be displayed (1-10): ");
                id = sc.nextInt(); 
                while (checkID(id,method)){
                    System.out.println("There is no activity done on this "+method+".");
                    System.out.print("Enter "+method+" id to be displayed (1-10): ");
                    id = sc.nextInt(); 
                }
                break;
            }
            default ->{
                System.out.print("Enter "+method+" id (1-100): ");
                id = sc.nextInt();

                while(checkOthers(id,choice,farms,farmid)){
                    System.out.println("The "+method+" is not in the list");
                    System.out.println("Enter "+method+" id (1-100): ");
                    id = sc.nextInt();
                }
                break;
            }
        }
        
        return id;
    }
    
    public static String displayFarmAndOther(int farmid, String method, int id){
        String actionSQL;
        String nameSQL=" and type="+id;
        switch (method){
            case "plant"->{
                actionSQL = "action<=3";                
                break;
            }
            case "fertilizer" ->{
                actionSQL = "action=4";
            }
            default->{
                actionSQL = "action=5";
            }
        }
        String sql = "SELECT * from activity WHERE farmid="+farmid +" and "+actionSQL+nameSQL;        
        return sql;        
    }
    
    public static String inputDate(Scanner sc){        
        System.out.print("Enter date with format (yyyy-MM-dd): ");
        String date = sc.next();
        while(!validDate(date)){
            System.out.println("Please enter date with correct format: ");
            date = sc.next();
        }
        
        return date;
    }
    
    public static boolean validDate(String date){
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
}
