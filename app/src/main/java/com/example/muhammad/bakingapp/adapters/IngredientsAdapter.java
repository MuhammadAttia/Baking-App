package com.example.muhammad.bakingapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.muhammad.bakingapp.R;
import com.example.muhammad.bakingapp.models.Ingredients;
import com.example.muhammad.bakingapp.models.Steps;

import java.util.ArrayList;


public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {

    private ArrayList<Ingredients> ingredientArrayList =new ArrayList<>();
    private Context mContext;



    public IngredientsAdapter(Context mContext,ArrayList<Ingredients> ingredientArrayList){
        this.mContext=mContext;
        this.ingredientArrayList=ingredientArrayList;
    }
    private Context getContext() {
        return mContext;
    }


    @Override
    public IngredientsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.ingredient_item,parent,false);
        IngredientsViewHolder holder = new IngredientsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(IngredientsViewHolder holder, int position) {
        holder.ingredientName.setText(ingredientArrayList.get(position).getmIngredient());
        String quantity = ingredientArrayList.get(position).getmQuantity() + " "+ ingredientArrayList.get(position).getmMeasure();
        holder.ingredientQuantity.setText(quantity);
    }

    @Override
    public int getItemCount() {
        return ingredientArrayList.size();
    }

    public void resetData(ArrayList<Ingredients> ingredients){
        this.ingredientArrayList = ingredients;
        notifyDataSetChanged();
    }


    public class IngredientsViewHolder extends RecyclerView.ViewHolder{

        private TextView ingredientName;
        private TextView ingredientQuantity;

        public IngredientsViewHolder(View view){
            super(view);
            ingredientName = view.findViewById(R.id.view_holder_ingredient_name);
            ingredientQuantity = view.findViewById(R.id.view_holder_ingredient_quantity);
        }
    }
}
