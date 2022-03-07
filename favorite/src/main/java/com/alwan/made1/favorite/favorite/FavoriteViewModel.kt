package com.alwan.made1.favorite.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alwan.made1.core.domain.usecase.AnimeUseCase

class FavoriteViewModel(animeUseCase: AnimeUseCase) : ViewModel() {
    val favoriteAnime = animeUseCase.getFavoriteAnime().asLiveData()
}