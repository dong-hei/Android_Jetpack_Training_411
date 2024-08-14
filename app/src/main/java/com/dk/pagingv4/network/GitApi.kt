package com.dk.pagingv4.network

import com.dk.pagingv4.data.GithubResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GitApi {

    @GET("search/repositories")
    suspend fun getData(
        @Query("q") query: String,
        @Query("page") page : Int,
        @Query("per_page") per_page: Int,
    ) : GithubResponse
}