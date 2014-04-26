package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

    private Button viewAllIngredients, viewAllRecipes;
    private Button search;
    private EditText searchInput;
    private TextView errorText;
    private DataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup);
        this.datasource = Main.datasource;

        Typeface typeFace= Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");

        viewAllIngredients = (Button) findViewById(R.id.viewAllIngredientsButton);
        viewAllIngredients.setTypeface(typeFace);
        viewAllRecipes = (Button) findViewById(R.id.viewAllRecipesButton);
        viewAllRecipes.setTypeface(typeFace);

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

        ((TextView) findViewById(R.id.heading_text)).setTypeface(typeFace);

        search = (Button) findViewById(R.id.button_search);
        search.setTypeface(typeFace);
        searchInput = (EditText) findViewById(R.id.edit_message);
        errorText = (TextView) findViewById(R.id.errorText);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(null, "Search Input: " + searchInput.getText());

                ArrayList<Recipe> recipes = datasource.searchRecipes(searchInput.getText().toString());

                // TODO: populate Recipe List view
                if(!recipes.isEmpty()){
                    Intent myIntent = new Intent(StartupActivity.this, RecipeListing.class);
                    myIntent.putExtra("recipe_list", recipes); //Optional parameters
                    startActivity(myIntent);
                }else errorText.setVisibility(View.VISIBLE);
            }
        });

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
