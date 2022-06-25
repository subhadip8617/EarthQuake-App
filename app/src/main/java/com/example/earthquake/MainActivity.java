package com.example.earthquake;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<QuakeClass> arr = new ArrayList<QuakeClass>();
    ListView listView;
    private String USGS_REQUEST_URL =  "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";
    private String urlResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // FETCHING DATA FROM URL

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, USGS_REQUEST_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                urlResponse = response.toString();
                arr = QueryUtils.extractData(urlResponse);
                listView = (ListView) findViewById(R.id.listView);
                QuakeAdapter ad = new QuakeAdapter(MainActivity.this,R.layout.shapecell,arr);
                listView.setAdapter(ad);
                Log.d("tag","API FETCHED SUCCESSFULLY");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag","SOMETHING WENT WRONG");
            }
        });

        requestQueue.add(jsonObjectRequest);

        /*
        arr.add("Delhi");
        arr.add("Mumbai");
        arr.add("Kolkata");
        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> ad=new<String> ArrayAdapter(this, android.R.layout.simple_list_item_1,arr);
        listView.setAdapter(ad);*/
        /*
        arr.add(new QuakeClass((float) 5.5,"Kolkata","Feb 24,22"));
        arr.add(new QuakeClass((float) 5.5,"Mumbai","Feb 24,22"));
        arr.add(new QuakeClass((float) 5.5,"Delhi","Feb 24,22"));
        arr.add(new QuakeClass((float) 19.5,"TechnoItDept","Feb 24,22"));*/


    }
}