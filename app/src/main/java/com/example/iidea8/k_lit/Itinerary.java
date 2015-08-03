package com.example.iidea8.k_lit;


import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


public class Itinerary extends Fragment {
    private View view;
    private TextView mtvDate;
    private AsyncItinereray aI;
    private String content;
    private ProgressBar pb;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_itinerary, container, false);
        pb = (ProgressBar) view.findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);
        mtvDate = (TextView) view.findViewById(R.id.tvDate);
        mtvDate.setMovementMethod(new ScrollingMovementMethod());
        aI = new AsyncItinereray();
        aI.execute("http://www.klftest.byethost17.com/test.php");
        return view;
    }
    public class AsyncItinereray extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            content = HttpManager.getData(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String s) {
            mtvDate.setText(s);
            pb.setVisibility(View.INVISIBLE);
        }
    }

    }

