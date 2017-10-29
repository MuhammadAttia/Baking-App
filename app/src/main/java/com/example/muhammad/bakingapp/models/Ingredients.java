package com.example.muhammad.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

/**
 * Created by Muhammad Attia on 17/06/2017.
 * Ingredients Class
 */


public class Ingredients implements Parcelable {
    private double mQuantity;
    private String mMeasure;
    private String mIngredient;

    public Ingredients(JSONObject ingredientJason) {
        this.mQuantity = ingredientJason.optDouble("quantity");
        this.mMeasure = ingredientJason.optString("measure");
        this.mIngredient = ingredientJason.optString("ingredient");
    }


    public double getmQuantity() {
        return mQuantity;
    }

    public String getmMeasure() {
        return mMeasure;
    }


    public String getmIngredient() {
        return mIngredient;
    }

    private Ingredients(Parcel in) {
        mQuantity = in.readDouble();
        mMeasure = in.readString();
        mIngredient = in.readString();
    }

    public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel in) {
            return new Ingredients(in);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(mQuantity);
        parcel.writeString(mMeasure);
        parcel.writeString(mIngredient);
    }
}