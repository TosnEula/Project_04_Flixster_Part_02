package com.example.project_04_flixster_part_02

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class SearchResponse(
    @SerialName("results")
    val response: List<TvShow>
)



@Keep
@Serializable
data class TvShow(
    @SerialName("name")
    val showName: String? = null,
    @SerialName("vote_average")
    val voteAverage: String? = null,
    @SerialName("poster_path")
    val imageUrl: String? = null,
    @SerialName("overview")
    val showOverview: String? = null,
    @SerialName("original_name")
    val showOriginalName: String? = null,
    @SerialName("original_language")
    val showLanguage: String? = null,
): java.io.Serializable {
    val mediaImageUrl = "https://image.tmdb.org/t/p/w500/$imageUrl"
}

