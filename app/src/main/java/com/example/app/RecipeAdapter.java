package com.example.app;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ehaydenr on 4/14/14.
 */
public class RecipeAdapter extends ArrayAdapter<Recipe> {

    private final Context context;
    private final ArrayList<Recipe> itemsArrayList;

    public RecipeAdapter(Context context, ArrayList<Recipe> itemsArrayList) {

        super(context, R.layout.recipe_row, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.recipe_row, parent, false);

        // 3. Get the two text view from the rowView
        TextView nameView = (TextView) rowView.findViewById(R.id.recipe_name);
        TextView subTextView = (TextView) rowView.findViewById(R.id.recipe_subText);

        // 4. Set the text for textView
        nameView.setText(itemsArrayList.get(position).getName());
        nameView.setTextColor(Color.WHITE);
        nameView.setTypeface(Main.typeface);
        if(subTextView == null) Log.d(null, "sub Text View null");
        subTextView.setText("" + itemsArrayList.get(position).getMealType());
        subTextView.setTextColor(Color.WHITE);
        subTextView.setTypeface(Main.typeface);
        // 5. return rowView
        return rowView;
    }
}
