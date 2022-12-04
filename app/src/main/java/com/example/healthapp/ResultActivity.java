package com.example.healthapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    int bmi;
    int bmr;
    String name;
    TextView showName;
    TextView showResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        bmi = getIntent().getIntExtra("BMI", 0);
        bmr = getIntent().getIntExtra("BMR",0);
        name = getIntent().getStringExtra("name");
        showName = findViewById(R.id.textViewName);
        showName.setText("Hi "+ name +",");
        showResults = findViewById(R.id.textViewResult);
        showResults.setText("Your BMI is " + bmi + "/n" + "Your BMR is " + bmr);
    }





    public void toForm(View view) {
        Intent intent = new Intent(ResultActivity.this,Form.class);
        startActivity(intent);
    }
}