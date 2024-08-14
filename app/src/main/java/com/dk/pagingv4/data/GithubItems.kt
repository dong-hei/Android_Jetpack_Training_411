package com.dk.pagingv4.data

import com.google.gson.annotations.SerializedName

data class GithubItems (
    @SerializedName("id") val id : Long,
    @SerializedName("name") val name : String,
    @SerializedName("html_url") val url : String,

        )