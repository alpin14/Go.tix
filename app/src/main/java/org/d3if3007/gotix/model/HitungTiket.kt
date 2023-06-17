package org.d3if3007.gotix.model

import org.d3if3007.gotix.db.GotixEntity

fun GotixEntity.hitungP(): HasilTiket {
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