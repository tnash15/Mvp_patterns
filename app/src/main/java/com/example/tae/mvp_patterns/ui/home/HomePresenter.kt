package com.example.tae.mvp_patterns.ui.home

import com.example.tae.mvp_patterns.model.APIData
import com.example.tae.mvp_patterns.model.APIResponse
import com.example.tae.mvp_patterns.network.APIService
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import javax.security.auth.callback.Callback

class HomePresenter (

    private val apiService: APIService,
    private val view: HomeContract.View) : HomeContract.Presenter {

    override fun getDetails(country: String, age: String) {

        apiService.getDetails(country, age.toInt()).enqueue(object : Callback<List<APIResponse>> {
            fun onFailure(call: Call<APIResponse>, throwable: Throwable){
                view.showError("Please try again")
            }

            fun onResponse(call: Callback<APIResponse>, response: Response<APIData>){
                if (response.isSuccessful){
                    view.showResults(response.body()?: emptyList()){
                    } else {
                        view.showError("An unknown error occured")
                    }
                }
            })
        }
    }


