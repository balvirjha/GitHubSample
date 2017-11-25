package com.cognitiveclouds.balvier.githubsample.restclients;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Balvier on 9/29/2017.
 */

public class GitHubClient {
    private static GitHubAPI gitHubAPI, gitHubAuthenticatorAPI;
    private static OkHttpClient okHttpClient;

    public static synchronized GitHubAPI getGitHubAPIAuthenticatorClient() {
        if (gitHubAuthenticatorAPI == null) {
            return new Retrofit.Builder().baseUrl("https://github.com/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).build().create(GitHubAPI.class);
        } else {
            return gitHubAuthenticatorAPI;
        }
    }

    public static synchronized GitHubAPI getGitHubAPI(Cache cache) {
        if (gitHubAPI == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .cache(cache)
                    .build();
            return new Retrofit.Builder().client(okHttpClient).baseUrl("https://api.github.com/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).build().create(GitHubAPI.class);
        } else {
            return gitHubAPI;
        }
    }

}
