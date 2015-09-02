package com.example.iidea8.k_lit;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Overview extends Fragment {
    View view;
    private ArrayList<DaysGnS> daysGnSArray = new ArrayList<DaysGnS>();
    private OverviewAdapter overviewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_overview, container, false);
//        ListView listViewOverview = (ListView) view.findViewById(R.id.overview_listView);
//        new OverviewAsync().execute("http://iidea8.webuda.com/services/overview_service.php");
        return view;
    }

//    public class OverviewAsync extends AsyncTask<String, Void, ArrayList<DaysGnS>> {
//
//
//        ProgressBar pb = (ProgressBar) view.findViewById(R.id.overview_progressBar);
//
//        @Override
//        protected void onPreExecute() {
//            pb.setVisibility(View.VISIBLE);
//            super.onPreExecute();
//        }
//
//        @Override
//        protected ArrayList<DaysGnS> doInBackground(String... params) {
//            JSONArray rootArray = HttpManager.getData(params[0]);
//            DaysGnS daysGnS = null;
//            StringBuilder stringBuilderDesc = new StringBuilder();
//            for (int i = 0; i<rootArray.length(); i++){
//                try {
//                    JSONObject jsonObject = rootArray.getJSONObject(i);
//                    String eventName = jsonObject.getString("event_Name");
//                     String eventDesc = jsonObject.getString("EVENT_DESC");
//                    String startTime = jsonObject.getString("EVENT_START_TIME");
//                    String endTime = jsonObject.getString("EVENT_END_TIME");
//
//                    stringBuilderDesc.append(eventDesc);
//                    stringBuilderDesc.toString();
//
//                    daysGnS = new DaysGnS();
//                    daysGnS.setEventName(eventName);
//                    daysGnS.setTime(startTime + " - " + endTime);
//                    daysGnS.setModerator(stringBuilderDesc);
//
//                    daysGnSArray.add(daysGnS);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//
//                }
//            }
//
//            return daysGnSArray;
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<DaysGnS> result) {
//            ListView listViewOverview = (ListView) view.findViewById(R.id.overview_listView);
//            OverviewAdapter overviewAdapter = new OverviewAdapter(getActivity().getBaseContext(), result);
//           listViewOverview.setAdapter(overviewAdapter);
//            pb.setVisibility(View.INVISIBLE);
//            super.onPostExecute(result);
//        }
//    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("Overview");
    }
}
