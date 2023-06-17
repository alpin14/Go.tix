package org.d3if3007.gotix.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if3007.gotix.R
import org.d3if3007.gotix.model.Movie

class MovieViewModel: ViewModel() {
    private val dataFilm = MutableLiveData<List<Movie>>()

    init {
        dataFilm.value = initData()
    }
    private fun initData(): List<Movie> {
        return listOf(
            Movie("Black Clover", "Action, Fantasy, Anime", R.drawable.black_clover),
            Movie("Cars", "Fantasy, Kids & Family", R.drawable.cars),
            Movie("Fast 9: Furious Saga", "Action, Romance", R.drawable.fast),
            Movie("John Wick 3: Parabellum", "Crime, Action, US", R.drawable.john_wick),
            Movie("Mission Impossible: Dead Reckoning", "Action, Spy Movies, US", R.drawable.mission),
            Movie("The Amazing Spiderman", "Sci-Fi, Action, Family Movies ", R.drawable.spiderman),
            Movie("Terminator: Genisys", "Sci-Fi, Action, US", R.drawable.terminator),
            Movie("Titanic", "Romance, Documentaries, Reality", R.drawable.titanic),
            Movie("Venom", "Thriller, Action, Fantasy", R.drawable.venom),
            Movie("White House Down", "Action, US", R.drawable.white_house)
        )
    }
    fun getData(): LiveData<List<Movie>> = dataFilm
}