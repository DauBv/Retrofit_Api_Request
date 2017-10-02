package retrofit.retrofit.views.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import retrofit.retrofit.R
import retrofit.retrofit.api.ApiClient
import retrofit.retrofit.api.ApiInterface
import retrofit.retrofit.models.themoviedb.MoviesResponse
import retrofit.retrofit.views.adapter.MoviesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitGetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_get)

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return
        }
        val recyclerView = findViewById(R.id.movies_recycler_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        val apiService = ApiClient().getClient()!!.create(ApiInterface::class.java)
        val call = apiService.getTopRatedMovies(API_KEY)
        call.enqueue(object : Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>?, t: Throwable?) {
                Log.e(TAG, "error" + t.toString())
            }


            override fun onResponse(call: Call<MoviesResponse>?, response: Response<MoviesResponse>?) {
                val movies = response!!.body().Results
                Log.e(TAG, "movies :> ${movies!!.size}")
                recyclerView.adapter = MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext())
            }
        })
    }

    fun newIntent(context: Context): Intent {
        val intent = Intent(context, RetrofitGetActivity::class.java)
        return intent
    }

    companion object {
        private val TAG = RetrofitGetActivity::class.java.simpleName
        private val API_KEY = "8c5f3fc4000a6fc3c578b4a9af37372a"
    }
}
