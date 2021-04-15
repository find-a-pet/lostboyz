package com.mtotowamkwe.lostboyz.api

class LostboyzApiResponse {
    lateinit var _embedded: PetsResponse
    @Transient lateinit var _links: PetsResponse
}