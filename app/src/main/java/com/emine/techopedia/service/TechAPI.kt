package com.emine.techopedia.service

import com.emine.techopedia.model.Tech
import retrofit2.http.GET

interface TechAPI {
    @GET("Emineyldrm/TechJSONDataSet/refs/heads/main/teknolojiler.json")
    suspend fun getTech(): List<Tech>
}

//https://raw.githubusercontent.com/Emineyldrm/TechJSONDataSet/refs/heads/main/teknolojiler.json
//BASE URL->https://raw.githubusercontent.com/
//ENDPOINT->Emineyldrm/TechJSONDataSet/refs/heads/main/teknolojiler.json