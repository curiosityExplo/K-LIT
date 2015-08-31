package com.example.iidea8.k_lit;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Abhigyan on 9/1/2015.
 */
public class ContestResult extends Fragment implements InterfaceContestUser {
    View view;
    public static int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://iidea8.webuda.com/services/";
    InterfaceContestUser contestUser;
    private String name;
    private String email;
    private int correctAnswers;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.result_contest, container, false);
        Bundle bundle = this.getArguments();
        String result = String.valueOf(bundle.getInt("answerNo"));
        contestUser = this;
        TextView tvResult = (TextView) view.findViewById(R.id.tv_result_contest);
        Button btFinish = (Button) view.findViewById(R.id.bt_result_contest);
        tvResult.setText(result + " Right Answers Out Of 5");
        btFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User submitUser = new User(name, email, correctAnswers);
                new SubmitContestAsync(submitUser).execute();
                        getFragmentManager().beginTransaction().add(R.id.frame_layout, new Home()).commit();
            }
        });
        return view;
    }

    @Override
    public void loggedUser(User user) {
        this.name = user.name;
        this.email = user.email;
        this.correctAnswers = user.correctAnswers;


    }


    public class SubmitContestAsync extends AsyncTask<Void, Void, User> {

        User user;
        GetUserCallBacks userCallBacks;
//        ProgressBar pb = (ProgressBar) findViewById(R.id.login_progressBar);


        public SubmitContestAsync(User user) {
            this.user = user;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected User doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("NAME", user.name));
            dataToSend.add(new BasicNameValuePair("EMAIL", user.email));
            dataToSend.add(new BasicNameValuePair("RESULT_CORRECT", Integer.toString(user.correctAnswers)));

            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, 1000 * 15);
            HttpConnectionParams.setSoTimeout(httpParams, 1000 * 15);

            HttpClient client = new DefaultHttpClient(httpParams);
            HttpPost post = new HttpPost("http://iidea8.webuda.com/services/submit_answer.php");
            try {
                client.execute(post);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
        }
    }
}