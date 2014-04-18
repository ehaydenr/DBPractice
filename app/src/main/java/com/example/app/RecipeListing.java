package com.example.app;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RecipeListing extends Activity {

    private ArrayList<Recipe> recipe_list;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listing);

        this.recipe_list = (ArrayList<Recipe>)getIntent().getSerializableExtra("recipe_list");
        listView = (ListView)findViewById(R.id.listView);
        populateList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parentView, View childView,
                                    int position, long id) {
                Intent myIntent = new Intent(RecipeListing.this, RecipeViewer.class);
                myIntent.putExtra("recipe", recipe_list.get(position)); //Optional parameters
                startActivity(myIntent);
            }
        });

    }

    private void populateList() {
        //ArrayAdapter<Recipe> adapter = new ArrayAdapter<Recipe>(this, android.R.layout.simple_list_item_1, recipe_list);
        RecipeAdapter adapter = new RecipeAdapter(this, recipe_list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
