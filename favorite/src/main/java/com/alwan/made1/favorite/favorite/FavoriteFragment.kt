package com.alwan.made1.favorite.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.alwan.made1.R
import com.alwan.made1.core.domain.model.Anime
import com.alwan.made1.core.ui.AnimeAdapter
import com.alwan.made1.core.utils.GridMarginItemDecoration
import com.alwan.made1.detail.DetailActivity
import com.alwan.made1.favorite.databinding.FragmentFavoriteBinding
import com.alwan.made1.favorite.di.favoriteModule
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ViewModelParameter
import org.koin.androidx.viewmodel.koin.getViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

class FavoriteFragment : Fragment(), AnimeAdapter.AnimeCallback {
    private inline fun <reified VM : ViewModel> Fragment.sharedGraphViewModel(
        @IdRes navGraphId: Int,
        qualifier: Qualifier? = null,
        noinline parameters: ParametersDefinition? = null
    ) = lazy {
        val store = findNavController().getViewModelStoreOwner(navGraphId).viewModelStore
        getKoin().getViewModel(
            ViewModelParameter(
                clazz = VM::class,
                qualifier = qualifier,
                parameters = parameters,
                viewModelStore = store
            )
        )
    }

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val favoriteAdapter = AnimeAdapter(this)
    private val favoriteViewModel: FavoriteViewModel by sharedGraphViewModel(R.id.main_navigation)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)

        loadKoinModules(favoriteModule)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupRecyclerView()
    }

    override fun onStop() {
        super.onStop()
        unloadKoinModules(favoriteModule)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViewModel() {
        favoriteViewModel.favoriteAnime.observe(viewLifecycleOwner) {
            favoriteAdapter.setData(ArrayList(it))
            if (it.isNullOrEmpty()) {
                showEmpty(true)
            } else {
                showEmpty(false)
            }
        }
    }

    private fun setupRecyclerView() {
        val spanCount = 2
        with(binding.rvFavorite) {
            layoutManager = GridLayoutManager(requireContext(), spanCount)
            setHasFixedSize(true)
            addItemDecoration(GridMarginItemDecoration(spanCount, 16, true))
            adapter = favoriteAdapter
        }
    }

    private fun showEmpty(state: Boolean) {
        binding.imgEmptyFavorite.visibility = if (state) View.VISIBLE else View.GONE
        binding.tvEmptyFavorite.visibility = if (state) View.VISIBLE else View.GONE
    }

    override fun onAnimeClick(anime: Anime) {
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ANIME_ID, anime.id)
        startActivity(intent)
    }
}