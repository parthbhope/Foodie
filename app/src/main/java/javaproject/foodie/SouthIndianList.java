package javaproject.foodie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SouthIndianList extends AppCompatActivity {
    int no_of_idlisambhar = 0;
    String idlisambhar = "Idli Sambhar";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_south_indian_list);
    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_idlisambhar);
        quantityTextView.setText("" + number);

    }

    public void increaseQuantity(View view) {
        displayQuantity(++no_of_idlisambhar);
        // displayPrice(i*20);
    }

    public void decreaseQuantity(View view) {

        if (no_of_idlisambhar > 0) {
            displayQuantity(--no_of_idlisambhar);
        }
    }

    public void added_to_cart(View view) {
        if (no_of_idlisambhar > 0)
            Toast.makeText(this, no_of_idlisambhar + " " + idlisambhar + " added", Toast.LENGTH_SHORT).show();
        if (no_of_idlisambhar == 0)
            Toast.makeText(this, "Tu Uth..Ghari ja ani banav", Toast.LENGTH_SHORT).show();


    }
}
