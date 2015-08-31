package com.example.iidea8.k_lit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Abhigyan on 8/24/2015.
 */
public class SpeakersAdapter extends BaseAdapter {
    private ArrayList<SpeakersBio> speakersBioArrayList;
    private LayoutInflater inflater;

    public SpeakersAdapter(Context context, ArrayList<SpeakersBio> speakersBioArrayList){
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.speakersBioArrayList = speakersBioArrayList;
    }


    public int getCount() {
        return speakersBioArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return speakersBioArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class SpeakerViewHolder{
        private TextView speakerName;
        private TextView speakerBio;
        private ImageView speakerPhoto;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SpeakerViewHolder speakerViewHolder = null;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.speakers_listview_item, parent, false);
            speakerViewHolder = new SpeakerViewHolder();
            speakerViewHolder.speakerName = (TextView) convertView.findViewById(R.id.tv_speaker_name);
            speakerViewHolder.speakerBio = (TextView) convertView.findViewById(R.id.tv_speaker_bio);
            speakerViewHolder.speakerPhoto= (ImageView) convertView.findViewById(R.id.iv_speaker);


            convertView.setTag(speakerViewHolder);
        }else{
            speakerViewHolder = (SpeakerViewHolder) convertView.getTag();
        }
        speakerViewHolder.speakerName.setText(speakersBioArrayList.get(position).getSpeakerName());
        speakerViewHolder.speakerBio.setText(speakersBioArrayList.get(position).getSpeakerBio());
        speakerViewHolder.speakerPhoto.setImageResource(speakersBioArrayList.get(position).getSpeakerPhoto());
        return convertView;
    }
}
