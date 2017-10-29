package com.example.muhammad.bakingapp.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.muhammad.bakingapp.R;
import com.example.muhammad.bakingapp.RecyclerItemClickListener;
import com.example.muhammad.bakingapp.adapters.RecipeListAdapter;
import com.example.muhammad.bakingapp.models.Ingredients;
import com.example.muhammad.bakingapp.models.Recipe;
import com.example.muhammad.bakingapp.models.Steps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/*
* Created by Muhammad Attia on 19/06/2017.
* RecipesActivity for  get List of Recipes from json file
*/
public class RecipesActivity extends AppCompatActivity {

    private static final String TAG = RecipesActivity.class.getSimpleName();


    private RecyclerView recipeListRecyclerView;

    ArrayList<Recipe> recipeArrayList;
    RecipeListAdapter recipeListAdapter;
    public CountingIdlingResource countingIdlingResource = new CountingIdlingResource("Check recipe list");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        recipeListRecyclerView = (RecyclerView) findViewById(R.id.rv_recipe_list);
        recipeArrayList = new ArrayList<>();
        recipeListAdapter = new RecipeListAdapter(getApplicationContext(), recipeArrayList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,getResources().getInteger(R.integer.columns_in_grid));
        recipeListRecyclerView.setAdapter(recipeListAdapter);
        recipeListRecyclerView.setLayoutManager(gridLayoutManager);

        RequestQueue requestQueue = Volley.newRequestQueue(RecipesActivity.this);
        String url = getResources().getString(R.string.url);
        JsonArrayRequest jsonArrayRequest = getJsonArray(url);
        if (isInternetConnectionAvailable()) {
            countingIdlingResource.increment(); // for ui test
            requestQueue.add(jsonArrayRequest);
        } else {
            Toast toast = Toast.makeText(this, "Check your Connection!", Toast.LENGTH_LONG);
            toast.show();
        }
        recipeListRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recipeListRecyclerView, new RecyclerItemClickListener
                .OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int mItemPosition = recipeListRecyclerView.getChildLayoutPosition(view);
                Recipe recipe = recipeArrayList.get(mItemPosition);
                Intent intent = new Intent(RecipesActivity.this, IngredientsAndStepsActivity.class);
                intent.putExtra("Recipe", recipe);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));

    }

    private JsonArrayRequest getJsonArray(String url) {
        return new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                recipeArrayList = parseRecipeJson(response);
                recipeListAdapter = new RecipeListAdapter(getApplicationContext(), recipeArrayList);
                recipeListRecyclerView.setAdapter(recipeListAdapter);
                recipeListRecyclerView.invalidate();
                countingIdlingResource.decrement();
                Log.v(TAG, "ended idling resource");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Volley error response");
                Toast toast = Toast.makeText(RecipesActivity.this, "Check your Connection!", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    private ArrayList<Recipe> parseRecipeJson(JSONArray response) {
        ArrayList<Recipe> returnedRecipeList = new ArrayList<>();

        if (response != null) {
            try {
                ArrayList<Ingredients> ingredients;
                ArrayList<Steps> steps;

                for (int i = 0; i < response.length(); i++) {
                    JSONObject obj = response.getJSONObject(i);
                    Recipe recipe = new Recipe(obj);

                    JSONArray ingredientsJsonArray = obj.getJSONArray("ingredients");
                    JSONArray stepsJsonArray = obj.getJSONArray("steps");

                    ingredients = new ArrayList<>();
                    for (int j = 0; j < ingredientsJsonArray.length(); j++) {
                        JSONObject ingredientsObject = ingredientsJsonArray.getJSONObject(j);
                        Ingredients ingredient = new Ingredients(ingredientsObject);
                        ingredients.add(ingredient);
                    }
                    steps = new ArrayList<>();
                    for (int j = 0; j < stepsJsonArray.length(); j++) {
                        JSONObject stepsJsonObject = stepsJsonArray.getJSONObject(j);
                        Steps step = new Steps(stepsJsonObject);
                        steps.add(step);
                    }
                    returnedRecipeList.add(recipe);
                }
            } catch (JSONException e) {
                e.printStackTrace();

            }
        } else {
            Log.e(TAG, "Empty Json response !");
            Toast toast = Toast.makeText(RecipesActivity.this, "Check your Connection!", Toast.LENGTH_LONG);
            toast.show();
        }
        return returnedRecipeList;
    }

    //to check Internet Connection Status
    private boolean isInternetConnectionAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
