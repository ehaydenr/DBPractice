package com.example.app;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecipeActivity extends ActionBarActivity {

    TextView id, name, mealType, ingredients, ingredientsTextWithNumVal, timeDisplay, timeActual, instructionsText, caloriesText, ratingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(null, "Recipe Activity Called");
        setContentView(R.layout.fragment_recipe_activity);
        initTextFields((Recipe)getIntent().getSerializableExtra("recipe"));

    }

    public void initTextFields(Recipe recipe){
        this.id = (TextView)findViewById(R.id.idText);
        this.name = (TextView)findViewById(R.id.nameText);
        this.mealType = (TextView)findViewById(R.id.mealTypeText);
        this.ingredients = (TextView)findViewById(R.id.ingredientsText);
        this.ingredientsTextWithNumVal = (TextView)findViewById(R.id.ingredientsTextWithNumValText);
        this.timeDisplay = (TextView)findViewById(R.id.timeDisplayText);
        this.timeActual = (TextView)findViewById(R.id.timeActualText);
        this.instructionsText = (TextView)findViewById(R.id.instructionsTextText);
        this.caloriesText = (TextView)findViewById(R.id.caloriesText);
        this.ratingText = (TextView)findViewById(R.id.ratingText);

        // id null??
        if(this.id == null)
            Log.d(null, "ID NULL");

        if(recipe != null){
            this.id.setText("ID: "+recipe.getId());
            this.name.setText("Name: " + recipe.getName());
            this.mealType.setText("Meal Type: " + recipe.getMealType());
            this.ingredients.setText("Ingredients: " + recipe.getIngredients());
            this.ingredientsTextWithNumVal.setText("Ingredients With Num Val: " + recipe.getIngredientsWithNumVal());
            this.timeDisplay.setText("Time Display: " + recipe.getTimeDisplay());
            this.timeActual.setText("Time Actual: "+recipe.getTimeActual());
            this.instructionsText.setText("Instructions Text: " + recipe.getInstructionsText());
            this.caloriesText.setText("Calories: "+recipe.getCalories());
            this.ratingText.setText("Rating"+recipe.getRating());
        }else{
            this.id.setText("Data not found.");
        }
    }

}
