package com.tmobile.dynamicui.api

import com.tmobile.dynamicui.data.model.HomeResponse
import retrofit2.Response
import retrofit2.http.GET

interface TMobileApiService {

    companion object {
        const val ENDPOINT = "https://private-8ce77c-tmobiletest.apiary-mock.com/test/"
    }

    @GET("home")
    suspend fun getHomeData(): Response<HomeResponse>


}