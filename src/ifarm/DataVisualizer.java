package ifarm;

import database.DBConnection;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leequan
 */
public class DataVisualizer {

    Farmer[] farmers;
    Farm[] farms;
    DBConnection conn = new DBConnection();
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public DataVisualizer(Farmer[] farmers, Farm[] farms) {
        this.farmers = farmers;
        this.farms = farms;
    }

    public String getMinAndMaxDate() throws SQLException {
        ResultSet rs = conn.retrieve("SELECT min(date), max(date) FROM activity");
        String date = "";
        while (rs.next()) {
            date += rs.getString(1) + "," + rs.getString(2);
        }

        return date;
    }

    public void getChoice(String str) throws SQLException {        
        String[] arr = str.split(",");
        String sql = "SELECT * FROM "
                + "((select ac._id, ac.userId, ac.farmId, a.action, p.name,ac.field,ac._row,ac.quantity,u.unit,date from activity ac "
                + "JOIN action a ON a._id = ac.action "
                + "JOIN unit u ON ac.unit = u._id "
                + "JOIN plant p ON p._id = ac.type "
                + "WHERE ac.action=1 OR ac.action=2 OR ac.action=3) "
                + "UNION"
                + "(select ac._id,  ac.userId, ac.farmId, a.action, f.name,ac.field,ac._row,ac.quantity,u.unit,date from activity ac "
                + "JOIN action a ON a._id = ac.action "
                + "JOIN unit u ON ac.unit = u._id "
                + "JOIN fertiliser f ON f._id = ac.type "
                + "WHERE ac.action=4) "
                + "UNION "
                + "(select ac._id,  ac.userId, ac.farmId, a.action, pes.name,ac.field,ac._row,ac.quantity,u.unit,date from activity ac "
                + "JOIN action a ON a._id = ac.action "
                + "JOIN unit u ON ac.unit = u._id "
                + "JOIN pesticide pes ON pes._id = ac.type "
                + "WHERE ac.action=5))  AS table1 WHERE ";
        switch (arr[0]) {
            case "1" -> {
                sql += " farmId=\"" + arr[1] + "\" ORDER BY CAST(_id as unsigned)";
                printLog(sql);
            }
            case "2" -> {
                sql += " userId=\"" + arr[1] + "\" ORDER BY CAST(_id as unsigned)";
                printLog(sql);
            }
            case "3" -> {
                sql = switch (arr[1]) {
                case "1" -> "select ac._id, ac.userId, ac.farmId, a.action, p.name,ac.field,ac._row,ac.quantity,u.unit,date from activity ac "
                        + "JOIN action a ON a._id = ac.action "
                        + "JOIN unit u ON ac.unit = u._id "
                        + "JOIN plant p ON p._id = ac.type "
                        + "WHERE (ac.action=1 OR ac.action=2 OR ac.action=3) AND ac.type=\"" + arr[2] + "\"   ORDER BY CAST(ac._id as unsigned)";
                case "2" -> "select ac._id,  ac.userId, ac.farmId, a.action, f.name,ac.field,ac._row,ac.quantity,u.unit,date from activity ac "
                        + "JOIN action a ON a._id = ac.action "
                        + "JOIN unit u ON ac.unit = u._id "
                        + "JOIN fertiliser f ON f._id = ac.type "
                        + "WHERE ac.action=4 AND ac.type=\"" + arr[2] + "\" ORDER BY CAST(ac._id as unsigned)";
                default -> "select ac._id,  ac.userId, ac.farmId, a.action, pes.name,ac.field,ac._row,ac.quantity,u.unit,date from activity ac "
                        + "JOIN action a ON a._id = ac.action "
                        + "JOIN unit u ON ac.unit = u._id "
                        + "JOIN pesticide pes ON pes._id = ac.type "
                        + "WHERE ac.action=5 AND ac.type=\"" + arr[2] + "\" ORDER BY CAST(ac._id as unsigned)";
                };
                printLog(sql);
            }
            case "4" -> {
                if (arr[1].equals("1")) {
                    sql = "select ac._id, ac.userId, ac.farmId, a.action, p.name,ac.field,ac._row,ac.quantity,u.unit,date from activity ac "
                            + "JOIN action a ON a._id = ac.action "
                            + "JOIN unit u ON ac.unit = u._id "
                            + "JOIN plant p ON p._id = ac.type "
                            + "WHERE ac.action=1 OR ac.action=2 OR ac.action=3 ";
                } else if (arr[1].equals("2")) {
                    sql = "select ac._id,  ac.userId, ac.farmId, a.action, f.name,ac.field,ac._row,ac.quantity,u.unit,date from activity ac "
                            + "JOIN action a ON a._id = ac.action "
                            + "JOIN unit u ON ac.unit = u._id "
                            + "JOIN fertiliser f ON f._id = ac.type "
                            + "WHERE ac.action=4 ";
                } else {
                    sql = "select ac._id,  ac.userId, ac.farmId, a.action, pes.name,ac.field,ac._row,ac.quantity,u.unit,date from activity ac "
                            + "JOIN action a ON a._id = ac.action "
                            + "JOIN unit u ON ac.unit = u._id "
                            + "JOIN pesticide pes ON pes._id = ac.type "
                            + "WHERE ac.action=5 ";
                }
                sql += " AND ac.type=\"" + arr[2] + "\" AND ac.date between \"" + arr[3] + "\" AND \"" + arr[4] + "\"  ORDER BY CAST(ac._id as unsigned)";
                printLog(sql);
            }
            default -> {
                    sql = "(select ac.action,a.action, ac.type, p.name, ac.unit, u.unit, ROUND(SUM(ac.quantity),2) "
                            + "from activity ac "
                            + "JOIN plant p ON p._id = ac.type "
                            + "JOIN unit u ON ac.unit = u._id "
                            + "JOIN action a on ac.action = a._id "
                            + "WHERE ac.action=1 OR ac.action=2 OR ac.action=3 AND ac.field=" + arr[2] + " AND ac._row=" + arr[3] + " AND ac.farmId=\"" + arr[1] + "\" AND ac.date between \"" + arr[4] + "\" and \"" + arr[5] + "\""
                            + " GROUP BY ac.action, ac.type, ac.unit) "
                            + "UNION "
                            + "(select ac.action,a.action, ac.type, p.name, ac.unit, u.unit, ROUND(SUM(ac.quantity),2) "
                            + "from activity ac "
                            + "JOIN fertiliser p ON p._id = ac.type "
                            + "JOIN unit u ON ac.unit = u._id "
                            + "JOIN action a on ac.action = a._id "
                            + "WHERE ac.action=4 AND ac.field=" + arr[2] + " AND ac._row=" + arr[3] + " AND ac.farmId=\"" + arr[1] + "\" AND ac.date between \"" + arr[4] + "\" and \"" + arr[5] + "\""
                            + " GROUP BY ac.action, ac.type, ac.unit) "
                            + "UNION "
                            + "(select ac.action,a.action, ac.type, p.name, ac.unit, u.unit, ROUND(SUM(ac.quantity),2) "
                            + "from activity ac "
                            + "JOIN pesticide p ON p._id = ac.type "
                            + "JOIN unit u ON ac.unit = u._id "
                            + "JOIN action a on ac.action = a._id "
                            + "WHERE ac.action=5 AND ac.field=" + arr[2] + " AND ac._row=" + arr[3] + " AND ac.farmId=\"" + arr[1] + "\" AND ac.date between \"" + arr[4] + "\" and \"" + arr[5] + "\""
                            + " GROUP BY ac.action, ac.type, ac.unit) "
                            + "ORDER BY type ";
                    printSummarisedLog(sql, arr);
            }

        }

//        int num = arr.length;
//
//        if (num == 2) {
//            switch (arr[0]) {
//                case "1":
//
//                    break;
//                default:
//
//                    break;
//            }
//        } else if (num == 3) {
//
//        } else if (num == 5) {
//            if (arr[1].equals("1")) {
//                sql = "select ac._id, ac.userId, ac.farmId, a.action, p.name,ac.field,ac._row,ac.quantity,u.unit,date from activity ac "
//                        + "JOIN action a ON a._id = ac.action "
//                        + "JOIN unit u ON ac.unit = u._id"
//                        + "JOIN plant p ON p._id = ac.type"
//                        + "WHERE ac.action=1 OR ac.action=2 OR ac.action=3";
//            } else if (arr[1].equals("2")) {
//                sql = "select ac._id,  ac.userId, ac.farmId, a.action, f.name,ac.field,ac._row,ac.quantity,u.unit,date from activity ac "
//                        + "JOIN action a ON a._id = ac.action "
//                        + "JOIN unit u ON ac.unit = u._id"
//                        + "JOIN fertiliser f ON f._id = ac.type"
//                        + "WHERE ac.action=4";
//            } else {
//                sql = "select ac._id,  ac.userId, ac.farmId, a.action, pes.name,ac.field,ac._row,ac.quantity,u.unit,date from activity ac "
//                        + "JOIN action a ON a._id = ac.action "
//                        + "JOIN unit u ON ac.unit = u._id"
//                        + "JOIN pesticide pes ON pes._id = ac.type"
//                        + "WHERE ac.action=5";
//            }
//            sql += " AND ac.type=\"" + arr[2] + "\" AND ac.date between \"" + arr[3] + "\" AND \"" + arr[4] + "\" ORDER BY ac._id";
//            System.out.println(sql);
//            
//        } else {
//            printSummarisedLog(arr);
//        }
    }

