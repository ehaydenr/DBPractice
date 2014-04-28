package com.example.app;

/**
 * Created by ehaydenr on 3/17/14.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataSource {

    // Database fields
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

    public DataSource(Context context, ArrayList<ArrayList<String>> ingredientsList, ArrayList<ArrayList<String>> recipesList) {
        this.ingredientsList = ingredientsList;
        this.recipesList = recipesList;
        dbHelper = new MySQLiteHelper(context);
        dbHelper.setSource(this);
    }

    public void open() throws SQLException {
        Log.d(null, "Grabbing writable database");
        database = dbHelper.getWritableDatabase();
        if(this.justMade){
            populate();
        }
    }

    public void close() {
        dbHelper.close();
    }

    //      INGREDIENT SUBROUTINES

    public Ingredient createIngredient(String name, String category){
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME, name);
        values.put(MySQLiteHelper.COLUMN_CATEGORY, category);
        long insertId = database.insert(MySQLiteHelper.TABLE_INGREDIENTS, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_INGREDIENTS, allIngredientColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Ingredient newIngredient = cursorToIngredient(cursor);
        cursor.close();
        return newIngredient;
    }

    public Ingredient getIngredient(long id){
        String[] whereArgs = { ""+id };
        String queryString =
                "SELECT * FROM " + MySQLiteHelper.TABLE_INGREDIENTS + " WHERE " + MySQLiteHelper.COLUMN_ID + " = ?";
        Cursor cursor = database.rawQuery(queryString, whereArgs);
        cursor.moveToFirst();
        if(!cursor.isAfterLast()){
            return cursorToIngredient(cursor);
        }
        return null;
    }

    public ArrayList<Ingredient> getAllIngredients(){
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_INGREDIENTS, allIngredientColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Ingredient ingredient = cursorToIngredient(cursor);
            ingredients.add(ingredient);
            cursor.moveToNext();
        }

        cursor.close();
        return ingredients;
    }

    private Ingredient cursorToIngredient(Cursor cursor){
        Ingredient ingredient = new Ingredient();
        ingredient.setId(cursor.getLong(0));
        ingredient.setName(cursor.getString(1));

        // Format Name
        String name = ingredient.getName();
        String[] words = name.split(" ");
        name = "";
        for(int i = 0; i<words.length; i++){
            String word = words[i];
            if(word.length() == 0) continue;
            char firstChar = Character.toUpperCase(word.charAt(0));
            if(word.length() == 1) name += firstChar + "";
            else name += firstChar + word.substring(1);
            if(i+1<words.length) name += " ";
        }
        ingredient.setName(name);

        ingredient.setCategory(cursor.getString(2));
        return ingredient;
    }

    //      RECIPE SUBROUTINES
    public Recipe createRecipe(String name, String mealType, String ingredients, String ingredientsWithNumVal, String timeDisplay, long timeActual, String instructionsText, long calories, long rating){
        ContentValues values = new ContentValues();

        // TODO: check for null values to make sure null isn't displayed in the app.
        values.put(MySQLiteHelper.COLUMN_NAME, name);
        values.put(MySQLiteHelper.COLUMN_MEAL_TYPE, mealType);
        values.put(MySQLiteHelper.COLUMN_INGREDIENTS, ingredients);
        values.put(MySQLiteHelper.COLUMN_INGREDIENTS_WITH_NUM_VAL, ingredientsWithNumVal);
        values.put(MySQLiteHelper.COLUMN_TIME_DISPLAY, timeDisplay);
        values.put(MySQLiteHelper.COLUMN_TIME_ACTUAL, timeActual);
        values.put(MySQLiteHelper.COLUMN_INSTRUCTIONS_TEXT, instructionsText);
        values.put(MySQLiteHelper.COLUMN_CALORIES, calories);
        values.put(MySQLiteHelper.COLUMN_RATING, rating);

        long insertId = database.insert(MySQLiteHelper.TABLE_RECIPES, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_RECIPES, allRecipeColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Recipe newRecipe = cursorToRecipe(cursor);
        cursor.close();
        return newRecipe;
    }

    public Recipe getRecipe(long id){
        String[] whereArgs = { ""+id };
        String queryString =
                "SELECT * FROM " + MySQLiteHelper.TABLE_RECIPES + " WHERE " + MySQLiteHelper.COLUMN_ID + " = ?";
        Cursor cursor = database.rawQuery(queryString, whereArgs);
        cursor.moveToFirst();
        if(!cursor.isAfterLast()){
            return cursorToRecipe(cursor);
        }
        return null;
    }

    public ArrayList<Recipe> searchRecipes(String keyword){
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        String queryString = "SELECT * FROM " + MySQLiteHelper.TABLE_RECIPES + (keyword.length() == 0 ? "" : " WHERE " + MySQLiteHelper.COLUMN_NAME + " LIKE '%" + keyword + "%'" + " OR " + MySQLiteHelper.COLUMN_INGREDIENTS + " LIKE '%" + keyword + "%'");
        Cursor cursor = database.rawQuery(queryString, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Recipe recipe = cursorToRecipe(cursor);
            recipes.add(recipe);
            cursor.moveToNext();
        }

        cursor.close();
        return recipes;
    }

    public ArrayList<Recipe> searchRecipesIngredients(Ingredient[] keyword) {
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        String queryText = "";
        for (int i = 0; i < keyword.length - 1; i++) {
            queryText = queryText + "'%" + keyword[i].getName() + "%'" + " OR ";
        }
        queryText = queryText + "'%" + keyword[keyword.length - 1].getName() + "%'";

        String queryString = "SELECT * FROM " + MySQLiteHelper.TABLE_RECIPES + (keyword.length == 0 ? "" : " WHERE " + MySQLiteHelper.COLUMN_INGREDIENTS + " LIKE " + queryText);
        Cursor cursor = database.rawQuery(queryString, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Recipe recipe = cursorToRecipe(cursor);
            recipes.add(recipe);
            cursor.moveToNext();
        }

        cursor.close();
        sortBasedOnScore(keyword, recipes);
        return recipes;
    }

    public int score(Ingredient[] keyword, Recipe r) {
        int score = 0;
        String s = r.getIngredients();
        String[] split = s.split(",");

        for (int i = 0; i < keyword.length - 1; i++) {
            for (int j = 0; j < split.length - 1; j++) {
                if (keyword[i].getName().equals(split[j])) score++;
            }
        }
        return score;
    }

    public void sortBasedOnScore(Ingredient[] keyword, ArrayList<Recipe> recipes) {
        for (int i = 0; i < recipes.size() - 1 ; i++) {
            recipes.get(i).setScore(score(keyword, recipes.get(i)));
        }
        Collections.sort(recipes);

    }

    public ArrayList<Recipe> getAllRecipes(){
        return this.searchRecipes("");
    }

    private Recipe cursorToRecipe(Cursor cursor){
        Recipe recipe = new Recipe();
        recipe.setId(cursor.getLong(0));
        recipe.setName(cursor.getString(1));
        recipe.setMealType(cursor.getString(2));
        recipe.setIngredients(cursor.getString(3));

        String ingredientsWithNumVal = cursor.getString(4);

        String[] words = ingredientsWithNumVal.split(" ");

        ingredientsWithNumVal = "";
        for(int i = 0; i<words.length; i++){
            String word = words[i];
            if(word.length() == 0) continue;
            char firstChar = Character.toUpperCase(word.charAt(0));
            if(word.length() == 1) ingredientsWithNumVal += firstChar + "";
            else ingredientsWithNumVal += firstChar + word.substring(1);
            if(i+1<words.length) ingredientsWithNumVal += " ";
        }

        recipe.setIngredientsWithNumVal(ingredientsWithNumVal);

        recipe.setTimeDisplay(cursor.getString(5));
        recipe.setTimeActual(cursor.getLong(6));
        recipe.setInstructionsText(cursor.getString(7));
        recipe.setCalories(cursor.getLong(8));
        recipe.setRating(cursor.getLong(9));

        return recipe;
    }


    public void justCreated(){
        justMade = true;
    }

    public void populate() {

        // Populate Ingredients

        if(this.ingredientsList != null){
            for(ArrayList<String> list : ingredientsList){
                if(list != null){
                    Ingredient i = createIngredient(list.get(0), list.get(1));
                    Log.d(null, "Created Ingredient: " + i.toString());
                }
            }
        }

        if(this.recipesList != null){
            for(ArrayList<String> list : recipesList){
                if(list != null){
                    createRecipe(list.get(0),
                            list.get(1),
                            list.get(2),
                            list.get(3),
                            list.get(4),
                            new Long(list.get(5)),
                            list.get(6),
                            new Long(list.get(7)),
                            new Long(list.get(8)));
                }
            }
        }

        justMade = false;
    }
}
