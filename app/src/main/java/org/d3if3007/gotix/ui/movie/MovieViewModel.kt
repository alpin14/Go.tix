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
import org.d3if3007.gotix.network.ApiStatus
import org.d3if3007.gotix.network.PosterApi

class MovieViewModel: ViewModel() {
    private val dataFilm = MutableLiveData<List<Movie>>()
    private val status = MutableLiveData<ApiStatus>()

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
                dataFilm.postValue(PosterApi.service.getPoster())
                status.postValue(ApiStatus.SUCCES)
            }catch (e: Exception){
                Log.d("MovieViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }

    fun getData(): LiveData<List<Movie>> = dataFilm

    fun getStatus(): LiveData<ApiStatus> = status
}