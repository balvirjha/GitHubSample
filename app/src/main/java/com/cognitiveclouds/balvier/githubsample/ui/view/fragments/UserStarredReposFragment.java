package com.cognitiveclouds.balvier.githubsample.ui.view.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cognitiveclouds.balvier.githubsample.ApplicationClass;
import com.cognitiveclouds.balvier.githubsample.R;
import com.cognitiveclouds.balvier.githubsample.databaseoperations.UpdateDataTables;
import com.cognitiveclouds.balvier.githubsample.modals.GitHubConstants;
import com.cognitiveclouds.balvier.githubsample.modals.starredreposmodals.UserStarredRepo;
import com.cognitiveclouds.balvier.githubsample.ui.view.adapter.StarredReposAdapter;
import com.cognitiveclouds.balvier.githubsample.ui.view.adapter.WatchingReposAdapter;
import com.cognitiveclouds.balvier.githubsample.viewmodals.UserStarredReposViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Cache;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Balvier on 9/30/2017.
 */

public class UserStarredReposFragment extends Fragment implements Observer<List<UserStarredRepo>> {

    private UserStarredReposViewModel viewModel;
    private List<UserStarredRepo> userStarredRepoArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private StarredReposAdapter mAdapter;
    private View mRoot;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(UserStarredReposViewModel.class);
        viewModel.init(getActivity().getSharedPreferences(GitHubConstants.getSharedPref(), MODE_PRIVATE).getString("access_token", null),
                new Cache(new File(getActivity().getCacheDir(), "responses"), 10 * 1024 * 1024));
        recyclerView = (RecyclerView) mRoot.findViewById(R.id.recycler_view);

        mAdapter = new StarredReposAdapter(userStarredRepoArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ApplicationClass.getApplicationConotext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        viewModel.getUserStarredReposWatchingLiveData().observe(this, this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.user_watchingrepos_fragment, container, false);
        return mRoot;
    }

    @Override
    public void onChanged(@Nullable List<UserStarredRepo> userStarredRepoList) {
        if (userStarredRepoArrayList != null) {
            userStarredRepoArrayList.clear();
        }
        Log.e(GitHubConstants.TAG, "fetching user starred repos success" + userStarredRepoArrayList.size());
        userStarredRepoArrayList.addAll(userStarredRepoList);
        mAdapter.notifyDataSetChanged();
        if (userStarredRepoArrayList.size() == 0 && mRoot.findViewById(R.id.errorView) != null) {
            mRoot.findViewById(R.id.errorView).setVisibility(View.VISIBLE);
        } else {
            //UpdateDataTables.getInstance(getActivity()).insertWholeStarringRepoTable(userStarredRepoArrayList);
            if (mRoot.findViewById(R.id.errorView) != null && mRoot.findViewById(R.id.errorView).getVisibility() == View.VISIBLE) {
                mRoot.findViewById(R.id.errorView).setVisibility(View.GONE);
            }
        }
    }
}
