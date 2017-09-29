package com.cognitiveclouds.balvier.githubsample.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.cognitiveclouds.balvier.githubsample.modals.watcherreposmodals.UserReposWatching;
import com.cognitiveclouds.balvier.githubsample.restclients.UserWatcherRepos;

import java.util.List;

import okhttp3.Cache;

/**
 * Created by Balvier on 9/29/2017.
 */

public class UserWatcherReposrepository implements UserWatcherRepos.UserWatcherReposSuccessResponse {
    final MutableLiveData<List<UserReposWatching>> data = new MutableLiveData<>();

    public LiveData<List<UserReposWatching>> getUserReposList(String access_token, Cache cache) {
        new UserWatcherRepos().fetchUserWatchingReposProfile(this, access_token,
                cache);
        return data;
    }

    @Override
    public void userWatcherReposSuccessResponse(List<UserReposWatching> userReposWatchings) {
        data.setValue(userReposWatchings);
    }

    @Override
    public void userWatcherReposErrorResponse(String message) {

    }
}
