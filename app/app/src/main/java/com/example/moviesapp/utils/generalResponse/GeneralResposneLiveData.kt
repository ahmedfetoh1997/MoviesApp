package com.example.moviesapp.utils.generalResponse

import androidx.lifecycle.MutableLiveData

class GeneralResponseLiveData<T> : MutableLiveData<GeneralResponse<out T>?>() {
    fun postLoading() {
        postValue(GeneralResponse<T>().loading())
    }

    fun postError(error: GeneralError) {
        postValue(GeneralResponse<T>().error(error))
    }

    fun postSuccess(data: T) {
        postValue(GeneralResponse<T>().success(data))
    }
}
