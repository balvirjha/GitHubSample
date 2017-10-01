package com.cognitiveclouds.balvier.githubsample.viewmodals;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cognitiveclouds.balvier.githubsample.modals.starredreposmodals.UserStarredRepo;
import com.cognitiveclouds.balvier.githubsample.modals.watcherreposmodals.UserReposWatching;
import com.cognitiveclouds.balvier.githubsample.repositories.UserWatcherReposrepository;

import java.util.List;

import okhttp3.Cache;

/**
 * Created by Balvier on 9/29/2017.
 */

public class UserWatcherReposViewModel extends ViewModel {

    private LiveData<List<UserReposWatching>> listLiveData;
    private UserWatcherReposrepository userWatcherReposrepository;

    public UserWatcherReposViewModel() {
        userWatcherReposrepository = new UserWatcherReposrepository();
    }

    public void init(String access_token, Cache cache) {
        listLiveData = userWatcherReposrepository.getUserReposList(access_token, cache);
    }

    public LiveData<List<UserReposWatching>> getUserReposWatchingLiveData() {
        return listLiveData;
    }
}
