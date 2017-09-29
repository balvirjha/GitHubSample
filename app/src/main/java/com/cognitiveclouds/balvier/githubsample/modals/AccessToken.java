package com.cognitiveclouds.balvier.githubsample.modals;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Balvier on 9/29/2017.
 */

public class AccessToken {
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("token_type")
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}
