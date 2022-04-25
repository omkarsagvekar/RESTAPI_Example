package com.example.restapiexample2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MainModel> arrayList;
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        arrayList = new ArrayList<>();
        String url = "https://jsonplaceholder.typicode.com/posts";

        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    for(int i=0; i<response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String title = jsonObject.getString("title");
                        String body = jsonObject.getString("body");

                        MainModel mainModel = new MainModel();
                        mainModel.setTitle(title);
                        mainModel.setBody(body);
                        arrayList.add(mainModel);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                mainAdapter = new MainAdapter(MainActivity.this, arrayList);
                recyclerView.setAdapter(mainAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(jsonRequest);
    }
}