package com.cognitiveclouds.balvier.githubsample.restclients;

import com.cognitiveclouds.balvier.githubsample.Utils;
import com.cognitiveclouds.balvier.githubsample.modals.AccessToken;
import com.cognitiveclouds.balvier.githubsample.modals.GitHubConstants;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Balvier on 9/29/2017.
 */

public class GitHubAuthenticator implements Callback<AccessToken> {
    GitAuthenticatorResponse gitAuthenticatorResponse;

    public void authenticateClient(GitAuthenticatorResponse gitAuthenticatorResponse, String code) {
        this.gitAuthenticatorResponse = gitAuthenticatorResponse;
        if (Utils.isNetworkAvailable()) {
            GitHubClient.getGitHubAPIAuthenticatorClient()
                    .getAccessToken(GitHubConstants.getClientId(), GitHubConstants.getClientSecret(), code)
                    .enqueue(this);
        }
    }

    @Override
    public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
        if (response.isSuccessful() && response.code() == 200) {
            if (gitAuthenticatorResponse != null) {
                gitAuthenticatorResponse.successResponse(response.body());
            }
        } else {
            try {
                gitAuthenticatorResponse.errorResponse(response.errorBody().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure(Call<AccessToken> call, Throwable t) {
        gitAuthenticatorResponse.errorResponse(t.getMessage());
    }

    public interface GitAuthenticatorResponse {
        public void successResponse(AccessToken userProfile);

        public void errorResponse(String message);
    }
}
