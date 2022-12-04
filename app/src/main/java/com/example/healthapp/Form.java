package com.example.healthapp;

import static com.example.healthapp.R.id.buttonCalculateBoth;

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
    double bmr = 0;
    int bmrInt = 0;
    String finalName;

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

    public void showBMI(View view) {
        String namestr = nameET.getText().toString();
        finalName = namestr;
        String heightstr= heightET.getText().toString();
        String weightstr = weightET.getText().toString();
        String agestr = ageET.getText().toString();

        double height = 0; int weight = 0;int age = 0;

        height = Integer.parseInt(heightstr);
        weight = Integer.parseInt(weightstr);
        age = Integer.parseInt(agestr);

        double heightInM = (height)/100;
        bmi = weight/(heightInM*heightInM);

        Toast.makeText(this, "Hi " + namestr + " Your BMI is " + bmi, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Form.this,ResultActivity.class);
        startActivity(intent);

    }

    public void showBMR(View view) {
        String namestr = nameET.getText().toString();
        finalName = namestr;
        String heightstr= heightET.getText().toString();
        String weightstr = weightET.getText().toString();
        String agestr = ageET.getText().toString();
        int gender = 0;
        if( radioButtonMale.isChecked() ) {
            gender = 1; //For Male;
        }
        if( radioButtonFemale.isChecked() ) {
            gender = 2; //For Female;
        }



        double height = 0; int weight = 0;int age = 0;

        height = Integer.parseInt(heightstr);
        weight = Integer.parseInt(weightstr);
        age = Integer.parseInt(agestr);


        if(gender==1){
            bmr = (88.362 + (13.397 * weight) + (4.799 * height)) - (5.677 * age);
        }
        if(gender == 2 ) {
            bmr = (447.593 + (9.247 * weight) + (3.098 * height)) - (4.330 * age);
        }

        bmrInt = (int) bmr ;

        Toast.makeText(this, "Hi " + namestr + " Your BMR is " + bmrInt , Toast.LENGTH_SHORT).show();
    }

    public void CalculateBoth(View view) {
        String namestr = nameET.getText().toString();
        finalName = namestr;

        Intent intent = new Intent(Form.this,ResultActivity.class);

        intent.putExtra("BMR",bmrInt);
        intent.putExtra("BMI",bmi);
        intent.putExtra("name",finalName);
        startActivity(intent);
    }
}