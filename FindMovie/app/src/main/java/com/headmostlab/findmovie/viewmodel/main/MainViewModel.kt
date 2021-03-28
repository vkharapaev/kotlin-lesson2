package com.headmostlab.findmovie.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.headmostlab.findmovie.Event
import com.headmostlab.findmovie.model.ShortMovie
import com.headmostlab.findmovie.model.Repository
import com.headmostlab.findmovie.model.RepositoryImpl
import java.util.*
import java.util.concurrent.TimeUnit

class MainViewModel(
    private val appStateLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: Repository = RepositoryImpl(),
    private val random: Random = Random(),
    private var shortMovies: List<ShortMovie>? = null
) :
    ViewModel() {

    fun getAppStateLiveData(): LiveData<AppState> = appStateLiveData

    fun getMovies() {
        appStateLiveData.value = AppState.Loading
        Thread {
            TimeUnit.SECONDS.sleep(1)
            if (random.nextBoolean()) {
                val movies = repository.getMovies()
                shortMovies = movies
                appStateLiveData.postValue(AppState.Success(movies))
            } else {
                appStateLiveData.postValue(AppState.Error(RuntimeException()))
            }
        }.start()
    }

    fun clickMovieItem(position: Int) {
        val movie = shortMovies?.get(position)
        if (movie != null) {
            appStateLiveData.value = AppState.OnMovieItemClicked(Event(movie.id))
        }
    }
}