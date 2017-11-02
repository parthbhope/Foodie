package javaproject.foodie;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Driver;


public class ConnectionClass {
    String classs = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://192.168.43.212:3306/foodie";
    String un = "kshiro";
    // String url = "jdbc:mysql://000webhost.com:3306/id2394272_foodie";
    //String un = "id3068596_qwerty";
    String password = "ashish";

    @SuppressLint("NewApi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {

            Class.forName(classs);
            conn = DriverManager.getConnection(url, un, password);
            if(conn == null)
                Log.e("ERRO1111111111111", "fgxccvbcb");
            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException se) {
            Log.e("ERRO1", se.getMessage());
        }
        catch (ClassNotFoundException e) {
            Log.e("ERRO2", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO3", e.getMessage());
        }
        return conn;
    }
}