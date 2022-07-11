package com.example.githubusers.utils

import com.example.githubusers.models.UserDetailsModel
import com.example.githubusers.models.UsersModel
import retrofit2.Call
import retrofit2.http.*

interface MyAPIService {
    @GET("users")
    fun getUsers(
        @Query("since") since: Int,
        @Query("per_page") perPage: Int
    ): Call<List<UsersModel>>

    @GET("users/{login}")
    fun getUserDetail(@Path("login") loginName: String): Call<UserDetailsModel>
}
