package com.cognitiveclouds.balvier.githubsample.restclients;

import com.cognitiveclouds.balvier.githubsample.modals.UserProfile;

import java.io.IOException;

import okhttp3.Cache;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Balvier on 9/29/2017.
 */

public class ProfileFetcher implements Callback<UserProfile> {
    UserProfileSuccessResponse userProfileSuccessResponse;

    public void fetchUserProfile(UserProfileSuccessResponse gitAuthenticatorResponse, String access_token, Cache cache) {
        this.userProfileSuccessResponse = gitAuthenticatorResponse;
        if (access_token != null) {
            GitHubClient.getGitHubAPI(cache).getUserProfile(access_token).enqueue(this);
        }
    }

    @Override
    public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
        if (response.isSuccessful() && response.code() == 200) {
            if (userProfileSuccessResponse != null) {
                userProfileSuccessResponse.userProfileSuccessResponse(response.body());
            }
        } else {
            if (userProfileSuccessResponse != null) {
                try {
                    userProfileSuccessResponse.userProfileErrorResponse(response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onFailure(Call<UserProfile> call, Throwable t) {
        t.printStackTrace();
        if (userProfileSuccessResponse != null) {
            userProfileSuccessResponse.userProfileErrorResponse(t.getMessage());
        }
    }

    public interface UserProfileSuccessResponse {
        public void userProfileSuccessResponse(UserProfile userProfile);

        public void userProfileErrorResponse(String message);
    }
}
