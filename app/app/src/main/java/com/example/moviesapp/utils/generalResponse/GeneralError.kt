package com.example.moviesapp.utils.generalResponse

data class GeneralError(var msg:String,var connectionProblem: Boolean,var responseCode: Int?,var isToast:Boolean){}