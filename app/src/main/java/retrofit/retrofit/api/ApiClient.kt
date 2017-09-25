package retrofit.retrofit.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by DauBV on 25/09/2017.
 */
class ApiClient {
    private var BASE_URL = "http://api.themoviedb.org/3/"
    private val BASE_URL1 = "http://mushtaq.16mb.com/"
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {
        if (retrofit == null)
            retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL1).addConverterFactory(GsonConverterFactory.create()).build()
    }
}