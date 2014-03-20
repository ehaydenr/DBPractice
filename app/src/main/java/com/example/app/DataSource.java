package com.example.app;

/**
 * Created by ehaydenr on 3/17/14.
 */
import java.util.ArrayList;
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

    public boolean justMade;

    public DataSource(Context context, String sql) {
        dbHelper = new MySQLiteHelper(context, sql);
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

    public List<Ingredient> getAllIngredients(){
        List<Ingredient> ingredients = new ArrayList<Ingredient>();

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

    public List<Recipe> getAllRecipes(){
        List<Recipe> recipes = new ArrayList<Recipe>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_RECIPES, allRecipeColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Recipe recipe = cursorToRecipe(cursor);
            recipes.add(recipe);
            cursor.moveToNext();
        }

        cursor.close();
        return recipes;
    }

    private Recipe cursorToRecipe(Cursor cursor){
        Recipe recipe = new Recipe();
        recipe.setId(cursor.getLong(0));
        recipe.setName(cursor.getString(1));
        recipe.setMealType(cursor.getString(2));
        recipe.setIngredients(cursor.getString(3));
        recipe.setIngredientsWithNumVal(cursor.getString(4));
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
        createIngredient("cornstarch", "Wet Ingredients");
        createIngredient("water", "Other");
        createIngredient("garlic powder", "Spices");
        createIngredient("ground round steak", "Meat");
        createIngredient("vegetable oil", "Wet Ingredients");
        createIngredient("broccoli flourets", "Vegetables");
        createIngredient("onion", "Vegetables");
        createIngredient("reduced sodium soy sauce", "Wet Ingredients");
        createIngredient("brown sugar", "Dry Ingredients");
        createIngredient( "ground ginger", "Spices");
        createIngredient( "cooked rice", "Grains");
        createIngredient( "chicken stove top stuffing mix", "Other");
        createIngredient( "milk", "Dairy");
        createIngredient( "ham", "Meat");
        createIngredient( "eggs", "Dairy");
        createIngredient( "cheddar cheese", "Dairy");
        createIngredient( "salt", "Dry Ingredients");
        createIngredient( "boneless chicken breasts", "Meat");
        createIngredient( "unsweetened canned pineapple c", "Fruits");
        createIngredient( "soy sauce", "Wet Ingredients");
        createIngredient( "dry mustard", "Spices");
        createIngredient( "fresh ground pepper", "Vegetables");
        createIngredient( "green pepper", "Vegetables");
        createIngredient( "mushrooms", "Vegetables");
        createIngredient( "cherry tomatoes", "Vegetables");
        createIngredient( "roma tomatoes", "Vegetables");
        createIngredient( "garlic cloves", "Spices");
        createIngredient( "olive oil", "Wet Ingredients");
        createIngredient( "balsamic vinegar ", "Wet Ingredients");
        createIngredient( "basil", "Spices");
        createIngredient( "fresh cracker pepper", "Spices");
        createIngredient( "italian bread", "Grains");
        createIngredient( "parmigiano-reggiano cheese", "Dairy");
        createIngredient( "meatballs", "Meat");
        createIngredient( "spaghetti sauce", "Canned Ingredients");
        createIngredient( "cream cheese", "Dairy");
        createIngredient( "mayonnaise ", "Other");
        createIngredient( "black pepper", "Spices");
        createIngredient( "italian seasoning", "Spices");
        createIngredient( "mozzarella cheese", "Dairy");

        // Populate Recipes
        createRecipe("Beef and Broccoli Stir Fry", "Dinner", "cornstarch,water,garlic powder,ground round steak,vegetable oil,broccoli flourets,onion,reduced sodium soy sauce,brown sugar,ground ginger,cooked rice", "", "20-30min", 25, "1. In a bowl, combine 2 tablespoons cornstarch, 2 tablespoons water and garlic powder until smooth.\r\n2. Add 1 lbs beef (cut into strips) and toss.\r\n3. In a large skillet or wok over medium high heat, stir-fry beef in 1 tablespoon oil until beef reaches desired doneness; remove and keep warm.\r\n4. Stir-fry 4 cups broccoli and 1 sliced small onion in remaining oil for 4-5 minutes.\r\n5. Return beef to pan.\r\n6. Combine 1/3 cup soy sauce, 2 Tablespoons brown sugar, 1 teaspoon ginger, 1 Tablespoon cornstarch and 2 cups water until smooth; add to the pan.\r\n7. Cook and stir for 2 minutes.\r\n8. Serve over rice.\r\n", 150, 5);
        createRecipe("Breakfast Casserole", "Breakfast", "chicken stove top stuffing mix,milk,ham,eggs,cheddar cheese,salt", "", "30+min", 65, "1. Preheat oven to 350 degrees Fahrenheit.\r\n2. Lightly grease a 9 x 12 baking dish.\r\n3. Mix all the ingredients together (2 cups stuffing mix, 3/2 cup cubed ham, 6 eggs, 1 cup shredded cheese, and a 1/2 teaspoon salt).\r\n4. Pour into prepared baking dish.\r\n5. Bake for about 45 to 50 minutes or until set.", 200, 5);
        createRecipe("Hawaiian Chicken Kebabs", "Dinner", "boneless chicken breasts,unsweetened canned pineapple chunks,soy sauce,vegetable oil,brown sugar,garlic powder,ground ginger,dry mustard,fresh ground pepper,green pepper,mushrooms,cherry tomatoes,cooked rice", "", "30+min", 32, "1. Put 3/2 lbs chicken in large shallow dish.\r\n2. Drain 1 15.25 oz can of pineapple, keep 1/2 cup juice.\r\n3. Set pineapple aside.\r\n4. Mix juice with the next 7 ingredients(1/2 cup soy sauce, 1/4 cup vegetable oil, 1 tablespoon brown sugar, 1 teaspoon garlic powder, 2 teaspoons ground ginger, 1 teaspoon dry mustard, 1/4 teaspoon fresh ground pepper, ) in small pan.\r\n5. Bring to a boil.\r\n6. Reduce heat and simmer for 5 minutes.\r\n7. Pour over chicken.\r\n8. Cover and chill for 1 hour.\r\n9. Remove chicken from marinade, reserve marinade.\r\n10. Alternate chicken, pineapple, 1 in pieces of green pepper, 12 medium mushrooms, and 18 tomatoes on skewers.\r\n11. Grill kabobs over hot coals 20 minutes or until chicken is done.\r\n12. Turn and baste frequently with marinade.\r\n13. Serve over hot rice.", 370, 5);
        createRecipe("", "Breakfast", "Test Ingredient,asdfasdf", "", "<5min", 0, "", 0, 0);
        createRecipe("Tomato and Basil Bruschetta", "Snack/Appetizer", "roma tomatoes,garlic cloves,olive oil,balsamic vinegar ,basil,salt,fresh cracker pepper,Italian Bread,parmigiano-reggiano cheese", "", "30+min", 45, "1. Whisk together 2 cloves chopped garlic, 2 1/4 teaspoons vinegar, 1/2 teaspoon salt, 1/4 teaspoon pepper, and 2 tablespoons chopped basil. 2. When combined slowly drizzle in 3 tablespoons olive oil. 3. Add 6 diced tomatoes and let sit for 20 minutes at room temp. 4. Toast the 1 in slices of bread. This can be done either in the toaster or under the broiler. 6. When the bread is toasted rub each piece, on one side, with the whole garlic pieces. 7. Place the bread on a cookie sheet and top with tomato mixture. 8. Sprinkle on a 2 tablespoons cheese and broil till the cheese melts. 9. Serve immediately.", 235, 5);
        createRecipe("Meatball Sandwiches", "Lunch", "meatballs,italian bread,spaghetti sauce,cream cheese,mayonnaise ,black pepper,italian seasoning,garlic powder,mozzarella cheese", "", "30+min", 45, "1. In a large pot combine 2 lbs meatballs and one jar of spaghetti sauce.\r\n2. Heat over medium until meatballs are heated throughout.\r\n3. Meanwhile, mix together one 8 oz package of cream cheese, 1/2 cup mayonnaise, 1 Tablespoon Italian seasoning, 1/4 teaspoon pepper, and a dash of garlic powder and set aside.\r\n4. Slice Italian style bread loaves horizontally with serrated bread knife.\r\n5. Cut loaves into three- or four-inch sections to make small sandwiches.\r\n6. Spread cream cheese mixture on insides of each sandwiche's tops and bottoms.\r\n7. Preheat oven to 350°F.\r\n8. When meatballs are heated, spoon onto sandwiches and top with shredded mozzarella cheese.\r\n9. Put sandwich tops on and place on a foil-lined baking sheet.\r\n10. Bake about 10-15 minutes or until cheese is melted.\r\n", 1955, 3);



        justMade = false;
    }
}
