package retrofit.retrofit.models.themoviedb

import com.google.gson.annotations.SerializedName

import java.util.ArrayList

/**
 * Created by DauBV on 25/09/2017.
 */

class Movie {

    @SerializedName("poster_path")
    private var posterPath: String? = null
    @SerializedName("adult")
    private var adult: Boolean = false
    @SerializedName("overview")
    private var overview: String? = null
    @SerializedName("release_date")
    private var releaseDate: String? = null
    @SerializedName("genre_ids")
    private var genreIds = ArrayList<Int>()
    @SerializedName("id")
    private var id: Int? = null
    @SerializedName("original_title")
    private var originalTitle: String? = null
    @SerializedName("original_language")
    private var originalLanguage: String? = null
    @SerializedName("title")
    private var title: String? = null
    @SerializedName("backdrop_path")
    private var backdropPath: String? = null
    @SerializedName("popularity")
    private var popularity: Double? = null
    @SerializedName("vote_count")
    private var voteCount: Int? = null
    @SerializedName("video")
    private var video: Boolean? = null
    @SerializedName("vote_average")
    private var voteAverage: Double? = null

    constructor(posterPath: String, adult: Boolean, overview: String, releaseDate: String, genreIds: ArrayList<Int>, id: Int,
                originalTitle: String, originalLanguage: String, title: String, backdropPath: String, popularity: Double,
                voteCount: Int, video: Boolean, voteAverage: Double) {
        this.posterPath = posterPath
        this.adult = adult
        this.overview = overview
        this.releaseDate = releaseDate
        this.genreIds = genreIds
        this.id = id
        this.originalTitle = originalTitle
        this.originalLanguage = originalLanguage
        this.title = title
        this.backdropPath = backdropPath
        this.popularity = popularity
        this.voteCount = voteCount
        this.video = video
        this.voteAverage = voteAverage
    }

    var Title: String?
        get() {
            return title
        }
        set(value) {
            title = value
        }
    var ReleaseDate: String?
        get() {
            return releaseDate
        }
        set(value) {
            releaseDate = value
        }
    var Overview: String?
        get() {
            return overview
        }
        set(value) {
            overview = value
        }
    var VoteAverage: Double?
        get() {
            return voteAverage
        }
        set(value) {
            voteAverage = value
        }
}
