package com.example.githubusers

import com.example.githubusers.models.UsersModel

interface UsersListContract {
    interface View {
        fun showProgress()

        fun hideProgress()

        fun setUsersList(usersList: List<UsersModel>)

        fun readNextPage(id: Int)
    }

    interface Model {
        interface OnFinishedListener {
            fun onFinished(usersList: List<UsersModel>)
        }

        fun getUsersList(onFinishedListener: OnFinishedListener?)

        fun getNextPageData(onFinishedListener: OnFinishedListener?, id: Int)
    }

    interface Presenter {
        fun onLoadData()

        fun onLoadNextPage(id: Int)

        fun onDestroy()
    }
}