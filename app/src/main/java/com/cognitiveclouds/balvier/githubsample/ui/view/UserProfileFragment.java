package com.cognitiveclouds.balvier.githubsample.ui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cognitiveclouds.balvier.githubsample.R;
import com.cognitiveclouds.balvier.githubsample.modals.GlideApp;
import com.cognitiveclouds.balvier.githubsample.modals.UserProfile;

import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment;

/**
 * Created by Balvier on 9/29/2017.
 */

public class UserProfileFragment extends SupportBlurDialogFragment {
    private View mRoot;
    UserProfile userProfile;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            userProfile = (UserProfile) bundle.getParcelable("userprofile");
        }
        mRoot = inflater.inflate(R.layout.user_profile_fragment, container, false);
        getDialog().setCanceledOnTouchOutside(false);
        return mRoot;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation;
        if (userProfile != null) {
            ((TextView) mRoot.findViewById(R.id.name)).setText(userProfile.getName());
            ((TextView) mRoot.findViewById(R.id.email)).setText(userProfile.getEmail());
            ((TextView) mRoot.findViewById(R.id.company)).setText(userProfile.getCompany());
            GlideApp.with(getActivity())
                    .load(userProfile.getAvatarUrl())
                    .error(R.drawable.gitlogo)
                    .fitCenter()
                    .into(((ImageView) mRoot.findViewById(R.id.profile_image)));
        }
    }
}
