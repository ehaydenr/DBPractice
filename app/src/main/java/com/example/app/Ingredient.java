package com.example.app;

public class Ingredient {
    private long id;
    private String ingredient;
    private String type;
    private String[] category;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return ingredient;
    }

    public void setName(String comment) {
        this.ingredient = comment;
    }

    public void setCategory(String[] arr){
        this.category = arr;
    }

    public String[] getCategory(){
        return this.category;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return ingredient;
    }
}