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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ItineraryDay1 extends Fragment  {
    private View view;
    private ArrayList<Day1GnS> day1GnSArray = new ArrayList<Day1GnS>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_itinerary_day1, container, false);
        new Day1Async().execute("http://iidea8.webuda.com/services/webservice_itenary.php");
        setRetainInstance(true);
        return view;
    }

    public class Day1Async extends AsyncTask<String, Void, ArrayList<Day1GnS>> {


        ProgressBar pb = (ProgressBar) view.findViewById(R.id.day1_progressBar);

        @Override
        protected void onPreExecute() {
            pb.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }
        @Override
        protected ArrayList<Day1GnS> doInBackground(String... params) {
          JSONArray rootArray = HttpManager.getData(params[0]);
            Day1GnS day1GnS = null;

            try {
                 JSONObject rootObject1 = rootArray.getJSONObject(0);
            String eventName = rootObject1.getString("event_Name");
            String start_time = rootObject1.getString("EVENT_START_TIME");
            String end_time = rootObject1.getString("EVENT_END_TIME");
            JSONArray subEventArray = rootObject1.getJSONArray("sub_event_Children");
                 StringBuilder subEventStringBuilder = new StringBuilder(subEventArray.length());

                 for (int s=0; s<subEventArray.length(); s++){
                     JSONObject subEventObjects = subEventArray.getJSONObject(s);
                     JSONArray subChildrenArray = subEventObjects.getJSONArray("Children");
                     StringBuilder speaker = new StringBuilder(subChildrenArray.length());

                     for (int j=0;j<subChildrenArray.length();j++){
                         JSONObject subChildrenObject = subChildrenArray.getJSONObject(j);
                         String speakerFirstName = subChildrenObject.getString("SPEAKER_NAME");
                         String speakerLastName = subChildrenObject.getString("SPEAKER_LAST_NAME");
                         String speakerProf = subChildrenObject.getString("SPEAKER_PROFILE");
                         String speakerProfile = (", " + speakerProf);
                         speaker.append("\n").append(speakerFirstName).append(" ").append(speakerLastName)
                                 .append(speakerProfile);
                     }
                     String subName = subEventObjects.getString("sub_event_Name");
                     subEventStringBuilder.append(subName).append(speaker).append("\n \n");
                     subEventStringBuilder.toString();
                 }
                 day1GnS = new Day1GnS();
                 day1GnS.setEventName(eventName);
                 day1GnS.setTime(start_time + "-" + end_time);
                 day1GnS.setSpeakers(subEventStringBuilder);
                 day1GnSArray.add(day1GnS);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            for (int i=1;i<rootArray.length();i++){
                try {
                    JSONObject rootObjects = rootArray.getJSONObject(i);
                    String eventName = rootObjects.getString("event_Name");
                    String start_time = rootObjects.getString("EVENT_START_TIME");
                    String end_time = rootObjects.getString("EVENT_END_TIME");
                    JSONArray speakerArray = rootObjects.getJSONArray("Speaker_Children");
                    StringBuilder speakerStringBuilder = new StringBuilder(speakerArray.length());
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
                    day1GnS = new Day1GnS();
                    day1GnS.setEventName(eventName);
                    day1GnS.setTime(start_time + "-" + end_time);
                    day1GnS.setSpeakers(speakerStringBuilder);
                    day1GnSArray.add(day1GnS);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return day1GnSArray;
        }

        @Override
        protected void onPostExecute(ArrayList<Day1GnS> result) {
            ListView listViewDay1 = (ListView) view.findViewById(R.id.list_view_day1);
            Day1Adapter day1Adapter = new Day1Adapter(getActivity().getBaseContext(),day1GnSArray);
            listViewDay1.setAdapter(day1Adapter);
            pb.setVisibility(View.INVISIBLE);
            super.onPostExecute(result);
        }
    }
}

