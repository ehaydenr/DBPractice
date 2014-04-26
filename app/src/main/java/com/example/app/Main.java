package com.example.app;

import android.app.Application;
import android.graphics.Typeface;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by ehaydenr on 4/17/14.
 */
public class Main extends Application {

    public static DataSource datasource;
    public static Typeface typeface;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(null, "Main Running");

        // Extract JSON from file and import into array
        Gson g = new Gson();
        Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
        ArrayList<ArrayList<String>> ingredientsList = g.fromJson(readFromFile(R.raw.ingredients), type);
        ArrayList<ArrayList<String>> recipeList = g.fromJson(readFromFile(R.raw.recipes), type);

        typeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");

        datasource = new DataSource(this, ingredientsList, recipeList);
        datasource.open();
    }

    private String readFromFile(int resource) {

        String ret = "";

        try {
            InputStream inputStream = this.getResources().openRawResource(resource);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
