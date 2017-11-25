package com.cognitiveclouds.balvier.githubsample.ui.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.cognitiveclouds.balvier.githubsample.R;
import com.cognitiveclouds.balvier.githubsample.Utils;
import com.cognitiveclouds.balvier.githubsample.modals.GlideApp;
import com.cognitiveclouds.balvier.githubsample.modals.watcherreposmodals.UserReposWatching;

import java.util.List;

/**
 * Created by Balvier on 9/29/2017.
 */

public class WatchingReposAdapter extends RecyclerView.Adapter<WatchingReposAdapter.MyViewHolder> {
    private Context context;
    private List<UserReposWatching> userReposWatchings;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description, htmlurl;
        public ImageView repo_image;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            htmlurl = (TextView) view.findViewById(R.id.htmlurl);
            description = (TextView) view.findViewById(R.id.description);
            repo_image = (ImageView) view.findViewById(R.id.repo_image);
        }
    }


    public WatchingReposAdapter(List<?> userReposWatchings) {
        this.userReposWatchings = (List<UserReposWatching>) userReposWatchings;
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
        UserReposWatching userReposWatching = userReposWatchings.get(position);
        holder.name.setText(userReposWatching.getName());
        holder.htmlurl.setText(userReposWatching.getHtmlUrl());
        holder.description.setText(userReposWatching.getDescription());
        if (userReposWatching.getOwner() != null && userReposWatching.getOwner().getAvatarUrl() != null && Utils.isNetworkAvailable()) {
            GlideApp.with(context)
                    .load(userReposWatching.getOwner().getAvatarUrl())
                    .error(R.drawable.gitlogo)
                    .fitCenter()
                    .into(holder.repo_image);
        }
    }

    @Override
    public int getItemCount() {
        Log.e("bvc", "size of list = " + userReposWatchings.size());
        return userReposWatchings.size();
    }
}
