package com.example.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecipeViewer extends ActionBarActivity {

    TextView id, name, mealType, ingredients, ingredientsTextWithNumVal, timeDisplay, timeActual, instructionsText, caloriesText, ratingText;

    private Button expandIngredients, expandInstructions;
    private TextView instructionsView, ingredientHeader, instructionsHeader;
    private ListView ingredientsView;
    private IngredientAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(null, "Recipe Activity Called");
        setContentView(R.layout.recipe_viewer);
        initTextFields((Recipe)getIntent().getSerializableExtra("recipe"));

        this.expandIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ingredientsView.getVisibility() == View.GONE) {
                    ingredientsView.setVisibility(View.VISIBLE);
                    expandIngredients.setText("<");
                    instructionsView.setVisibility(View.GONE);
                    expandInstructions.setText(">");
                }else{
                    ingredientsView.setVisibility(View.GONE);
                    expandIngredients.setText(">");
                }
            }
        });

        this.expandInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(instructionsView.getVisibility() == View.GONE) {
                    instructionsView.setVisibility(View.VISIBLE);
                    expandInstructions.setText("<");
                    ingredientsView.setVisibility(View.GONE);
                    expandIngredients.setText(">");
                }else{
                    instructionsView.setVisibility(View.GONE);
                    expandInstructions.setText(">");
                }
            }
        });

        ingredientsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(null, "Clicked Cell");
                adapter.toggleCheckBox(i);
                adapter.notifyDataSetChanged();
            }
        });

    }

    public void initTextFields(Recipe recipe){
//        this.id = (TextView)findViewById(R.id.idText);
        this.name = (TextView)findViewById(R.id.nameText);
//        this.mealType = (TextView)findViewById(R.id.mealTypeText);
//        this.ingredients = (TextView)findViewById(R.id.ingredientsText);
//        this.ingredientsTextWithNumVal = (TextView)findViewById(R.id.ingredientsTextWithNumValText);
//        this.timeDisplay = (TextView)findViewById(R.id.timeDisplayText);
//        this.instructionsText = (TextView)findViewById(R.id.instructionsTextText);
//        this.caloriesText = (TextView)findViewById(R.id.caloriesText);
//        this.ratingText = (TextView)findViewById(R.id.ratingText);

        ((TextView) findViewById(R.id.nameText)).setTypeface(Main.typeface);

        this.expandIngredients = (Button)findViewById(R.id.expandIngredients);
        this.expandInstructions = (Button)findViewById(R.id.expandInstructions);
        this.ingredientsView = (ListView)findViewById(R.id.ingredientsView);
        this.instructionsView = (TextView)findViewById(R.id.instructionsView);

        this.ingredientHeader = (TextView)findViewById(R.id.ingredientHeader);
        this.instructionsHeader = (TextView)findViewById(R.id.instructionsHeader);
        this.instructionsHeader.setTypeface(Main.typeface);
        this.ingredientHeader.setTypeface(Main.typeface);

        String[] ingredients = recipe.getIngredients().split(",");
        ArrayList<Ingredient> ingredient_list = new ArrayList<Ingredient>();
        for(int i = 0; i<ingredients.length; i++)
            ingredient_list.add(new Ingredient(ingredients[i]));

        adapter = new IngredientAdapter(this, ingredient_list);
        ingredientsView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        this.instructionsView.setText(recipe.getInstructionsText());

        // id null??
        if(this.id == null)
            Log.d(null, "ID NULL");

        if(recipe != null){


//            this.id.setText("Recipe #" + recipe.getId());
            this.name.setText(recipe.getName());

//            this.mealType.setText("Meal Type: " + recipe.getMealType());
//            this.ingredients.setText("Ingredients: " + recipe.getIngredients());
//            this.ingredientsTextWithNumVal.setText("Ingredients With Num Val: " + recipe.getIngredientsWithNumVal());
//            this.timeDisplay.setText("Preparation Time: " + recipe.getTimeDisplay());
//            this.instructionsText.setText("Instructions Text: " + recipe.getInstructionsText());
//            this.caloriesText.setText("Calories: "+recipe.getCalories());
//            this.ratingText.setText("Rating"+recipe.getRating());
        }else{
            this.id.setText("Data not found.");
        }
    }

}
