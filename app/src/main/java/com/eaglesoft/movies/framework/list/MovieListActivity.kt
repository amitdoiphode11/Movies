package com.eaglesoft.movies.framework.list

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.eaglesoft.movies.R
import com.eaglesoft.movies.business.network.model.WeMovie
import com.eaglesoft.movies.business.util.MoviesFilterType
import com.eaglesoft.movies.business.util.extension.tintMenuIcon
import com.eaglesoft.movies.framework.details.MovieDetailsActivity
import com.eaglesoft.movies.framework.list.adapter.MoviePagedAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieListActivity : AppCompatActivity(), MoviePagedAdapter.OnItemClick {
    private val TAG = "MainActivity"
    private var adapter: MoviePagedAdapter? = null
    private val mViewModel: MovieListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar as Toolbar?)
        initView()
    }

    private fun initView() {
        setUI()
        initViewModel()
        apiCall(null)
    }

    private fun initViewModel() {
        mViewModel.getCurrentTitle()?.observe(this, Observer {
            supportActionBar?.title = it?.let { it1 -> getString(it1) }
        })

    }

    private fun setUI() {
        adapter = MoviePagedAdapter(this)
        rv_movie.layoutManager = GridLayoutManager(this, 2)
        rv_movie.adapter = adapter
    }

    private fun apiCall(sortBy: MoviesFilterType?) {
        lifecycleScope.launch {
            mViewModel?.getMoviesList(sortBy)?.collectLatest {
                adapter?.submitData(it)
            }
        }
    }

    override fun onMovieItemClicked(movie: WeMovie?) {
        intent = MovieDetailsActivity.getStartIntent(this, movie?.id)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        if (menu != null) {
            tintMenuIcon(
                menu.findItem(R.id.action_sort_by),
                R.color.md_white_1000
            )
            when {
                mViewModel?.getCurrentSorting() === MoviesFilterType.POPULAR -> {
                    menu.findItem(R.id.action_popular_movies).isChecked = true
                    apiCall(MoviesFilterType.POPULAR)
                }
                mViewModel?.getCurrentSorting() === MoviesFilterType.TOP_RATED -> {
                    menu.findItem(R.id.action_top_rated).isChecked = true
                    apiCall(MoviesFilterType.TOP_RATED)
                }
                mViewModel?.getCurrentSorting() === MoviesFilterType.NOW_PLAYING -> {
                    menu.findItem(R.id.action_now_playing).isChecked = true
                    apiCall(MoviesFilterType.NOW_PLAYING)
                }
            }
        } else {
            Log.e(TAG, "onCreateOptionsMenu: menu is null")
        }
        return true

        //return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.groupId == R.id.menu_sort_group) {
            mViewModel?.setSortMoviesBy(item.itemId)
            item.isChecked = true
        }
        return super.onOptionsItemSelected(item)
    }


}