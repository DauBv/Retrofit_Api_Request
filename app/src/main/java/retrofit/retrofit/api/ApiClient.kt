package retrofit.retrofit.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by DauBV on 25/09/2017.
 */
class ApiClient {
    private var BASE_URL = "http://api.themoviedb.org/3/"
    private val BASE_URL0 = "http://mushtaq.16mb.com/"
    private val BASE_URL1 = "https://reqres.in"

    private var retrofit: Retrofit? = null

    /**
     *  Method getClient url Base_URL
     */
    fun getClient(): Retrofit? {
        if (retrofit == null)
            retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit
    }

    /**
     *  Method getRetrofit url BASE_URL0
     */
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL0).addConverterFactory(GsonConverterFactory.create()).build()
    }

    /**
     *  Method getClient1 request url BASE_URL1
     */
    fun getClient1(): Retrofit? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder().baseUrl(BASE_URL1).addConverterFactory(GsonConverterFactory.create()).client(client).build()
    }

}