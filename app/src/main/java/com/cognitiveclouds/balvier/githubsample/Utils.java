package com.cognitiveclouds.balvier.githubsample;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Balvier on 9/29/2017.
 */

public class Utils {

    public static boolean isNetworkAvailable() {
        NetworkInfo networkInfo = ((ConnectivityManager) ApplicationClass.getApplicationConotext().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected() ? true : false;
    }

}
