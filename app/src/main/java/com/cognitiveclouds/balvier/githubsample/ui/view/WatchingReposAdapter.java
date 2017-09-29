package com.cognitiveclouds.balvier.githubsample.ui.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cognitiveclouds.balvier.githubsample.R;
import com.cognitiveclouds.balvier.githubsample.modals.GlideApp;
import com.cognitiveclouds.balvier.githubsample.modals.starredreposmodals.UserStarredRepo;
import com.cognitiveclouds.balvier.githubsample.modals.watcherreposmodals.UserReposWatching;

import java.util.List;

/**
 * Created by Balvier on 9/29/2017.
 */

public class WatchingReposAdapter extends RecyclerView.Adapter<WatchingReposAdapter.MyViewHolder> {
    private Context context;
    private List<UserReposWatching> userReposWatchings;
    private List<UserStarredRepo> userStarredRepoArrayList;
    boolean flagUserReposWatching, flagUserStarredRepo;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description, htmlurl;
        public ImageView repo_image;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            htmlurl = (TextView) view.findViewById(R.id.htmlurl);
            description = (TextView) view.findViewById(R.id.description);
            repo_image = (ImageView) view.findViewById(R.id.repo_image);
            repo_image = (ImageView) view.findViewById(R.id.repo_image);
        }
    }


    public WatchingReposAdapter(List<?> userReposWatchings) {
        if (userReposWatchings != null && userReposWatchings.size() > 0 && userReposWatchings.get(0) instanceof UserReposWatching) {
            clearData();
            this.userReposWatchings = (List<UserReposWatching>) userReposWatchings;
            flagUserReposWatching = true;
            flagUserStarredRepo = false;
        } else if (userReposWatchings != null && userReposWatchings.size() > 0 && userReposWatchings.get(0) instanceof UserStarredRepo) {
            clearData();
            userStarredRepoArrayList = (List<UserStarredRepo>) userReposWatchings;
            flagUserReposWatching = false;
            flagUserStarredRepo = true;
        }
    }

    public void setFragmentPerspective(boolean flagUserReposWatching) {
        if (flagUserReposWatching) {
            this.flagUserReposWatching = true;
            this.flagUserStarredRepo = false;
        } else {
            this.flagUserReposWatching = false;
            this.flagUserStarredRepo = true;
        }

    }

    private void clearData() {
        if (this.userReposWatchings != null && this.userReposWatchings.size() > 0) {
            this.userReposWatchings.clear();
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.watchingrepos_list_row, parent, false);
        if (context == null) {
            context = parent.getContext();
        }
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (flagUserReposWatching) {
            UserReposWatching userReposWatching = userReposWatchings.get(position);
            holder.name.setText(userReposWatching.getName());
            holder.htmlurl.setText(userReposWatching.getHtmlUrl());
            GlideApp.with(context)
                    .load(userReposWatching.getOwner().getAvatarUrl())
                    .error(R.drawable.gitlogo)
                    .fitCenter()
                    .into(holder.repo_image);
        } else if (flagUserStarredRepo) {
            UserStarredRepo userReposWatching = userStarredRepoArrayList.get(position);
            holder.name.setText(userReposWatching.getName());
            holder.htmlurl.setText(userReposWatching.getHtmlUrl());
            GlideApp.with(context)
                    .load(userReposWatching.getOwner().getAvatarUrl())
                    .error(R.drawable.gitlogo)
                    .fitCenter()
                    .into(holder.repo_image);
        }
    }

    @Override
    public int getItemCount() {
        if (flagUserReposWatching) {
            Log.e("bvc", "size of list = " + userReposWatchings.size());
            if (userReposWatchings.size() == 6) {
                userReposWatchings.addAll(userReposWatchings);
            }
            return userReposWatchings.size();
        } else if (flagUserStarredRepo) {
            Log.e("bvc", "size of list = " + userStarredRepoArrayList.size());
            if (userStarredRepoArrayList.size() == 6) {
                userStarredRepoArrayList.addAll(userStarredRepoArrayList);
            }
            return userStarredRepoArrayList.size();
        }
        return 0;
    }
}
