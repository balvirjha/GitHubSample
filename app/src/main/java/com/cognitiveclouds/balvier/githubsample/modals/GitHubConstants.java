package com.cognitiveclouds.balvier.githubsample.modals;

/**
 * Created by Balvier on 9/27/2017.
 */

public class GitHubConstants {
    private static final String REDIRECT_URI = "githubsamplefromme://balvierjha";
    private static final String CLIENT_ID = "28342937ca207f17eaef";
    private static final String CLIENT_SECRET = "52fc51ece78c80d196e6095c72adb2962706a331";
    private static final String SHARED_PREF = "code";
    public static final String TAG = "bvc";
    private static final String BASE_AUTH_URL = "https://github.com/login/oauth/";

    public static String getBaseAuthUrl() {
        return BASE_AUTH_URL;
    }

    public static String getRedirectUri() {
        return REDIRECT_URI;
    }

    public static String getClientId() {
        return CLIENT_ID;
    }

    public static String getSharedPref() {
        return SHARED_PREF;
    }

    public static String getClientSecret() {
        return CLIENT_SECRET;
    }
}
