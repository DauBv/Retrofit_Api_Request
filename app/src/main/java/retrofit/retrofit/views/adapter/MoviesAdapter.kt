package retrofit.retrofit.views.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import retrofit.retrofit.R
import retrofit.retrofit.models.Movie
import retrofit.retrofit.views.adapter.MoviesAdapter.MovieViewHolder
import android.view.LayoutInflater


/**
 * Created by daubv on 25/09/2017.
 */
class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private var movies: List<Movie>? = null
    private var rowLayout: Int = 0
    private var context: Context? = null

    constructor(movies: List<Movie>, rowLayout: Int, context: Context) {
        this.movies = movies
        this.rowLayout = rowLayout
        this.context = context
    }


    class MovieViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val moviesLayout: LinearLayout
        val movieTitle: TextView
        val data: TextView
        val movieDescription: TextView
        val rating: TextView

        init {
            moviesLayout = v.findViewById<LinearLayout>(R.id.movies_layout) as LinearLayout
            movieTitle = v.findViewById<TextView>(R.id.title) as TextView
            data = v.findViewById<TextView>(R.id.subtitle) as TextView
            movieDescription = v.findViewById<TextView>(R.id.description) as TextView
            rating = v.findViewById<TextView>(R.id.rating) as TextView
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder?, position: Int) {
        holder!!.movieTitle.setText(movies!!.get(position).Title)
        holder!!.data.setText(movies!!.get(position).ReleaseDate)
        holder!!.movieDescription.setText(movies!!.get(position).Overview)
        holder!!.rating.setText(movies!!.get(position).VoteAverage.toString())
    }

    override fun getItemCount(): Int {
        return movies!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent!!.getContext()).inflate(rowLayout, parent, false)
        return MovieViewHolder(view)
    }
}