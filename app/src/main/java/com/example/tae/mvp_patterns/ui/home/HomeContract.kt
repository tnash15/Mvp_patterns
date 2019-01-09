package com.example.tae.mvp_patterns.ui.home

import com.example.tae.mvp_patterns.model.APIData

interface HomeContract {

    interface View {
        fun showResults(results: APIData?)
        fun showError(message: String)
    }

    interface Presenter {
        fun getDetails(country: String, age: String)
    }
}