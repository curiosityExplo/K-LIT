package com.example.iidea8.k_lit;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class ContestAsync extends AsyncTask<Void, Void, ContestsGnS> {

    Context mContext;
    View rootView;
    ContestsGnS contestsGnS;
    ContestResponse contestResp;


    public ContestAsync(Context context, View rootView,ContestsGnS contestsGnS, ContestResponse contestResp) {
        this.mContext = context;
        this.contestsGnS = contestsGnS;
        this.rootView = rootView;
        this.contestResp = contestResp;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    protected ContestsGnS doInBackground(Void...params) {
        ArrayList<NameValuePair> dataToSend = new ArrayList<>();
        dataToSend.add(new BasicNameValuePair("Q_ID", Integer.toString(contestsGnS.queID)));

        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 1000 * 15);
        HttpConnectionParams.setSoTimeout(httpParams, 1000 * 15);

        HttpClient client = new DefaultHttpClient(httpParams);
        HttpPost post = new HttpPost("http://iidea8.webuda.com/services/contest_service.php");
        ContestsGnS returnedContestsGnS = null;

        try {
            post.setEntity(new UrlEncodedFormEntity(dataToSend));
            HttpResponse httpResponse = client.execute(post);
            HttpEntity entity = httpResponse.getEntity();
            String result = EntityUtils.toString(entity);
            JSONObject jsonObject = new JSONObject(result);
            String question = jsonObject.getString("QUESTION");
            String optA = jsonObject.getString("OPTA");
            String optB = jsonObject.getString("OPTB");
            String optC = jsonObject.getString("OPTC");
            String optD = jsonObject.getString("OPTD");
            String answer = jsonObject.getString("ANSWER");

            returnedContestsGnS = new ContestsGnS(question, optA, optB, optC, optD, answer);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return returnedContestsGnS;
    }

    @Override
    protected void onPostExecute(ContestsGnS result) {

        TextView question = (TextView) rootView.findViewById(R.id.tv_question);
        RadioButton optA = (RadioButton) rootView.findViewById(R.id.rb_optA);
        RadioButton optB = (RadioButton) rootView.findViewById(R.id.rb_optB);
        RadioButton optC = (RadioButton) rootView.findViewById(R.id.rb_optC);
        RadioButton optD = (RadioButton) rootView.findViewById(R.id.rb_optD);
        question.setText(result.question);
        optA.setText(result.optA);
        optB.setText(result.optB);
        optC.setText(result.optC);
        optD.setText(result.optD);
        contestResp.processFinish(result);
        super.onPostExecute(result);
    }
}
