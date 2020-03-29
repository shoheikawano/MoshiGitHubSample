package com.shoheikawano.sample.github.data.remote.response

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
class RepositoriesResponse(
    @Json(name = "total_count") val totalCount: Int,
    val items: List<RepositoryResponse> = emptyList() // Removing this default value solves the occurrence of java.lang.NoSuchMethodException
)

@Keep
@JsonClass(generateAdapter = true)
class RepositoryResponse(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "full_name") val fullName: String,
    val owner: OwnerResponse,
    @Json(name = "url") val url: String?
)

@Keep
@JsonClass(generateAdapter = true)
class OwnerResponse(
    @Json(name = "id") val id: Long
)
