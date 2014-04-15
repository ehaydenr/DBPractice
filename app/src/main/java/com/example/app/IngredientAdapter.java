package com.example.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import java.util.ArrayList;


public class IngredientAdapter extends ArrayAdapter<Ingredient> {

    private final Context context;
    private final ArrayList<Ingredient> itemsArrayList;

    public IngredientAdapter(Context context, ArrayList<Ingredient> itemsArrayList) {

        super(context, R.layout.ingredient_row, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.ingredient_row, parent, false);



        CheckBox check = (CheckBox) rowView.findViewById(R.id.checkBox);

        check.setText(itemsArrayList.get(position).getName());

        // 5. return rowView
        return rowView;
    }
}