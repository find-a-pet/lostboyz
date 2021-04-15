package com.mtotowamkwe.lostboyz.api

import com.google.gson.annotations.SerializedName
import com.mtotowamkwe.lostboyz.model.Pet

class PetsResponse {
    @SerializedName("petList")
    lateinit var lostPets: List<Pet>
}