package com.example.tae.mvp_patterns.ui.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.tae.mvp_patterns.R
import com.example.tae.mvp_patterns.model.APIData
import com.example.tae.mvp_patterns.network.APIService
import com.example.tae.mvp_patterns.network.BASE_URL
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), HomeContract.View {

    private val apiAdapter = APIAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        val retrofit = retrofitBuilder
            .client(okHttpClient)
            .build()

        val apiService = retrofit.create(APIService::class.java)

        val homePresenter: HomeContract.Presenter = HomePresenter(apiService, this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = apiAdapter

        btnButton.setOnClickListener {
            homePresenter.getDetails(etCountry.text.toString(),
                etAge.text.toString())
        }
    }

    override fun showResults(results: List<APIData>) {
        apiAdapter.setData(results)
    }

    override fun showError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}