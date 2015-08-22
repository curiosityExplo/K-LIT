package com.example.iidea8.k_lit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
public class LoginActivity extends Activity {

    private EditText etRegisterName;
    private EditText etRegisterContact;
    private EditText etRegisterEmail;
    private EditText etRegisterPassword;
    private EditText etLoginEmail;
    private EditText etLoginPassword;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private ProgressBar pbLogin;


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
        loginButton = (LoginButton)findViewById(R.id.login_button);
        userLocalStore = new UserLocalStore(this);
        etLoginEmail = (EditText) findViewById(R.id.et_login_email);
        etLoginPassword = (EditText) findViewById(R.id.et_login_password);
        pbLogin = (ProgressBar) findViewById(R.id.login_progressBar);

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
                Toast.makeText(LoginActivity.this, "Network Error... Try again", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
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
        pbLogin.setVisibility(View.VISIBLE);
        String email = etLoginEmail.getText().toString();
        String password = etLoginPassword.getText().toString();

        User loggedUser = new User(email, password);
        new fetchUserDataAsync(loggedUser).execute();
    }


    //    public void checkUser(User user) {
//        new fetchUserDataAsync(user, new GetUserCallBacks() {
//            @Override
//            public void done(User returnedUser) {
//                if (returnedUser == null) {
//                    Toast.makeText(LoginActivity.this, "Invalid Details..", Toast.LENGTH_LONG).show();
//                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                    builder.setMessage("Incorrect Details!");
//                    builder.setPositiveButton("OK", null);
//                } else {
//                    Intent intent = new Intent(LoginActivity.this, DrawerActivity.class);
//                    startActivity(intent);
//                    Toast.makeText(LoginActivity.this, "WELCOME TO KLF", Toast.LENGTH_LONG).show();
//                    finish();
//                    userLocalStore.storeUserData(returnedUser);
//                    userLocalStore.setUserLoggedIn(true);
//                }
//
//            }
//        });
//    }

    public class fetchUserDataAsync extends AsyncTask<Void, Void, User> {

        User user;
        GetUserCallBacks userCallBacks;


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
                    int contact = Integer.parseInt(jsonObject.getString("CONTACT_NO"));

                    returnedUser = new User(name, email, password, contact);
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
            pbLogin.setVisibility(View.INVISIBLE);
            if (returnedUser == null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("Please Try Again..");
                builder.setMessage("INCORRECT DETAILS!");
                builder.setPositiveButton("OK", null);
                builder.show();
//                startActivity(new Intent(LoginActivity.this, LoginActivity.class));
//                finish();
            }else{
                userLocalStore.storeUserData(returnedUser);
                userLocalStore.setUserLoggedIn(true);
                startActivity(new Intent(LoginActivity.this, DrawerActivity.class));
                finish();
            }
            super.onPostExecute(returnedUser);
        }
    }
}
