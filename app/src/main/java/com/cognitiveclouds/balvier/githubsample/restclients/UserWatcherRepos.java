package com.cognitiveclouds.balvier.githubsample.restclients;

import com.cognitiveclouds.balvier.githubsample.ApplicationClass;
import com.cognitiveclouds.balvier.githubsample.Utils;
import com.cognitiveclouds.balvier.githubsample.databaseoperations.UpdateDataTables;
import com.cognitiveclouds.balvier.githubsample.modals.starredreposmodals.UserStarredRepo;
import com.cognitiveclouds.balvier.githubsample.modals.watcherreposmodals.UserReposWatching;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Balvier on 9/29/2017.
 */

public class UserWatcherRepos/* implements Callback<List<UserReposWatching>>*/ {
    UserWatcherReposSuccessResponse userWatcherReposSuccessResponse;

    public void fetchUserWatchingReposProfile(final UserWatcherReposSuccessResponse userWatcherReposSuccessResponse,
                                              final String access_token, final Cache cache) {
        this.userWatcherReposSuccessResponse = userWatcherReposSuccessResponse;
        if (access_token != null && Utils.isNetworkAvailable()) {
            Observable<List<UserReposWatching>> observable = GitHubClient.getGitHubAPI(cache).getWatchingRepos(access_token).subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread());

            observable.
                    subscribe(new Observer<List<UserReposWatching>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(List<UserReposWatching> userStarredRepos) {
                            if (userWatcherReposSuccessResponse != null) {
                                UpdateDataTables.getInstance(ApplicationClass.getApplicationConotext()).insertWholeWatchingRepoTable(userStarredRepos);
                                userWatcherReposSuccessResponse.userWatcherReposSuccessResponse(userStarredRepos);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            if (userWatcherReposSuccessResponse != null) {
                                userWatcherReposSuccessResponse.userWatcherReposErrorResponse("Error fetching the watched list");
                            }
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    /* @Override
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
 */
    public interface UserWatcherReposSuccessResponse {
        public void userWatcherReposSuccessResponse(List<UserReposWatching> userProfile);

        public void userWatcherReposErrorResponse(String message);
    }
}
