package com.example.YouTubeHugs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class QuizFragment extends Fragment {

    RecyclerView recyclerView;
    Vector<YoutubeVideos> youtubeVideos = new Vector<>() ;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_chart, container,false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));


        youtubeVideos.add (new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/UCEH2sbR5dupZ1dLt8ShQJ1g\" frameborder=\"0\" allowfullscreen></iframe>") );




        VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);

        recyclerView.setAdapter(videoAdapter);

        return rootView;
    }
}

