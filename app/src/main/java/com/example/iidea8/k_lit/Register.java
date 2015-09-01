package com.example.iidea8.k_lit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Abhigyan on 8/5/2015.
 */
public class Register extends Activity{
    private EditText etName;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etContact;
    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://iidea8.webuda.com/services/";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

         etName = (EditText) findViewById(R.id.etRegisterName);
         etEmail = (EditText) findViewById(R.id.etRegisterEmailId);
         etPassword = (EditText) findViewById(R.id.etRegisterPassword);
//         etContact = (EditText) findViewById(R.id.etRegisterContactNumber);
    }

    public void registeredClickHandler(View view) {
        String name = etName.getText().toString();
        String email =etEmail.getText().toString();
        String password = etPassword.getText().toString();

        User newUser = new User(name, email, password);
        if (newUser.name.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
            builder.setTitle("NAME IS MANDATORY..");
            builder.setMessage("Please Try Again..");
            builder.setPositiveButton("OK", null);
            builder.show();
        }
           else if (newUser.email.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
            builder.setTitle("EMAIL IS MANDATORY..");
            builder.setMessage("Please Try Again..");
            builder.setPositiveButton("OK", null);
            builder.show();
            }
                else if (newUser.password.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
            builder.setTitle("PASSWORD IS OBVIOUSLY MANDATORY..");
            builder.setMessage("Please Try Again..");
            builder.setPositiveButton("OK", null);
            builder.show();
        } else {
            new storeUserDataAsync(newUser).execute();
        Intent intent = new Intent(Register.this, LoginActivity.class);
        startActivity(intent);
        Toast.makeText(Register.this, "Successfully Registered. Please Login To Continue..", Toast.LENGTH_LONG)
                .show();
        finish();
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Register.this, LoginActivity.class));
        finish();
        }

    public class storeUserDataAsync extends AsyncTask<Void, Void, Void> {

        User user;
        GetUserCallBacks userCallBacks;


        public storeUserDataAsync(User user) {
            this.user = user;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progressDialog = new ProgressDialog(context);
//            progressDialog.setCancelable(false);
//            progressDialog.setTitle("Processing");
//            progressDialog.setMessage("Please Wait...");
//            progressDialog.show();
        }


        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("NAME", user.name));
            dataToSend.add(new BasicNameValuePair("EMAIL", user.email));
            dataToSend.add(new BasicNameValuePair("PASSWORD", user.password));

            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "register.php");

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            }catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //progressDialog.dismiss();
            super.onPostExecute(aVoid);
        }
    }
}
