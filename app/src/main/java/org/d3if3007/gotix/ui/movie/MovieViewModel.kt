package org.d3if3007.gotix.ui.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3007.gotix.R
import org.d3if3007.gotix.model.Movie
import org.d3if3007.gotix.network.PosterApi

class MovieViewModel: ViewModel() {
    private val dataFilm = MutableLiveData<List<Movie>>()

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                dataFilm.postValue(PosterApi.service.getPoster())
            }catch (e: Exception){
                Log.d("MovieViewModel", "Failure: ${e.message}")
            }
        }
    }

    fun getData(): LiveData<List<Movie>> = dataFilm
}