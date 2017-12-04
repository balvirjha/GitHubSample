package com.cognitiveclouds.balvier.githubsample.ui.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.cognitiveclouds.balvier.githubsample.R;
import com.cognitiveclouds.balvier.githubsample.Utils;
import com.cognitiveclouds.balvier.githubsample.modals.GlideApp;
import com.cognitiveclouds.balvier.githubsample.modals.starredreposmodals.UserStarredRepo;

import java.util.List;

/**
 * Created by Balvier on 9/30/2017.
 */

public class StarredReposAdapter extends RecyclerView.Adapter<StarredReposAdapter.MyViewHolder> {
    private Context context;
    private List<UserStarredRepo> userStarredRepoArrayList;

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


    public StarredReposAdapter(List<?> userReposWatchings) {

        userStarredRepoArrayList = (List<UserStarredRepo>) userReposWatchings;

    }

    @Override
    public StarredReposAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.watchingrepos_list_row_new, parent, false);
        if (context == null) {
            context = parent.getContext();
        }
        return new StarredReposAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final StarredReposAdapter.MyViewHolder holder, int position) {

        final UserStarredRepo userReposWatching = userStarredRepoArrayList.get(position);
        holder.name.setText(userReposWatching.getName());
        holder.htmlurl.setText(userReposWatching.getHtmlUrl());

        if (userReposWatching.getOwner() != null && userReposWatching.getOwner().getAvatarUrl() != null && Utils.isNetworkAvailable()) {
            GlideApp.with(context)
                    .load(userReposWatching.getOwner().getAvatarUrl())
                    .error(R.drawable.gitlogo)
                    .transition(DrawableTransitionOptions.withCrossFade(1500))
                    .fitCenter()
                    .override(100, 100)
                    .into(holder.repo_image);
        }

    }

    @Override
    public int getItemCount() {
        return userStarredRepoArrayList.size();

    }
}

