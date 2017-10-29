package com.example.muhammad.bakingapp;

import android.support.test.rule.ActivityTestRule;

import com.example.muhammad.bakingapp.activities.RecipesActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.example.muhammad.bakingapp.RecyclerViewItemCountAssertion.withItemCount;

/**
 * Created by Muhammad Attia on 25/06/2017.
 */

public class RecycleViewItemCount {

    @Rule
    public ActivityTestRule<RecipesActivity> activityTestRule =
            new ActivityTestRule<>(RecipesActivity.class);

    @Test
    public void check(){
        onView(withId(R.id.rv_recipe_list)).check(withItemCount(4));
    }
}
