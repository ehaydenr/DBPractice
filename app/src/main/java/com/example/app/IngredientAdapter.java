package com.example.app;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import java.util.ArrayList;


public class IngredientAdapter extends ArrayAdapter<Ingredient> {

    private final Context context;
    private final ArrayList<Ingredient> itemsArrayList;
    private final ArrayList<Boolean> checked;

    public IngredientAdapter(Context context, ArrayList<Ingredient> itemsArrayList) {

        super(context, R.layout.ingredient_row, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
        this.checked = new ArrayList<Boolean>();
        for(int i = 0; i<itemsArrayList.size(); i++)
            checked.add(false);
    }

    public void toggleCheckBox(int position){
        checked.set(position, !checked.get(position));
    }

    public ArrayList<Ingredient> getCheckedIngredients(){
        ArrayList<Ingredient> checkedIngredients = new ArrayList<Ingredient>();
        for(int i = 0; i<checked.size(); i++)
            if(checked.get(i))
                checkedIngredients.add(itemsArrayList.get(i));
        return checkedIngredients;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.ingredient_row, parent, false);

        CheckBox check = (CheckBox) rowView.findViewById(R.id.checkBox);
        check.setChecked(checked.get(position));

        check.setText(itemsArrayList.get(position).getName());
        check.setTextColor(Color.WHITE);
        check.setTypeface(Main.typeface);

        return rowView;
    }
}