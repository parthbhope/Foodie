package javaproject.foodie;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class WelcomeScreen extends AppCompatActivity {
    /*EditText et_username,et_password;*/
    static public String user_id;
    static public String user_name;
    EditText username, pass;
    Button  login;
    ProgressDialog progressDialog;
    ConnectionClass connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
//        et_username = (EditText) findViewById(R.id.username);
//        et_password = (EditText) findViewById(R.id.password);
        // getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        username = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        connectionClass = new ConnectionClass();
        progressDialog = new ProgressDialog(this);

        /*register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeScreen.this, Main4Activity.class));
            }*/

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dologin dologin=new Dologin();
                dologin.execute();
            }
        });

    }
    private class Dologin extends AsyncTask<String, String, String> {

        String user_name = username.getText().toString();
        String passstr = pass.getText().toString();
        String z = "";
        boolean isSuccess = false;
        String em, password;

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Loading...");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            z = "default message";
            String name = "";
            if (user_name.trim().equals("") || passstr.trim().equals(""))
                z = "Please enter all fields....";
            else {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Please check your internet connection";
                    } else {
                        z = "hi";
                        String query = " select * from login where USERNAME='" + user_name + "' and PASS = '" + passstr + "'";
                        Statement stmt = con.createStatement();
                        // stmt.executeUpdate(query);
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            //name = rs.getString(1);
                            em = rs.getString(1);
                            password = rs.getString(2);
                            if (em.equals(user_name) && password.equals(passstr)) {
                                isSuccess = true;
                                user_id = user_name;
                                z = "Welcome " + user_name;
                                user_name = name;
                            } else {
                                isSuccess = false;
                                z = "Login unsuccessfull";
                            }
                        }
                    }
                } catch (Exception ex) {
                    isSuccess = false;
                    z = "Exceptions" + ex;
                }
            }
            return z;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getBaseContext(), "" + z, Toast.LENGTH_LONG).show();
            if (isSuccess) {

                Intent intent = new Intent(WelcomeScreen.this, MainActivity.class);
                startActivity(intent);
            }
            progressDialog.hide();
        }
    }
           /* {
                @Override
                public void onClick (View v){
                Dologin dologin = new Dologin();
                dologin.execute();
            }
            }

*/

    //  public void login(View view){
               /* String user_name = et_username.getText().toString();
                 String user_pass = et_password.getText().toString();
                  String type = "login";
                  BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                  backgroundWorker.execute(type,user_name,user_pass);*/
    //    Intent i= new Intent(this, OnlyCategory.class);
    //        startActivity(i);
    //}
}