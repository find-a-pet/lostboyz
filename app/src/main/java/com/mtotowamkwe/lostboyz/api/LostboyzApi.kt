package com.mtotowamkwe.lostboyz.api

import com.mtotowamkwe.lostboyz.util.Constants
import retrofit2.Call
import retrofit2.http.GET

interface LostboyzApi {

    @GET(Constants.all)
    fun pets(): Call<LostboyzApiResponse>
}