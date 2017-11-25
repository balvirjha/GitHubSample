package com.cognitiveclouds.balvier.githubsample.restclients;

import com.cognitiveclouds.balvier.githubsample.ApplicationClass;
import com.cognitiveclouds.balvier.githubsample.Utils;
import com.cognitiveclouds.balvier.githubsample.databaseoperations.UpdateDataTables;
import com.cognitiveclouds.balvier.githubsample.modals.starredreposmodals.UserStarredRepo;
import com.cognitiveclouds.balvier.githubsample.repositories.UserStarredRepositories;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Balvier on 9/30/2017.
 */

public class UserStarrededRepos/* implements Callback<List<UserStarredRepo>> */{
    UserStarrdedReposSuccessResponse userStarrdedReposSuccessResponse;

    public void fetchUserStarredReposProfile(final UserStarrdedReposSuccessResponse userStarrdedReposSuccessResponse,
                                             final String access_token, final Cache cache) {
        this.userStarrdedReposSuccessResponse = userStarrdedReposSuccessResponse;
        if (access_token != null && Utils.isNetworkAvailable()) {

            GitHubClient.getGitHubAPI(cache).getStarredRepos(access_token).subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread()).
                    subscribe(new Observer<List<UserStarredRepo>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(List<UserStarredRepo> userStarredRepos) {
                            if (userStarredRepos != null && userStarrdedReposSuccessResponse != null) {
                                UpdateDataTables.getInstance(ApplicationClass.getApplicationConotext()).insertWholeStarringRepoTable(userStarredRepos);
                                userStarrdedReposSuccessResponse.userStarddedReposSuccessResponse(userStarredRepos);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            if (userStarrdedReposSuccessResponse != null) {
                                userStarrdedReposSuccessResponse.userStarrdedReposErrorResponse("Error fetching the starred list");
                            }
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

   /* @Override
    public void onResponse(Call<List<UserStarredRepo>> call, Response<List<UserStarredRepo>> response) {
        if (response.isSuccessful() && response.code() == 200) {
            if (userStarrdedReposSuccessResponse != null) {
                UpdateDataTables.getInstance(ApplicationClass.getApplicationConotext()).insertWholeStarringRepoTable(response.body());
                userStarrdedReposSuccessResponse.userStarddedReposSuccessResponse(response.body());
            }
        } else {
            if (userStarrdedReposSuccessResponse != null) {
                try {
                    userStarrdedReposSuccessResponse.userStarrdedReposErrorResponse(response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onFailure(Call<List<UserStarredRepo>> call, Throwable t) {
        if (userStarrdedReposSuccessResponse != null) {
            userStarrdedReposSuccessResponse.userStarrdedReposErrorResponse(t.getMessage());
        }
    }*/

    public interface UserStarrdedReposSuccessResponse {
        public void userStarddedReposSuccessResponse(List<UserStarredRepo> userProfile);

        public void userStarrdedReposErrorResponse(String message);
    }
}
