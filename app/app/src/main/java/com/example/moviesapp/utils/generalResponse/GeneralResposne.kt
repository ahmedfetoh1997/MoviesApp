package com.example.moviesapp.utils.generalResponse

 class GeneralResponse<T>() {
    lateinit var status: Status
      var data: T?=null
    lateinit var error:GeneralError
    fun loading(): GeneralResponse<T>? {
        status = Status.Loading
        error != null
        return this
    }

    fun success(data: T): GeneralResponse<T>? {
        status = Status.Success
        this.data = data
        error != null
        return this
    }

    fun error(error: GeneralError): GeneralResponse<T>? {
        status = Status.Failure
        this.error = error
        data != null
        return this
    }

}
