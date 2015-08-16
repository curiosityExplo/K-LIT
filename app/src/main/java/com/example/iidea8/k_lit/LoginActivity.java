package com.example.iidea8.k_lit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Abhigyan on 7/25/2015.
 */
public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void SignningIn(View view) {
        Intent intent = new Intent(this, DrawerActivity.class);
        startActivity(intent);
        Toast.makeText(LoginActivity.this, "WELCOME TO KLF", Toast.LENGTH_LONG).show();
        finish();
    }

    public void registerClick(View view) {
        Intent intent = new Intent(LoginActivity.this, Register.class);
        startActivity(intent);
        finish();
    }
}
