package com.example.healthapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    double bmi;
    int bmr;
    String name;
    TextView showName;
    TextView showBmi;
    TextView showBmr;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        bmi = getIntent().getDoubleExtra("BMI",0);
        bmr = getIntent().getIntExtra("BMR",0);
        name = getIntent().getStringExtra("name");
        showName = findViewById(R.id.textViewName);
        showName.setText("Hi "+ name +",");
        showBmi = findViewById(R.id.textViewBMIValue);
        showBmi.setText(bmi + "");
        showBmr = findViewById(R.id.textViewBMRValue);
        showBmr.setText(bmr + "Kcal");

        //Toast.makeText(this, "BMI is " + bmi, Toast.LENGTH_SHORT).show();
    }





    public void toForm(View view) {
        Intent intent = new Intent(ResultActivity.this,Form.class);
        startActivity(intent);
    }
}