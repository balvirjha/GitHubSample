package com.cognitiveclouds.balvier.githubsample.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.cognitiveclouds.balvier.githubsample.ApplicationClass;
import com.cognitiveclouds.balvier.githubsample.Utils;
import com.cognitiveclouds.balvier.githubsample.databaseoperations.UpdateDataTables;
import com.cognitiveclouds.balvier.githubsample.modals.starredreposmodals.UserStarredRepo;
import com.cognitiveclouds.balvier.githubsample.restclients.UserStarrededRepos;

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
 * Created by Balvier on 9/30/2017.
 */

public class UserStarredRepositories implements UserStarrededRepos.UserStarrdedReposSuccessResponse {

    final MutableLiveData<List<UserStarredRepo>> data = new MutableLiveData<>();

    Observable<List<UserStarredRepo>> listObservable = Observable.create(new ObservableOnSubscribe<List<UserStarredRepo>>() {
        @Override
        public void subscribe(ObservableEmitter<List<UserStarredRepo>> e) throws Exception {
            e.onNext(UpdateDataTables.getInstance(ApplicationClass.getApplicationConotext()).getWholeStarringRepoTable());
        }
    }).subscribeOn(Schedulers.io());


    public LiveData<List<UserStarredRepo>> getStarredReposList(final String access_token, final Cache cache) {
        listObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<UserStarredRepo>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<UserStarredRepo> userStarredRepos) {
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
            Observable<List<UserStarredRepo>> observable = Observable.create(new ObservableOnSubscribe<List<UserStarredRepo>>() {
                @Override
                public void subscribe(ObservableEmitter<List<UserStarredRepo>> e) throws Exception {
                    new UserStarrededRepos().fetchUserStarredReposProfile(UserStarredRepositories.this, access_token,
                            cache);
                }
            }).subscribeOn(Schedulers.io());
            observable.subscribe();
        }
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
