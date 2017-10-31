package javaproject.foodie;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class BeveragesList extends android.support.v4.app.Fragment {
    String thumbsup = "Thumbs Up";
    int no_of_thumbsup = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_beverages_list, container, false);
    }
//    private void displayQuantity(int number) {
//        TextView quantityTextView = (TextView) findViewById(R.id.quantity_thumbsup);
//        quantityTextView.setText("" + number);
//
//    }
//
//    public void increaseQuantity(View view) {
//        displayQuantity(++no_of_thumbsup);
//        // displayPrice(i*20);
//    }
//
//    public void decreaseQuantity(View view) {
//
//        if (no_of_thumbsup > 0) {
//            displayQuantity(--no_of_thumbsup);
//        }
//    }
//
//    public void added_to_cart(View view) {
//        if (no_of_thumbsup > 0)
//            Toast.makeText(this, no_of_thumbsup + " " + thumbsup + " added", Toast.LENGTH_SHORT).show();
//        if (no_of_thumbsup == 0)
//            Toast.makeText(this, "Tu Uth..Ghari ja ani banav", Toast.LENGTH_SHORT).show();
//
//
//    }
}
