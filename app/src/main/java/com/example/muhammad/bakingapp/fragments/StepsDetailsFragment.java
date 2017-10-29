package com.example.muhammad.bakingapp.fragments;

import android.content.res.Configuration;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.muhammad.bakingapp.R;
import com.google.android.exoplayer2.DefaultLoadControl;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

/**
 * Created by Muhammad Attia on 20/06/2017
 * The fragment which plays the associated video and shows the detailed description.
 */

public class StepsDetailsFragment extends Fragment {

    private static final String TAG = "VideoFragment";

    SimpleExoPlayerView videoStepView;

    String videoUrl;
    String description;
    String thumbnailUrl;
    long playbackPosition;
    int currentWindow;
    boolean playWhenReady;
    private SimpleExoPlayer mSimpleExoPlayer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_video_description, container, false);

        Bundle bundle = getArguments();
        videoUrl = bundle.getString("Step URL");
        description = bundle.getString("Step Description");
        thumbnailUrl = bundle.getString("Step thumbnail");
        //Only for portrait-phone and tablet

        if (rootView.findViewById(R.id.tv_title_description_step) != null) {
            TextView descriptionStepTitle = (TextView) rootView.findViewById(R.id.tv_title_description_step);
            if (!description.equals("")) {
                TextView descriptionStep = (TextView) rootView.findViewById(R.id.tv_description_step);
                descriptionStep.setText(description);
            } else {
                descriptionStepTitle.setVisibility(View.GONE);
            }
        }


        videoStepView = (SimpleExoPlayerView) rootView.findViewById(R.id.view_video_step);


        if (savedInstanceState !=null){
            currentWindow = savedInstanceState.getInt("currentWindow");
            playWhenReady = savedInstanceState.getBoolean("playWhenReady");
            playbackPosition = savedInstanceState.getLong("playbackPosition");
        }


            if ("".equals(videoUrl) || videoUrl == null) {
                videoStepView.setVisibility(View.GONE);
                final ImageView thumbnail = (ImageView) rootView.findViewById(R.id.iv_thumbnail_description_step);
                thumbnail.setVisibility(View.VISIBLE);
                if ("".equals(thumbnailUrl) || thumbnailUrl == null) {
                    return rootView;
                } else {
                    Glide.with(getContext()).load(thumbnailUrl).into(thumbnail);
                }
                if (getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    Toast toast = Toast.makeText(getContext(), "No Video Available", Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            else {
                //Initialize the media player
                initializePlayer(Uri.parse(videoUrl));
            }

            return rootView;

    }
    @Override
    public void onStop() {
        if (mSimpleExoPlayer != null) {
            mSimpleExoPlayer.stop();
            mSimpleExoPlayer.release();
            mSimpleExoPlayer = null;
        }
        super.onStop();
    }

    public void initializePlayer(Uri videoUri){
        TrackSelector trackSelector = new DefaultTrackSelector();
        LoadControl loadControl = new DefaultLoadControl();
        mSimpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
        videoStepView.setPlayer(mSimpleExoPlayer);

        String userAgent = Util.getUserAgent(getActivity(), "BakingAppExoPlayer");
        MediaSource mediaSource = new ExtractorMediaSource(videoUri, new DefaultDataSourceFactory(
                getActivity(), userAgent), new DefaultExtractorsFactory(), null, null);
        mSimpleExoPlayer.prepare(mediaSource);
    }
}
