package com.example.muhammad.bakingapp.models;


import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Muhammad Attia on 17/06/2017.
 * Recipe Class
 */


public class Recipe implements Parcelable {


    private int mID;
    private String mName;
    private ArrayList<Ingredients> mRecipeIngredientArrayList;
    private ArrayList<Steps> mRecipeStepsArrayList;
    private int servings;


    public Recipe(JSONObject recipeJason) {
        this.mID = recipeJason.optInt("id");

        this.mName = recipeJason.optString("name");
        this.mRecipeIngredientArrayList = new ArrayList<>();
        JSONArray ingredientsJA = recipeJason.optJSONArray("ingredients");
        for (int i = 0; i < ingredientsJA.length(); i++) {
            mRecipeIngredientArrayList.add(new Ingredients(ingredientsJA.optJSONObject(i)));
        }
        this.mRecipeStepsArrayList = new ArrayList<>();
        JSONArray stepsJA = recipeJason.optJSONArray("steps");
        for (int i = 0; i < stepsJA.length(); i++) {
            mRecipeStepsArrayList.add(new Steps(stepsJA.optJSONObject(i)));
        }
        this.servings = recipeJason.optInt("servings");
    }


    public int getmID() {
        return mID;
    }

    public String getmName() {
        return mName;
    }

    public ArrayList<Ingredients> getmRecipeIngredientArrayList() {
        return mRecipeIngredientArrayList;
    }

    public ArrayList<Steps> getmRecipeStepsArrayList() {
        return mRecipeStepsArrayList;
    }

    public int getServings() {
        return servings;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mID);
        dest.writeString(this.mName);
        dest.writeTypedList(this.mRecipeIngredientArrayList);
        dest.writeTypedList(this.mRecipeStepsArrayList);
        dest.writeInt(this.servings);
    }

    protected Recipe(Parcel in) {
        this.mID = in.readInt();
        this.mName = in.readString();
        this.mRecipeIngredientArrayList = in.createTypedArrayList(Ingredients.CREATOR);
        this.mRecipeStepsArrayList = in.createTypedArrayList(Steps.CREATOR);
        this.servings = in.readInt();
    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}