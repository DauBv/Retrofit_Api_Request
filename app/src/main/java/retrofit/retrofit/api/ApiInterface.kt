package retrofit.retrofit.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit.retrofit.models.MoviesResponse
import retrofit.retrofit.models.ServerResponse
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by DauBV on 25/09/2017.
 */
interface ApiInterface {
    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String): Call<MoviesResponse>

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") id: Int, @Query("api_key") apiKey: String): Call<MoviesResponse>

    @Multipart
    @POST("retrofit_example/upload_image.php")
    fun uploadFile(@Part file: MultipartBody.Part,
                   @Part("file") name: RequestBody): Call<ServerResponse>
}