package com.cognitiveclouds.balvier.githubsample.databaseoperations;

import android.content.Context;
import android.util.Log;

import com.cognitiveclouds.balvier.githubsample.modals.GitHubConstants;
import com.cognitiveclouds.balvier.githubsample.modals.UserProfile;
import com.cognitiveclouds.balvier.githubsample.modals.starredreposmodals.UserStarredRepo;
import com.cognitiveclouds.balvier.githubsample.modals.watcherreposmodals.UserReposWatching;

import java.util.List;

/**
 * Created by Balvier on 9/30/2017.
 */

public class UpdateDataTables {

    private static UpdateDataTables updateDataTables;
    private Context context;

    private UpdateDataTables(Context contexta) {
        context = contexta;
    }

    public static UpdateDataTables getInstance(Context contexta) {
        if (updateDataTables == null) {
            return new UpdateDataTables(contexta);
        } else {
            return updateDataTables;
        }
    }

    public synchronized void insertUserTable(final UserProfile userProfile) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseHandler.getInstance(context).addUser(userProfile);
            }
        }).start();
    }


    public synchronized void updateUserTable(final UserProfile userProfile) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseHandler.getInstance(context).updateUserProfile(userProfile);
            }
        }).start();

    }

    public synchronized UserProfile getUserTable() {
        return DatabaseHandler.getInstance(context).getUserProfile();
    }

    public synchronized void deleteUserTable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseHandler.getInstance(context).deleteUserProfile();
            }
        }).start();

    }

    public synchronized void insertWatchingRepoTable(final UserReposWatching userReposWatching) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseHandler.getInstance(context).addWatchinRepo(userReposWatching);
            }
        }).start();
    }

    public synchronized void insertWholeWatchingRepoTable(final List<UserReposWatching> userReposWatchingList) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e(GitHubConstants.TAG, "inserting watching repo");
                DatabaseHandler.getInstance(context).addWholeListWatchinRepo(userReposWatchingList);
            }
        }).start();
    }

    public synchronized List<UserReposWatching> getWholeWatchingRepoTable() {
        Log.e(GitHubConstants.TAG, "inserting watching repo");
        return DatabaseHandler.getInstance(context).getAllWatchinRepo();

    }

    public synchronized void deleteWatchingRepoTable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseHandler.getInstance(context).deleteALlUserReposWatching();
            }
        }).start();
    }

    public synchronized void insertWholeStarringRepoTable(final List<UserStarredRepo> userStarredRepoList) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e(GitHubConstants.TAG, "inserting starring repo");
                DatabaseHandler.getInstance(context).addWholeListStarringRepo(userStarredRepoList);
            }
        }).start();
    }

    public synchronized List<UserStarredRepo> getWholeStarringRepoTable() {
        Log.e(GitHubConstants.TAG, "getting starring repo");
        return DatabaseHandler.getInstance(context).getAllStarringRepo();

    }

    public synchronized void deleteStarringRepoTable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseHandler.getInstance(context).deleteALlUserReposStarring();
            }
        }).start();
    }

}
