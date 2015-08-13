package com.example.iidea8.k_lit;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


public class Gallery extends Fragment {
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gallery, container, false);
        GridView gridview = (GridView) view.findViewById(R.id.gallery_gridView);
        gridview.setAdapter(new GalleryAdapter(getActivity()));

        // Listening to GridView item click
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // Launch ImageViewPager.java on selecting GridView Item
                Intent i = new Intent(getActivity().getApplicationContext(), GalleryViewPager.class);

                // Send the click position to ImageViewPager.java using intent
                i.putExtra("id", position);

                // Start ImageViewPager
                startActivity(i);
            }
        });
        setRetainInstance(true);
        return view;
    }
}
