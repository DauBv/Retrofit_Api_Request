package retrofit.retrofit.models.themoviedb

import com.google.gson.annotations.SerializedName


/**
 * Created by DauBV on 25/09/2017.
 */
class MoviesResponse {
    @SerializedName("page")
    private var page: Int = 0
    @SerializedName("results")
    private var results: ArrayList<Movie>? = null
    @SerializedName("total_results")
    private var totalResults: Int = 0
    @SerializedName("total_pages")
    private var totalPages: Int = 0

    var Page: Int
        get() {
            return page
        }
        set(value) {
            page = value
        }
    var Results: ArrayList<Movie>?
        get() {
            return results
        }
        set(value) {
            results = value
        }

    var TotalResults: Int
        get() {
            return totalResults
        }
        set(value) {
            totalResults = value
        }
    var TotalPages: Int
        get() {
            return totalPages
        }
        set(value) {
            totalPages = value
        }
}