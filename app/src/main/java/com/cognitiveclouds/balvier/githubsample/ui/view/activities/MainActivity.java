package com.cognitiveclouds.balvier.githubsample.ui.view.activities;

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
import com.cognitiveclouds.balvier.githubsample.databaseoperations.UpdateDataTables;
import com.cognitiveclouds.balvier.githubsample.modals.AccessToken;
import com.cognitiveclouds.balvier.githubsample.modals.GitHubConstants;
import com.cognitiveclouds.balvier.githubsample.modals.UserProfile;
import com.cognitiveclouds.balvier.githubsample.restclients.GitHubAuthenticator;
import com.cognitiveclouds.balvier.githubsample.restclients.ProfileFetcher;
import com.cognitiveclouds.balvier.githubsample.ui.view.fragments.LoginFragment;
import com.cognitiveclouds.balvier.githubsample.ui.view.fragments.UserProfileFragment;
import com.cognitiveclouds.balvier.githubsample.ui.view.fragments.UserStarredReposFragment;
import com.cognitiveclouds.balvier.githubsample.ui.view.fragments.UserWatchingReposFragment;

import java.io.File;

import okhttp3.Cache;

public class MainActivity extends AppCompatActivity implements LoginFragment.GitHubLogin,
        GitHubAuthenticator.GitAuthenticatorResponse, ProfileFetcher.UserProfileSuccessResponse,
        BottomNavigationView.OnNavigationItemSelectedListener {

    private LoginFragment loginFragment;
    private UserProfileFragment userProfileFragment;
    private UserProfile userProfile;
    private BottomNavigationView bottomNavigationView;
    private UserWatchingReposFragment userWatchingReposFragment;
    private UserStarredReposFragment userStarredReposFragment;
    private String fragmentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            fragmentName = savedInstanceState.getString("active_fragment");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        if (getSharedPreferences(GitHubConstants.getSharedPref(), MODE_PRIVATE).getString("code", null) != null) {
            if (fragmentName != null && fragmentName.equals(UserWatchingReposFragment.class.getSimpleName())) {
                openUserWatchingReposFragment();
            } else if (fragmentName != null && fragmentName.equals(UserStarredReposFragment.class.getSimpleName())) {
                openUserStarredReposFragment();
            }
        }
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
            if (fragmentName == null || (fragmentName != null && fragmentName.equals(UserWatchingReposFragment.class.getSimpleName()))) {
                openUserWatchingReposFragment();
            } else if (fragmentName != null && fragmentName.equals(UserStarredReposFragment.class.getSimpleName())) {
                openUserStarredReposFragment();
            }
        }
    }

    private void openUserWatchingReposFragment() {
        if (MainActivity.this != null && !MainActivity.this.isFinishing()) {
            userWatchingReposFragment = new UserWatchingReposFragment();
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.framelayout, userWatchingReposFragment, UserWatchingReposFragment.class.getSimpleName())
                    .commit();
        }
    }

    private void openUserStarredReposFragment() {
        if (MainActivity.this != null && !MainActivity.this.isFinishing()) {
            userStarredReposFragment = new UserStarredReposFragment();
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.framelayout, userStarredReposFragment, UserStarredReposFragment.class.getSimpleName())
                    .commit();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeLoginDialog();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finishAffinity();
    }

    private void showLoginDialog() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if ((MainActivity.this != null && !MainActivity.this.isFinishing()) && (loginFragment == null || (loginFragment != null && !loginFragment.isAdded() && !loginFragment.isVisible()))) {
                    loginFragment = new LoginFragment();
                    loginFragment.setGitHubLoginCallback(MainActivity.this);
                    loginFragment.show(getSupportFragmentManager(), LoginFragment.class.getSimpleName());
                }
            }
        }, 200);

    }

    private void showUserProfileDialog(final UserProfile userProfile) {
        if ((MainActivity.this != null && !MainActivity.this.isFinishing()) && ((userProfile != null && userProfileFragment == null) || (userProfileFragment != null && !userProfileFragment.isAdded() && !userProfileFragment.isVisible()))) {
            userProfileFragment = new UserProfileFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("userprofile", userProfile);
            userProfileFragment.setArguments(bundle);
            userProfileFragment.show(getSupportFragmentManager(), LoginFragment.class.getSimpleName());
        }
    }

    private void dismissUserProfileDialog() {
        if (userProfileFragment != null && userProfileFragment.isVisible() && (MainActivity.this != null && !MainActivity.this.isFinishing())) {
            userProfileFragment.dismiss();
        }
    }

    private void openLoginPage() {
        if ((MainActivity.this != null && !MainActivity.this.isFinishing())) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(new StringBuilder()
                    .append(GitHubConstants.getBaseAuthUrl())
                    .append("authorize?client_id=")
                    .append(GitHubConstants.getClientId())
                    .append("&scope=repo, user, public_repo, private_repo&redirect_uri=")
                    .append(GitHubConstants.getRedirectUri()).toString())));
        }
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
            if (Utils.isNetworkAvailable()) {
                if (userProfile != null) {
                    showUserProfileDialog(userProfile);
                } else {
                    fetchUserProfile();
                }
            } else {
                userProfile = UpdateDataTables.getInstance(this).getUserTable();
                if (userProfile != null && userProfile.getName() != null) {
                    showUserProfileDialog(userProfile);
                }
            }
        }
        return true;
    }

    @Override
    public void openGitHubLoginPage() {
        openLoginPage();
    }

    @Override
    public void successResponse(AccessToken accessToken) {
        if (this != null && !this.isFinishing() && accessToken != null && accessToken.getAccessToken() != null) {
            Toast.makeText(MainActivity.this, "Got It!", Toast.LENGTH_SHORT).show();
            Log.e(GitHubConstants.TAG, "access token = " + accessToken.getAccessToken());
            getSharedPreferences(GitHubConstants.getSharedPref(), MODE_PRIVATE).edit().putString("access_token", accessToken.getAccessToken()).commit();
            Log.e(GitHubConstants.TAG, "access tokenn SharedPredf saved " + getSharedPreferences(GitHubConstants.getSharedPref(), MODE_PRIVATE).getString("access_token", null));

            openUserWatchingReposFragment();
        }
    }

    private void fetchUserProfile() {
        new ProfileFetcher().fetchUserProfile(this,
                getSharedPreferences(GitHubConstants.getSharedPref(), MODE_PRIVATE).getString("access_token", null),
                new Cache(new File(this.getCacheDir(), "responses"), 10 * 1024 * 1024));
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
            UpdateDataTables.getInstance(this).insertUserTable(userProfile);
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
                fragmentName = UserWatchingReposFragment.class.getSimpleName();
                openUserWatchingReposFragment();
                break;
            case R.id.menu_starred:
                fragmentName = UserStarredReposFragment.class.getSimpleName();
                openUserStarredReposFragment();
                break;
            case R.id.menu_aisehi:

        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("active_fragment", fragmentName);
    }
}
