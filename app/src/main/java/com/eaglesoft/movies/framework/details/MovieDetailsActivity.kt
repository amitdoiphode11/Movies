package com.eaglesoft.movies.framework.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.size.Scale
import com.eaglesoft.movies.R
import com.eaglesoft.movies.business.domain.state.DataState
import com.eaglesoft.movies.business.network.RetrofitBuilder
import com.eaglesoft.movies.business.network.model.WeGenre
import com.eaglesoft.movies.business.network.model.WeMovie
import com.eaglesoft.movies.business.network.repository.MovieRepositoryImpl
import com.eaglesoft.movies.framework.base.ViewModelFactory
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {
    private var imageId: Int? = null
    private var mViewModel: MovieDetailsViewModel? = null
    private var movie: WeMovie? = null

    companion object {
        private val TAG = "MovieDetailsActivity"
        fun getStartIntent(context: Context, id: Int?): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra("id", id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = getString(R.string.label_details)
        imageId = intent.getIntExtra("id", 1)
        initViewModel()
    }

    private fun initViewModel() {
        mViewModel = ViewModelProvider(
            this,
            ViewModelFactory(MovieRepositoryImpl(RetrofitBuilder.apiService))
        ).get(MovieDetailsViewModel::class.java)

        mViewModel?.getMoviesDetails(imageId)

        mViewModel?.movieDetails?.observe(this) {
            when (it) {
                is DataState.Success -> {
                    /*progressBar.visibility = View.GONE
                    textView.visibility = View.GONE*/
                    showContent(it.data)
                }
                is DataState.Loading -> {
                    /* progressBar.visibility = View.VISIBLE
                    textView.visibility = View.GONE*/
                }
                is DataState.Error -> {
                    /*progressBar.visibility = View.GONE
                    textView.visibility = View.VISIBLE*/
                }
            }
        }
    }

    private fun showContent(movie: WeMovie?) {
        val backdrop_path = "${getString(R.string.base_image_path)}${movie?.backdrop_path}"
        iv_movie_backdrop.load(backdrop_path) {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_background)
            scale(Scale.FILL)
        }
        val poster_path = "${getString(R.string.base_image_path)}${movie?.poster_path}"
        iv_poster.load(poster_path)

        this.movie = movie
        handleCollapsedToolbarTitle()
        tv_title.text = movie?.title

        tv_release_date.text = movie?.release_date
        label_vote.text = getString(R.string.label_votes,movie?.vote_count.toString())
        tv_vote.text = movie?.voteAverage.toString()
        tv_language.text = movie?.original_language
        tv_overview.text = movie?.overview

        for (item in movie?.genres!!) {
            val chip = LayoutInflater.from(this).inflate(R.layout.item_chips, null) as Chip
            chip.text = item.name
            chip.isClickable=false
            chip_group.addView(chip)
        }
    }

    private fun handleCollapsedToolbarTitle() {
        appbar.addOnOffsetChangedListener(object : OnOffsetChangedListener {
            var isShow = true
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                // verify if the toolbar is completely collapsed and set the movie name as the title
                if (scrollRange + verticalOffset == 0) {
                    collapsing_toolbar.title =
                        movie?.title
                    isShow = true
                } else if (isShow) {
                    // display an empty string when toolbar is expanded
                    collapsing_toolbar.title = " "
                    isShow = false
                }
            }
        })
    }

}