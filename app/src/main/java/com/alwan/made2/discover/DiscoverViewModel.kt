package com.alwan.made2.discover

import androidx.lifecycle.*
import com.alwan.made2.core.domain.usecase.AnimeUseCase

class DiscoverViewModel(animeUseCase: AnimeUseCase) : ViewModel() {
    private val query = MutableLiveData<String>()
    private val _startSearchState = MutableLiveData<Boolean>()
    val startSearchState: LiveData<Boolean> = _startSearchState
    val animeList = Transformations.switchMap(query) {
        animeUseCase.getSearchAnime(it).asLiveData()
    }

    init {
        _startSearchState.value = true
    }

    fun setQuery(query: String) {
        if (this.query.value == query) return
        this.query.value = query
    }

    fun setStartSearchState(state: Boolean) {
        _startSearchState.value = state
    }
}