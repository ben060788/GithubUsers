package com.example.githubusers

import com.example.githubusers.models.ErrorResponseModel
import com.example.githubusers.models.UserDetailsModel

class UserDetailsPresenter(
    private var mainView: UserDetailsContract.View?,
    private val model: UserDetailsContract.Model
) : UserDetailsContract.Presenter, UserDetailsContract.Model.OnFinishedListener {
    override fun onFinished(userDetail: UserDetailsModel) {
        if (mainView != null) {
            mainView!!.setUserDetails(userDetail)
            mainView!!.hideProgress()
        }
    }

    override fun onErrorRequest(errorBody: ErrorResponseModel) {
        mainView!!.hideProgress()
        mainView!!.showToast(errorBody.message)
    }

    override fun onLoadData() {
        if (mainView != null) {
            mainView!!.showProgress()
        }

        model.getUserDetails(this)
    }

    override fun onDestroy() {
        mainView = null
    }

}