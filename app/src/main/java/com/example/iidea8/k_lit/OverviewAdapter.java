package com.example.iidea8.k_lit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Abhigyan on 8/29/2015.
 */
public class OverviewAdapter extends BaseAdapter {

    private ArrayList<DaysGnS> daysGnS;
    private LayoutInflater inflater;

    public OverviewAdapter(Context context, ArrayList<DaysGnS> daysGnSes){
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
    private class OverviewViewHolder{
        private TextView eventName;
        private TextView time;
        private TextView moderator;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OverviewViewHolder overviewViewHolder = null;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.overview_items, parent, false);
            overviewViewHolder = new OverviewViewHolder();
            overviewViewHolder.eventName = (TextView) convertView.findViewById(R.id.overview_item_eventName);
            overviewViewHolder.time = (TextView) convertView.findViewById(R.id.overview_item_time);
            overviewViewHolder.moderator= (TextView) convertView.findViewById(R.id.overview_item_desc);

            convertView.setTag(overviewViewHolder);
        }else{
            overviewViewHolder = (OverviewViewHolder) convertView.getTag();
        }
        overviewViewHolder.eventName.setText(daysGnS.get(position).getEventName());
        overviewViewHolder.time.setText(daysGnS.get(position).getTime());
        overviewViewHolder.moderator.setText(daysGnS.get(position).getModerator());
        return convertView;
    }
}
