package com.dk.pagingv4.data

data class GithubResponse(
    val total_count : Int,
    val incomplete_results : Boolean,
    val items : List<GithubItems>
)
