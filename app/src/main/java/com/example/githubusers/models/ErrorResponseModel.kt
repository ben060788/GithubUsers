package com.example.githubusers.models

import com.google.gson.annotations.SerializedName

class ErrorResponseModel {
    @SerializedName("message")
    var message: String = ""

    @SerializedName("documentation_url")
    var docURL: String = ""
}