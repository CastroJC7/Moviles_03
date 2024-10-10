package com.example.desafio3

import retrofit2.Call
import retrofit2.http.*

interface ResourceService {
    @GET("materias")
    fun getAllResources(): Call<List<Materia>>

    @GET("materias/{id}")
    fun getResourceById(@Path("id") id: String): Call<Materia>

    @POST("materias")
    fun createResource(@Body recurso: Materia): Call<Materia>

    @PUT("materias/{id}")
    fun updateResource(@Path("id") id: String, @Body recurso: Materia): Call<Materia>

    @DELETE("materias/{id}")
    fun deleteResource(@Path("id") id: String): Call<Void>
}
