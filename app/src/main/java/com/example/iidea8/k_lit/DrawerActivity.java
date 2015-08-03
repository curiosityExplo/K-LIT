package com.example.iidea8.k_lit;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class DrawerActivity extends ActionBarActivity {


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
        mListView.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_items, mArrayList ));

        mListView.setOnItemClickListener(new drawerItemClickListner());

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.drawable.iidea_logo);
        actionBar.setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mdrawerLayout, null,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close );

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mdrawerLayout.setDrawerListener(mDrawerToggle);

        getFragmentManager().beginTransaction().replace(R.id.frame_layout, new Home())
                            .commit();
        setTitle(null);

        getActivity = getTitle();


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
        // automatically handle clicks on the Home/Up button, so long
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
        }

        return false;
    }

    private class drawerItemClickListner implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);

        }

        public void selectItem(int position) {
            switch (position){
                case 0:
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, new Home()).commit();
                    break;

                case 1:
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, new Itinerary()).commit();
                    break;

                case 2:
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, new Speakers()).commit();
                    break;

                case 3:
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, new Overview()).commit();
                    break;

                case 4:
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, new Quiz()).commit();
                    break;

                case 5:
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, new Gallery()).commit();
                    break;

                case 6:
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, new Sponsors()).commit();
                    break;

                case 7:
                    Intent intent = new Intent(DrawerActivity.this, LogoutActivity.class);
                    startActivity(intent);
                    break;
                default:
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, new Home()).commit();
            }


            mListView.setItemChecked(position, true);
        mdrawerLayout.closeDrawer(mListView);
            setTitle(mListItems[position]);
        }
    }

    }


