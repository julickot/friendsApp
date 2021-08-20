package com.julickot.friendsapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://firebasestorage.googleapis.com/v0/b/candidates--questionnaire.appspot.com/o/"


interface FirebaseApiService {
    @GET("users.json?alt=media&token=e3672c23-b1a5-4ca7-bb77-b6580d75810c")
    suspend fun getUsers(): List<User>
}

object NetworkService {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val firebaseService: FirebaseApiService by lazy {
        retrofit.create(FirebaseApiService::class.java)
    }
}