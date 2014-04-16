package com.example.app;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by chudak on 4/15/14.
 */
public class SearchWithAllIngredients extends Activity {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allIngredientColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_NAME, MySQLiteHelper.COLUMN_CATEGORY };
    private String[] allRecipeColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_NAME,
            MySQLiteHelper.COLUMN_MEAL_TYPE,
            MySQLiteHelper.COLUMN_INGREDIENTS,
            MySQLiteHelper.COLUMN_INGREDIENTS_WITH_NUM_VAL,
            MySQLiteHelper.COLUMN_TIME_DISPLAY,
            MySQLiteHelper.COLUMN_TIME_ACTUAL,
            MySQLiteHelper.COLUMN_INSTRUCTIONS_TEXT,
            MySQLiteHelper.COLUMN_CALORIES,
            MySQLiteHelper.COLUMN_RATING};

    private boolean justMade;
    private ArrayList<ArrayList<String>> ingredientsList, recipesList;
    private ArrayList<Recipe> recipe_list;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listing);

    }




}
