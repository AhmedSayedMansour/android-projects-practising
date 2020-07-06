package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    boolean whippedCream =false, chocolate = false;
    Editable name ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameEditText = (EditText) findViewById(R.id.name_EditText);
        name = nameEditText.getText();
        CheckBox whippedCreamView = (CheckBox) findViewById(R.id.whipped_cream_view);
        if(whippedCreamView.isChecked()){
            whippedCream = true;
        }else{
            whippedCream = false;
        }
        CheckBox ChocolateView = (CheckBox) findViewById(R.id.chocolate_checkbox);
        if(ChocolateView.isChecked()){
            chocolate = true;
        }else{
            chocolate = false;
        }
        String priceMessage =   "Name : "+ name +"\n"+getString(R.string.addWhipped)+whippedCream +"\n"+getString(R.string.addChoco)+chocolate+
                                "\nQuantity: "+quantity+"\ntotal: $"+ (quantity *5+ (whippedCream?1:0) *quantity + (chocolate?2:0) *quantity)
                                + "\nThank you!";
        composeEmail(priceMessage,"Just Java order for " + name);
//        displayMessage(priceMessage);
    }

    public void increment(View view) {
        quantity+=1;
        display(quantity);
    }

    public void decrement(View view) {
        quantity-=1;
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    /*private void displayPrice(int number) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }*/

    /**
     * This method displays the given text on the screen.
     */
    /*private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }*/

    public void composeEmail(String body, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_TEXT, body);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}