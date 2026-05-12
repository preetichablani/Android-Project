package com.example.assignment1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
EditText nameET,cityET,emailET,mobileET;
Button click;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                nameET=findViewById(R.id.name);
                cityET=findViewById(R.id.city);
                emailET=findViewById(R.id.email);
                mobileET=findViewById(R.id.mobile);
                click=findViewById(R.id.submit);
                click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name,city,email,mobile;
                        name=nameET.getText().toString();
                        city=cityET.getText().toString();
                        email=emailET.getText().toString();
                        mobile=mobileET.getText().toString();
                        String Output="i am "+ name +
                                "i live in "+ city +
                                " my email and mobile number is: "+ email + mobile;
                        Toast.makeText(getApplicationContext(),Output,Toast.LENGTH_LONG).show();

                    }
                });
            return insets;
        });
    }
}