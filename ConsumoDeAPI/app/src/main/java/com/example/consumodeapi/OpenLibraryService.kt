package com.example.consumodeapi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenLibraryService {
    @GET("search.json")
    suspend fun searchBooks(@Query("q") query: String, @Query("page") page: Int): Response<BookResponse>
}
