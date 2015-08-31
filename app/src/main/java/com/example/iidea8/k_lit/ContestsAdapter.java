package com.example.iidea8.k_lit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by Abhigyan on 8/31/2015.
 */
public class ContestsAdapter extends BaseAdapter {

    private ContestsGnS contestsGnS;
    private LayoutInflater inflater;

    public ContestsAdapter(Context context, ContestsGnS contestsGnSes) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.contestsGnS = contestsGnSes;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ContestViewHolder {
        private TextView question;
        private RadioButton optA;
        private RadioButton optB;
        private RadioButton optC;
        private RadioButton optD;
    }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ContestViewHolder contestViewHolder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.fragment_contest, parent, false);
                contestViewHolder = new ContestViewHolder();
                contestViewHolder.question = (TextView) convertView.findViewById(R.id.tv_question);
                contestViewHolder.optA = (RadioButton) convertView.findViewById(R.id.rb_optA);
                contestViewHolder.optB = (RadioButton) convertView.findViewById(R.id.rb_optB);
                contestViewHolder.optC = (RadioButton) convertView.findViewById(R.id.rb_optC);
                contestViewHolder.optD = (RadioButton) convertView.findViewById(R.id.rb_optD);
                convertView.setTag(contestViewHolder);
            } else {
                contestViewHolder = (ContestViewHolder) convertView.getTag();
            }
            contestViewHolder.question.setText(contestsGnS.getQuestion());
            contestViewHolder.optA.setText(contestsGnS.getOptA());
            contestViewHolder.optB.setText(contestsGnS.getOptB());
            contestViewHolder.optC.setText(contestsGnS.getOptC());
            contestViewHolder.optD.setText(contestsGnS.getOptD());

            return convertView;
        }
    }

