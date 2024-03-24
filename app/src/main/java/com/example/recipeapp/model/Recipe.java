package com.example.recipeapp.model;

import java.util.List;

public class Recipe {
    public String name;
    public int calories;
    public String instructions;
    public List<String> ingredients;
    public List<Double> quantities;

    public Recipe(String name, int calories, String instructions, List<String> ingredients, List<Double> quantities){
        this.name = name;
        this.calories = calories;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.quantities = quantities;
    }

    /**
     * Overridden to provide sanity check
     * @return debug output
     */
    public String toString(){
        return "Recipe: "+ this.name + " Calories: "+ this.calories + " Instructions: "+ instructions;
    }

}
