package com.emine.techopedia.service

import com.emine.techopedia.model.Tech
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TechAPIServis {
   private val retrofit= Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TechAPI::class.java)

    suspend fun getData(): List<Tech>{
        return retrofit.getTech()
    }
}