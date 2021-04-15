package com.mtotowamkwe.lostboyz.model

data class Pet(
    var id: String,
    var age: Int,
    var sex: String,
    var description: String,
    var name: String,
    var type: String,
    var url: String,
    var breed: String,
    var found: Boolean = false,
    var location: String
)