package com.example.project_04_flixster_part_02

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide



class ShowDetail : AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var ratingTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var originalNameTextView: TextView
    private lateinit var languageTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_show)

        // Find the views for the screen
        mediaImageView = findViewById(R.id.showImage)
        titleTextView = findViewById(R.id.showName)
        ratingTextView = findViewById(R.id.showRating)
        descriptionTextView = findViewById(R.id.showDescription)
        originalNameTextView = findViewById(R.id.showOriginalName)
        languageTextView = findViewById(R.id.showLanguage)

        // Get the extra from the Intent
        val tvShow = intent.getSerializableExtra(SHOW_EXTRA) as TvShow

        // Set the title, byline, and abstract information from the article
        titleTextView.text = tvShow.showName
        ratingTextView.text = "Rating: " + tvShow.voteAverage.toString() + "/10"
        descriptionTextView.text = "Overview \n" + tvShow.showOverview
        originalNameTextView.text = tvShow.showOriginalName
        languageTextView.text = "Original Language: " + tvShow.showLanguage


        // Load the media image
        Glide.with(this)
            .load(tvShow.mediaImageUrl)
            .into(mediaImageView)
    }
}