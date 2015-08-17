package com.example.iidea8.k_lit;


import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Home extends Fragment {
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        new HomeAsync().execute("http://iidea8.webuda.com/services/home_screen_service.php?home");
        return view;
    }
    public class HomeAsync extends AsyncTask<String,Void, String>{

        ProgressBar pb = (ProgressBar) view.findViewById(R.id.homeProgressBar);

        @Override
        protected void onPreExecute() {
            pb.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            JSONArray rootArray = HttpManager.getData(params[0]);
            String home = null;
            try {
                JSONObject rootObject = rootArray.getJSONObject(0);
                home = rootObject.getString("home");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return home;
        }

        @Override
        protected void onPostExecute(String result) {
            TextView tvHomeDesc = (TextView) view.findViewById(R.id.tvHomeDesc);
            tvHomeDesc.setText(result);
            pb.setVisibility(View.INVISIBLE);
            super.onPostExecute(result);
        }
    }
}
