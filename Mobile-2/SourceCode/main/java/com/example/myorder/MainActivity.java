package com.example.myorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity{
    Button orderhere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        orderhere=findViewById(R.id.orderherebutton);
        orderhere.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                toMenuPage();
            }
        });

    }

    public void toMenuPage() {
        Intent intent = new Intent(MainActivity.this, PizzaOrder.class);
        startActivity(intent);
    }

}
