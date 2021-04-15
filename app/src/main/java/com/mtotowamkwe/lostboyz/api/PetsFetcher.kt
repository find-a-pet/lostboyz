package com.mtotowamkwe.lostboyz.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtotowamkwe.lostboyz.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "PetsFetcher"

class PetsFetcher {

    val api: LostboyzApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constants.pets)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        api = retrofit.create(LostboyzApi::class.java)
    }

    fun fetchPets(): LiveData<String> {
        val responseLiveData: MutableLiveData<String> = MutableLiveData()
        val petsRequest: Call<String> = api.pets()

        petsRequest.enqueue(object : Callback<String> {
            
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d(TAG, "Response received: ${response.body()}")
                responseLiveData.value = response.body()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e(TAG, "Failed to fetch pets", t)
            }
        })

        return responseLiveData
    }
}