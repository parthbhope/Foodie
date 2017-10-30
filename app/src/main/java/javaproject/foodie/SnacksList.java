package javaproject.foodie;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SnacksList extends AppCompatActivity {
    int no_of_vcg = 0;
    String VegCG = " Veg Cheese Grilled S/W";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snacks_list);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_vcg);
        quantityTextView.setText("" + number);

    }

    public void increaseQuantity(View view) {
        displayQuantity(++no_of_vcg);
        // displayPrice(i*20);
    }

    public void decreaseQuantity(View view) {

        if (no_of_vcg > 0) {
            displayQuantity(--no_of_vcg);
        }
    }

    public void added_to_cart(View view) {
        if (no_of_vcg > 0)
            Toast.makeText(this, no_of_vcg + " " + VegCG + " added", Toast.LENGTH_SHORT).show();
        if (no_of_vcg == 0)
            Toast.makeText(this, "Tu Uth..Ghari ja ani banav", Toast.LENGTH_SHORT).show();


    }
}
