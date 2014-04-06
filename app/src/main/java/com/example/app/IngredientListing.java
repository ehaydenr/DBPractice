package com.example.app;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by ehaydenr on 4/6/14.
 */
public class IngredientListing extends ListActivity {
    private List<Ingredient> ingredient_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listing);

        this.ingredient_list = (List<Ingredient>)getIntent().getSerializableExtra("ingredient_list");
        Log.d(null, "Recieved Ingredient List: " + ingredient_list.toString());
        populateList();
    }

    private void populateList(){
        ArrayAdapter<Ingredient> adapter = new ArrayAdapter<Ingredient>(this, android.R.layout.simple_list_item_1, ingredient_list);
        setListAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
