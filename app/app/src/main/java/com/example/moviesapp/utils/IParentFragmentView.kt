package com.example.moviesapp.utils

import android.view.View
import com.example.moviesapp.utils.generalResponse.GeneralError

interface IParentFragmentView {

    fun initSettings()

    fun setupUI(view: View?)

    fun showMainLoading()

    fun hideMainLoading()

    fun showLoadMoreProgress()

    fun hideLoadMoreProgress()

    fun showSubLoading()

    fun hideSubLoading()

    fun handleErrorMsg(error: GeneralError?)
}