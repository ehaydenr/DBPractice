package com.example.app;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by chudak on 4/15/14.
 */
public class SearchWithSelectedIngredients extends Activity {

    private ArrayList<Recipe> recipes;
    private RecipeAdapter adapter;
    private ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listing);
        ArrayList<Ingredient> ingredientsList = (ArrayList<Ingredient>)getIntent().getSerializableExtra("ingredient_list");
        Ingredient[] arr = new Ingredient[ingredientsList.size()];
        for(int i = 0; i<ingredientsList.size(); i++) arr[i] = ingredientsList.get(i);
        recipes = StartupActivity.datasource.searchRecipesIngredients(arr);
        listView = (ListView)findViewById(R.id.list);
        populateList();
    }

    private void populateList(){
        adapter = new RecipeAdapter(this, recipes);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}