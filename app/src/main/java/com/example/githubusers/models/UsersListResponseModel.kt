package com.example.githubusers.models

import com.example.githubusers.UsersListContract
import com.example.githubusers.utils.RetrofitManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.os.Handler
import android.os.Looper
import android.util.Log

class UsersListResponseModel() : UsersListContract.Model {
    val myAPIService = RetrofitManager().getInstance()
    var since: Int = 0
    var call: Call<List<UsersModel>> = myAPIService!!.getUsers(since, 20)

    override fun getUsersList(onFinishedListener: UsersListContract.Model.OnFinishedListener?) {
        call.enqueue(object : Callback<List<UsersModel>> {
            override fun onResponse(
                call: Call<List<UsersModel>>?,
                response: Response<List<UsersModel>>?
            ) {
                if (response != null) {
                    if (response.isSuccessful) {
                        Handler(Looper.getMainLooper()).postDelayed({
                            onFinishedListener!!.onFinished(response.body())
                        }, 1000)
                    }
                }
            }

            override fun onFailure(call: Call<List<UsersModel>>?, t: Throwable?) {
                Log.e("_usersListResponseModel", "on failure", t)
            }
        })
    }

    override fun getNextPageData(
        onFinishedListener: UsersListContract.Model.OnFinishedListener?,
        id: Int
    ) {
        Log.i("_usersListResponseModel", id.toString())
        // 抓page的最後一項id, 傳給下一頁的since
        var call: Call<List<UsersModel>> = myAPIService!!.getUsers(id, 20)
        call.enqueue(object : Callback<List<UsersModel>> {
            override fun onResponse(
                call: Call<List<UsersModel>>?,
                response: Response<List<UsersModel>>?
            ) {
                if (response != null) {
                    if (response.isSuccessful) {
                        Handler(Looper.getMainLooper()).postDelayed({
                            onFinishedListener!!.onFinished(response.body())
                        }, 1000)
                    }
                }
            }

            override fun onFailure(call: Call<List<UsersModel>>?, t: Throwable?) {
                Log.e("_usersListResponseModel", "on failure", t)
            }
        })
    }
}