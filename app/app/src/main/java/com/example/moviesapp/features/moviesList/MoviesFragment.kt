package com.example.moviesapp.features.moviesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentMoviesBinding
import com.example.moviesapp.models.moviesResponse.Movie
import com.example.moviesapp.utils.ParentFragment
import com.example.moviesapp.utils.generalResponse.Status


/**
 * A simple [Fragment] subclass.
 */
class MoviesFragment : ParentFragment() {

    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var viewModel: MoviesViewModel
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSettings()
        initMoviesAdapterAndViewModel()
        observeMoviesResponse()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI(view)
    }


    override fun setupUI(view: View?) {
        super.setupUI(view)
        setupMoviesRecycle()
        handleMoviesSearch()
    }

    private fun initMoviesAdapterAndViewModel() {
        moviesAdapter = MoviesAdapter(arrayListOf())
        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        viewModel.getMovies(API_KEY, page)
    }

    private fun setupMoviesRecycle() {
        binding.rvMovies.apply {
            adapter = moviesAdapter
            layoutManager = GridLayoutManager(context, 3)
        }
    }

    private fun observeMoviesResponse() {
        viewModel.moviesResponseApi.observe(this, Observer {
            when (it?.status) {
                Status.Loading->showMainLoading()
                Status.Failure->handleErrorMsg(it.error)
                Status.Success->onMoviesResponseSuccess(it.data?.results)
            }
        })
    }

    private fun onMoviesResponseSuccess(movies: List<Movie>?) {
        hideMainLoading()
        hideLoadMoreProgress()
        moviesAdapter.addMore(movies)
    }

    private fun handleMoviesSearch()
    {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return true
            }

            fun callSearch(query: String?) {

            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.ubBind(this)
    }
}
