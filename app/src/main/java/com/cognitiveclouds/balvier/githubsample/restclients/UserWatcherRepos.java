package com.cognitiveclouds.balvier.githubsample.restclients;

import com.cognitiveclouds.balvier.githubsample.ApplicationClass;
import com.cognitiveclouds.balvier.githubsample.Utils;
import com.cognitiveclouds.balvier.githubsample.databaseoperations.UpdateDataTables;
import com.cognitiveclouds.balvier.githubsample.modals.watcherreposmodals.UserReposWatching;

import java.io.IOException;
import java.util.List;

import okhttp3.Cache;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Balvier on 9/29/2017.
 */

public class UserWatcherRepos implements Callback<List<UserReposWatching>> {
    UserWatcherReposSuccessResponse userWatcherReposSuccessResponse;

    public void fetchUserWatchingReposProfile(UserWatcherReposSuccessResponse userWatcherReposSuccessResponse, String access_token, Cache cache) {
        this.userWatcherReposSuccessResponse = userWatcherReposSuccessResponse;
        if (access_token != null && Utils.isNetworkAvailable()) {
            GitHubClient.getGitHubAPI(cache).getWatchingRepos(access_token).enqueue(this);
        }
    }

    @Override
    public void onResponse(Call<List<UserReposWatching>> call, Response<List<UserReposWatching>> response) {
        if (response.isSuccessful() && response.code() == 200) {
            if (userWatcherReposSuccessResponse != null) {
                UpdateDataTables.getInstance(ApplicationClass.getApplicationConotext()).insertWholeWatchingRepoTable(response.body());
                userWatcherReposSuccessResponse.userWatcherReposSuccessResponse(response.body());
            }
        } else {
            if (userWatcherReposSuccessResponse != null) {
                try {
                    userWatcherReposSuccessResponse.userWatcherReposErrorResponse(response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onFailure(Call<List<UserReposWatching>> call, Throwable t) {
        if (userWatcherReposSuccessResponse != null) {
            userWatcherReposSuccessResponse.userWatcherReposErrorResponse(t.getMessage());
        }
    }

    public interface UserWatcherReposSuccessResponse {
        public void userWatcherReposSuccessResponse(List<UserReposWatching> userProfile);

        public void userWatcherReposErrorResponse(String message);
    }
}
