package com.example.githubusers

import com.example.githubusers.models.ErrorResponseModel
import com.example.githubusers.models.UserDetailsModel

interface UserDetailsContract {
    interface View {
        fun showProgress()

        fun hideProgress()

        fun showToast(msg: String)

        fun setUserDetails(userDetail: UserDetailsModel)

    }

    interface Model {
        interface OnFinishedListener {
            fun onFinished(userDetail: UserDetailsModel)

            fun onErrorRequest(errorBody: ErrorResponseModel)
        }

        fun getUserDetails(onFinishedListener: OnFinishedListener?)

    }

    interface Presenter {
        fun onLoadData()

        fun onDestroy()
    }
}