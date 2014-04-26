package com.example.app;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ehaydenr on 4/6/14.
 */
public class IngredientListing extends Activity {
    private ArrayList<Ingredient> ingredient_list;
    private Button findRecipes;
    private ListView listView;
    private IngredientAdapter adapter;
    private DataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredient_picker);

        this.ingredient_list = (ArrayList<Ingredient>)getIntent().getSerializableExtra("ingredient_list");
        Log.d(null, "Received Ingredient List: " + ingredient_list.toString());
        listView = (ListView)findViewById(R.id.listView);

        datasource = Main.datasource;

        populateList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.toggleCheckBox(i);
                adapter.notifyDataSetChanged();
            }
        });

        findRecipes = (Button)findViewById(R.id.findRecipes);
        findRecipes.setTypeface(Main.typeface);
        findRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Clicked Cells
                ArrayList<Ingredient> ingredients = adapter.getCheckedIngredients();
                if(ingredients.size() < 1) return;
                Log.d(null, "Checked Ingredients: " + ingredients);

                // Search for Recipes
                ArrayList<Recipe> recipes;
                Ingredient[] arr = new Ingredient[ingredients.size()];
                for(int i = 0; i<ingredients.size(); i++) arr[i] = ingredients.get(i);
                recipes = datasource.searchRecipesIngredients(arr);

                Intent myIntent = new Intent(IngredientListing.this, RecipeListing.class);
                myIntent.putExtra("recipe_list", recipes); //Optional parameters
                startActivity(myIntent);
            }
        });

    }

    private void populateList(){
        adapter = new IngredientAdapter(this, ingredient_list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }


}