    public void printLog(String sql) throws SQLException {
        System.out.println();
        ResultSet rs = conn.retrieve(sql);
        if (!rs.next())
            System.out.println("There is no activity done" );
        while (rs.next()) {
            System.out.println(rs.getString(1) + " - " + " Farmer " + rs.getString(2) + " on Farm " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5)
                    + " Field " + rs.getInt(6) + " Row " + rs.getInt(7) + " " + rs.getDouble(8) + " " + rs.getString(9) + " " + rs.getString(10));
        }
    }

    public void printSummarisedLog(String sql, String[] arr) throws SQLException {
        System.out.println();
        ResultSet rs = conn.retrieve(sql);
//        (select ac.action,a.action, ac.type, p.name, ac.unit, u.unit, ROUND(SUM(ac.quantity),2)
        List<Double> newQuantity = new ArrayList<>();
        List<String> unit = new ArrayList<>();
        List<String> pair = new ArrayList<>();
        
        if (!rs.next())
            System.out.println("There is no activity done" );
        while (rs.next()) {
            String testPair = rs.getString(2) + " " + rs.getString(4);
//            System.out.println(testPair+" "+pair.indexOf(testPair));
            if (pair.indexOf(testPair) == -1) {
                double quantity = 0;
                String Unit = "";
                switch (rs.getInt(5)) {
                    case 2 -> {
                        quantity = rs.getDouble(7) / 1000;
                        Unit = "kg";
                    }
                    case 4 -> {
                        quantity = rs.getDouble(7) / 2;
                        Unit = "pack (1000g)";
                    }
                    case 6 -> {
                        quantity = rs.getDouble(7) / 1000;
                        Unit = "l";
                    }
                    default -> {
                            quantity = rs.getDouble(7);
                            Unit = rs.getString(6);
                    }
                }
                pair.add(testPair);
                newQuantity.add(quantity);
                unit.add(Unit);

            } else {
//                System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" ");
                int index = pair.indexOf(testPair);
                double quantity = 0;
                String Unit = "";
                quantity = switch (rs.getInt(5)) {
                    case 2 -> rs.getDouble(7) / 1000;
                    case 4 -> rs.getDouble(7) / 2;
                    case 6 -> rs.getDouble(7) / 1000;
                    default -> rs.getDouble(7);
                };
                newQuantity.set(index, newQuantity.get(index) + quantity);
            }

            for (int i = 0; i < pair.size(); i++) {
                if(unit.get(i).equals("kg") || unit.get(i).equals("l")){
                    System.out.println(pair.get(i) + " " + df.format(newQuantity.get(i)) + " " + unit.get(i));
                }else{
                    Double data = (newQuantity.get(i));
                    System.out.println(pair.get(i) + " " + data.intValue() + " " + unit.get(i));
                }
                
            }
        }

    }
}
