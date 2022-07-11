package com.example.githubusers

import com.example.githubusers.models.UsersModel

class UsersListPresenter(
    private var mainView: UsersListContract.View?,
    private val model: UsersListContract.Model
) : UsersListContract.Presenter, UsersListContract.Model.OnFinishedListener {

    override fun onLoadData() {
        if (mainView != null) {
            mainView!!.showProgress()
        }
        model.getUsersList(this)
    }

    override fun onFinished(usersList: List<UsersModel>) {
        if (mainView != null) {
            mainView!!.setUsersList(usersList)
            mainView!!.hideProgress()
        }
    }

    override fun onLoadNextPage(id: Int) {
        model.getNextPageData(this, id)
    }

    override fun onDestroy() {
        mainView = null
    }
}