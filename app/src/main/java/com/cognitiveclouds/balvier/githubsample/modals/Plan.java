package com.cognitiveclouds.balvier.githubsample.modals;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by Balvier on 9/29/2017.
 */

public class Plan implements Parcelable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("space")
    @Expose
    private Integer space;
    @SerializedName("private_repos")
    @Expose
    private Integer privateRepos;
    @SerializedName("collaborators")
    @Expose
    private Integer collaborators;
    public final static Parcelable.Creator<Plan> CREATOR = new Creator<Plan>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Plan createFromParcel(Parcel in) {
            return new Plan(in);
        }

        public Plan[] newArray(int size) {
            return (new Plan[size]);
        }

    }
            ;

    protected Plan(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.space = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.privateRepos = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.collaborators = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Plan() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSpace() {
        return space;
    }

    public void setSpace(Integer space) {
        this.space = space;
    }

    public Integer getPrivateRepos() {
        return privateRepos;
    }

    public void setPrivateRepos(Integer privateRepos) {
        this.privateRepos = privateRepos;
    }

    public Integer getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(Integer collaborators) {
        this.collaborators = collaborators;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(space);
        dest.writeValue(privateRepos);
        dest.writeValue(collaborators);
    }

    public int describeContents() {
        return 0;
    }

}