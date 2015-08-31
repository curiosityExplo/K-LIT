package com.example.iidea8.k_lit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Abhigyan on 8/23/2015.
 */
public class SwipeRefreshAdapter extends BaseAdapter{

    //private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<DaysGnS> daysGnS;
    private String[] bgColors;

    public SwipeRefreshAdapter(Context context, ArrayList<DaysGnS> daysGnSes){
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = inflater.inflate(R.layout.days_listview_item, parent, false);
            TextView eventName = (TextView) convertView.findViewById(R.id.tv_item_event);
            TextView time = (TextView) convertView.findViewById(R.id.tv_item_time);
            TextView moderator= (TextView) convertView.findViewById(R.id.tv_item_moderator);
            TextView speakers= (TextView) convertView.findViewById(R.id.tv_item_speakers);

            eventName.setText(daysGnS.get(position).getEventName());
            time.setText(daysGnS.get(position).getTime());
            moderator.setText(daysGnS.get(position).getModerator());
            speakers.setText(daysGnS.get(position).getSpeakers());
    }
        return convertView;
    }
}
