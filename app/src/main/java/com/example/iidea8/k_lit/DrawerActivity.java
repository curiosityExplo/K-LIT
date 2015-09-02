package com.example.iidea8.k_lit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.common.api.GoogleApiClient;


public class DrawerActivity extends ActionBarActivity {


    GoogleApiClient mGoogleApiClient;
    private ListView mListView;
    private String[] mListItems;
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence getActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mListItems = getResources().getStringArray(R.array.array_list_item);
        mListView = (ListView) findViewById(R.id.list_view);
        String[] mArrayList = getResources().getStringArray(R.array.array_list_item);
        mListView.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_items, mArrayList));

        mListView.setOnItemClickListener(new drawerItemClickListener());

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.drawable.iidea_logo);
        actionBar.setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mdrawerLayout, null,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close );

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mdrawerLayout.setDrawerListener(mDrawerToggle);

        getFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, new Home()).addToBackStack("home")
                            .commit();
        setTitle(null);

        getActivity = getTitle();

    }

    @Override
    public void onBackPressed() {

            if (getFragmentManager().getBackStackEntryCount() > 1) {
                getFragmentManager().popBackStack();
                getFragmentManager().beginTransaction().commit();


            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("EXIT!");
                builder.setMessage("Do you really wish to exit..?");
                builder.setNegativeButton("NO", null);
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DrawerActivity.super.onBackPressed();
                    }
                });
                builder.show();

            }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up bt_login_ideaWebClick, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            if (mdrawerLayout.isDrawerOpen(mListView)){
                mdrawerLayout.closeDrawer(mListView);
                setTitle(getActivity);
            }
            else{
                mdrawerLayout.openDrawer(mListView);
                setTitle("BROWSE");
            }
            return false;
        }

        return super.onOptionsItemSelected(item);
    }



    public void onClick_day1(View view) {

        getFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, new ItineraryDay1()).addToBackStack("iti1").commit();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void onClick_day2(View view) {
        getFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, new ItineraryDay2()).addToBackStack("iti2").commit();
    }

    public void onClick_day3(View view) {
        getFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, new ItineraryDay3()).addToBackStack("iti3").commit();
    }

    public void onClick_day4(View view) {
        getFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, new ItineraryDay4()).addToBackStack("iti4").commit();
    }

    public void onClick_day5(View view) {
        getFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, new ItineraryDay5()).addToBackStack("iti5").commit();
    }



    private class drawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);

        }

        public void selectItem(int position) {
            switch (position){
                case 0:
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, new Home()).addToBackStack("hom").commit();
                    break;

                case 1:
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, new Itinerary()).addToBackStack("iti").commit();
                    ActionBar actionBar = getSupportActionBar();
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    break;


                case 2:
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, new Speakers()).addToBackStack("spk").commit();
                    break;

                case 3:
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, new Overview()).addToBackStack("ovr").commit();
                    break;

                case 4:
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, new TermsFragment()).commit();
                    break;

                case 5:
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, new Gallery()).addToBackStack("gal").commit();
                    break;

                case 6:
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, new Sponsors()).addToBackStack("spo").commit();
                    break;

                case 7:
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, new MoreFragment()).addToBackStack("mor").commit();
                    break;

                case 8:
                    Intent intent = new Intent(DrawerActivity.this, LogoutActivity.class);
                    startActivity(intent);
                    break;
            }


            mListView.setItemChecked(position, true);
        mdrawerLayout.closeDrawer(mListView);
            setTitle(mListItems[position]);
        }
    }

    }


