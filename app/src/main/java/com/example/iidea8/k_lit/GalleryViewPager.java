package com.example.iidea8.k_lit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhigyan on 8/13/2015.
 */
public class GalleryViewPager extends Activity {

    // Declare Variable
    int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set title for the ViewPager
        setTitle("<-- sWipe -->");
        // Get the view from view_pager.xml
        setContentView(R.layout.gallery_view_pager);

        // Retrieve data from MainActivity on item click event
        Intent p = getIntent();
        position = p.getExtras().getInt("id");

        GalleryAdapter imageAdapter = new GalleryAdapter(this);
        List<ImageView> images = new ArrayList<ImageView>();

        // Retrieve all the images
        for (int i = 0; i < imageAdapter.getCount(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imageAdapter.mGallery[i]);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            images.add(imageView);
        }


        // Set the images into ViewPager
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(images);
        ViewPager viewpager = (ViewPager) findViewById(R.id.gallery_viewPager);
        viewpager.setAdapter(pagerAdapter);
        // Show images following the position
        viewpager.setCurrentItem(position);
    }
}
