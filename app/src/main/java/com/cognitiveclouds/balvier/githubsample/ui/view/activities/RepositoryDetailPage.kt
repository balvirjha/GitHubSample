package com.cognitiveclouds.balvier.githubsample.ui.view.activities

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.view.ViewTreeObserver
import com.cognitiveclouds.balvier.githubsample.R
import com.cognitiveclouds.balvier.githubsample.Utils
import com.cognitiveclouds.balvier.githubsample.modals.watcherreposmodals.UserReposWatching
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_activity.*


/**
 * Created by Balvier on 11/27/2017.
 */
class RepositoryDetailPage : AppCompatActivity() {

    var userRepowatching: UserReposWatching? = null
    var context: Activity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        if (intent.extras != null && intent.getParcelableExtra<UserReposWatching>("dataItem") != null) {
            userRepowatching = intent.getParcelableExtra<UserReposWatching>("dataItem")
        }
        setContentView(R.layout.detail_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        userName.text = userRepowatching?.owner?.htmlUrl
        descriptionText.text = userRepowatching?.description
        supportActionBar?.title = userRepowatching?.name
        profileImage.setImageResource(R.drawable.github)
        if (userRepowatching?.getOwner() != null && userRepowatching?.getOwner()?.getAvatarUrl() != null && Utils.isNetworkAvailable()) {
            Picasso.with(this).load(userRepowatching?.getOwner()?.getAvatarUrl()).into(profileImage,
                    object : Callback {
                        override fun onSuccess() {

                            scheduleStartPostponedTransition(profileImage)
                        }

                        override fun onError() {
                        }
                    })
        }

    }

    private fun scheduleStartPostponedTransition(sharedElement: View) {
        sharedElement.getViewTreeObserver().addOnPreDrawListener(
                object : ViewTreeObserver.OnPreDrawListener {

                    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                    override fun onPreDraw(): Boolean {
                        sharedElement.getViewTreeObserver().removeOnPreDrawListener(this)
                        try {
                            startPostponedEnterTransition()
                        } catch (e: NoSuchMethodError) {
                            if (context != null && !context?.isFinishing!!) {
                                Picasso.with(context).load(userRepowatching?.getOwner()?.getAvatarUrl()).into(profileImage)
                            }
                        }
                        return true
                    }
                })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.getItemId()) {
            android.R.id.home -> {
                supportFinishAfterTransition();
                onBackPressed()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }
}