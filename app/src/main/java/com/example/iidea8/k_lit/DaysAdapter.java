package com.example.iidea8.k_lit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Abhigyan on 8/10/2015.
 */
public class DaysAdapter extends BaseAdapter {

    private ArrayList<DaysGnS> daysGnS;
    private LayoutInflater inflater;

    public DaysAdapter(Context context, ArrayList<DaysGnS> daysGnSes){
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.daysGnS = daysGnSes;
    }

    @Override
    public int getCount() {
        return daysGnS.size();
    }

    @Override
    public Object getItem(int position) {
        return daysGnS.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class Day1ViewHolder{
        private TextView eventName;
        private TextView time;
        private TextView moderator;
        private TextView speakers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Day1ViewHolder day1ViewHolder = null;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.days_listview_item, parent, false);
            day1ViewHolder = new Day1ViewHolder();
            day1ViewHolder.eventName = (TextView) convertView.findViewById(R.id.tv_item_event);
            day1ViewHolder.time = (TextView) convertView.findViewById(R.id.tv_item_time);
            day1ViewHolder.moderator= (TextView) convertView.findViewById(R.id.tv_item_moderator);
            day1ViewHolder.speakers= (TextView) convertView.findViewById(R.id.tv_item_speakers);

            convertView.setTag(day1ViewHolder);
        }else{
            day1ViewHolder = (Day1ViewHolder) convertView.getTag();
        }
        day1ViewHolder.eventName.setText(daysGnS.get(position).getEventName());
        day1ViewHolder.time.setText(daysGnS.get(position).getTime());
        day1ViewHolder.moderator.setText(daysGnS.get(position).getModerator());
        day1ViewHolder.speakers.setText(daysGnS.get(position).getSpeakers());
        return convertView;
    }
}
