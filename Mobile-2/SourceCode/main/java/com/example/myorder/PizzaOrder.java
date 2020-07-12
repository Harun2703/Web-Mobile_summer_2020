package com.example.myorder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.lang3.BooleanUtils;

import java.util.ArrayList;
import java.util.List;


public class PizzaOrder extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final Integer PIZZA_PRICE = 7;
    private static final Integer CHICKEN_PRICE = 3;
    private static final Integer BACON_PRICE = 2;
    private static final Integer PEPPERONI_PRICE = 2;
    private static final Integer OP_PRICE = 1;
    private static final Integer SPICY_PRICE = 1;
    float totalPrice;
    Integer quantity = 1;
    String orderSummary;

    EditText userNameText;
    TextView quantityTextView;
    CheckBox chickenChecked, baconChecked, pepperoniChecked, opChecked;
    RadioButton extraspicy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);

        // using ID's to fetch
        quantityTextView = findViewById(R.id.pizzaquantity);
        userNameText = findViewById(R.id.user_input);
        chickenChecked = findViewById(R.id.chicken_checked);
        baconChecked = findViewById(R.id.bacon_checked);
        pepperoniChecked = findViewById(R.id.checkBox_Pepperoni);
        opChecked = findViewById(R.id.op_checked);
        extraspicy= findViewById(R.id.extraspicy);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Credit");
        categories.add("Debit");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    // UserName error
    private boolean isUserEmpty(){
        // Checking whether the username is correct or not
        String userName = userNameText.getText().toString();
        if(userName == null || userName.isEmpty()){
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.userNull);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return true;
        }
        return false;
    }

    // Fetching the Details
    private String fetchDetails() {
        boolean chickenFlag = chickenChecked.isChecked();
        boolean baconFlag = baconChecked.isChecked();
        boolean pepperoniFlag = pepperoniChecked.isChecked();
        boolean opFlag = opChecked.isChecked();
        boolean extraspicyFlag = extraspicy.isChecked();

        // Getting the Total Price.
        totalPrice = calculatePrice(chickenFlag, baconFlag, pepperoniFlag, opFlag, extraspicyFlag , quantity);
        // Creating Order Summary
        return fetchOrderSummary(userNameText.getText().toString(), chickenFlag, baconFlag, pepperoniFlag, opFlag,extraspicyFlag ,totalPrice);
    }

    public void orderSummary(View view) {
        if (!isUserEmpty()) {
            orderSummary = fetchDetails();
            Intent intent = new Intent(PizzaOrder.this, OrderSummary.class);
            intent.putExtra("orderSummary", orderSummary);
            startActivity(intent);
        }
    }


    public void orderPizzaMain(View view) {
        if (!isUserEmpty()) {
            orderSummary = fetchDetails();
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"PizzaOrder@gmail.com"});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Order Summary");
            emailIntent.putExtra(Intent.EXTRA_TEXT, orderSummary);
            startActivity(Intent.createChooser(emailIntent, ""));
        }
    }



    private String fetchOrderSummary(String userName, boolean chickenFlag, boolean baconFlag,
                                     boolean pepperoniFlag, boolean opFlag,boolean extraspicyFlag, float totalPrice) {
        return  getString(R.string.order_summary_name,userName) +"\n"+
                getString(R.string.order_summary_chicken,BooleanUtils.toStringYesNo(chickenFlag))+"\n"+
                getString(R.string.order_summary_bacon,BooleanUtils.toStringYesNo(baconFlag)) +"\n"+
                getString(R.string.order_summary_pepperoni,BooleanUtils.toStringYesNo(pepperoniFlag)) +"\n"+
                getString(R.string.order_summary_op,BooleanUtils.toStringYesNo(opFlag)) +"\n"+
                "Extra spicy? (yes)" + "\n"+
                getString(R.string.order_summary_quantity,quantity)+"\n"+
                getString(R.string.order_summary_total_price,totalPrice) +"\n"+
                getString(R.string.thank_you);
    }

    // Calculating the total Price
    private float calculatePrice(boolean chicken, boolean bacon, boolean pepperoni, boolean op,boolean extraspicy ,Integer quantity) {
        int basePrice = PIZZA_PRICE;
        if (chicken) {
            basePrice += CHICKEN_PRICE;
        }
        if (bacon) {
            basePrice += BACON_PRICE;
        }
        if (pepperoni){
            basePrice +=PEPPERONI_PRICE;
        }
        if(op){
            basePrice +=OP_PRICE;
        }
        if(extraspicy){
            basePrice +=SPICY_PRICE;
        }
        return quantity * basePrice;
    }

    /**
     * incrementing the pizza by one
     *
     */
    public void increment(View view) {
        if (quantity < 20) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Log.i("PizzaOrder", "Please select less than 20 Pizzas");
            Context context = getApplicationContext();
            String lowerLimitToast = "Please select less than 20 Pizzas";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;
        }
    }

    /**
     * decrementing the pizza by one
     *
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Log.i("PizzaOrder", "Please select atleast one Pizza");
            Context context = getApplicationContext();
            String upperLimitToast = "Please select atleast one Pizza";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }
    }

    // Displaying the Quantity
    private void display(int number) {
        quantityTextView.setText("" + number);
    }



}
