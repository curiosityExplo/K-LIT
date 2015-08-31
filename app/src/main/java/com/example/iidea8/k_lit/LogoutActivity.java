package com.example.iidea8.k_lit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Abhigyan on 7/25/2015.
 */
public class LogoutActivity extends Activity {

    private GoogleApiClient mGoogleApiClient;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userLocalStore = new UserLocalStore(this);
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Toast.makeText(this, "Successfully Logged Out", Toast.LENGTH_LONG).show();
        userLocalStore.clearUserData();
        userLocalStore.setUserLoggedIn(false);
    }

}
