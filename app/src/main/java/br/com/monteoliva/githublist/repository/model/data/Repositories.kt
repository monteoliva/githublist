package br.com.monteoliva.githublist.repository.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Repositories(
    @SerializedName("incomplete_results") @Expose val incompleteResults: Boolean = false,
    @SerializedName("items")              @Expose val items: MutableList<Item>? = null,
    @SerializedName("total_count")        @Expose val totalCount: Int = 0
) : java.io.Serializable