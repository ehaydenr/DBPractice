package com.example.app;

/**
 * Created by ehaydenr on 3/17/14.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_RECIPES = "Recipes";
    public static final String TABLE_INGREDIENTS = "Ingredients";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_CATEGORY = "IngredientCategory";
    public static final String COLUMN_MEAL_TYPE = "MealType";
    public static final String COLUMN_INGREDIENTS = "Ingredients";
    public static final String COLUMN_INGREDIENTS_WITH_NUM_VAL = "IngredientsWithNumVal";
    public static final String COLUMN_TIME_DISPLAY = "TimeDisplay";
    public static final String COLUMN_TIME_ACTUAL = "TimeActual";
    public static final String COLUMN_INSTRUCTIONS_TEXT = "InstructionsText";
    public static final String COLUMN_CALORIES = "Calories";
    public static final String COLUMN_RATING = "Rating";

    private static final String DATABASE_NAME = "recipe_app.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static String CREATE_INGREDIENTS = "CREATE TABLE " + TABLE_INGREDIENTS + "(" +
            COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_NAME + " text not null, " +
            COLUMN_CATEGORY + " text not null" + ");";

    private static String CREATE_RECIPES = "CREATE TABLE " + TABLE_RECIPES + "(" +
            COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_NAME + " text not null, " +
            COLUMN_MEAL_TYPE + " text not null, " +
            COLUMN_INGREDIENTS + " text not null, " +
            COLUMN_INGREDIENTS_WITH_NUM_VAL + " text not null, " +
            COLUMN_TIME_DISPLAY + " text, " +
            COLUMN_TIME_ACTUAL + " integer, " +
            COLUMN_INSTRUCTIONS_TEXT + " text not null, " +
            COLUMN_CALORIES + " integer, " +
            COLUMN_RATING + " integer);";


    private DataSource dataSource;

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_INGREDIENTS);
        database.execSQL(CREATE_RECIPES);
        dataSource.justCreated();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        onCreate(db);
    }

    public void setSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
