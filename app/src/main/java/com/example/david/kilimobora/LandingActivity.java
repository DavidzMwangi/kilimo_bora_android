package com.example.david.kilimobora;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.david.kilimobora.models.County;
import com.example.david.kilimobora.models.SubCounty;
import com.example.david.kilimobora.utils.CoreUtils;
import com.example.david.kilimobora.utils.NetController;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.raizlabs.android.dbflow.config.FlowManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class LandingActivity extends AppCompatActivity {

    ArrayList<String> county=new ArrayList<String>();
    Spinner county_spinner;
    AppCompatButton explore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        FlowManager.init(this);
        county_spinner=(Spinner)findViewById(R.id.county_spinner);
        explore=(AppCompatButton)findViewById(R.id.explore_button);


        county.add(0,"Select a county");
        county.add(1,"Nakuru");

        ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,county);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        county_spinner.setAdapter(adapter);

        //load the data here
        getCountiesSubCountiesOnline();
//            silly();
        explore.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int county_index= (int) county_spinner.getSelectedItemId();

                if (county_index==0){
//                    Toast.makeText(getApplicationContext(),"Please select a county before continuing",Toast.LENGTH_SHORT).show();
                    Snackbar.make( v,"Please select a county before continuing", Snackbar.LENGTH_LONG).show();
                }else{
                    //launch the other activit with the intent extra sa the county index
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("county_id",county_index);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });
    }


    public void getCountiesSubCountiesOnline(){
//        NetController client=new NetController();
        RequestParams params=new RequestParams();

        AsyncHttpClient client=new AsyncHttpClient(true,80,443);
        client.addHeader("Accept", "application/json");
        client.get(CoreUtils.base_url+"home_data",params,new JsonHttpResponseHandler(){



            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

//                Toast.makeText(getApplicationContext(),"Success started",Toast.LENGTH_SHORT).show();
                try {
//                    Toast.makeText(getApplicationContext(),"started saving",Toast.LENGTH_SHORT).show();

                    JSONArray counties=response.getJSONArray("counties");
                   for (int i=0; i<counties.length();i++){
                       JSONObject obj = counties.getJSONObject(i);
                       County county=new County();
                       county.id = obj.getInt("id");
                       county.name=obj.getString("name");
                       county.county_no=obj.getInt("county_no");
                       county.save();
                   }

                   JSONArray sub_counties=response.getJSONArray("sub_counties");
                   for (int a=0;a<sub_counties.length();a++){
                       JSONObject object=sub_counties.getJSONObject(a);

                       SubCounty sub=new SubCounty();
                       sub.id=object.getInt("id");
                       sub.name=object.getString("name");
                       sub.county_id=object.getInt("county_id");
                       sub.save();
                   }


                } catch (JSONException e) {
                    e.printStackTrace();
//                    Toast.makeText(getApplicationContext(),"catch error",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(getApplicationContext(),CoreUtils.base_url,Toast.LENGTH_SHORT).show();
                Log.e("error", errorResponse.toString());
            }
        });
    }

    public void silly(){
        AsyncHttpClient client=new AsyncHttpClient(true,80,443);
        client.addHeader("Accept", "application/json");
        RequestParams params=new RequestParams();


        client.get(CoreUtils.base_url+"silly",params,new JsonHttpResponseHandler(){


            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);

                Toast.makeText(getApplicationContext(),"String",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(getApplicationContext(),CoreUtils.base_url,Toast.LENGTH_SHORT).show();
                Log.e("error", errorResponse.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e("error",responseString);
                Toast.makeText(getApplicationContext(),responseString, Toast.LENGTH_SHORT).show();
            }
        });
    }

}