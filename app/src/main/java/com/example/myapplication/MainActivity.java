package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity {

    private EditText ageEditText, heightEditText, weightEditText;
    private Button calculateButton;
    private TextView resultTextView;
    private RadioButton maleButton, femaleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void findViews() {
        resultTextView = findViewById(R.id.text_view_result);
        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);
        ageEditText = findViewById(R.id.edit_text_Age);
        heightEditText = findViewById(R.id.edit_text_inches);  // Updated ID
        weightEditText = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_Calculate);
    }

    private void calculateBMI() {
        String ageString = ageEditText.getText().toString();
        String heightString = heightEditText.getText().toString();
        String weightString = weightEditText.getText().toString();

        if (ageString.isEmpty() || heightString.isEmpty() || weightString.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int age = Integer.parseInt(ageString);
        double height = Double.parseDouble(heightString);
        double weight = Double.parseDouble(weightString);

        // Limit age to a maximum of 100
        if (age > 100) {
            Toast.makeText(this, "Please enter a valid age (0-100)", Toast.LENGTH_SHORT).show();
            return;
        }

        // Limit weight to a maximum of 500
        if (weight > 500) {
            Toast.makeText(this, "Please enter a valid weight (0-500)", Toast.LENGTH_SHORT).show();
            return;
        }

        // Limit height to a maximum of 200
        if (height > 200) {
            Toast.makeText(this, "Please enter a valid height (0-200)", Toast.LENGTH_SHORT).show();
            return;
        }

        String gender;
        if (maleButton.isChecked()) {
            gender = "Male";
        } else {
            gender = "Female";
        }

        double heightInMeters = height / 100;
        double bmi = weight / (heightInMeters * heightInMeters);

        String result = "Age: " + age + "\nGender: " + gender + "\nBMI: " + bmi;

        String bmiCategory;
        if (bmi < 18.5) {
            bmiCategory = "Underweight";
            Toast.makeText(this, "You are Underweight", Toast.LENGTH_SHORT).show();
        } else if (bmi < 24.9) {
            bmiCategory = "Normal weight";
            Toast.makeText(this, "You are Normal Weight", Toast.LENGTH_SHORT).show();
        } else if (bmi < 29.9) {
            bmiCategory = "Overweight";
            Toast.makeText(this, "You are Overweight", Toast.LENGTH_SHORT).show();
        } else {
            bmiCategory = "Obese";
            Toast.makeText(this, "You are Obese", Toast.LENGTH_SHORT).show();
        }

        result += "\nBMI Category: " + bmiCategory;
        resultTextView.setText(result);


        // Show notification
        String notificationText = "Your BMI is " + bmi + ". Category: " + bmiCategory;


    }
}
