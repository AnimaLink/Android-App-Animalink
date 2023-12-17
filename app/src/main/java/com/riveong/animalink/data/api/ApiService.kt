package com.riveong.animalink.data.api

import com.riveong.animalink.data.model.LoginResponse
import com.riveong.animalink.data.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.POST

interface ApiService {
    /*@GET("search/users")
    fun getGithub(
        @Query("q") query: String

    ): Call<GithubResponse>*/

    @POST("/auth/login")
    fun postLogin(): Call<LoginResponse>

    @POST("/auth/register")
    fun postRegister(): Call<RegisterResponse>


}