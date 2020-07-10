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

public class ReviewFragment extends Fragment {

    RecyclerView recyclerView;
    Vector<YoutubeVideos> youtubeVideos = new Vector<>() ;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_review, container,false);

    recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));


        youtubeVideos.add (new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/93TFMs5nU_A\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add (new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/bShS2tia-aQ\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add (new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/2_XUd6VytxE\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add (new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/0CBMAA1hnTg\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add (new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/eWA5YIQ8RQM\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add (new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/XI0wsleDxUM\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add (new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/Gp0GCbD5m0E\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add (new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/oHR1Aw718nE\" frameborder=\"0\" allowfullscreen></iframe>") );



        VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);

        recyclerView.setAdapter(videoAdapter);

        return rootView;
    }
}

