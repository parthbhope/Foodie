package javaproject.foodie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OnlyCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only_category);
    }

    public void listOfSnacks(View view) {
        Intent i = new Intent(this, SnacksList.class);
        startActivity(i);

    }

//    public void listOfChat(View view) {
//        Intent i = new Intent(this, ChatList.class);
//        startActivity(i);
//
//    }

    public void listOfChinese(View view) {
        Intent i = new Intent(this, ChineseList.class);
        startActivity(i);

    }

    public void listOfSouthIndian(View view) {
        Intent i = new Intent(this, SouthIndianList.class);
        startActivity(i);

    }

    public void listOfBeverages(View view) {
        Intent i = new Intent(this, BeveragesList.class);
        startActivity(i);

    }
}
