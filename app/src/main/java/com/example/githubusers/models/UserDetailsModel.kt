package com.example.githubusers.models

import com.google.gson.annotations.SerializedName

class UserDetailsModel {
    @SerializedName("login")
    var loginName: String = ""

    @SerializedName("avatar_url")
    var avatarURL: String = ""

    @SerializedName("site_admin")
    var siteAdmin: Boolean = false

    @SerializedName("name")
    var userName: String = ""

    @SerializedName("bio")
    var bio: String = ""

    @SerializedName("blog")
    var blog: String = ""

    @SerializedName("location")
    var location: String? = null
}