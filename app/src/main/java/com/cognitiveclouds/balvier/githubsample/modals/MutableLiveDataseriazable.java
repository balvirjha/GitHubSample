package com.cognitiveclouds.balvier.githubsample.modals;

import android.arch.lifecycle.MutableLiveData;

import com.cognitiveclouds.balvier.githubsample.modals.watcherreposmodals.UserReposWatching;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Balvier on 9/30/2017.
 */

public class MutableLiveDataseriazable implements Serializable {
    MutableLiveData<List<UserReposWatching>> listMutableLiveData;

    public MutableLiveData<List<UserReposWatching>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public void setListMutableLiveData(MutableLiveData<List<UserReposWatching>> listMutableLiveData) {
        this.listMutableLiveData = listMutableLiveData;
    }
}
