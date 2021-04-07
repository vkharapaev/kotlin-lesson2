package com.headmostlab.findmovie.ui.viewmodel.main

import com.headmostlab.findmovie.Event
import com.headmostlab.findmovie.domain.entity.MovieWithCategory

sealed class MainAppState {
    data class MoviesLoaded(val movies: List<MovieWithCategory>) : MainAppState()
    data class LoadingError(val error: Throwable) : MainAppState()
    data class OnMovieItemClicked(val movieId: Event<Int>) : MainAppState()
    object Loading : MainAppState()
}
