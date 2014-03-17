package com.example.app;

public class Ingredient {
    private long id;
    private String ingredient;
    private String type;

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
    // public void setIngredient(String comment) {
    //     this.ingredient = comment;
    // }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return ingredient;
    }
}