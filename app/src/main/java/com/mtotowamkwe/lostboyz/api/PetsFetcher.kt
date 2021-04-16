package com.mtotowamkwe.lostboyz.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtotowamkwe.lostboyz.model.Pet
import com.mtotowamkwe.lostboyz.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "PetsFetcher"

class PetsFetcher {

    val api: LostboyzApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constants.pets)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(LostboyzApi::class.java)
    }

    fun fetchPets(): LiveData<List<Pet>> {
        val responseLiveData: MutableLiveData<List<Pet>> = MutableLiveData()
        val petsRequest: Call<LostboyzApiResponse> = api.pets()

        petsRequest.enqueue(object : Callback<LostboyzApiResponse> {
            
            override fun onResponse(call: Call<LostboyzApiResponse>,
                                    response: Response<LostboyzApiResponse>) {
                Log.d(TAG, "Response received.")

                val apiResponse: LostboyzApiResponse? = response.body()
                val petsResponse: PetsResponse? = apiResponse?._embedded
                val petsLost: List<Pet> = petsResponse?.lostPets ?: mutableListOf()

                /* TODO: Cache results in local storage or a database
                    in case of configuration changes in the app e.g. screen orientation,
                    change phone call etc. Cached results can be used instead of making
                    a new request every time a configuration change occurs. */

                responseLiveData.value = petsLost
            }

            override fun onFailure(call: Call<LostboyzApiResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch pets", t)
            }
        })

        return responseLiveData
    }
}