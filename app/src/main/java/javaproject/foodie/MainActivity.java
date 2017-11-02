package javaproject.foodie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static public int cat_id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = new fragment_nav();
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_main,fragment);
        ft.commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Fragment fragment = new BillActivity();
                android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_main,fragment);
                ft.commit();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(cat_id != 0)
            {
                Fragment fragment = new fragment_nav();
                android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_main, fragment);
                ft.commit();
                cat_id = 0;
            }
            else
            {
                // TODO: Add alert message
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            int count = 0, bill_amount = 0;
            ConnectionClass connectionClass = new ConnectionClass();
            final Connection con = connectionClass.CONN();
            try{
                if (con != null) {
                    Statement st = con.createStatement();
                    ResultSet rs;
                    rs = st.executeQuery("select * from cart");
                    boolean flag = false;

                    if (rs.first())
                    {
                        count = 1;
                        bill_amount += Integer.parseInt(rs.getString(2));
                    }
                    while (rs.next()){
                        count++;
                        bill_amount += Integer.parseInt(rs.getString(2));
                    }



                }
                else {
                    Toast.makeText(this, "Please Check your Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e) {
                Toast.makeText(this, "Exception : "+e, Toast.LENGTH_SHORT).show();
            }
            ConnectionClass connectionClass3 = new ConnectionClass();
            Connection con3 = connectionClass3.CONN();
            con3 = connectionClass3.CONN();

            Statement st1 ;
            try {
                Toast.makeText(this, "Order Successfully placed!!\nYour bill_amount is: Rs" + bill_amount, Toast.LENGTH_LONG).show();
                st1 = con3.createStatement();
                String sql2 = "insert into orders values(" + count + "," + bill_amount + ")";
                st1.execute(sql2);
            } catch (SQLException e) {

                Toast.makeText(this, "Order Succsdvsdfcsdavasvcdcessfully placed!!\nYour bill_amount is: Rs" + bill_amount, Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            ConnectionClass connectionClass2 = new ConnectionClass();
            //final Connection con = connectionClass.CONN();
            Connection con2 = connectionClass2.CONN();
            Statement st2;
            try {
                //Toast.makeText(this, "Cart Cleared!", Toast.LENGTH_LONG).show();
                st2 = con2.createStatement();
                String sql2 = "truncate cart";
                st2.execute(sql2);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if(id == R.id.action_clear)
        {
            ConnectionClass connectionClass = new ConnectionClass();
            final Connection con = connectionClass.CONN();
            Connection con1 = connectionClass.CONN();
            Statement st1;
            try {
                Toast.makeText(this, "Cart Cleared!", Toast.LENGTH_LONG).show();
                st1 = con1.createStatement();
                String sql2 = "truncate cart";
                st1.execute(sql2);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nac_home) {
            Fragment fragment = new fragment_nav();
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main,fragment);
            ft.commit();
        } else if (id == R.id.nav_about) {
            Fragment fragment = new About();
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main,fragment);
            ft.commit();
        } else if (id == R.id.nav_logout) {

            Intent i = new Intent(this,WelcomeScreen.class);
            startActivity(i);
        } else if (id == R.id.nav_settings) {
            Fragment fragment = new settings();
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main,fragment);
            ft.commit();
        } else if (id == R.id.nav_orders) {
            Fragment fragment = new Orders();
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main,fragment);
            ft.commit();
        }
        /*else{
            Fragment fragment = new Cart();
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main,fragment);
            ft.commit();
        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //@Override
    //public void onCategorySelected()
    public void onSelectCategory(View view)
    {
        if(view.getId() == R.id.cardView) {
            cat_id = 1;
            Fragment fragment = new ListActivity();
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        }

        else if(view.getId() == R.id.cardView2) {
            cat_id = 2;
            Fragment fragment = new ListActivity();
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();

        }
        else if(view.getId() == R.id.cardView3) {
            cat_id = 3;
            Fragment fragment = new ListActivity();
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        }
        else if(view.getId() == R.id.cardView4) {

            cat_id = 4;
            Fragment fragment = new ListActivity();
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        }
        else if(view.getId() == R.id.cardView5) {
            Fragment fragment = new BeveragesList();
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
            cat_id = 5;
        }
        }
/*
    private void displayQuantity(View v, int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_chat);
        quantityTextView.setText("" + number);

    }

    public void increaseQuantity(View view) {
        //displayQuantity(view, );
        // displayPrice(i*20);
    }

    public void decreaseQuantity(View view) {

        if (no_ocat_idodles > 0) {
            displayQuantity(--no_ocat_idodles);
        }
    }

    public void added_to_cart(View view) {
        if (no_ocat_idodles > 0)
            Toast.makeText(this, no_ocat_idodles + " " + HakkaNoodles + " added", Toast.LENGTH_SHORT).show();
        if (no_ocat_idodles == 0)
            Toast.makeText(this, "Tu Uth..Ghari ja ani banav", Toast.LENGTH_SHORT).show();


    }*/

}

