package com.example.barcic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Adapter.OnItemClickListener {

    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_NAME = "AlbumName";
    public static final String EXTRA_TITLE = "Title";

    private static final String URL_DATA = "https://api.deezer.com/search?redirect_uri=http%253A%252F%252Fguardian.mashape.com%252Fcallback&q=prljavo%20kazali%C5%A1te&index=25";
    private List<ListItem> listItems;

    private RecyclerView recyclerView;
    private Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        listItems = new ArrayList<>();



        loadRecyclerViewData();


    }

    private void loadRecyclerViewData(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                      progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(s);

                            JSONArray array = jsonObject.getJSONArray("data");

                            for (int i = 0; i<array.length();i++){

                                JSONObject o  = array.getJSONObject(i);
                                JSONObject album = o.getJSONObject("album");
                                JSONObject artist = o.getJSONObject("artist");


                                ListItem item = new ListItem(
                                        album.getString("title"),
                                        artist.getString("name"),
                                        album.getString("cover_medium")
                                );

                                listItems.add(item);

                        }

                                adapter = new Adapter(listItems,getApplicationContext());
                                recyclerView.setAdapter(adapter);
                                adapter.setOnItemClickListener(MainActivity.this);




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onItemClick(int position) {
        Intent detalIntent = new Intent(this,SecondActivity.class);
        ListItem clickedItem = listItems.get(position);

        detalIntent.putExtra(EXTRA_URL, clickedItem.getImgUrl());
        detalIntent.putExtra(EXTRA_NAME,clickedItem.getHead());
        detalIntent.putExtra(EXTRA_TITLE,clickedItem.getDesc());

        startActivity(detalIntent);



    }
}
