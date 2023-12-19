package com.riveong.animalink.data.api

import com.riveong.animalink.data.model.LoginResponse
import com.riveong.animalink.data.model.RegisterResponse
import com.riveong.animalink.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("/api/auth/login")
    fun postLogin(
        @Field("email",encoded=true) email:String,
        @Field("password",encoded=true) password:String,
        ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("/api/auth/register")
    fun postRegister(
        @Field("first_name",encoded = true) fname:String,
        @Field("last_name",encoded = true) lname:String,
        @Field("wa_number",encoded = true) phone:String,
        @Field("email",encoded=true) email:String,
        @Field("password",encoded=true) password:String,
    ): Call<RegisterResponse>

    @GET("/api/users")
    fun getUserData(
    ): Call<UserResponse>



}