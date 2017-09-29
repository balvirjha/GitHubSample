package com.cognitiveclouds.balvier.githubsample.ui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cognitiveclouds.balvier.githubsample.R;

import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment;

/**
 * Created by Balvier on 9/27/2017.
 */

public class LoginFragment extends SupportBlurDialogFragment implements View.OnClickListener {

    private View mRoot;
    GitHubLogin gitHubLoginCallback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.login_fragment, container, false);
        getDialog().setCanceledOnTouchOutside(false);
        return mRoot;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation;
        mRoot.findViewById(R.id.loginButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.loginButton) {
            if (gitHubLoginCallback != null && getActivity() != null && !getActivity().isFinishing()) {
                gitHubLoginCallback.openGitHubLoginPage();
            }
        }
    }

    public void setGitHubLoginCallback(GitHubLogin gitHubLoginCallback) {
        this.gitHubLoginCallback = gitHubLoginCallback;
    }

    public interface GitHubLogin {
        public void openGitHubLoginPage();
    }

}
