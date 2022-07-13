package com.example.githubusers.models

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.githubusers.UserDetailsContract
import com.example.githubusers.utils.RetrofitManager
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailsResponseModel(val login: String) : UserDetailsContract.Model {
    val myAPIService = RetrofitManager().getInstance()
    var call: Call<UserDetailsModel> = myAPIService!!.getUserDetails(loginName = login)

    override fun getUserDetails(onFinishedListener: UserDetailsContract.Model.OnFinishedListener?) {
        call.enqueue(object : Callback<UserDetailsModel> {
            override fun onResponse(
                call: Call<UserDetailsModel>?,
                response: Response<UserDetailsModel>?
            ) {
                if (response != null) {
                    if (response.isSuccessful && response.code() == 200) {
                        Handler(Looper.getMainLooper()).postDelayed({
                            onFinishedListener!!.onFinished(response.body())
                        }, 500)
                    } else {
                        onFinishedListener!!.onErrorRequest(
                            Gson().fromJson(
                                response.errorBody().string(), ErrorResponseModel::class.java
                            )
                        )
                    }
                }
            }

            override fun onFailure(call: Call<UserDetailsModel>?, t: Throwable?) {
                Log.e("_userDetailsResponseModel", "on failure", t)
            }
        })
    }
}