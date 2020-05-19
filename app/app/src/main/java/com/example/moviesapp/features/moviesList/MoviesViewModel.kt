package com.example.moviesapp.features.moviesList

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.moviesapp.Remote.ApiUtil
import com.example.moviesapp.models.moviesResponse.MoviesResponse
import com.example.moviesapp.utils.generalResponse.GeneralError
import com.example.moviesapp.utils.generalResponse.GeneralResponseLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel :ViewModel() {
    private val TAG = "MoviesViewModel"

    val moviesResponseApi: GeneralResponseLiveData<MoviesResponse?> = GeneralResponseLiveData()

    private var moviesListCall: Call<MoviesResponse>? = null


    fun getMovies(apiKey:String,page:Int)
    {
        Log.v(TAG, "requesting moviesList")
        moviesResponseApi.postLoading()
        moviesListCall = ApiUtil.getMoviesNetwork()?.getMovies(apiKey, page)
        moviesListCall?.enqueue(object :Callback<MoviesResponse>
        {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                if (response.isSuccessful)
                {
                    moviesResponseApi.postSuccess(response.body())

                }else
                {
                    moviesResponseApi.postError(GeneralError(response.message(),isToast = page!=1,connectionProblem = false,responseCode = response.code()))
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                if (moviesListCall!!.isCanceled()) return
                moviesResponseApi.postError(GeneralError(t.message.toString(),isToast = page!=1,connectionProblem = true,responseCode = -1))
            }
        })

    }

    fun ubBind(context: Fragment?) {
        context?.let { moviesResponseApi.removeObservers(it) }
        if (moviesListCall != null && moviesListCall!!.isExecuted()) {
            moviesListCall!!.cancel()
        }
    }

}