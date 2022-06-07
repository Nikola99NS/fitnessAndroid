package com.example.gymandlifestyle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gymandlifestyle.databinding.ActivityTreneriListBinding;
//import com.example.gymandlifestyle.databinding.ActivityTreneriBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import classes.ListAdapterTreneri;
import classes.Trener;
import db.DBHelper5;

public class TreneriListActivity extends AppCompatActivity {


    private Button zakazi;
    private ActivityTreneriListBinding binding;
    private ArrayList<Trener> treneri = new ArrayList<>();

    DBHelper5 db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTreneriListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = new DBHelper5(this);
//        ArrayList<Trener> treneri = new ArrayList<>();

        zakazi = findViewById(R.id.zakazi);

        Intent i = this.getIntent();
        String gym = i.getStringExtra("gym");
        String username = i.getStringExtra("username");
// ...

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://my-json-server.typicode.com/Nikola99NS/android/db";



// Request a string response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String g = gym;
                            if (gym.equals("Non stop")) {
                                g = "NonStop";
                            }

                            JSONArray jsonArray = response.getJSONArray(g);
                            int j = 1;
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject object = jsonArray.getJSONObject(i);
                                String name = object.getString("ime");
                                String age = object.getString("godine");
                                String kursevi = object.getString("kursevi");
                                Trener t = new Trener(name, age, kursevi, j, g, username);
                                j++;

                                treneri.add(t);

                            }

                            ListAdapterTreneri listAdapter = new ListAdapterTreneri(TreneriListActivity.this, treneri);
                            listAdapter.setDB(db);

                            binding.lista.setAdapter(listAdapter);
                            binding.lista.setClickable(true);



                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


// Add the request to the RequestQueue.
        queue.add(stringRequest);



    }



    private void postRequest(){
        RequestQueue requestQueue = Volley.newRequestQueue(TreneriListActivity.this);
        String url = "";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String,String>();
                return params;
            }
            @Override
            public Map<String,String> getHeaders(){
                Map<String,String> params = new HashMap<String,String>();

                return params;
            }

        };
        requestQueue.add(stringRequest);
    }

}