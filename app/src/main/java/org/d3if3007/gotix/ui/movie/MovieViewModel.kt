package org.d3if3007.gotix.ui.movie

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3007.gotix.model.Movie
import org.d3if3007.gotix.network.ApiStatus
import org.d3if3007.gotix.network.PosterApi
import org.d3if3007.gotix.network.UpdateWorker
import java.util.concurrent.TimeUnit

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

    fun scheduleUpdater(app: Application){
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(app).enqueueUniqueWork(
            UpdateWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}