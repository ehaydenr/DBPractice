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

        adapter.add(datasource.createIngredient("Eggs"));

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