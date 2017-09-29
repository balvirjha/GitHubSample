package com.cognitiveclouds.balvier.githubsample.viewmodals;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cognitiveclouds.balvier.githubsample.modals.starredreposmodals.UserStarredRepo;
import com.cognitiveclouds.balvier.githubsample.repositories.UserStarredRepositories;

import java.util.List;

import okhttp3.Cache;

/**
 * Created by Balvier on 9/30/2017.
 */

public class UserStarredReposViewModel extends ViewModel {

    private LiveData<List<UserStarredRepo>> liveData;
    private UserStarredRepositories userStarredRepositories;

    public UserStarredReposViewModel() {
        userStarredRepositories = new UserStarredRepositories();

    }

    public void init(String access_token, Cache cache) {
        liveData = userStarredRepositories.getStarredReposList(access_token, cache);

    }

    public LiveData<List<UserStarredRepo>> getUserStarredReposWatchingLiveData() {
        return liveData;
    }

}
