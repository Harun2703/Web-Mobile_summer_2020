package com.csee5590.helloworldapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    // Initializing variables
    Button Loginbutton;
    EditText Username;
    EditText Password;
    TextView ControlStatus;
    String uname;
    String pwd;
    boolean flag = false;
    //Function to acquire login credentials and comparing with the given credentials
    //if matched then it redirects to welcome page else it displays incorrect password
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Username = findViewById(R.id.Username);
        Password = findViewById(R.id.Password);
        ControlStatus = findViewById(R.id.textView3);
        uname = Username.getText().toString();
        pwd = Password.getText().toString();
        Loginbutton = findViewById(R.id.button3);
        Loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Username.getText().toString().isEmpty() && !Password.getText().toString().isEmpty()) {
                    if (Username.getText().toString().equals("harun") && Password.getText().toString().equals("harun123"))
                    { flag = true; }
                }
                if (!flag)
                { ControlStatus.setText("Incorrect Password/Username"); }
                else
                { reDirectToWelcomePage(); }
            }
        });
    }
    public void reDirectToWelcomePage () {
        Intent intent = new Intent(MainActivity.this, Logout.class);
        startActivity(intent);
    }
}