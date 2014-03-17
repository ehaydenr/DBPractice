package com.example.app;

/**
 * Created by ehaydenr on 3/17/14.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_INGREDIENTS = "Ingredients";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_CATEGORY = "IngredientCategory";


    private static final String DATABASE_NAME = "commments.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static String DATABASE_CREATE = "CREATE TABLE " + TABLE_INGREDIENTS + "(" + COLUMN_ID +
            " integer primary key autoincrement, " + COLUMN_NAME +
            " text not null, " + COLUMN_CATEGORY +
            " text not null" +
            ");";
    private IngredientsDataSource ingredientsDataSource;

    public MySQLiteHelper(Context context, String sql) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //this.DATABASE_CREATE = sql;
        //Log.d(null, "constant update");
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.d(null, "Going to create database");
        database.execSQL(DATABASE_CREATE);
        ingredientsDataSource.justCreated();
        Log.d(null, "Create executed");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENTS);
        onCreate(db);
    }

    public void setSource(IngredientsDataSource ingredientsDataSource) {
        this.ingredientsDataSource = ingredientsDataSource;
    }
}
