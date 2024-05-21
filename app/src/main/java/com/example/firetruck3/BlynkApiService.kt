package com.example.firetruck3

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

object BlynkConstants {
    const val BLYNK_TEMPLATE_ID = "TMPL6AeLHr5Uk"
    const val BLYNK_TEMPLATE_NAME = "IoT Smart Car"
    const val BLYNK_AUTH_TOKEN = "7HH45BxX0ntawWd6ab5vfiUKxIddy3oA"
}

interface BlynkApiService {
    @GET("/external/api/get")
    fun getValueForPin(
        @Query("token") token: String,
        @Query("pin") pin: String
    ): Call<String>
}
