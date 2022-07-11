package com.acera.test.api

import com.acera.test.model.DataModelItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("todos")
    fun getTodo(): Call<List<DataModelItem>>

    @GET("todos/{id}")
    fun getDetailTodo(
        @Path("id") id: String
    ): Call<DataModelItem>

}