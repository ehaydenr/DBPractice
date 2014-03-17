package com.example.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

public class TestDatabaseActivity extends ListActivity {
    private IngredientsDataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_test_database);


        String sql = this.readFromFile();
        Log.i(null, "Found: " + sql);
        datasource = new IngredientsDataSource(this, sql);
        datasource.open();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        List<Ingredient> values = datasource.getAllIngredients();
        ArrayAdapter<Ingredient> adapter = new ArrayAdapter<Ingredient>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

        if(datasource.justMade){
            adapter.add(datasource.createIngredient("cornstarch", "Wet Ingredients"));
            adapter.add(datasource.createIngredient("water", "Other"));
            adapter.add(datasource.createIngredient("garlic powder", "Spices"));
            adapter.add(datasource.createIngredient("ground round steak", "Meat"));
            adapter.add(datasource.createIngredient("vegetable oil", "Wet Ingredients"));
            adapter.add(datasource.createIngredient("broccoli flourets", "Vegetables"));
            adapter.add(datasource.createIngredient("onion", "Vegetables"));
            adapter.add(datasource.createIngredient("reduced sodium soy sauce", "Wet Ingredients"));
            adapter.add(datasource.createIngredient("brown sugar", "Dry Ingredients"));
            adapter.add(datasource.createIngredient( "ground ginger", "Spices"));
            adapter.add(datasource.createIngredient( "cooked rice", "Grains"));
            adapter.add(datasource.createIngredient( "chicken stove top stuffing mix", "Other"));
            adapter.add(datasource.createIngredient( "milk", "Dairy"));
            adapter.add(datasource.createIngredient( "ham", "Meat"));
            adapter.add(datasource.createIngredient( "eggs", "Dairy"));
            adapter.add(datasource.createIngredient( "cheddar cheese", "Dairy"));
            adapter.add(datasource.createIngredient( "salt", "Dry Ingredients"));
            adapter.add(datasource.createIngredient( "boneless chicken breasts", "Meat"));
            adapter.add(datasource.createIngredient( "unsweetened canned pineapple c", "Fruits"));
            adapter.add(datasource.createIngredient( "soy sauce", "Wet Ingredients"));
            adapter.add(datasource.createIngredient( "dry mustard", "Spices"));
            adapter.add(datasource.createIngredient( "fresh ground pepper", "Vegetables"));
            adapter.add(datasource.createIngredient( "green pepper", "Vegetables"));
            adapter.add(datasource.createIngredient( "mushrooms", "Vegetables"));
            adapter.add(datasource.createIngredient( "cherry tomatoes", "Vegetables"));
            adapter.add(datasource.createIngredient( "roma tomatoes", "Vegetables"));
            adapter.add(datasource.createIngredient( "garlic cloves", "Spices"));
            adapter.add(datasource.createIngredient( "olive oil", "Wet Ingredients"));
            adapter.add(datasource.createIngredient( "balsamic vinegar ", "Wet Ingredients"));
            adapter.add(datasource.createIngredient( "basil", "Spices"));
            adapter.add(datasource.createIngredient( "fresh cracker pepper", "Spices"));
            adapter.add(datasource.createIngredient( "italian bread", "Grains"));
            adapter.add(datasource.createIngredient( "parmigiano-reggiano cheese", "Dairy"));
            adapter.add(datasource.createIngredient( "meatballs", "Meat"));
            adapter.add(datasource.createIngredient( "spaghetti sauce", "Canned Ingredients"));
            adapter.add(datasource.createIngredient( "cream cheese", "Dairy"));
            adapter.add(datasource.createIngredient( "mayonnaise ", "Other"));
            adapter.add(datasource.createIngredient( "black pepper", "Spices"));
            adapter.add(datasource.createIngredient( "italian seasoning", "Spices"));
            adapter.add(datasource.createIngredient( "mozzarella cheese", "Dairy"));
        }


        adapter.notifyDataSetChanged();
    }

    private String readFromFile() {

        String ret = "";

        try {
            InputStream inputStream = this.getResources().openRawResource(R.raw.recipe);

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