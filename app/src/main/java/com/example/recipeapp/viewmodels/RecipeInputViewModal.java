package com.example.recipeapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.recipeapp.model.Ingredient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RecipeInputViewModal extends ViewModel {
    private final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private MutableLiveData<Boolean> isSaveSuccessful = new MutableLiveData<>();
    private MutableLiveData<String> recipeName = new MutableLiveData<>();
    private MutableLiveData<String> recipeInstructions = new MutableLiveData<>();
    private MutableLiveData<String> calorieCount = new MutableLiveData<>();

    public boolean validateName(String name) {
        return !name.isEmpty();
    }
    public boolean validateInstructions(String instr) {
        return !instr.isEmpty();
    }
    public boolean validateCalories(String calories) {
        return !calories.isEmpty();
    }
    public void saveRecipe(String name, String instr, String calories, Ingredient[] ingredients) {
        Map<String, Object> recipeData = new HashMap<>();
        Integer cal = Integer.parseInt(calories);
        recipeData.put("user", user.getUid());
        recipeData.put("name", name);
        recipeData.put("instructions", instr);
        recipeData.put("calories", cal);

        DatabaseReference db = FirebaseDatabase.getInstance("https://recipeapp-1fba1-default-rtdb.firebaseio.com/")
                .getReference();
        db.child("recipes")
                .push()
                .setValue(recipeData)
                .addOnSuccessListener(aVoid -> isSaveSuccessful.postValue(true))
                .addOnFailureListener(e -> isSaveSuccessful.postValue(false));
    }
    public LiveData<Boolean> getIsSaveSuccessful() {
        return isSaveSuccessful;
    }
}