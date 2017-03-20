package com.vaibhav.assignment192.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkStatus {

    private static NetworkStatus instance = new NetworkStatus();
    private static Context context;
    private boolean connected = false;

    public static NetworkStatus getInstance(Context ctx) {
        context = ctx;
        return instance;
    }

    public boolean isConnectedToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            return true;
        }
        return false;
    }
}
