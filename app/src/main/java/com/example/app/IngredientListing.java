package com.example.app;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ehaydenr on 4/6/14.
 */
public class IngredientListing extends ListActivity {
    private ArrayList<Ingredient> ingredient_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listing);

        this.ingredient_list = (ArrayList<Ingredient>)getIntent().getSerializableExtra("ingredient_list");
        Log.d(null, "Received Ingredient List: " + ingredient_list.toString());
        populateList();

        /**
         * ToDo
         ListView view = this.getListView();
         view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         public void onItemClick(AdapterView parentView, View childView,
         int position, long id) {
         Log.d(null, "hit");
         CheckBox check = (CheckBox) childView.findViewById(R.id.checkBox);
         check.toggle();

         }
         });
         */
    }

    private void populateList(){
        IngredientAdapter adapter = new IngredientAdapter(this, ingredient_list);
        setListAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
