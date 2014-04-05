package com.example.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TestDatabaseActivity extends ListActivity {
    private DataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_test_database);

        // Extract JSON from file and import into array
        Gson g = new Gson();
        Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
        ArrayList<ArrayList<String>> ingredientsList = g.fromJson(readFromFile(R.raw.ingredients), type);
        ArrayList<ArrayList<String>> recipeList = g.fromJson(readFromFile(R.raw.recipes), type);

        datasource = new DataSource(this, ingredientsList, recipeList);
        datasource.open();

        List<Ingredient> values = datasource.getAllIngredients();
        ArrayAdapter<Ingredient> adapter = new ArrayAdapter<Ingredient>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
        adapter.notifyDataSetChanged();

        ListView view = this.getListView();
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parentView, View childView,
                                       int position, long id) {
                Log.d(null, "Item selected: " + position);
                Intent myIntent = new Intent(TestDatabaseActivity.this, RecipeActivity.class);
                myIntent.putExtra("recipe", datasource.getRecipe(position)); //Optional parameters
                startActivity(myIntent);
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