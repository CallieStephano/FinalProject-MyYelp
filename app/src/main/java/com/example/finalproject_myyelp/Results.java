package com.example.finalproject_myyelp;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


public class Results extends AppCompatActivity {

    TextView name;
    TextView number;
    TextView rating;
    TextView category;
    TextView address;
    TextView status;
    //Button next;
    int index = 0;
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.fragment_notifications);
        // SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        //  mapFragment.getMapAsync(this);
        name = findViewById(R.id.Rname);
        address = (TextView) findViewById(R.id.Raddress);
        // rating = (TextView) findViewById(R.id.Rrating);
        // category = (TextView) findViewById(R.id.Rcategory);
        number = findViewById(R.id.Rnumber);
        // status = (TextView) findViewById(R.id.Rstatus);
        //Getting data sent from Search.java


        Intent intent = getIntent();
        //String str = intent.getStringExtra("message_key");
        String find = intent.getStringExtra("message_keyZ");
        String lat = intent.getStringExtra("lat");
        String lon = intent.getStringExtra("lon");
        //getLocation(lat, lon);
        // getYelpData(str2);


        url = "https://api.yelp.com/v3/businesses/search?term=mexican&location=bocaraton";

        final RequestQueue queue = Volley.newRequestQueue(this);
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject item = jsonObject.getJSONArray("businesses").getJSONObject(0);
                            JSONArray array = jsonObject.getJSONArray("businesses");
                            if (array.length() == 0) {
                                return;
                            }

                            name.setText(item.getString("name"));
                            //address.setText(item.getJSONObject("location").getString("display_address"));
                            //rating.setText(item.getJSONObject("businesses").getString("rating"));
                            //category.setText(item.getJSONObject("categories").getString("title"));
                            number.setText(item.getString("display_phone"));
                            //number.setText(item.getJSONObject("businesses").getString("display_phone"));
                            //status.setText(item.getString("is_closed"));
                            //onMapReady(mMap);
                        } catch (Exception e) {
                            System.out.println("Didnt work");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        }) {
            //Bearer token authentication for API
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "bearer " + "5a9MOiC3ZR8H-YBsDriDXeWniXORNYhK8xT5Z2cG6JUDqpd4ohAdrMI6ioK_8RvEZQhsW5h2REDE51bvPYz9-CBIowbNX0E4v7Gm6OdLflnOKqK4i3mlEKZktxHcX3Yx");
                //return headers;
                // String auth = "Bearer" + "YBsDriDXeWniXORNYhK8xT5Z2cG6JUDqpd4ohAdrMI6ioK_8RvEZQhsW5h2REDE51bvPYz9-CBIowbNX0E4v7Gm6OdLflnOKqK4i3mlEKZktxHcX3Yx";
                // headers.put("Authorization", auth);
                return headers;
            }
        };
        queue.add(stringRequest);

        final RequestQueue queue1 = Volley.newRequestQueue(this);


    }

    public void onMapReady(){

    }
}
