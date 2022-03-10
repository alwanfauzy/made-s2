package com.alwan.made2.detail

import androidx.lifecycle.*
import com.alwan.made2.core.domain.model.Anime
import com.alwan.made2.core.domain.usecase.AnimeUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val animeUseCase: AnimeUseCase) : ViewModel() {
    private val animeId = MutableLiveData<String>()
    val animeDetail = Transformations.switchMap(animeId) {
        animeUseCase.getDetailAnime(it).asLiveData()
    }
    private val _isFavorite = MutableLiveData<Int>()
    var isFavorite: LiveData<Int> = _isFavorite


    fun setId(id: String) {
        if (animeId.value == id) return
        animeId.value = id
    }

    fun isFavoriteAnime(id: String?) {
        _isFavorite.postValue(id?.let { animeUseCase.isFavoriteAnime(it) })
    }

    fun insertFavoriteAnime(anime: Anime) =
        viewModelScope.launch { animeUseCase.insertFavoriteAnime(anime) }

    fun deleteFavoriteAnime(anime: Anime) =
        viewModelScope.launch { animeUseCase.deleteFavoriteAnime(anime) }

}