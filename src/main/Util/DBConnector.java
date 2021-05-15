package main.Util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//db-wgu-mwilliams-capstone-do-user-9208289-0.b.db.ondigitalocean.com:25060/";
    private static final String dbName = "defaultdb";
    private static final String autoReconnect = "?ssl-mode=REQUIRED?autoReconnect=true";

    private static final String jdbcURL = protocol+vendorName+ipAddress+dbName+autoReconnect;


    private static final String MYSQLJDBCDriver = "com.mysql.cj.jdbc.Driver";

    private static final String username = "doadmin";

    private static Connection conn = null;

    public static void startConnection(){
        try{
            Class.forName(MYSQLJDBCDriver);
            conn = DriverManager.getConnection(jdbcURL,username,"t9zkf0yt015tdm6x");
        }catch (SQLException s){
            System.out.println("SQL Exception");
            s.printStackTrace();
        }catch (ClassNotFoundException c){
            System.out.println("MYSQLJDBCDriver not found");
        }
    }

    public static Connection getConnection(){
        return conn;
    }

    public static void closeConnection(){
        try {
            conn.close();
            conn = null;
        }catch (Exception ignored){

        }
    }
}
