package br.com.monteoliva.githublist.repository.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class License(
    @SerializedName("key")     @Expose val key: String = "",
    @SerializedName("name")    @Expose val name: String = "",
    @SerializedName("node_id") @Expose val nodeId: String = "",
    @SerializedName("spdx_id") @Expose val spdxId: String = "",
    @SerializedName("url")     @Expose val url: String = ""
) : java.io.Serializable