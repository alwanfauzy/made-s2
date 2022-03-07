package com.alwan.made1.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.alwan.made1.R
import com.alwan.made1.core.data.Resource
import com.alwan.made1.core.domain.model.Anime
import com.alwan.made1.core.utils.loadImage
import com.alwan.made1.databinding.ActivityDetailBinding
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!
    private val detailViewModel: DetailViewModel by viewModel()
    private var statusFavorite = false
    private var anime: Anime? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val animeId = intent.getStringExtra(EXTRA_ANIME_ID)
        populateDetail(animeId)
        binding.fabFavorite.setOnClickListener {
            statusFavorite = !statusFavorite
            anime?.let { anime ->
                if (statusFavorite) {
                    detailViewModel.insertFavoriteAnime(anime)
                    Toasty.success(this, "Added to Favorite", Toasty.LENGTH_SHORT).show()
                } else {
                    detailViewModel.deleteFavoriteAnime(anime)
                    Toasty.error(this, "Removed from Favorite", Toasty.LENGTH_SHORT).show()
                }
                setStatusFavorite(statusFavorite)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun populateDetail(animeId: String?) {
        enableFabFavorite(false)
        animeId?.let { detailViewModel.setId(it) }

        detailViewModel.animeDetail.observe(this) {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> showLoading(true)
                    is Resource.Success -> {
                        showLoading(false)
                        it.data?.let { anime ->
                            this.anime = anime
                            binding.collapsingToolbarDetail.title = anime.canonicalTitle
                            binding.imgPosterDetail.loadImage(anime.posterImage?.original)
                            binding.imgCoverDetail.loadImage(anime.coverImage?.original)
                            binding.tvPopularityDetail.text =
                                getString(R.string.popularity_rank, anime.popularityRank)
                            binding.tvFavoriteCountDetail.text =
                                anime.favoritesCount?.toString() ?: "-"
                            binding.tvEpisodeDetail.text =
                                getString(R.string.episode_count, anime.episodeCount)
                            binding.tvAverageRatingDetail.text = anime.averageRating ?: "-"
                            binding.tvUserCountDetail.text = anime.userCount?.toString() ?: "-"
                            binding.tvStatusDetail.text = anime.status ?: "-"
                            binding.tvTitleEnJpDetail.text = anime.titles?.enJp ?: "-"
                            binding.tvTitleJaJpDetail.text = anime.titles?.jaJp ?: "-"
                            binding.tvSynopsisDetail.text = anime.synopsis ?: "-"

                            CoroutineScope(Dispatchers.IO).launch {
                                detailViewModel.isFavoriteAnime(anime.id)
                            }
                        }
                    }
                    is Resource.Error -> {
                        it.message?.let { error ->
                            Toasty.warning(this, error, Toasty.LENGTH_SHORT).show()
                        }
                        showLoading(false)
                    }
                }
            }
        }
        detailViewModel.isFavorite.observe(this@DetailActivity) {
            statusFavorite = it > 0
            setStatusFavorite(statusFavorite)
            enableFabFavorite(true)
        }


    }

    private fun showLoading(state: Boolean) {
        binding.spinDetail.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun setStatusFavorite(state: Boolean) {
        if (state) {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite
                )
            )
        } else {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_outline
                )
            )
        }
    }

    private fun enableFabFavorite(state: Boolean) {
        binding.fabFavorite.isEnabled = state
    }

    companion object {
        const val EXTRA_ANIME_ID = "extra_anime_id"
    }
}