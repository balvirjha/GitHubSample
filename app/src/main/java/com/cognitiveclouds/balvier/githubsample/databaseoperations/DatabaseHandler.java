package com.cognitiveclouds.balvier.githubsample.databaseoperations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cognitiveclouds.balvier.githubsample.modals.GitHubConstants;
import com.cognitiveclouds.balvier.githubsample.modals.UserProfile;
import com.cognitiveclouds.balvier.githubsample.modals.starredreposmodals.UserStarredRepo;
import com.cognitiveclouds.balvier.githubsample.modals.watcherreposmodals.UserReposWatching;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Balvier on 9/30/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "GitHubDataBase";

    public static final String TABLE_USER_PROFILE = "user_profile";
    public static final String TABLE_USER_WATCHING_REPO = "user_watching_repo";
    public static final String TABLE_USER_STARRED_REPO = "user_starred_repo";

    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_ORGANIZATION = "organization";

    public static final String KEY_REPO_ID = "repo_id";
    public static final String KEY_REPO_NAME = "repo_name";
    public static final String KEY_HTML_URL = "repo_html_url";
    public static final String KEY_REPO_DESCRIPTION = "repo_description";

    public static final String KEY_STARRED_REPO_NAME = "starred_repo_name";
    public static final String KEY_STARRED_HTML_URL = "starred_repo_html_url";
    public static final String KEY_STARRED_REPO_DESCRIPTION = "starred_repo_description";

    private static DatabaseHandler databaseHandler;

    private DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHandler getInstance(Context context) {
        if (databaseHandler == null) {
            return new DatabaseHandler(context);
        } else {
            return databaseHandler;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER_PROFILE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT," + KEY_ORGANIZATION + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);

        String CREATE_WATCHING_REPO_TABLE = "CREATE TABLE " + TABLE_USER_WATCHING_REPO + "("
                + KEY_REPO_ID + " INTEGER PRIMARY KEY," + KEY_REPO_NAME + " TEXT,"
                + KEY_HTML_URL + " TEXT," + KEY_REPO_DESCRIPTION + " TEXT" + ")";
        db.execSQL(CREATE_WATCHING_REPO_TABLE);

        String CREATE_STARRED_REPO_TABLE = "CREATE TABLE " + TABLE_USER_STARRED_REPO + "("
                + KEY_REPO_ID + " INTEGER PRIMARY KEY," + KEY_STARRED_REPO_NAME + " TEXT,"
                + KEY_STARRED_HTML_URL + " TEXT," + KEY_STARRED_REPO_DESCRIPTION + " TEXT" + ")";
        db.execSQL(CREATE_STARRED_REPO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_PROFILE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_WATCHING_REPO);

        onCreate(db);
    }

    void addStarringRepo(UserStarredRepo userStarredRepo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STARRED_REPO_NAME, userStarredRepo.getName());
        values.put(KEY_STARRED_HTML_URL, userStarredRepo.getHtmlUrl());
        values.put(KEY_STARRED_REPO_DESCRIPTION, userStarredRepo.getDescription());

        db.insert(TABLE_USER_STARRED_REPO, null, values);
        db.close();
    }

    void addWholeListStarringRepo(List<UserStarredRepo> userStarredRepoList) {
        deleteALlUserReposStarring();
        SQLiteDatabase db = this.getWritableDatabase();
        for (UserStarredRepo userStarredRepo :
                userStarredRepoList) {
            ContentValues values = new ContentValues();
            values.put(KEY_STARRED_REPO_NAME, userStarredRepo.getName());
            values.put(KEY_STARRED_HTML_URL, userStarredRepo.getHtmlUrl());
            values.put(KEY_STARRED_REPO_DESCRIPTION, userStarredRepo.getDescription());

            db.insert(TABLE_USER_STARRED_REPO, null, values);
            Log.e(GitHubConstants.TAG, "inserted starring repo");
        }

        db.close();
    }

    List<UserStarredRepo> getAllStarringRepo() {
        List<UserStarredRepo> userStarredRepos = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String query = "select * from " + TABLE_USER_STARRED_REPO;
            Cursor cursor = db.rawQuery(query, null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    UserStarredRepo userStarredRepo = new UserStarredRepo();
                    userStarredRepo.setName(cursor.getString(1));
                    userStarredRepo.setHtmlUrl(cursor.getString(2));
                    userStarredRepo.setDescription(cursor.getString(3));
                    userStarredRepos.add(userStarredRepo);
                } while (cursor.moveToNext());
            }
            return userStarredRepos;
        } finally {
            db.close();
        }
    }

    void deleteALlUserReposStarring() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER_STARRED_REPO, null, null);
        db.close();
    }

    void addWatchinRepo(UserReposWatching userReposWatching) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_REPO_NAME, userReposWatching.getName());
        values.put(KEY_HTML_URL, userReposWatching.getHtmlUrl());
        values.put(KEY_REPO_DESCRIPTION, userReposWatching.getDescription());

        db.insert(TABLE_USER_WATCHING_REPO, null, values);
        db.close();
    }

    void addWholeListWatchinRepo(List<UserReposWatching> userReposWatchingList) {
        deleteALlUserReposWatching();
        SQLiteDatabase db = this.getWritableDatabase();
        for (UserReposWatching userReposWatching :
                userReposWatchingList) {
            ContentValues values = new ContentValues();
            values.put(KEY_REPO_NAME, userReposWatching.getName());
            values.put(KEY_HTML_URL, userReposWatching.getHtmlUrl());
            values.put(KEY_REPO_DESCRIPTION, userReposWatching.getDescription());

            db.insert(TABLE_USER_WATCHING_REPO, null, values);
            Log.e(GitHubConstants.TAG, "inserted watching repo");
        }

        db.close();
    }

    List<UserReposWatching> getAllWatchinRepo() {
        List<UserReposWatching> userProfileList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String query = "select * from " + TABLE_USER_WATCHING_REPO;
            Cursor cursor = db.rawQuery(query, null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    UserReposWatching userReposWatching = new UserReposWatching();
                    userReposWatching.setName(cursor.getString(1));
                    userReposWatching.setHtmlUrl(cursor.getString(2));
                    userReposWatching.setDescription(cursor.getString(3));
                    userProfileList.add(userReposWatching);
                } while (cursor.moveToNext());
            }
            return userProfileList;
        } finally {
            db.close();
        }
    }

    void deleteALlUserReposWatching() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER_WATCHING_REPO, null, null);
        db.close();
    }

    void addUser(UserProfile userProfile) {
        deleteUserProfile();
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, userProfile.getName());
        values.put(KEY_EMAIL, userProfile.getEmail());
        values.put(KEY_ORGANIZATION, userProfile.getCompany());

        db.insert(TABLE_USER_PROFILE, null, values);
        db.close();
    }

    UserProfile getUserProfile() {
        SQLiteDatabase db = this.getWritableDatabase();
        UserProfile userProfile = new UserProfile();
        try {
            String query = "select * from " + TABLE_USER_PROFILE;
            Cursor cursor = db.rawQuery(query, null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();


                userProfile.setName(cursor.getString(1));
                userProfile.setEmail(cursor.getString(2));
                userProfile.setCompany(cursor.getString(3));
            }
            return userProfile;
        } finally {
            db.close();
        }
    }

    int updateUserProfile(UserProfile userProfile) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, userProfile.getName());
            values.put(KEY_EMAIL, userProfile.getEmail());
            values.put(KEY_ORGANIZATION, userProfile.getCompany());

            return db.update(TABLE_USER_PROFILE, values, KEY_EMAIL + " = ?",
                    new String[]{String.valueOf(userProfile.getEmail())});
        } finally {
            db.close();
        }
    }

    void deleteUserProfile() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery("delete from " + TABLE_USER_PROFILE, null);
        db.close();
    }
}
