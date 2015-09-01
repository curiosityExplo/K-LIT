package com.example.iidea8.k_lit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

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

/**
 * Created by Abhigyan on 7/25/2015.
 */
public class LoginActivity extends Activity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {

    private boolean mIsResolving = false;
    private boolean mShouldResolve = false;
    private ConnectionResult mConnectionResult;
    private static final int RC_SIGN_IN = 0;
    private GoogleApiClient mGoogleApiClient;
    private EditText etLoginEmail;

    private EditText etLoginPassword;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private ProgressBar pb;
    InterfaceContestUser interfaceContestUser;
    public User loginuser;
    public String loggedName;
    public String loggedEmail;

    private UserLocalStore userLocalStore;
    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://iidea8.webuda.com/services/";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
//        loginuser = new User(loggedName, loggedEmail, 0);
        pb = (ProgressBar) findViewById(R.id.login_progressBar);
        pb.setVisibility(View.INVISIBLE);
        ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API, Plus.PlusOptions.builder().build())
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();
        loginButton = (LoginButton) findViewById(R.id.fb_login_button);
        userLocalStore = new UserLocalStore(this);
        etLoginEmail = (EditText) findViewById(R.id.et_login_email);
        etLoginPassword = (EditText) findViewById(R.id.et_login_password);
        findViewById(R.id.sign_in_button).setOnClickListener(this);

        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {


            @Override
            public void onSuccess(LoginResult loginResult) {
                userLocalStore.setUserLoggedIn(true);
                startActivity(new Intent(LoginActivity.this, DrawerActivity.class));
                Toast.makeText(LoginActivity.this, "WELCOME TO KLF", Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException e) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("Please Try Again..");
                builder.setMessage("NETWORK ERROR!");
                builder.setPositiveButton("OK", null);
                builder.show();
            }
        });

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (!connectionResult.hasResolution()) {

            GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(), this, 0).show();
            return;
        }

        if (!mIsResolving) {
            // store mConnectionResult
            mConnectionResult = connectionResult;

            if (mShouldResolve) {
                resolveSignInError();

            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode != RESULT_OK) {
                mShouldResolve = false;
            }

            mIsResolving = false;
            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        }
    }

    private void resolveSignInError() {

        if (mConnectionResult.hasResolution()) {

            try {
               mIsResolving = true;
                mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);

            } catch (IntentSender.SendIntentException e) {
                mIsResolving = false;
                mGoogleApiClient.connect();
            }
        }
    }


    @Override
    public void onConnected(Bundle bundle) {
        mShouldResolve = false;
        startActivity(new Intent(LoginActivity.this, DrawerActivity.class));
        finish();
        getProfileInformation();
        Toast.makeText(LoginActivity.this,"WELCOME TO KLF",Toast.LENGTH_LONG).show();
        userLocalStore.setUserLoggedIn(true);
    }

    private void getProfileInformation() {
        if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
            Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
            String personName = currentPerson.getDisplayName();
            String email = Plus.AccountApi.getAccountName(mGoogleApiClient);
//          String personPhotoUrl = currentPerson.getImage().getUrl();
            Toast.makeText(LoginActivity.this,"Hello, " + personName,Toast.LENGTH_SHORT).show();
        }
    }

        @Override
        public void onConnectionSuspended ( int i){
            mGoogleApiClient.connect();
        }

        @Override
        public void onClick (View v){
            if (v.getId() == R.id.sign_in_button) {
                if (mGoogleApiClient.isConnected()) {
                    Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
                    mGoogleApiClient.disconnect();
                }
                onSignInClicked();
            }
        }

    public void onSignInClicked() {

        googlePlusLogin();
    }

    private void googlePlusLogin(){
        if (!mGoogleApiClient.isConnecting()){
            mShouldResolve = true;
            mGoogleApiClient.connect();
            //resolveSignInError();
        }
    }
    public void logout(){
        googlePlusLogout();
    }

    private void googlePlusLogout(){
        if (mGoogleApiClient.isConnected()){
        Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
        mGoogleApiClient.disconnect();
         }
    }


    public void registerClick(View view) {
        Intent intent = new Intent(LoginActivity.this, Register.class);
        startActivity(intent);
        finish();
    }


    public void webClick(View view) {
        Uri uri = Uri.parse("http://www.iidea8.com/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (authenticate() == true) {
            //mGoogleApiClient.connect();
            Intent intent = new Intent(LoginActivity.this, DrawerActivity.class);
            startActivity(intent);
            Toast.makeText(LoginActivity.this, "WELCOME TO KLF", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public boolean authenticate() {
        return userLocalStore.getUserLoggedIn();
    }

    public void SignningIn(View view) {
        String email = etLoginEmail.getText().toString();
        String password = etLoginPassword.getText().toString();

        User loggedUser = new User(email, password);
        new fetchUserDataAsync(loggedUser).execute();
    }


    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("EXIT!");
        builder.setMessage("Do you really wish to exit..?");
        builder.setNegativeButton("NO", null);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               LoginActivity.super.onBackPressed();

            }
        });
        builder.show();
    }

    public class fetchUserDataAsync extends AsyncTask<Void, Void, User> {

        User user;

//        ProgressBar pb = (ProgressBar) findViewById(R.id.login_progressBar);


        public fetchUserDataAsync(User user) {
            this.user = user;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected User doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("EMAIL", user.email));
            dataToSend.add(new BasicNameValuePair("PASSWORD", user.password));

            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "fetch_data.php");
            User returnedUser = null;
            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);
                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONObject jsonObject = new JSONObject(result);
                if (jsonObject.length() == 0) {
                    returnedUser = null;
                } else {
                    String name = jsonObject.getString("NAME");
                    String email = jsonObject.getString("EMAIL");
                    String password = jsonObject.getString("PASSWORD");

                    returnedUser = new User(name, email, password);
                }


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return returnedUser;
        }

        @Override
        protected void onPostExecute(User returnedUser) {
            if (returnedUser == null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("Please Try Again..");
                builder.setMessage("INCORRECT DETAILS!");
                builder.setPositiveButton("OK", null);
                builder.show();
//                startActivity(new Intent(LoginActivity.this, LoginActivity.class));
//                finish();
            } else {
                userLocalStore.storeUserData(returnedUser);
                userLocalStore.setUserLoggedIn(true);
                startActivity(new Intent(LoginActivity.this, DrawerActivity.class));
                finish();
            }
            super.onPostExecute(returnedUser);
        }
    }
}
