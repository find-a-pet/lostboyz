package com.mtotowamkwe.lostboyz.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mtotowamkwe.lostboyz.api.PetsFetcher

class PetViewModel : ViewModel() {

    val listOfLostPetsLiveData: LiveData<List<Pet>>

    init {
        listOfLostPetsLiveData = PetsFetcher().fetchPets()
    }
}