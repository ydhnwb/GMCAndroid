package com.annaalfiani.gmcapps.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name") var name: String? = null,
    @SerializedName("api_token") var token: String? = null
)