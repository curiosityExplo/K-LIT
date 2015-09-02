package com.example.iidea8.k_lit;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Abhigyan on 8/13/2015.
 */
public class GalleryAdapter extends BaseAdapter {

    private Context mContext;


    public GalleryAdapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return mGallery.length;
    }

    @Override
    public Object getItem(int position) {
        return mGallery[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(170, 170));
            //imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(1, 1, 1, 1);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mGallery[position]);
        return imageView;
    }

    public Integer[] mGallery = {
            R.mipmap.sponsors_uttarakhand, R.mipmap.sponsors_abbotsford,
            R.mipmap.sponsors_amarchitralatha, R.mipmap.sponsors_atmanya,
            R.mipmap.sponsors_authorspress, R.mipmap.sponsors_blot,
            R.mipmap.sponsors_butterflyandbee, R.mipmap.sponsors_cafechica,
            R.mipmap.sponsors_cafeflashback, R.mipmap.sponsors_chitrashala,
            R.mipmap.sponsors_delhibooklovers, R.mipmap.sponsors_destinationdhanachuli,
            R.mipmap.sponsors_dff, R.mipmap.sponsors_digitalmediadigital,
            R.mipmap.sponsors_goodnessvanilla,  R.mipmap.sponsors_goodwordmedia,
            R.mipmap.sponsors_harpercollins,  R.mipmap.sponsors_iidea8,
            R.mipmap.sponsors_impact,  R.mipmap.sponsors_israelembassy,
            R.mipmap.sponsors_kingfisher,  R.mipmap.sponsors_milap,
            R.mipmap.sponsors_mittalteas,  R.mipmap.sponsors_ombooks,
            R.mipmap.sponsors_poetscorner,  R.mipmap.sponsors_profsppassi,
            R.mipmap.sponsors_punjabtourism,  R.mipmap.sponsors_readersclub,
            R.mipmap.sponsors_rollibooks,  R.mipmap.sponsors_soultree,
            R.mipmap.sponsors_tarapress,  R.mipmap.sponsors_tearoha,
            R.mipmap.sponsors_theclaridges,  R.mipmap.sponsors_theluxecafe,
            R.mipmap.sponsors_umwomen,  R.mipmap.sponsors_uttarakhand,
            R.mipmap.sponsors_vintage31,
    };
}
