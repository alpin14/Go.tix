package org.d3if3007.gotix.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3007.gotix.db.GotixDao
import org.d3if3007.gotix.db.GotixEntity
import org.d3if3007.gotix.model.HasilTiket
import org.d3if3007.gotix.model.Tiket

class MainViewModel(private val db: GotixDao): ViewModel() {
    private val hasilTiket = MutableLiveData<HasilTiket>()

    fun hasilGotix(kuota: Int, film: String){
        val hitungTiket = Tiket (
            kuota = kuota,
            film = film
        )
        hasilTiket.value = hitungTiket.hitungPenonton()
    }

    fun Tiket.hitungPenonton(): HasilTiket{
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataGotix = GotixEntity(
                    kuota = kuota,
                    film = film
                )
                db.insert(dataGotix)
            }
        }
        val kuota = kuota
        val filmbioskop = if (film.equals("Black Clover" , ignoreCase = true)) {
            kuota * 35000
        } else if (film.equals("Cars 3" , ignoreCase = true)) {
            kuota * 35000
        } else if (film.equals("Fast 9: Furious Saga" , ignoreCase = true)) {
            kuota * 35000
        } else if (film.equals("John Wick 3: Parabellum" , ignoreCase = true)) {
            kuota * 35000
        } else if (film.equals("Mission Impossible: Dead Reckoning" , ignoreCase = true)) {
            kuota * 35000
        } else if (film.equals("The Amazing Spiderman" , ignoreCase = true)) {
            kuota * 35000
        } else if (film.equals("Terminator: Genisys" , ignoreCase = true)) {
            kuota * 35000
        } else if (film.equals("Titanic" , ignoreCase = true)) {
            kuota * 35000
        } else if (film.equals("Venom" , ignoreCase = true)) {
            kuota * 35000
        } else if (film.equals("White House Down" , ignoreCase = true)) {
            kuota * 35000
        } else {
            0
        }
        return HasilTiket(kuota, filmbioskop)
    }

    fun getHasil(): LiveData<HasilTiket?> = hasilTiket
}