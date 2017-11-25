package com.cognitiveclouds.balvier.githubsample.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.cognitiveclouds.balvier.githubsample.ApplicationClass;
import com.cognitiveclouds.balvier.githubsample.Utils;
import com.cognitiveclouds.balvier.githubsample.databaseoperations.UpdateDataTables;
import com.cognitiveclouds.balvier.githubsample.modals.starredreposmodals.UserStarredRepo;
import com.cognitiveclouds.balvier.githubsample.modals.watcherreposmodals.UserReposWatching;
import com.cognitiveclouds.balvier.githubsample.restclients.UserWatcherRepos;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;

/**
 * Created by Balvier on 9/29/2017.
 */

public class UserWatcherReposrepository implements UserWatcherRepos.UserWatcherReposSuccessResponse {
    final MutableLiveData<List<UserReposWatching>> data = new MutableLiveData<>();

    Observable<List<UserReposWatching>> listObservable = Observable.create(new ObservableOnSubscribe<List<UserReposWatching>>() {
        @Override
        public void subscribe(ObservableEmitter<List<UserReposWatching>> e) throws Exception {
            e.onNext(UpdateDataTables.getInstance(ApplicationClass.getApplicationConotext()).getWholeWatchingRepoTable());
        }
    }).subscribeOn(Schedulers.io());

    public LiveData<List<UserReposWatching>> getUserReposList(String access_token, Cache cache) {
        listObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<UserReposWatching>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<UserReposWatching> userStarredRepos) {
                        data.setValue(userStarredRepos);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        if (Utils.isNetworkAvailable()) {
            new UserWatcherRepos().fetchUserWatchingReposProfile(this, access_token,
                    cache);
        }
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
