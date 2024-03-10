package com.example.recipeapp.views;

import static android.content.ContentValues.TAG;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recipeapp.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputMealActivity extends AppCompatActivity {


    private final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    EditText mealName = findViewById(R.id.mealName);
    EditText calories = findViewById(R.id.calorieCount);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_meal);
        Button input = findViewById(R.id.inputButton);
        input.setOnClickListener(view -> inputMeal());
    }

    class Meal {
        private String name;
        private String calories;
        public Meal (String name, String calories) {
            this.name = name;
            this.calories = calories;
        }
        public String getMealName() {
            return name;
        }
        public String getCalories() {
            return calories;
        }
    }
    private void inputMeal() {
            String nameOfMeal = mealName.getText().toString();
            String cals = calories.getText().toString();
            if (nameOfMeal.isEmpty()) {
                mealName.setError("Please enter the name of your meal!");
            } else if (cals.isEmpty()) {
                calories.setError("Please enter the amount of calories in your meal!");
            } else {
                Meal myMeal = new Meal(nameOfMeal, cals);

                FirebaseDatabase database = FirebaseDatabase
                        .getInstance("https://recipeapp-1fba1-default-rtdb.firebaseio.com/");
                DatabaseReference mealsref = database.getReference().child("meals/"
                        + user.getUid());

                mealsref.push().setValue(myMeal)
                        .addOnSuccessListener(success -> {
                            Toast.makeText(InputMealActivity.this,
                                    "Meal inputted successfully!", Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(failure -> {
                            Toast.makeText(InputMealActivity.this,
                                    "Could not input meal", Toast.LENGTH_SHORT).show();
                        });
            }
        }
}