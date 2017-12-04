package com.cognitiveclouds.balvier.githubsample.ui.view.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cognitiveclouds.balvier.githubsample.ApplicationClass;
import com.cognitiveclouds.balvier.githubsample.R;
import com.cognitiveclouds.balvier.githubsample.modals.GitHubConstants;
import com.cognitiveclouds.balvier.githubsample.modals.watcherreposmodals.UserReposWatching;
import com.cognitiveclouds.balvier.githubsample.ui.view.activities.RepositoryDetailPage;
import com.cognitiveclouds.balvier.githubsample.ui.view.adapter.RecyclerItemClickListener;
import com.cognitiveclouds.balvier.githubsample.ui.view.adapter.WatchingReposAdapter;
import com.cognitiveclouds.balvier.githubsample.viewmodals.UserWatcherReposViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import okhttp3.Cache;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Balvier on 9/29/2017.
 */

public class UserWatchingReposFragment extends Fragment implements Observer<List<UserReposWatching>> {
    private UserWatcherReposViewModel viewModel;
    private List<UserReposWatching> userReposWatchingArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private WatchingReposAdapter mAdapter;
    private View mRoot;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(UserWatcherReposViewModel.class);
        viewModel.init(getActivity().getSharedPreferences(GitHubConstants.getSharedPref(), MODE_PRIVATE).getString("access_token", null),
                new Cache(new File(getActivity().getCacheDir(), "responses"), 10 * 1024 * 1024));
        recyclerView = (RecyclerView) mRoot.findViewById(R.id.recycler_view);

        mAdapter = new WatchingReposAdapter(userReposWatchingArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ApplicationClass.getApplicationConotext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), RepositoryDetailPage.class);
                        intent.putExtra("dataItem", userReposWatchingArrayList.get(position));

                        ActivityOptionsCompat options = ActivityOptionsCompat.
                                makeSceneTransitionAnimation(getActivity(),
                                        view.findViewById(R.id.repo_image),
                                        ViewCompat.getTransitionName(view.findViewById(R.id.repo_image)));
                        startActivity(intent, options.toBundle());


                       /* Intent intent = new Intent(getActivity(), RepositoryDetailPage.class);
                        intent.putExtra("dataItem", userReposWatchingArrayList.get(position));
                        startActivity(intent);*/

                        // do whatever
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        viewModel.getUserReposWatchingLiveData().observe(this, this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.user_watchingrepos_fragment, container, false);
        return mRoot;
    }

    @Override
    public void onChanged(List<UserReposWatching> userReposWatchings) {
        if (userReposWatchingArrayList != null) {
            userReposWatchingArrayList.clear();
        }
        Log.e(GitHubConstants.TAG, "fetching user repos success" + userReposWatchings.size());

        Observable.fromIterable(userReposWatchings)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.Observer<UserReposWatching>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserReposWatching userReposWatching) {
                        userReposWatchingArrayList.add(userReposWatching);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        if (userReposWatchingArrayList.size() == 0) {
                            mRoot.findViewById(R.id.errorView).setVisibility(View.VISIBLE);

                        } else {
                            if (mRoot.findViewById(R.id.errorView) != null && mRoot.findViewById(R.id.errorView).getVisibility() == View.VISIBLE) {
                                mRoot.findViewById(R.id.errorView).setVisibility(View.GONE);

                            }

                        }
                    }
                });

    }


    public interface RecyclerViewClickListener {

        void onClick(View view, int position);
    }

}
