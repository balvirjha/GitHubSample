package com.cognitiveclouds.balvier.githubsample.restclients;

import com.cognitiveclouds.balvier.githubsample.modals.AccessToken;
import com.cognitiveclouds.balvier.githubsample.modals.UserProfile;
import com.cognitiveclouds.balvier.githubsample.modals.starredreposmodals.UserStarredRepo;
import com.cognitiveclouds.balvier.githubsample.modals.watcherreposmodals.UserReposWatching;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Balvier on 9/28/2017.
 */

public interface GitHubAPI {

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    Call<AccessToken> getAccessToken(
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("code") String code
    );

    @Headers("Accept: application/json")
    @GET("user")
    Call<UserProfile> getUserProfile(@Query("access_token") String access_token);

    @Headers("Accept: application/json")
    @GET("/user/subscriptions")
    Observable<List<UserReposWatching>> getWatchingRepos(@Query("access_token") String access_token);

    @Headers("Accept: application/json")
    @GET("/user/starred")
    Observable<List<UserStarredRepo>> getStarredRepos(@Query("access_token") String access_token);
}
