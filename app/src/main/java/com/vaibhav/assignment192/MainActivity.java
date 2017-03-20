package com.vaibhav.assignment192;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.vaibhav.assignment192.adapter.DataAdapter;
import com.vaibhav.assignment192.models.DataHandler;
import com.vaibhav.assignment192.network.CallAddrVolley;
import com.vaibhav.assignment192.network.NetworkStatus;
import com.vaibhav.assignment192.network.OnWebServiceResult;
import com.vaibhav.assignment192.utils.CommonUtilities;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnWebServiceResult {

    String url = "http://api.themoviedb.org/3/movie/tt0816692/credits?api_key=8496be0b2149805afa458ab8ec27560c";
    List<DataHandler> model = new ArrayList<>();
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        hitRequest();
    }

    private void hitRequest() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("page", "1");
        if (NetworkStatus.getInstance(this).isConnectedToInternet()) {
            CallAddrVolley call = new CallAddrVolley(this, parameters, Request.Method.GET, url, CommonUtilities.SERVICE_TYPE.GET_DATA, this);
            call.execute();
        } else {
            Toast.makeText(this, "No Network!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getWebResponse(String result, CommonUtilities.SERVICE_TYPE type) {
        Log.e("res", "type= " + type + " res= " + result);
        try {
            JSONObject obj = new JSONObject(result);
            JSONArray arr = obj.getJSONArray("cast");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject jobj = arr.getJSONObject(i);
                DataHandler handler = new DataHandler();
                handler.setCast_id(jobj.getInt("cast_id"));
                handler.setOrder(jobj.getInt("order"));
                handler.setName(jobj.getString("name"));
                handler.setCharacter(jobj.getString("character"));
                model.add(handler);
            }
            DataAdapter adapter = new DataAdapter(this, model);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
