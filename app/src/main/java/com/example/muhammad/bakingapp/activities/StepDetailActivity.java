package com.example.muhammad.bakingapp.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.muhammad.bakingapp.R;
import com.example.muhammad.bakingapp.fragments.StepsDetailsFragment;

/*
* Created by Muhammad Attia on 19/06/2017.
* Activity that will contain the video playing fragment on Phone
*/
public class StepDetailActivity extends AppCompatActivity {

    StepsDetailsFragment stepsDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            requestWindowFeature(Window.FEATURE_NO_TITLE); //Remove title
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            ActionBar actionBar = getSupportActionBar();
            actionBar.hide();
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setContentView(R.layout.activity_step_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Steps");

        if (savedInstanceState == null) {
            stepsDetailsFragment = new StepsDetailsFragment();
            stepsDetailsFragment.setArguments(getIntent().getExtras());
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.frame_fragment_video_description, stepsDetailsFragment).commit();
        }
    }
}
