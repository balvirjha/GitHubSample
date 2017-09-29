package com.cognitiveclouds.balvier.githubsample.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.cognitiveclouds.balvier.githubsample.modals.starredreposmodals.UserStarredRepo;
import com.cognitiveclouds.balvier.githubsample.restclients.UserStarrededRepos;

import java.util.List;

import okhttp3.Cache;

/**
 * Created by Balvier on 9/30/2017.
 */

public class UserStarredRepositories implements UserStarrededRepos.UserStarrdedReposSuccessResponse {

    final MutableLiveData<List<UserStarredRepo>> data = new MutableLiveData<>();

    public LiveData<List<UserStarredRepo>> getStarredReposList(String access_token, Cache cache) {
        new UserStarrededRepos().fetchUserStarredReposProfile(this, access_token,
                cache);
        return data;
    }

    @Override
    public void userStarddedReposSuccessResponse(List<UserStarredRepo> userStarredRepoList) {
        data.setValue(userStarredRepoList);
    }

    @Override
    public void userStarrdedReposErrorResponse(String message) {

    }
}
