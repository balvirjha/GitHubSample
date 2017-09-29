package com.cognitiveclouds.balvier.githubsample.modals.starredreposmodals;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Balvier on 9/30/2017.
 */

public class Permissions implements Parcelable {

    @SerializedName("admin")
    @Expose
    private Boolean admin;
    @SerializedName("push")
    @Expose
    private Boolean push;
    @SerializedName("pull")
    @Expose
    private Boolean pull;
    public final static Parcelable.Creator<Permissions> CREATOR = new Creator<Permissions>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Permissions createFromParcel(Parcel in) {
            return new Permissions(in);
        }

        public Permissions[] newArray(int size) {
            return (new Permissions[size]);
        }

    };

    protected Permissions(Parcel in) {
        this.admin = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.push = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.pull = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
    }

    public Permissions() {
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getPush() {
        return push;
    }

    public void setPush(Boolean push) {
        this.push = push;
    }

    public Boolean getPull() {
        return pull;
    }

    public void setPull(Boolean pull) {
        this.pull = pull;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(admin);
        dest.writeValue(push);
        dest.writeValue(pull);
    }

    public int describeContents() {
        return 0;
    }

}