package com.example.iidea8.k_lit;


import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ItineraryDay2 extends Fragment {
    private View view;
    private ArrayList<DaysGnS> daysGnSArray = new ArrayList<DaysGnS>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_itinerary_day2, container, false);
        new Day2Async().execute("http://iidea8.webuda.com/services/itenary_service.php?date=2015-10-24");
        setRetainInstance(true);
        return view;
    }

    public class Day2Async extends AsyncTask<String, Void, ArrayList<DaysGnS>> {


        ProgressBar pb = (ProgressBar) view.findViewById(R.id.day2_progressBar);

        @Override
        protected void onPreExecute() {
            pb.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }
        @Override
        protected ArrayList<DaysGnS> doInBackground(String... params) {
            JSONArray rootArray = HttpManager.getData(params[0]);
            DaysGnS daysGnS = null;

            for (int i=0;i<rootArray.length();i++){
                try {
                    JSONObject rootObjects = rootArray.getJSONObject(i);
                    String eventName = rootObjects.getString("event_Name");
                    String start_time = rootObjects.getString("EVENT_START_TIME");
                    String end_time = rootObjects.getString("EVENT_END_TIME");
                    JSONArray speakerArray = rootObjects.getJSONArray("Speaker_Children");
                    JSONArray moderaterArray = rootObjects.getJSONArray("moderator_Children");
                    JSONArray curaterArray = rootObjects.getJSONArray("curator_Children");

                    StringBuilder speakerStringBuilder = new StringBuilder(speakerArray.length());
                    StringBuilder moderaterStringBuilder = new StringBuilder(moderaterArray.length());
                    StringBuilder curatorStringBuilder = new StringBuilder(curaterArray.length());
                    StringBuilder moderatedBy = new StringBuilder();
                    moderatedBy.append("Moderated By:  ");
                    StringBuilder curatedBy = new StringBuilder();
                    curatedBy.append("\n").append("Curated  By:  ");

                    for (int m=0; m<moderaterArray.length();m++){
                        JSONObject moderaterObjects = moderaterArray.getJSONObject(m);
                        String moderaterFirstName = moderaterObjects.getString("Moderator_name");
                        String moderaterLastName = moderaterObjects.getString("Moderator_last_name");
                        moderaterStringBuilder.append(moderaterFirstName).append(" ")
                                .append(moderaterLastName).append("  ");
                    }

                    for (int c=0; c<curaterArray.length();c++){
                        JSONObject curaterObjects = curaterArray.getJSONObject(c);
                        String curaterFirstName = curaterObjects.getString("curator_name");
                        String curaterLastName = curaterObjects.getString("curator_last_name");
                        curatorStringBuilder.append(curaterFirstName)
                                .append(" ").append(curaterLastName).append("  ");
                    }

                    for (int k=0;k<speakerArray.length();k++){
                        JSONObject speakerObjects = speakerArray.getJSONObject(k);
                        String speakerFirstName = speakerObjects.getString("SPEAKER_NAME");
                        String speakerLastName = speakerObjects.getString("SPEAKER_LAST_NAME");
                        String speakerProf = speakerObjects.getString("SPEAKER_PROFILE");
                        String speakerProfile = (", " + speakerProf);
                        speakerStringBuilder.append("\n").append(speakerFirstName).append(" ").append(speakerLastName)
                                .append(speakerProfile).append("\n");
                    }
                    speakerStringBuilder.toString();
                    daysGnS = new DaysGnS();
                    daysGnS.setEventName(eventName);
                    daysGnS.setTime(start_time + "-" + end_time);
                    daysGnS.setSpeakers(speakerStringBuilder);
                    daysGnS.setModerator(moderatedBy.append(moderaterStringBuilder).append(curatedBy).append(curatorStringBuilder));
                    daysGnSArray.add(daysGnS);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return daysGnSArray;
        }

        @Override
        protected void onPostExecute(ArrayList<DaysGnS> result) {
            ListView listViewDay1 = (ListView) view.findViewById(R.id.list_view_day1);
            DaysAdapter daysAdapter = new DaysAdapter(getActivity().getBaseContext(),result);
            listViewDay1.setAdapter(daysAdapter);
            pb.setVisibility(View.INVISIBLE);
            super.onPostExecute(result);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("Itinerary Day 2");
    }
}

