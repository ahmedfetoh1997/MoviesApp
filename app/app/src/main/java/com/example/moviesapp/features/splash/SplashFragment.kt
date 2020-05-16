package com.example.moviesapp.features.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.example.moviesapp.R

/**
 * A simple [Fragment] subclass.
 */
class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loading()
    }

    private fun loading() {
        Handler().postDelayed({ openMoviesFragment() }, 3000)
    }



    private fun openMoviesFragment() {
        val navController = view?.let { Navigation.findNavController(it) }
        navController?.navigate(R.id.action_splashFragment_to_moviesFragment)
    }


}
