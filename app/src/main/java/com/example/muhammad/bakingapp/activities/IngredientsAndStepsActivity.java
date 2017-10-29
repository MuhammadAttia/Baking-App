package com.example.muhammad.bakingapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.muhammad.bakingapp.R;
import com.example.muhammad.bakingapp.fragments.IngredientsAndStepsFragment;
import com.example.muhammad.bakingapp.fragments.StepsDetailsFragment;
import com.example.muhammad.bakingapp.models.Ingredients;
import com.example.muhammad.bakingapp.models.Recipe;
import com.example.muhammad.bakingapp.models.Steps;

import java.util.ArrayList;

/*
* Created by Muhammad Attia on 19/06/2017.
* IngredientsAndStepsActivity for  List all ingredients and steps for each Steps
*/

public class IngredientsAndStepsActivity extends AppCompatActivity implements IngredientsAndStepsFragment.IngredientStepsOnClickListener {

    Recipe recipe;
    Fragment ingredientsAndStepsFragment;
    StepsDetailsFragment stepsDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients_and_steps);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recipe = getIntent().getParcelableExtra("Recipe");
        ArrayList<Ingredients> ingredientArrayList = recipe.getmRecipeIngredientArrayList();
        ArrayList<Steps> stepsArrayList = recipe.getmRecipeStepsArrayList();

        if (savedInstanceState != null) {
            Log.d("Saved", "onCreate: Retrieve Details Activity here");
            //Restore the fragment's instance
            ingredientsAndStepsFragment = getSupportFragmentManager().getFragment(savedInstanceState,"steps");
        }else {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("Ingredients", ingredientArrayList);
            bundle.putParcelableArrayList("Steps", stepsArrayList);
            ingredientsAndStepsFragment = new IngredientsAndStepsFragment();
            ingredientsAndStepsFragment.setArguments(bundle);

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.frame_fragment_ingredients_steps, ingredientsAndStepsFragment).commit();
            getSupportActionBar().setTitle(recipe.getmName());
        }

        //For tablet
        if (findViewById(R.id.frame_fragment_video_description) != null) {
            stepsDetailsFragment = new StepsDetailsFragment();
            Bundle bundleVideo = new Bundle();
            bundleVideo.putString("Step URL", stepsArrayList.get(0).getmVideoURL());
            bundleVideo.putString("Step Description", stepsArrayList.get(0).getmDescription());
            stepsDetailsFragment.setArguments(bundleVideo);
            FragmentTransaction videoFragmentTransaction = getSupportFragmentManager().beginTransaction();
            videoFragmentTransaction.add(R.id.frame_fragment_video_description, stepsDetailsFragment).commit();
        }
    }

    @Override
    public void onIngredientStepItemClicked(Bundle bundle) {
        //For phone
        if (findViewById(R.id.frame_fragment_video_description) == null) {
            Intent intent = new Intent(this, StepDetailActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        } else {
            //for tablets
            stepsDetailsFragment = new StepsDetailsFragment();
            stepsDetailsFragment.setArguments(bundle);
            android.support.v4.app.FragmentTransaction videoFragmentTransaction = getSupportFragmentManager().beginTransaction();
            videoFragmentTransaction.replace(R.id.frame_fragment_video_description, stepsDetailsFragment).commit();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


}
