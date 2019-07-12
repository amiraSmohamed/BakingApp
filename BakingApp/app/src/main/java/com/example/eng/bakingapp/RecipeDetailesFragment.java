package com.example.eng.bakingapp;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

public class RecipeDetailesFragment extends Fragment {
    View rootView;
    PlayerView playerView;
    TextView shortDescriptionTV;
    TextView descriptionTV;
    SimpleExoPlayer player;
    Step steps = null;
    ArrayList<String> shortDescription = new ArrayList<>();
    ArrayList<String> description = new ArrayList<>();
    ArrayList<String> urlVedio = new ArrayList<>();
    int position;
    long playbackPosition;
    int currentWindow;
    boolean playWhenReady;
    ImageView placeholderImage;
    public RecipeDetailesFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.recipe_detailes_fragment, container, false);
        playerView = rootView.findViewById(R.id.player_view);
        placeholderImage = rootView.findViewById(R.id.placeholder_image);
        shortDescriptionTV = rootView.findViewById(R.id.short_desc);
        descriptionTV = rootView.findViewById(R.id.description_tv);
        Bundle arguments = getArguments();
        if (arguments != null) {
            steps = (Step) arguments.getSerializable("steps");
            Log.v("steps", "is not null in detail" + steps.getShortDescription());
        } else {
            Log.v("stepsis ", "null");
        }
        shortDescriptionTV.setText(steps.getShortDescription());
        descriptionTV.setText(steps.getDescription());
        Log.v("stepurl", steps.getVideoURL());
        if ( steps.getVideoURL() != null && !steps.getVideoURL().isEmpty() && steps.getVideoURL() != "" ) {
            Log.v("gettingVedioURL",steps.getVideoURL());
            player = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultTrackSelector());
            playerView.setPlayer(player);
//            player.seekTo(1);
            DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(getContext(), com.google.android.exoplayer2.util.Util.getUserAgent(getContext(), "ExoPlayer"));
            ExtractorMediaSource extractorMediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(steps.getVideoURL()));
            player.prepare(extractorMediaSource, true, false);
            player.setPlayWhenReady(true);
        }
        else if (!steps.getThumbnailURL().isEmpty() && steps.getThumbnailURL() != null && steps.getThumbnailURL() !=""){
            Log.v("gettingThumbnail",steps.getThumbnailURL());
            playerView = rootView.findViewById(R.id.player_view);
            player = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultTrackSelector());
            playerView.setPlayer(player);
