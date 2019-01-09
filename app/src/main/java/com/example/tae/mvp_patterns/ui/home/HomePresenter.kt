package com.example.tae.mvp_patterns.ui.home

import com.example.tae.mvp_patterns.model.APIData
import com.example.tae.mvp_patterns.network.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter (

    private val apiService: APIService,
    private val view: HomeContract.View) : HomeContract.Presenter {

    override fun getDetails(country: String, age: String) {

        apiService.getDetails(country, age.toInt()).enqueue(object : Callback<APIData> {
            override fun onFailure(call: Call<APIData>?, t: Throwable?) {
                view.showError("Please try again")
            }

            override fun onResponse(call: Call<APIData>?, response: Response<APIData>?) {
                if (response!!.isSuccessful) {
                    view.showResults(response.body())
                } else {
                    view.showError("An unknown error occured")
                }
            }

//            fun onFailure(call: Call<APIResponse>, throwable: Throwable) {
//                view.showError("Please try again")
//            }
//
//            fun onResponse(call: Callback<APIResponse>, response: Response<List<APIData>>) {
//                if (response.isSuccessful) {
//                    view.showResults(response.body() ?: emptyList())
//                } else {
//                    view.showError("An unknown error occured")
//                }
//            }
        })
    }
}