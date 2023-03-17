package br.com.monteoliva.githublist.repository.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("allow_forking")
    @Expose val allowForking: Boolean = false,
    @SerializedName("archive_url")
    @Expose val archiveUrl: String = "",
    @SerializedName("archived")
    @Expose val archived: Boolean = false,
    @SerializedName("assignees_url")
    @Expose val assigneesUrl: String = "",
    @SerializedName("blobs_url")
    @Expose val blobsUrl: String = "",
    @SerializedName("branches_url")
    @Expose val branchesUrl: String = "",
    @SerializedName("clone_url")
    @Expose val cloneUrl: String = "",
    @SerializedName("collaborators_url")
    @Expose val collaboratorsUrl: String = "",
    @SerializedName("comments_url")
    @Expose val commentsUrl: String = "",
    @SerializedName("commits_url")
    @Expose val commitsUrl: String = "",
    @SerializedName("compare_url")
    @Expose val compareUrl: String = "",
    @SerializedName("contents_url")
    @Expose val contentsUrl: String = "",
    @SerializedName("contributors_url")
    @Expose val contributorsUrl: String = "",
    @SerializedName("created_at")
    @Expose val createdAt: String = "",
    @SerializedName("default_branch")
    @Expose val defaultBranch: String = "",
    @SerializedName("deployments_url")
    @Expose val deploymentsUrl: String = "",
    @SerializedName("description")
    @Expose val description: String = "",
    @SerializedName("disabled")
    @Expose val disabled: Boolean = false,
    @SerializedName("downloads_url")
    @Expose val downloadsUrl: String = "",
    @SerializedName("events_url")
    @Expose val eventsUrl: String = "",
    @SerializedName("fork")
    @Expose val fork: Boolean = false,
    @SerializedName("forks")
    @Expose val forks: Int = 0,
    @SerializedName("forks_count")
    @Expose val forksCount: Int = 0,
    @SerializedName("forks_url")
    @Expose val forksUrl: String = "",
    @SerializedName("full_name")
    @Expose val fullName: String = "",
    @SerializedName("git_commits_url")
    @Expose val gitCommitsUrl: String = "",
    @SerializedName("git_refs_url")
    @Expose val gitRefsUrl: String = "",
    @SerializedName("git_tags_url")
    @Expose val gitTagsUrl: String = "",
    @SerializedName("git_url")
    @Expose val gitUrl: String = "",
    @SerializedName("has_discussions")
    @Expose val hasDiscussions: Boolean = false,
    @SerializedName("has_downloads")
    @Expose val hasDownloads: Boolean = false,
    @SerializedName("has_issues")
    @Expose val hasIssues: Boolean = false,
    @SerializedName("has_pages")
    @Expose val hasPages: Boolean = false,
    @SerializedName("has_projects")
    @Expose val hasProjects: Boolean = false,
    @SerializedName("has_wiki")
    @Expose val hasWiki: Boolean = false,
    @SerializedName("homepage")
    @Expose val homepage: String = "",
    @SerializedName("hooks_url")
    @Expose val hooksUrl: String = "",
    @SerializedName("html_url")
    @Expose val htmlUrl: String = "",
    @SerializedName("id")
    @Expose val id: Int = 0,
    @SerializedName("is_template")
    @Expose val isTemplate: Boolean = false,
    @SerializedName("issue_comment_url")
    @Expose val issueCommentUrl: String = "",
    @SerializedName("issue_events_url")
    @Expose val issueEventsUrl: String = "",
    @SerializedName("issues_url")
    @Expose val issuesUrl: String = "",
    @SerializedName("keys_url")
    @Expose val keysUrl: String = "",
    @SerializedName("labels_url")
    @Expose val labelsUrl: String = "",
    @SerializedName("language")
    @Expose val language: String = "",
    @SerializedName("languages_url")
    @Expose val languagesUrl: String = "",
    @SerializedName("license")
    @Expose val license: License = License(),
    @SerializedName("merges_url")
    @Expose val mergesUrl: String = "",
    @SerializedName("milestones_url")
    @Expose val milestonesUrl: String = "",
    @SerializedName("mirror_url")
    @Expose val mirrorUrl: Any = Any(),
    @SerializedName("name")
    @Expose val name: String = "",
    @SerializedName("node_id")
    @Expose val nodeId: String = "",
    @SerializedName("notifications_url")
    @Expose val notificationsUrl: String = "",
    @SerializedName("open_issues")
    @Expose val openIssues: Int = 0,
    @SerializedName("open_issues_count")
    @Expose val openIssuesCount: Int = 0,
    @SerializedName("owner")
    @Expose val owner: Owner = Owner(),
    @SerializedName("private")
    @Expose val `private`: Boolean = false,
    @SerializedName("pulls_url")
    @Expose val pullsUrl: String = "",
    @SerializedName("pushed_at")
    @Expose val pushedAt: String = "",
    @SerializedName("releases_url")
    @Expose val releasesUrl: String = "",
    @SerializedName("score")
    @Expose val score: Double = 0.0,
    @SerializedName("size")
    @Expose val size: Int = 0,
    @SerializedName("ssh_url")
    @Expose val sshUrl: String = "",
    @SerializedName("stargazers_count")
    @Expose val stargazersCount: Int = 0,
    @SerializedName("stargazers_url")
    @Expose val stargazersUrl: String = "",
    @SerializedName("statuses_url")
    @Expose val statusesUrl: String = "",
    @SerializedName("subscribers_url")
    @Expose val subscribersUrl: String = "",
    @SerializedName("subscription_url")
    @Expose val subscriptionUrl: String = "",
    @SerializedName("svn_url")
    @Expose val svnUrl: String = "",
    @SerializedName("tags_url")
    @Expose val tagsUrl: String = "",
    @SerializedName("teams_url")
    @Expose val teamsUrl: String = "",
    @SerializedName("topics")
    @Expose val topics: MutableList<String>? = null,
    @SerializedName("trees_url")
    @Expose val treesUrl: String = "",
    @SerializedName("updated_at")
    @Expose val updatedAt: String = "",
    @SerializedName("url")
    @Expose val url: String = "",
    @SerializedName("visibility")
    @Expose val visibility: String = "",
    @SerializedName("watchers")
    @Expose val watchers: Int = 0,
    @SerializedName("watchers_count")              @Expose val watchersCount: Int = 0,
    @SerializedName("web_commit_signoff_required") @Expose val webCommitSignoffRequired: Boolean = false
) : java.io.Serializable