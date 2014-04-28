package com.example.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class RecipeViewer extends Activity {

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
        this.mealType = (TextView)findViewById(R.id.mealTypeText);
//        this.ingredients = (TextView)findViewById(R.id.ingredientsText);
//        this.ingredientsTextWithNumVal = (TextView)findViewById(R.id.ingredientsTextWithNumValText);
        this.timeDisplay = (TextView)findViewById(R.id.timeDisplayText);
//        this.instructionsText = (TextView)findViewById(R.id.instructionsTextText);
        this.caloriesText = (TextView)findViewById(R.id.caloriesText);
        this.ratingText = (TextView)findViewById(R.id.ratingText);

        ((TextView) findViewById(R.id.nameText)).setTypeface(Main.typeface);

        this.expandIngredients = (Button)findViewById(R.id.expandIngredients);
        this.expandInstructions = (Button)findViewById(R.id.expandInstructions);
        this.ingredientsView = (ListView)findViewById(R.id.ingredientsView);
        this.instructionsView = (TextView)findViewById(R.id.instructionsView);

        this.ingredientHeader = (TextView)findViewById(R.id.ingredientHeader);
        this.instructionsHeader = (TextView)findViewById(R.id.instructionsHeader);
        this.instructionsHeader.setTypeface(Main.typeface);
        this.ingredientHeader.setTypeface(Main.typeface);

        String[] ingredients = recipe.getIngredientsWithNumVal().split(",");
        ArrayList<Ingredient> ingredient_list = new ArrayList<Ingredient>();
        for(int i = 0; i<ingredients.length; i++)
            ingredient_list.add(new Ingredient(ingredients[i]));

        adapter = new IngredientAdapter(this, ingredient_list);
        ingredientsView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        String instructionsTextFromRecipe = recipe.getInstructionsText();
        String[] instructionsArray = instructionsTextFromRecipe.split("\n");
        instructionsTextFromRecipe = "";
        for(int i = 0; i< instructionsArray.length; i++)
            instructionsTextFromRecipe += instructionsArray[i] + "\n\n";

        this.instructionsView.setText(instructionsTextFromRecipe);

        // id null??
        if(this.id == null)
            Log.d(null, "ID NULL");

        if(recipe != null){
            this.name.setText(recipe.getName());
            this.mealType.setText(recipe.getMealType());
            this.timeDisplay.setText(recipe.getTimeDisplay());
            if(recipe.getCalories() != 0)
                this.caloriesText.setText(recipe.getCalories() + " calories");
            this.ratingText.setText(recipe.getRating() + "/5");
        }else{
            this.id.setText("Data not found.");
        }
    }

}