//            player.seekTo(1);
            DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(getContext(), com.google.android.exoplayer2.util.Util.getUserAgent(getContext(), "ExoPlayer"));
            ExtractorMediaSource extractorMediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(steps.getThumbnailURL()));
            player.prepare(extractorMediaSource, true, false);
            player.setPlayWhenReady(true);
              }
        else {
            Log.v("VedioandThumbnail","are null");
            playerView.setVisibility(View.GONE);
//            hideSystemUi();
            placeholderImage.setImageResource(R.drawable.errorimage);
            placeholderImage.setVisibility(View.VISIBLE);
            Toast.makeText(getContext(),"There is no vedio for this step",Toast.LENGTH_SHORT).show();
        }


        if(savedInstanceState !=null){
            int mcurrentwindow = savedInstanceState.getInt("currentWindow");
            long mplaybackPosition = savedInstanceState.getLong("position");
            Step steps = (Step) savedInstanceState.getSerializable("steps");
            shortDescriptionTV.setText(steps.getShortDescription());
            descriptionTV.setText(steps.getDescription());
            Log.v("stepurl", steps.getVideoURL());
            if (steps.getVideoURL() != null && !steps.getVideoURL().isEmpty() && steps.getVideoURL() != "") {
                Log.v("gettingVedioURL",steps.getVideoURL());
                playerView = rootView.findViewById(R.id.player_view);
                player = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultTrackSelector());
                playerView.setPlayer(player);
                player.seekTo(mcurrentwindow,mplaybackPosition);
                DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(getContext(), com.google.android.exoplayer2.util.Util.getUserAgent(getContext(), "ExoPlayer"));
                ExtractorMediaSource extractorMediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(steps.getVideoURL()));
                player.prepare(extractorMediaSource, true, false);
                player.setPlayWhenReady(true);
            }

            else if (!steps.getThumbnailURL().isEmpty() && steps.getThumbnailURL() != null && steps.getThumbnailURL() !="" && !steps.getThumbnailURL().equals("null")){
                Log.v("gettingThumbnail",steps.getThumbnailURL());
                playerView = rootView.findViewById(R.id.player_view);
                player = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultTrackSelector());
                playerView.setPlayer(player);
                player.seekTo(mcurrentwindow,mplaybackPosition);
                DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(getContext(), com.google.android.exoplayer2.util.Util.getUserAgent(getContext(), "ExoPlayer"));
                ExtractorMediaSource extractorMediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(steps.getThumbnailURL()));
                player.prepare(extractorMediaSource, true, false);
                player.setPlayWhenReady(true);
            }
            else {
                Log.v("VedioandThumbnail","are null");
                playerView.setVisibility(View.GONE);
                placeholderImage.setImageResource(R.drawable.errorimage);
                placeholderImage.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(),"There is no vedio for this step",Toast.LENGTH_SHORT).show();
            }
        }
        //initializePlayer();
        return rootView;

    }
    private void initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getContext()),
                new DefaultTrackSelector(), new DefaultLoadControl());
        playerView.setPlayer(player);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        Uri uri = Uri.parse(steps.getVideoURL());
        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource, true, false);
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("exoplayer-codelab")).
                createMediaSource(uri);
    }

    @Override
    public void onStart() {
        super.onStart();
        initializePlayer();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (player == null) {
            initializePlayer();
        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    public void onPause() {

        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }
    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("steps",steps);
        outState.putInt("currentWindow",currentWindow);
        outState.putLong("position",playbackPosition);
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        if(savedInstanceState !=null){
//            int mcurrentwindow = savedInstanceState.getInt("currentWindow");
//            long mplaybackPosition = savedInstanceState.getLong("position");
//            Step steps = (Step) savedInstanceState.getSerializable("steps");
//            shortDescriptionTV.setText(steps.getShortDescription());
//            descriptionTV.setText(steps.getDescription());
//            Log.v("stepurl", steps.getVideoURL());
//            if (steps.getVideoURL() != null && !steps.getVideoURL().isEmpty() && steps.getVideoURL() != "") {
//                Log.v("gettingVedioURL",steps.getVideoURL());
//                playerView = rootView.findViewById(R.id.player_view);
//                player = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultTrackSelector());
//                playerView.setPlayer(player);
//                player.seekTo(mcurrentwindow,mplaybackPosition);
//                DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(getContext(), com.google.android.exoplayer2.util.Util.getUserAgent(getContext(), "ExoPlayer"));
//                ExtractorMediaSource extractorMediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(steps.getVideoURL()));
//                player.prepare(extractorMediaSource, true, false);
//                player.setPlayWhenReady(true);
//            }
//
//            else if (!steps.getThumbnailURL().isEmpty() && steps.getThumbnailURL() != null && steps.getThumbnailURL() !="" && !steps.getThumbnailURL().equals("null")){
//                Log.v("gettingThumbnail",steps.getThumbnailURL());
//                playerView = rootView.findViewById(R.id.player_view);
//                player = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultTrackSelector());
//                playerView.setPlayer(player);
//                player.seekTo(mcurrentwindow,mplaybackPosition);
//                DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(getContext(), com.google.android.exoplayer2.util.Util.getUserAgent(getContext(), "ExoPlayer"));
//                ExtractorMediaSource extractorMediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(steps.getThumbnailURL()));
//                player.prepare(extractorMediaSource, true, false);
//                player.setPlayWhenReady(true);
//            }
//            else {
//                Log.v("VedioandThumbnail","are null");
//                playerView.setVisibility(View.GONE);
//                placeholderImage.setImageResource(R.drawable.errorimage);
//                placeholderImage.setVisibility(View.VISIBLE);
//                Toast.makeText(getContext(),"There is no vedio for this step",Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
}
