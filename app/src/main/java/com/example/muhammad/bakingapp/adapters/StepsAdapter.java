package com.example.muhammad.bakingapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.muhammad.bakingapp.R;
import com.example.muhammad.bakingapp.models.Recipe;
import com.example.muhammad.bakingapp.models.Steps;

import java.util.ArrayList;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {

    private ArrayList<Steps> stepsArrayList;
    private StepsOnClickListener stepsOnClickListener;

    public StepsAdapter(ArrayList<Steps> stepsArrayList, StepsOnClickListener stepsOnClickListener){
        this.stepsArrayList=stepsArrayList;
        this.stepsOnClickListener = stepsOnClickListener;
    }

    @Override
    public StepsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.step_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.stepShortDescription.setText(stepsArrayList.get(position).getmShortDescription());

    }

    @Override
    public int getItemCount() {
        return stepsArrayList.size();
    }

    public void resetData(ArrayList<Steps> steps){
        this.stepsArrayList = steps;
        notifyDataSetChanged();
    }

    public interface StepsOnClickListener{
        void onStepItemClicked(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView stepShortDescription;

        public ViewHolder(View view){
            super(view);
            stepShortDescription = view.findViewById(R.id.view_holder_steps_title);
            stepShortDescription.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            stepsOnClickListener.onStepItemClicked(getAdapterPosition());
        }
    }
}
