package com.sd.moviedb.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.sd.moviedb.constants.AppConstants.IMAGE_PATH

@Entity
data class Movies(
    @SerializedName("adult")
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    // @SerializedName("genre_ids")
    //val genreIds: List<Int>?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("poster_path")
    var posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @PrimaryKey
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val video: Boolean?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    var favourite: Boolean = false
) {
    fun getFullPosterPath() =
        if (posterPath.isNullOrBlank()) null else IMAGE_PATH + posterPath
}