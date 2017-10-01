package com.cognitiveclouds.balvier.githubsample.modals;

import android.os.Parcel;
import android.os.Parcelable;

import com.cognitiveclouds.balvier.githubsample.modals.watcherreposmodals.UserReposWatching;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Balvier on 9/30/2017.
 */

public class UserWatchingListParcelable implements Serializable {
    List<UserReposWatching> userReposWatchingArrayList;

    public List<UserReposWatching> getUserReposWatchingArrayList() {
        return userReposWatchingArrayList;
    }

    public void setUserReposWatchingArrayList(List<UserReposWatching> userReposWatchingArrayList) {
        this.userReposWatchingArrayList = userReposWatchingArrayList;
    }
}
