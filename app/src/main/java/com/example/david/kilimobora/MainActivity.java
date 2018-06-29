package com.example.david.kilimobora;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.david.kilimobora.fragments.HomeFragment;
import com.example.david.kilimobora.models.CountyCrop;
import com.example.david.kilimobora.models.Crop;
import com.example.david.kilimobora.models.Disease;
import com.example.david.kilimobora.models.MitigationPlan;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.raizlabs.android.dbflow.config.FlowManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FlowManager.init(getApplicationContext());
        //by default the home fragment is called
        changeFragment(1);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action

            Intent intent=new Intent(this,DiseaseActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void changeFragment(int a){
      FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();

        switch (a){
            case 1:{
                transaction.replace(R.id.main_frame, new HomeFragment(),"HomeFragment");
                transaction.commit();
            }
            case 2:{

            }
            case 3:{

            }
            default:{

            }
        }
    }
    public void getCropsData(){
        AsyncHttpClient client=new AsyncHttpClient(true,80,443);
        client.addHeader("Accept", "application/json");

        RequestParams params=new RequestParams();

        client.get("all_crops",params,new JsonHttpResponseHandler(){

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {

                    JSONArray crops_records=response.getJSONArray("crops");

                    for (int i=0;i<crops_records.length();i++){
                        JSONObject obj=crops_records.getJSONObject(i);
                        Crop crop=new Crop();
                        crop.id=obj.getInt("id");
                        crop.name=obj.getString("name");
                        crop.save();
                    }

                    JSONArray county_crops_records=response.getJSONArray("county_crops");

                    for (int i=0; i<county_crops_records.length();i++){
                        JSONObject obj=county_crops_records.getJSONObject(i);
                        CountyCrop cp=new CountyCrop();
                        cp.id=obj.getInt("id");
                        cp.crop_id=obj.getInt("crop_id");
                        cp.sub_county_id=obj.getInt("sub_county_id");
                        cp.save();
                    }
                }catch (JSONException e){

                    e.printStackTrace();

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
    public void getDiseasesData(){
        AsyncHttpClient client=new AsyncHttpClient(true,80,443);
        client.addHeader("Accept", "application/json");

        RequestParams params=new RequestParams();

        client.get("diseases",params,new JsonHttpResponseHandler(){


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);


                try {
                    JSONArray disease_records=response.getJSONArray("diseases");
                    for (int a=0;a<disease_records.length();a++){
                        JSONObject disease=disease_records.getJSONObject(a);
                        Disease disease1=new Disease();
                        disease1.id=disease.getInt("id");
                        disease1.name=disease.getString("name");
                        disease1.county_crop_id=disease.getInt("county_crop_id");
                        disease1.image_name=disease.getString("image_name");
                        disease1.save();
                    }


                    JSONArray mitigations=response.getJSONArray("mitigations");
                    for (int a=0;a<mitigations.length();a++){
                        JSONObject mitigation=mitigations.getJSONObject(a);
                        MitigationPlan mitigation_recoord=new MitigationPlan();
                        mitigation_recoord.id=mitigation.getInt("id");
                        mitigation_recoord.crop_id=mitigation.getInt("crop_id");
                        mitigation_recoord.mitigation_plan=mitigation.getString("mitigation_plan");
                        mitigation_recoord.save();
                    }

                } catch (JSONException e) {
                    Log.e("err",e.toString());
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}
