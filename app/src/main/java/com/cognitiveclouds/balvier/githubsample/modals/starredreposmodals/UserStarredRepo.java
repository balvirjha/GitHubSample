package com.cognitiveclouds.balvier.githubsample.modals.starredreposmodals;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Balvier on 9/30/2017.
 */

public class UserStarredRepo implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("owner")
    @Expose
    private Owner owner;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("private")
    @Expose
    private Boolean _private;
    @SerializedName("fork")
    @Expose
    private Boolean fork;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("html_url")
    @Expose
    private String htmlUrl;
    @SerializedName("archive_url")
    @Expose
    private String archiveUrl;
    @SerializedName("assignees_url")
    @Expose
    private String assigneesUrl;
    @SerializedName("blobs_url")
    @Expose
    private String blobsUrl;
    @SerializedName("branches_url")
    @Expose
    private String branchesUrl;
    @SerializedName("clone_url")
    @Expose
    private String cloneUrl;
    @SerializedName("collaborators_url")
    @Expose
    private String collaboratorsUrl;
    @SerializedName("comments_url")
    @Expose
    private String commentsUrl;
    @SerializedName("commits_url")
    @Expose
    private String commitsUrl;
    @SerializedName("compare_url")
    @Expose
    private String compareUrl;
    @SerializedName("contents_url")
    @Expose
    private String contentsUrl;
    @SerializedName("contributors_url")
    @Expose
    private String contributorsUrl;
    @SerializedName("deployments_url")
    @Expose
    private String deploymentsUrl;
    @SerializedName("downloads_url")
    @Expose
    private String downloadsUrl;
    @SerializedName("events_url")
    @Expose
    private String eventsUrl;
    @SerializedName("forks_url")
    @Expose
    private String forksUrl;
    @SerializedName("git_commits_url")
    @Expose
    private String gitCommitsUrl;
    @SerializedName("git_refs_url")
    @Expose
    private String gitRefsUrl;
    @SerializedName("git_tags_url")
    @Expose
    private String gitTagsUrl;
    @SerializedName("git_url")
    @Expose
    private String gitUrl;
    @SerializedName("hooks_url")
    @Expose
    private String hooksUrl;
    @SerializedName("issue_comment_url")
    @Expose
    private String issueCommentUrl;
    @SerializedName("issue_events_url")
    @Expose
    private String issueEventsUrl;
    @SerializedName("issues_url")
    @Expose
    private String issuesUrl;
    @SerializedName("keys_url")
    @Expose
    private String keysUrl;
    @SerializedName("labels_url")
    @Expose
    private String labelsUrl;
    @SerializedName("languages_url")
    @Expose
    private String languagesUrl;
    @SerializedName("merges_url")
    @Expose
    private String mergesUrl;
    @SerializedName("milestones_url")
    @Expose
    private String milestonesUrl;
    @SerializedName("mirror_url")
    @Expose
    private String mirrorUrl;
    @SerializedName("notifications_url")
    @Expose
    private String notificationsUrl;
    @SerializedName("pulls_url")
    @Expose
    private String pullsUrl;
    @SerializedName("releases_url")
    @Expose
    private String releasesUrl;
    @SerializedName("ssh_url")
    @Expose
    private String sshUrl;
    @SerializedName("stargazers_url")
    @Expose
    private String stargazersUrl;
    @SerializedName("statuses_url")
    @Expose
    private String statusesUrl;
    @SerializedName("subscribers_url")
    @Expose
    private String subscribersUrl;
    @SerializedName("subscription_url")
    @Expose
    private String subscriptionUrl;
    @SerializedName("svn_url")
    @Expose
    private String svnUrl;
    @SerializedName("tags_url")
    @Expose
    private String tagsUrl;
    @SerializedName("teams_url")
    @Expose
    private String teamsUrl;
    @SerializedName("trees_url")
    @Expose
    private String treesUrl;
    @SerializedName("homepage")
    @Expose
    private String homepage;
    @SerializedName("language")
    @Expose
    private Object language;
    @SerializedName("forks_count")
    @Expose
    private Integer forksCount;
    @SerializedName("stargazers_count")
    @Expose
    private Integer stargazersCount;
    @SerializedName("watchers_count")
    @Expose
    private Integer watchersCount;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("default_branch")
    @Expose
    private String defaultBranch;
    @SerializedName("open_issues_count")
    @Expose
    private Integer openIssuesCount;
    @SerializedName("topics")
    @Expose
    private List<String> topics = null;
    @SerializedName("has_issues")
    @Expose
    private Boolean hasIssues;
    @SerializedName("has_wiki")
    @Expose
    private Boolean hasWiki;
    @SerializedName("has_pages")
    @Expose
    private Boolean hasPages;
    @SerializedName("has_downloads")
    @Expose
    private Boolean hasDownloads;
    @SerializedName("pushed_at")
    @Expose
    private String pushedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("permissions")
    @Expose
    private Permissions permissions;
    @SerializedName("allow_rebase_merge")
    @Expose
    private Boolean allowRebaseMerge;
    @SerializedName("allow_squash_merge")
    @Expose
    private Boolean allowSquashMerge;
    @SerializedName("allow_merge_commit")
    @Expose
    private Boolean allowMergeCommit;
    @SerializedName("subscribers_count")
    @Expose
    private Integer subscribersCount;
    @SerializedName("network_count")
    @Expose
    private Integer networkCount;
    public final static Parcelable.Creator<UserStarredRepo> CREATOR = new Creator<UserStarredRepo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UserStarredRepo createFromParcel(Parcel in) {
            return new UserStarredRepo(in);
        }

        public UserStarredRepo[] newArray(int size) {
            return (new UserStarredRepo[size]);
        }

    };

    protected UserStarredRepo(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.owner = ((Owner) in.readValue((Owner.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.fullName = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this._private = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.fork = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
        this.htmlUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.archiveUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.assigneesUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.blobsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.branchesUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.cloneUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.collaboratorsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.commentsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.commitsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.compareUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.contentsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.contributorsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.deploymentsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.downloadsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.eventsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.forksUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.gitCommitsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.gitRefsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.gitTagsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.gitUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.hooksUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.issueCommentUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.issueEventsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.issuesUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.keysUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.labelsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.languagesUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.mergesUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.milestonesUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.mirrorUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.notificationsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.pullsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.releasesUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.sshUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.stargazersUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.statusesUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.subscribersUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.subscriptionUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.svnUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.tagsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.teamsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.treesUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.homepage = ((String) in.readValue((String.class.getClassLoader())));
        this.language = ((Object) in.readValue((Object.class.getClassLoader())));
        this.forksCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.stargazersCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.watchersCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.size = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.defaultBranch = ((String) in.readValue((String.class.getClassLoader())));
        this.openIssuesCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.topics, (java.lang.String.class.getClassLoader()));
        this.hasIssues = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.hasWiki = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.hasPages = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.hasDownloads = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.pushedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.permissions = ((Permissions) in.readValue((Permissions.class.getClassLoader())));
        this.allowRebaseMerge = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.allowSquashMerge = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.allowMergeCommit = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.subscribersCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.networkCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public UserStarredRepo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getPrivate() {
        return _private;
    }

    public void setPrivate(Boolean _private) {
        this._private = _private;
    }

    public Boolean getFork() {
        return fork;
    }

    public void setFork(Boolean fork) {
        this.fork = fork;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getArchiveUrl() {
        return archiveUrl;
    }

    public void setArchiveUrl(String archiveUrl) {
        this.archiveUrl = archiveUrl;
    }

    public String getAssigneesUrl() {
        return assigneesUrl;
    }

    public void setAssigneesUrl(String assigneesUrl) {
        this.assigneesUrl = assigneesUrl;
    }

    public String getBlobsUrl() {
        return blobsUrl;
    }

    public void setBlobsUrl(String blobsUrl) {
        this.blobsUrl = blobsUrl;
    }

    public String getBranchesUrl() {
        return branchesUrl;
    }

    public void setBranchesUrl(String branchesUrl) {
        this.branchesUrl = branchesUrl;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public void setCloneUrl(String cloneUrl) {
        this.cloneUrl = cloneUrl;
    }

    public String getCollaboratorsUrl() {
        return collaboratorsUrl;
    }

    public void setCollaboratorsUrl(String collaboratorsUrl) {
        this.collaboratorsUrl = collaboratorsUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public String getCommitsUrl() {
        return commitsUrl;
    }

    public void setCommitsUrl(String commitsUrl) {
        this.commitsUrl = commitsUrl;
    }

    public String getCompareUrl() {
        return compareUrl;
    }

    public void setCompareUrl(String compareUrl) {
        this.compareUrl = compareUrl;
    }

    public String getContentsUrl() {
        return contentsUrl;
    }

    public void setContentsUrl(String contentsUrl) {
        this.contentsUrl = contentsUrl;
    }

    public String getContributorsUrl() {
        return contributorsUrl;
    }

    public void setContributorsUrl(String contributorsUrl) {
        this.contributorsUrl = contributorsUrl;
    }

    public String getDeploymentsUrl() {
        return deploymentsUrl;
    }

    public void setDeploymentsUrl(String deploymentsUrl) {
        this.deploymentsUrl = deploymentsUrl;
    }

    public String getDownloadsUrl() {
        return downloadsUrl;
    }

    public void setDownloadsUrl(String downloadsUrl) {
        this.downloadsUrl = downloadsUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public String getForksUrl() {
        return forksUrl;
    }

    public void setForksUrl(String forksUrl) {
        this.forksUrl = forksUrl;
    }

    public String getGitCommitsUrl() {
        return gitCommitsUrl;
    }

    public void setGitCommitsUrl(String gitCommitsUrl) {
        this.gitCommitsUrl = gitCommitsUrl;
    }

    public String getGitRefsUrl() {
        return gitRefsUrl;
    }

    public void setGitRefsUrl(String gitRefsUrl) {
        this.gitRefsUrl = gitRefsUrl;
    }

    public String getGitTagsUrl() {
        return gitTagsUrl;
    }

    public void setGitTagsUrl(String gitTagsUrl) {
        this.gitTagsUrl = gitTagsUrl;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public String getHooksUrl() {
        return hooksUrl;
    }

    public void setHooksUrl(String hooksUrl) {
        this.hooksUrl = hooksUrl;
    }

    public String getIssueCommentUrl() {
        return issueCommentUrl;
    }

    public void setIssueCommentUrl(String issueCommentUrl) {
        this.issueCommentUrl = issueCommentUrl;
    }

    public String getIssueEventsUrl() {
        return issueEventsUrl;
    }

    public void setIssueEventsUrl(String issueEventsUrl) {
        this.issueEventsUrl = issueEventsUrl;
    }

    public String getIssuesUrl() {
        return issuesUrl;
    }

    public void setIssuesUrl(String issuesUrl) {
        this.issuesUrl = issuesUrl;
    }

    public String getKeysUrl() {
        return keysUrl;
    }

    public void setKeysUrl(String keysUrl) {
        this.keysUrl = keysUrl;
    }

    public String getLabelsUrl() {
        return labelsUrl;
    }

    public void setLabelsUrl(String labelsUrl) {
        this.labelsUrl = labelsUrl;
    }

    public String getLanguagesUrl() {
        return languagesUrl;
    }

    public void setLanguagesUrl(String languagesUrl) {
        this.languagesUrl = languagesUrl;
    }

    public String getMergesUrl() {
        return mergesUrl;
    }

    public void setMergesUrl(String mergesUrl) {
        this.mergesUrl = mergesUrl;
    }

    public String getMilestonesUrl() {
        return milestonesUrl;
    }

    public void setMilestonesUrl(String milestonesUrl) {
        this.milestonesUrl = milestonesUrl;
    }

    public String getMirrorUrl() {
        return mirrorUrl;
    }

    public void setMirrorUrl(String mirrorUrl) {
        this.mirrorUrl = mirrorUrl;
    }

    public String getNotificationsUrl() {
        return notificationsUrl;
    }

    public void setNotificationsUrl(String notificationsUrl) {
        this.notificationsUrl = notificationsUrl;
    }

    public String getPullsUrl() {
        return pullsUrl;
    }

    public void setPullsUrl(String pullsUrl) {
        this.pullsUrl = pullsUrl;
    }

    public String getReleasesUrl() {
        return releasesUrl;
    }

    public void setReleasesUrl(String releasesUrl) {
        this.releasesUrl = releasesUrl;
    }

    public String getSshUrl() {
        return sshUrl;
    }

    public void setSshUrl(String sshUrl) {
        this.sshUrl = sshUrl;
    }

    public String getStargazersUrl() {
        return stargazersUrl;
    }

    public void setStargazersUrl(String stargazersUrl) {
        this.stargazersUrl = stargazersUrl;
    }

    public String getStatusesUrl() {
        return statusesUrl;
    }

    public void setStatusesUrl(String statusesUrl) {
        this.statusesUrl = statusesUrl;
    }

    public String getSubscribersUrl() {
        return subscribersUrl;
    }

    public void setSubscribersUrl(String subscribersUrl) {
        this.subscribersUrl = subscribersUrl;
    }

    public String getSubscriptionUrl() {
        return subscriptionUrl;
    }

    public void setSubscriptionUrl(String subscriptionUrl) {
        this.subscriptionUrl = subscriptionUrl;
    }

    public String getSvnUrl() {
        return svnUrl;
    }

    public void setSvnUrl(String svnUrl) {
        this.svnUrl = svnUrl;
    }

    public String getTagsUrl() {
        return tagsUrl;
    }

    public void setTagsUrl(String tagsUrl) {
        this.tagsUrl = tagsUrl;
    }

    public String getTeamsUrl() {
        return teamsUrl;
    }

    public void setTeamsUrl(String teamsUrl) {
        this.teamsUrl = teamsUrl;
    }

    public String getTreesUrl() {
        return treesUrl;
    }

    public void setTreesUrl(String treesUrl) {
        this.treesUrl = treesUrl;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Object getLanguage() {
        return language;
    }

    public void setLanguage(Object language) {
        this.language = language;
    }

    public Integer getForksCount() {
        return forksCount;
    }

    public void setForksCount(Integer forksCount) {
        this.forksCount = forksCount;
    }

    public Integer getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(Integer stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public Integer getWatchersCount() {
        return watchersCount;
    }

    public void setWatchersCount(Integer watchersCount) {
        this.watchersCount = watchersCount;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    public Integer getOpenIssuesCount() {
        return openIssuesCount;
    }

    public void setOpenIssuesCount(Integer openIssuesCount) {
        this.openIssuesCount = openIssuesCount;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public Boolean getHasIssues() {
        return hasIssues;
    }

    public void setHasIssues(Boolean hasIssues) {
        this.hasIssues = hasIssues;
    }

    public Boolean getHasWiki() {
        return hasWiki;
    }

    public void setHasWiki(Boolean hasWiki) {
        this.hasWiki = hasWiki;
    }

    public Boolean getHasPages() {
        return hasPages;
    }

    public void setHasPages(Boolean hasPages) {
        this.hasPages = hasPages;
    }

    public Boolean getHasDownloads() {
        return hasDownloads;
    }

    public void setHasDownloads(Boolean hasDownloads) {
        this.hasDownloads = hasDownloads;
    }

    public String getPushedAt() {
        return pushedAt;
    }

    public void setPushedAt(String pushedAt) {
        this.pushedAt = pushedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public Boolean getAllowRebaseMerge() {
        return allowRebaseMerge;
    }

    public void setAllowRebaseMerge(Boolean allowRebaseMerge) {
        this.allowRebaseMerge = allowRebaseMerge;
    }

    public Boolean getAllowSquashMerge() {
        return allowSquashMerge;
    }

    public void setAllowSquashMerge(Boolean allowSquashMerge) {
        this.allowSquashMerge = allowSquashMerge;
    }

    public Boolean getAllowMergeCommit() {
        return allowMergeCommit;
    }

    public void setAllowMergeCommit(Boolean allowMergeCommit) {
        this.allowMergeCommit = allowMergeCommit;
    }

    public Integer getSubscribersCount() {
        return subscribersCount;
    }

    public void setSubscribersCount(Integer subscribersCount) {
        this.subscribersCount = subscribersCount;
    }

    public Integer getNetworkCount() {
        return networkCount;
    }

    public void setNetworkCount(Integer networkCount) {
        this.networkCount = networkCount;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(owner);
        dest.writeValue(name);
        dest.writeValue(fullName);
        dest.writeValue(description);
        dest.writeValue(_private);
        dest.writeValue(fork);
        dest.writeValue(url);
        dest.writeValue(htmlUrl);
        dest.writeValue(archiveUrl);
        dest.writeValue(assigneesUrl);
        dest.writeValue(blobsUrl);
        dest.writeValue(branchesUrl);
        dest.writeValue(cloneUrl);
        dest.writeValue(collaboratorsUrl);
        dest.writeValue(commentsUrl);
        dest.writeValue(commitsUrl);
        dest.writeValue(compareUrl);
        dest.writeValue(contentsUrl);
        dest.writeValue(contributorsUrl);
        dest.writeValue(deploymentsUrl);
        dest.writeValue(downloadsUrl);
        dest.writeValue(eventsUrl);
        dest.writeValue(forksUrl);
        dest.writeValue(gitCommitsUrl);
        dest.writeValue(gitRefsUrl);
        dest.writeValue(gitTagsUrl);
        dest.writeValue(gitUrl);
        dest.writeValue(hooksUrl);
        dest.writeValue(issueCommentUrl);
        dest.writeValue(issueEventsUrl);
        dest.writeValue(issuesUrl);
        dest.writeValue(keysUrl);
        dest.writeValue(labelsUrl);
        dest.writeValue(languagesUrl);
        dest.writeValue(mergesUrl);
        dest.writeValue(milestonesUrl);
        dest.writeValue(mirrorUrl);
        dest.writeValue(notificationsUrl);
        dest.writeValue(pullsUrl);
        dest.writeValue(releasesUrl);
        dest.writeValue(sshUrl);
        dest.writeValue(stargazersUrl);
        dest.writeValue(statusesUrl);
        dest.writeValue(subscribersUrl);
        dest.writeValue(subscriptionUrl);
        dest.writeValue(svnUrl);
        dest.writeValue(tagsUrl);
        dest.writeValue(teamsUrl);
        dest.writeValue(treesUrl);
        dest.writeValue(homepage);
        dest.writeValue(language);
        dest.writeValue(forksCount);
        dest.writeValue(stargazersCount);
        dest.writeValue(watchersCount);
        dest.writeValue(size);
        dest.writeValue(defaultBranch);
        dest.writeValue(openIssuesCount);
        dest.writeList(topics);
        dest.writeValue(hasIssues);
        dest.writeValue(hasWiki);
        dest.writeValue(hasPages);
        dest.writeValue(hasDownloads);
        dest.writeValue(pushedAt);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeValue(permissions);
        dest.writeValue(allowRebaseMerge);
        dest.writeValue(allowSquashMerge);
        dest.writeValue(allowMergeCommit);
        dest.writeValue(subscribersCount);
        dest.writeValue(networkCount);
    }

    public int describeContents() {
        return 0;
    }

}