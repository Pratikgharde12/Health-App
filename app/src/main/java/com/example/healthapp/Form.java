package com.example.healthapp;

import static com.example.healthapp.R.id.buttonCalculateBoth;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Form extends AppCompatActivity {

    public TextInputEditText nameET;
    TextInputEditText heightET;
    TextInputEditText weightET;
    TextInputEditText ageET;
    RadioGroup radioGroup;
    RadioButton radioButtonMale;
    RadioButton radioButtonFemale;
    double bmi = 0;
    double roundedBmi;
    int bmrInt = 0;
    String finalName;

    double height;
    double weight;
    int age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        nameET = findViewById(R.id.InputEditTextName);
        heightET = findViewById(R.id.InputEditTextHeight);
        weightET = findViewById(R.id.InputEditTextWeight);
        ageET = findViewById(R.id.InputEditTextAge);
        radioGroup = findViewById(R.id.RadioGroup);
        radioButtonMale = findViewById(R.id.radioButtonM);
        radioButtonFemale = findViewById(R.id.radioButtonF);


    }

    public void Calculate(View view){
        String name = nameET.getText().toString();
        String heightStr = heightET.getText().toString();
        String weightStr = weightET.getText().toString();
        String ageStr = ageET.getText().toString();
        finalName = name;

        try {
            height = Integer.parseInt(heightStr);
        } catch (NumberFormatException numberFormatException){
            height = 0;
        }

        try {
            weight = Integer.parseInt(weightStr);
        } catch (NumberFormatException numberFormatException){
            weight = 0;
        }

        try {
            age = Integer.parseInt(ageStr);
        } catch (NumberFormatException numberFormatException){
            age = 0;
        }

        //All the values successfully initialized.....now the Real Game Starts

        if( name.isEmpty())
        {
            //Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            nameET.setError("Please enter your name");
        }

        else if(height == 0)
        {
            //Toast.makeText(this, "Please enter your height", Toast.LENGTH_SHORT).show();
            heightET.setError("PLease enter your height");
        }

        else if(weight ==0)
        {
            //Toast.makeText(this, "Please enter your weight", Toast.LENGTH_SHORT).show();
            weightET.setError("Please enter your weight");
        }
        else if(weight<25 || weight>150){
            Toast.makeText(this,"Weight should be in range 25 to 150 ", Toast.LENGTH_SHORT).show();
        }

        else if(age ==0)
        {
            //Toast.makeText(this, "Please enter your age", Toast.LENGTH_SHORT).show();
            ageET.setError("Please enter your age");
        }

        else if(age<13 || age>150){
            Toast.makeText(this,"Age should be in range 13 to 150 ", Toast.LENGTH_SHORT).show();
        }


        else
        {
            //Calculating BMI
            double heightInM = (height) / 100;
            bmi = weight / (heightInM * heightInM);

            //-------------------------------------------------------------------
            final DecimalFormat df = new DecimalFormat("0.00");
            roundedBmi = Double.parseDouble(df.format(bmi));
            //-------------------------------------------------------------------
            //BMI Calculation Done

            //Calculating BMR
            int gender = 0;
            double bmr = 0;
            if (radioButtonMale.isChecked()) {
                gender = 1; //For Male;
            }
            if (radioButtonFemale.isChecked()) {
                gender = 2; //For Female;
            }

            if (gender == 1) {
                bmr = (88.362 + (13.397 * weight) + (4.799 * height)) - (5.677 * age);
            }
            if (gender == 2) {
                bmr = (447.593 + (9.247 * weight) + (3.098 * height)) - (4.330 * age);
            }
            bmrInt = (int) bmr;
            //BMR Calculation Done

            Intent intent = new Intent(Form.this, ResultActivity.class);
            intent.putExtra("BMR", bmrInt);
            intent.putExtra("BMI", roundedBmi);
            intent.putExtra("name", finalName);
            startActivity(intent);
        }
    }
}