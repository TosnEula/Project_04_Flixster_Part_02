package com.example.project_04_flixster_part_02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.project_04_flixster_part_02.databinding.ActivityMainBinding
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val TAG = "MainActivity/"
private const val SEARCH_API_KEY = BuildConfig.API_KEY
private const val TV_SHOW_SEARCH_URL =
    "https://api.themoviedb.org/3/tv/airing_today?api_key=${SEARCH_API_KEY}&language=en-US&page=1"

class MainActivity : AppCompatActivity() {
    private val tvShows = mutableListOf<TvShow>()
    private lateinit var TvShowsRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        TvShowsRecyclerView = findViewById(R.id.showOverviewRV)
        //Set up ArticleAdapter with articles
        val tvShowAdapter = TvShowAdapter(this, tvShows)
        TvShowsRecyclerView.adapter = tvShowAdapter

        TvShowsRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            TvShowsRecyclerView.addItemDecoration(dividerItemDecoration)
        }

        val client = AsyncHttpClient()
        client.get(TV_SHOW_SEARCH_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch articles: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(TAG, "Successfully fetched TV Shows: $json")
                try {
                    // Create the parsedJSON
                    // Do something with the returned json (contains article information)
                    val parsedJson = createJson().decodeFromString(
                        SearchResponse.serializer(),
                        json.jsonObject.toString()
                    )
                    Log.i(TAG, "Successfully fetched articles: $json")

                    // Save the shows
                    parsedJson.response.let { list ->
                        tvShows.addAll(list)

                    }

                    // Save the articles and reload the screen
                    tvShowAdapter.notifyDataSetChanged()

                } catch (e: JSONException) {
                    Log.e(TAG, "Exception: $e")
                }
            }

        })


    }
}
