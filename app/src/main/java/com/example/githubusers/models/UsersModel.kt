package com.example.githubusers.models

import com.google.gson.annotations.SerializedName

class UsersModel {
    @SerializedName("login")
    var login: String = ""

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("avatar_url")
    var avatarURL: String = ""

    @SerializedName("site_admin")
    var siteAdmin: Boolean = false
}