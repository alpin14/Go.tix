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
        dataFilm.value = initData()
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = PosterApi.service.getPoster()
                Log.d("MovieViewModel", "Succes: $result")
            }catch (e: Exception){
                Log.d("MovieViewModel", "Failure: ${e.message}")
            }
        }
    }

    private fun initData(): List<Movie> {
        return listOf(
            Movie("Black Clover", "Action, Fantasy, Anime", R.drawable.black_clover),
            Movie("Cars 3", "Fantasy, Kids & Family", R.drawable.cars),
            Movie("Fast 9: Furious Saga", "Action, Romance", R.drawable.fast),
            Movie("John Wick 3: Parabellum", "Crime, Action, US", R.drawable.john_wick),
            Movie("Mission Impossible: Dead Reckoning", "Action, Spy Movies, US", R.drawable.mission),
            Movie("The Amazing Spiderman", "Sci-Fi, Action, Family Movies", R.drawable.spiderman),
            Movie("Terminator: Genisys", "Sci-Fi, Action, US", R.drawable.terminator),
            Movie("Titanic", "Romance, Documentaries, Reality", R.drawable.titanic),
            Movie("Venom", "Thriller, Action, Fantasy", R.drawable.venom),
            Movie("White House Down", "Action, US", R.drawable.white_house)
        )
    }
    fun getData(): LiveData<List<Movie>> = dataFilm
}