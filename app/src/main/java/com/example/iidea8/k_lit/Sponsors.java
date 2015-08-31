package com.example.iidea8.k_lit;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


public class Sponsors extends Fragment {
    View view;
    GridView gridView;
    SponsorsAdapter sponsorsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sponsors, container, false);
        gridView = (GridView) view.findViewById(R.id.gallery_grid_view);
        sponsorsAdapter = new SponsorsAdapter(getActivity().getBaseContext());
        gridView.setAdapter(sponsorsAdapter);
        setRetainInstance(true);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("Sponsors");
    }
}
