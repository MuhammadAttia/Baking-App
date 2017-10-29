package com.example.muhammad.bakingapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.muhammad.bakingapp.R;
import com.example.muhammad.bakingapp.models.Recipe;

import java.util.ArrayList;


public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder> {

    private ArrayList<Recipe> recipeArrayList;
    private Context mContext;
    public RecipeListAdapter(Context mContext , ArrayList<Recipe> recipeArrayList){
        this.mContext =mContext;
        this.recipeArrayList=recipeArrayList;
    }
    private Context getContext() {
        return mContext;
    }

    @Override
    public RecipeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_item,parent,false);
        RecipeListViewHolder holder = new RecipeListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecipeListViewHolder holder, int position) {
        int recipeId = recipeArrayList.get(position).getmID();

        switch (recipeId){
            case 1:
                holder.recipeimage.setImageResource((R.drawable.nutalla));
                break;
            case 2:
                holder.recipeimage.setImageResource((R.drawable.brownies));
                break;
            case 3:
                holder.recipeimage.setImageResource((R.drawable.yellow_cake));
                break;
            case 4:
                holder.recipeimage.setImageResource((R.drawable.cheesecake));
                break;
            default:
                holder.recipeimage.setImageResource((R.drawable.nutalla));
        }
        String name = recipeArrayList.get(position).getmName();
        holder.recipeName.setText(name);

        String servingsString = "Serves "+ recipeArrayList.get(position).getServings() + " People";
        holder.recipeServings.setText(servingsString);
    }

    @Override
    public int getItemCount() {
        return recipeArrayList.size();
    }

    public void resetData(ArrayList<Recipe> recipes){
        this.recipeArrayList = recipes;
        notifyDataSetChanged();
    }

    //Interface to handle onClickListener on the recyclerview

    public class RecipeListViewHolder extends RecyclerView.ViewHolder {

        private ImageView recipeimage;
        private TextView recipeName;
        private TextView recipeServings;

        public RecipeListViewHolder(View view){
            super(view);
            recipeimage =view.findViewById(R.id.recipe_image);
            recipeName = view.findViewById(R.id.tv_recipe_name);
            recipeServings = view.findViewById(R.id.tv_number_servings);

        }


    }

}
