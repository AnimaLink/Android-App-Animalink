package com.riveong.animalink.data.api

import com.riveong.animalink.data.model.BehaviourResponse
import com.riveong.animalink.data.model.CommentResponse
import com.riveong.animalink.data.model.ForumResponse
import com.riveong.animalink.data.model.GetCommentNew
import com.riveong.animalink.data.model.LoginResponse
import com.riveong.animalink.data.model.PostCommentResponse
import com.riveong.animalink.data.model.RegisterResponse
import com.riveong.animalink.data.model.SingleForumResponse
import com.riveong.animalink.data.model.UserResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

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

    @GET("/api/forums")
    fun getForum(
    ): Call<ForumResponse>

    @GET("/api/forums/{id}")
    fun getSingleForum(
        @Path("id") id:String
    ): Call<SingleForumResponse>

    @GET("/api/forums/comments/{forumId}")
    fun getComment(
        @Path("forumId") id:String
    ): Call<GetCommentNew>

    @FormUrlEncoded
    @POST("/api/forums/comments/{forumId}")
    fun postComment(
        @Path("forumId") id:String,
        @Field("comment",encoded = true) comment:String
    ): Call<PostCommentResponse>


    @GET("/api/animals")
    fun getAllAnimals(
    ): Call<BehaviourResponse>






}