package br.com.monteoliva.githublist.repository.model.list

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Repos(
    @SerializedName("total_count")        @Expose var totalCount: Int? = null,
    @SerializedName("incomplete_results") @Expose var incompleteResults: Boolean? = null,
    @SerializedName("items")              @Expose var items: MutableList<Item>? = null
) : Serializable