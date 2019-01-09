package com.example.tae.mvp_patterns.network

import com.example.tae.mvp_patterns.model.APIData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import java.lang.StringBuilder

interface APIService {

    @GET("population/{country}/{age}")
    fun getDetails(@Path("country") country: String,
                   @Path("age") age: Int): Call<APIData>
}