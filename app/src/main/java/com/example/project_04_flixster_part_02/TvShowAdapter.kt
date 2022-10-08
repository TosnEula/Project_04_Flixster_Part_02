package com.example.project_04_flixster_part_02

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val SHOW_EXTRA = "SHOW_EXTRA"
private const val TAG = "TvShowAdapter"

class TvShowAdapter(private val context: Context, private val tvShows: List<TvShow>) :
    RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movies_main_page, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Get the individual article and bind to holder
        val tvShow = tvShows[position]
        holder.bind(tvShow)

    }

    override fun getItemCount() = tvShows.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val showImageView = itemView.findViewById<ImageView>(R.id.showImage)
        private val showNameTextView = itemView.findViewById<TextView>(R.id.showName)
        private val showRatingTextView = itemView.findViewById<TextView>(R.id.showRating)

        init {
            itemView.setOnClickListener(this)
        }

        //A helper method to help set up the onBindViewHolder method
        fun bind(tvShow: TvShow) {
            showNameTextView.text = tvShow.showName
            showRatingTextView.text = "Rating: " + tvShow.voteAverage.toString() + "/10"

            Glide.with(context)
                .load(tvShow.mediaImageUrl)
                .into(showImageView)

        }

        override fun onClick(p0: View?) {
            // Get selected show
            val article = tvShows[absoluteAdapterPosition]

            // Navigate to Details screen and pass selected show
            val intent = Intent(context, ShowDetail::class.java)
            intent.putExtra(SHOW_EXTRA, article)
            context.startActivity(intent)
        }

    }
}