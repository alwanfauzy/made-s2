package com.alwan.made2.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alwan.made2.core.domain.model.Anime
import com.alwan.made2.core.databinding.ItemAnimeBinding
import com.alwan.made2.core.utils.loadImage

class AnimeAdapter(private val callback: AnimeCallback) :
    RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    private val mData = ArrayList<Anime>()

    fun setData(data: ArrayList<Anime>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    interface AnimeCallback {
        fun onAnimeClick(anime: Anime)
    }

    inner class AnimeViewHolder(private val binding: ItemAnimeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(anime: Anime) {
            with(binding) {
                tvTitleItem.text = anime.canonicalTitle ?: "-"
                imgPosterItem.loadImage(anime.posterImage?.large)
                root.setOnClickListener { callback.onAnimeClick(anime) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AnimeViewHolder(
            ItemAnimeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) =
        holder.bind(mData[position])

    override fun getItemCount(): Int = mData.count()

}