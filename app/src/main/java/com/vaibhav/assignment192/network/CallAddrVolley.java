package com.vaibhav.assignment192.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.vaibhav.assignment192.App;
import com.vaibhav.assignment192.utils.CommonUtilities;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

public class CallAddrVolley extends AsyncTask<Void, Void, Void> {

    private static String TAG = "CallAddr";
    private Context context;
    private Map<String, String> paramss;
    private OnWebServiceResult resultListener;
    private CommonUtilities.SERVICE_TYPE Servicetype;
    private String url;
    private int method;


    public CallAddrVolley(Context context, Map<String, String> params, int method, String url, CommonUtilities.SERVICE_TYPE Servicetype, OnWebServiceResult resultListener) {
        this.context = context;
        this.paramss = params;
        this.url = url;
        this.resultListener = resultListener;
        this.Servicetype = Servicetype;
        this.method = method;
        Log.e("size", "size= " + paramss.size());
    }


    @Override
    protected Void doInBackground(Void... params) {
        StringRequest myReq = new StringRequest(method,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, response);
                        try {
                            resultListener.getWebResponse(response, Servicetype);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                    }
                }) {

            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {

                Log.e("params", "params= " + paramss.size());
                Log.e(TAG, "Url= " + url + paramss.toString());
                return paramss;
            }
        };
        String tag_json_obj = "jobj_req";
        App.getmInstance().addToRequestQueue(myReq,
                tag_json_obj);
        return null;
    }

}
