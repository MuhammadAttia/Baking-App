package com.example.muhammad.bakingapp.fragments;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.muhammad.bakingapp.R;
import com.example.muhammad.bakingapp.adapters.IngredientsAdapter;
import com.example.muhammad.bakingapp.adapters.StepsAdapter;
import com.example.muhammad.bakingapp.models.Ingredients;
import com.example.muhammad.bakingapp.models.Steps;

import java.util.ArrayList;

/**
 * Created by Muhammad Attia on 21/06/2017.
 * The fragment to hold the recycler view of the ingredients and steps of the recipe.
 */

public class IngredientsAndStepsFragment extends Fragment implements StepsAdapter.StepsOnClickListener {

    private ArrayList<Ingredients> ingredientArrayList;
    private ArrayList<Steps> stepsArrayList;


    RecyclerView ingredientsRecyclerView;
    NestedScrollView scrollView;


    RecyclerView stepsRecyclerView;
    TextView ingredientsTitle;

    TextView stepsTitle;

    //Empty constructor
    public IngredientsAndStepsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_ingredients_steps, container, false);

        scrollView = (NestedScrollView) rootView.findViewById(R.id.scrollview);
        ingredientArrayList = getArguments().getParcelableArrayList("Ingredients");
        stepsArrayList = getArguments().getParcelableArrayList("Steps");
        ingredientsTitle = (TextView) rootView.findViewById(R.id.tv_ingredients_title);
        stepsTitle = (TextView) rootView.findViewById(R.id.tv_steps_title);

            ingredientsRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_ingredients_list);
            ingredientsRecyclerView.setHasFixedSize(true);
            ingredientsRecyclerView.setNestedScrollingEnabled(false);
            ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(getContext(), ingredientArrayList);
            ingredientsRecyclerView.setAdapter(ingredientsAdapter);

            stepsRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_steps_list);
            stepsRecyclerView.setHasFixedSize(true);
            stepsRecyclerView.setNestedScrollingEnabled(false);
            stepsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            StepsAdapter stepsAdapter = new StepsAdapter(stepsArrayList, this);
            stepsRecyclerView.setAdapter(stepsAdapter);

        if (savedInstanceState == null)
            scrollView.smoothScrollTo(0, 0);

            return rootView;

    }
    @Override
    public void onStepItemClicked(int position) {
        IngredientStepsOnClickListener ingredientStepsOnClickListener =(IngredientStepsOnClickListener) getActivity();
        Bundle bundle = new Bundle();
        bundle.putString("Step URL",stepsArrayList.get(position).getmVideoURL());
        bundle.putString("Step Description",stepsArrayList.get(position).getmDescription());
        bundle.putString("Step thumbnail",stepsArrayList.get(position).getmThumbnailURL());
        ingredientStepsOnClickListener.onIngredientStepItemClicked(bundle);
        Log.v("STEPS FRAGMENTS","Position clicked");
    }


    public interface IngredientStepsOnClickListener{
        void onIngredientStepItemClicked(Bundle bundle);
    }
}
