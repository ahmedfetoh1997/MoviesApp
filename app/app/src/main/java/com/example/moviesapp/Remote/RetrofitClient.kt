package com.example.moviesapp.Remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    var AcceptType = "application/json"
    var MethodTypePut = "PUT"

    companion object {

        private var retrofit: Retrofit? = null
        private var googleRetrofit: Retrofit? = null

        fun getClient(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(setTime())
                    .build()
            }
            return retrofit
        }


        fun getGoogle(): Retrofit? {
            if (googleRetrofit == null) {
                googleRetrofit = Retrofit.Builder()
                    .baseUrl("https://maps.googleapis.com/maps/api/directions/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(setTime())
                    .build()
            }
            return googleRetrofit
        }


        private fun setTime(): OkHttpClient? {
            return OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build()
        }
    }
}