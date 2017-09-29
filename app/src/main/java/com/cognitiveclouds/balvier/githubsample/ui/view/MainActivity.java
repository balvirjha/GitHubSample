package com.cognitiveclouds.balvier.githubsample.ui.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cognitiveclouds.balvier.githubsample.R;
import com.cognitiveclouds.balvier.githubsample.Utils;
import com.cognitiveclouds.balvier.githubsample.modals.AccessToken;
import com.cognitiveclouds.balvier.githubsample.modals.GitHubConstants;
import com.cognitiveclouds.balvier.githubsample.modals.UserProfile;
import com.cognitiveclouds.balvier.githubsample.restclients.GitHubAuthenticator;
import com.cognitiveclouds.balvier.githubsample.restclients.ProfileFetcher;

import java.io.File;

import okhttp3.Cache;

public class MainActivity extends AppCompatActivity implements LoginFragment.GitHubLogin,
        GitHubAuthenticator.GitAuthenticatorResponse, ProfileFetcher.UserProfileSuccessResponse,
        BottomNavigationView.OnNavigationItemSelectedListener {

    LoginFragment loginFragment;
    UserProfileFragment userProfileFragment;
    UserProfile userProfile;
    BottomNavigationView bottomNavigationView;
    UserWatchingReposFragment userWatchingReposFragment;
    UserStarredReposFragment userStarredReposFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private void openUserWatchingReposFragment() {
       /* if (userWatchingReposFragment == null || (userWatchingReposFragment != null && !userWatchingReposFragment.isAdded())) {*/
        userWatchingReposFragment = new UserWatchingReposFragment();
        getSupportFragmentManager().beginTransaction().
                add(R.id.framelayout, userWatchingReposFragment, UserWatchingReposFragment.class.getSimpleName()).commit();
        // }
    }

    private void openUserStarredReposFragment() {
      /*  if (userStarredReposFragment == null || (userStarredReposFragment != null && !userStarredReposFragment.isAdded())) {*/
        userStarredReposFragment = new UserStarredReposFragment();
        getSupportFragmentManager().beginTransaction().
                add(R.id.framelayout, userStarredReposFragment, UserStarredReposFragment.class.getSimpleName()).commit();
        // }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (Utils.isNetworkAvailable()) {
            Uri uri = getIntent().getData();
            if (uri != null) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                String code = uri.getQueryParameter("code");
                if (code != null) {
                    Log.e(GitHubConstants.TAG, "Recieved code = " + code);
                    getSharedPreferences(GitHubConstants.getSharedPref(), MODE_PRIVATE).edit().putString("code", code).commit();
                    Log.e(GitHubConstants.TAG, "SharedPredf saved " + getSharedPreferences(GitHubConstants.getSharedPref(), MODE_PRIVATE).getString("code", null));
                    new GitHubAuthenticator().authenticateClient(this, getSharedPreferences(GitHubConstants.getSharedPref(), MODE_PRIVATE).getString("code", null));
                    closeLoginDialog();
                }
                return;
            }
            if (getSharedPreferences(GitHubConstants.getSharedPref(), MODE_PRIVATE) != null &&
                    getSharedPreferences(GitHubConstants.getSharedPref(), MODE_PRIVATE).getString("code", null) != null) {

                openLoginPage();
                Log.e(GitHubConstants.TAG, "SharedPredf present opening login page");
            } else {
                Log.e(GitHubConstants.TAG, "SharedPredf not present opening dialog");
                showLoginDialog();
            }
        } else {
            openUserWatchingReposFragment();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeLoginDialog();
    }

    @Override
    public void onBackPressed() {

        if ((loginFragment != null && loginFragment.isAdded() && loginFragment.isVisible())) {
            finish();
        }
        super.onBackPressed();
    }

    private void showLoginDialog() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (loginFragment == null || (loginFragment != null && !loginFragment.isAdded() && !loginFragment.isVisible())) {
                    loginFragment = new LoginFragment();
                    loginFragment.setGitHubLoginCallback(MainActivity.this);
                    loginFragment.show(getSupportFragmentManager(), LoginFragment.class.getSimpleName());
                }
            }
        }, 200);

    }

    private void showUserProfileDialog(final UserProfile userProfile) {
        if (userProfile != null && userProfileFragment == null || (userProfileFragment != null && !userProfileFragment.isAdded() && !userProfileFragment.isVisible())) {
            userProfileFragment = new UserProfileFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("userprofile", userProfile);
            userProfileFragment.setArguments(bundle);
            userProfileFragment.show(getSupportFragmentManager(), LoginFragment.class.getSimpleName());
        }
    }

    private void dismissUserProfileDialog() {
        if (userProfileFragment != null && userProfileFragment.isVisible() && this != null && !this.isFinishing()) {
            userProfileFragment.dismiss();
        }
    }

    private void openLoginPage() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(new StringBuilder()
                .append(GitHubConstants.getBaseAuthUrl())
                .append("authorize?client_id=")
                .append(GitHubConstants.getClientId())
                .append("&scope=repo, user, public_repo, private_repo&redirect_uri=")
                .append(GitHubConstants.getRedirectUri()).toString())));
    }


    private void closeLoginDialog() {
        if (loginFragment != null && loginFragment.isAdded()
                && loginFragment.isVisible() && this != null && !this.isFinishing()) {
            loginFragment.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.showUserProfile) {
            showUserProfileDialog(userProfile);
        }
        return true;
    }

    @Override
    public void openGitHubLoginPage() {
        openLoginPage();
    }

    @Override
    public void successResponse(AccessToken accessToken) {
        if (this != null && !this.isFinishing()) {
            Toast.makeText(MainActivity.this, "Got It!", Toast.LENGTH_SHORT).show();
            Log.e(GitHubConstants.TAG, "access token = " + accessToken.getAccessToken());
            getSharedPreferences(GitHubConstants.getSharedPref(), MODE_PRIVATE).edit().putString("access_token", accessToken.getAccessToken()).commit();
            Log.e(GitHubConstants.TAG, "access tokenn SharedPredf saved " + getSharedPreferences(GitHubConstants.getSharedPref(), MODE_PRIVATE).getString("access_token", null));
            new ProfileFetcher().fetchUserProfile(this,
                    getSharedPreferences(GitHubConstants.getSharedPref(), MODE_PRIVATE).getString("access_token", null),
                    new Cache(new File(this.getCacheDir(), "responses"), 10 * 1024 * 1024));
            openUserWatchingReposFragment();
        }
    }

    @Override
    public void errorResponse(String mesdage) {
        if (this != null && !this.isFinishing()) {
            Toast.makeText(this, "Fetching access token failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void userProfileSuccessResponse(UserProfile userProfile) {
        Log.e(GitHubConstants.TAG, "user profile success");
        if (this != null && !this.isFinishing()) {
            Toast.makeText(MainActivity.this, "user profile success", Toast.LENGTH_SHORT).show();
            this.userProfile = userProfile;
            showUserProfileDialog(userProfile);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dismissUserProfileDialog();
                }
            }, 3000);
        }
    }

    @Override
    public void userProfileErrorResponse(String message) {
        Log.e(GitHubConstants.TAG, "user profile failed");
        if (this != null && !this.isFinishing()) {
            Toast.makeText(MainActivity.this, "user profile failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_watcher:
                openUserWatchingReposFragment();
                break;
            case R.id.menu_starred:
                openUserStarredReposFragment();
                break;
            case R.id.menu_aisehi:

        }
        return true;
    }

}
