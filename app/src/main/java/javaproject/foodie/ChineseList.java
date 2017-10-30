package javaproject.foodie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ChineseList extends AppCompatActivity {
    String HakkaNoodles = "Veg Hakka Noodles";
    int no_of_noodles = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese_list);
    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_hakkaNoodles);
        quantityTextView.setText("" + number);

    }

    public void increaseQuantity(View view) {
        displayQuantity(++no_of_noodles);
        // displayPrice(i*20);
    }

    public void decreaseQuantity(View view) {

        if (no_of_noodles > 0) {
            displayQuantity(--no_of_noodles);
        }
    }

    public void added_to_cart(View view) {
        if (no_of_noodles > 0)
            Toast.makeText(this, no_of_noodles + " " + HakkaNoodles + " added", Toast.LENGTH_SHORT).show();
        if (no_of_noodles == 0)
            Toast.makeText(this, "Tu Uth..Ghari ja ani banav", Toast.LENGTH_SHORT).show();


    }
}
