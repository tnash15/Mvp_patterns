package com.example.tae.mvp_patterns.model

import retrofit2.Response

data class APIResponse (
    val country: String, val age: String,
    val caresults: List<APIResponse>
)