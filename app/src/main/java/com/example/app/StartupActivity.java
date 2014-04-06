package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ehaydenr on 4/6/14.
 */
public class StartupActivity extends Activity {

    private DataSource datasource;
    private Button viewAllIngredients, viewAllRecipes;
    private Button search;
    private EditText searchInput;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup);

        // Extract JSON from file and import into array
        Gson g = new Gson();
        Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
        ArrayList<ArrayList<String>> ingredientsList = g.fromJson(readFromFile(R.raw.ingredients), type);
        ArrayList<ArrayList<String>> recipeList = g.fromJson(readFromFile(R.raw.recipes), type);

        datasource = new DataSource(this, ingredientsList, recipeList);
        datasource.open();

        viewAllIngredients = (Button) findViewById(R.id.viewAllIngredientsButton);
        viewAllRecipes = (Button) findViewById(R.id.viewAllRecipesButton);

        viewAllIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(StartupActivity.this, IngredientListing.class);
                myIntent.putExtra("ingredient_list", datasource.getAllIngredients()); //Optional parameters
                startActivity(myIntent);
            }
        });

        viewAllRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(StartupActivity.this, RecipeListing.class);
                myIntent.putExtra("recipe_list", datasource.getAllRecipes()); //Optional parameters
                startActivity(myIntent);
            }
        });

        search = (Button) findViewById(R.id.button_search);
        searchInput = (EditText) findViewById(R.id.edit_message);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(null, "Search Input: " + searchInput.getText());

                // TODO: Generate query based on search input

                // TODO: Run Query

                // TODO: populate Recipe List view
            }
        });

    }

    private String readFromFile(int resource) {

        String ret = "";

        try {
            InputStream inputStream = this.getResources().openRawResource(resource);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
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
