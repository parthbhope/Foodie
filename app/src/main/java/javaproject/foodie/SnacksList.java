package javaproject.foodie;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SnacksList extends Fragment {
    int no_of_vcg = 0;
    int no_of_garlictoast = 0;
    int no_of_tikkisandwich = 0;
    String VegCG = " Veg Cheese Grilled S/W";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_snacks_list, container, false);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    /*private void displayQuantity(int number) {
       TextView quantityTextView = (TextView)findViewById(R.id.quantity_vcg);
        quantityTextView.setText("" + number);

    }

    public void increaseQuantityvcg(View view) {
        displayQuantity(++no_of_vcg);
        // displayPrice(i*20);
    }

    public void decreaseQuantityvcg(View view) {

        if (no_of_vcg > 0) {
            displayQuantity(--no_of_vcg);
        }
    }
*/
//    public void added_to_cart(View view) {
//        if (no_of_vcg > 0)
//            Toast.makeText(this, no_of_vcg + " " + VegCG + " added", Toast.LENGTH_SHORT).show();
//        if (no_of_vcg == 0)
//            Toast.makeText(this, "Tu Uth..Ghari ja ani banav", Toast.LENGTH_SHORT).show();
//
//
//    }
}
