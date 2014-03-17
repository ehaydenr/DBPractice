package com.example.app;

/**
 *   `id` int(11) NOT NULL AUTO_INCREMENT,
 `Name` varchar(30) NOT NULL,
 `MealType` enum('Breakfast','Lunch','Dinner','Dessert','Snack/Appetizer') NOT NULL,
 `Ingredients` text NOT NULL,
 `IngredientsWithNumVal` text NOT NULL,
 `TimeDisplay` enum('<5min','5-10min','10-20min','20-30min','30+min') NOT NULL,
 `TimeActual` int(11) NOT NULL,
 `InstructionsText` text NOT NULL,
 `Calories` int(11) NOT NULL,
 `Rating` int(11) NOT NULL,
 * Created by ehaydenr on 3/17/14.
 */
public class Recipe {
    private long id, TimeActual, Calories, Rating;
    private String Name, Ingredients, IngredientsWithNumVal, TimeDisplay, InstructionsText;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTimeActual() {
        return TimeActual;
    }

    public void setTimeActual(long timeActual) {
        TimeActual = timeActual;
    }

    public long getCalories() {
        return Calories;
    }

    public void setCalories(long calories) {
        Calories = calories;
    }

    public long getRating() {
        return Rating;
    }

    public void setRating(long rating) {
        Rating = rating;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIngredients() {
        return Ingredients;
    }

    public void setIngredients(String ingredients) {
        Ingredients = ingredients;
    }

    public String getIngredientsWithNumVal() {
        return IngredientsWithNumVal;
    }

    public void setIngredientsWithNumVal(String ingredientsWithNumVal) {
        IngredientsWithNumVal = ingredientsWithNumVal;
    }

    public String getTimeDisplay() {
        return TimeDisplay;
    }

    public void setTimeDisplay(String timeDisplay) {
        TimeDisplay = timeDisplay;
    }

    public String getInstructionsText() {
        return InstructionsText;
    }

    public void setInstructionsText(String instructionsText) {
        InstructionsText = instructionsText;
    }
}
