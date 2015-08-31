package com.example.iidea8.k_lit;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Itinerary extends Fragment {
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_itinerary, container, false);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("Itinerary");
    }
}
