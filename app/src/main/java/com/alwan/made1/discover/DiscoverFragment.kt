package com.alwan.made1.discover

import android.app.SearchManager
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Context.SEARCH_SERVICE
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.alwan.made1.R
import com.alwan.made1.core.data.Resource
import com.alwan.made1.core.domain.model.Anime
import com.alwan.made1.core.ui.AnimeAdapter
import com.alwan.made1.core.utils.GridMarginItemDecoration
import com.alwan.made1.databinding.FragmentDiscoverBinding
import com.alwan.made1.detail.DetailActivity
import es.dmoral.toasty.Toasty
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiscoverFragment : Fragment(), AnimeAdapter.AnimeCallback {
    private var _binding: FragmentDiscoverBinding? = null
    private val binding get() = _binding!!
    private lateinit var searchView: SearchView
    private val discoverViewModel: DiscoverViewModel by viewModel()
    private val animeAdapter = AnimeAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)

        val searchManager = requireActivity().getSystemService(SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.menu_search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.maxWidth = Integer.MAX_VALUE


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                discoverViewModel.setQuery(query)
                closeKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun setupViewModel() {
        discoverViewModel.animeList.observe(viewLifecycleOwner) {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> {
                        showLoading(true)
                        discoverViewModel.setStartSearchState(false)
                    }
                    is Resource.Success -> {
                        it.data?.let { data -> animeAdapter.setData(ArrayList(data)) }
                        showLoading(false)
                    }
                    is Resource.Error -> {
                        it.message?.let { error ->
                            Toasty.warning(requireContext(), error, Toasty.LENGTH_SHORT).show()
                            showLoading(false)
                        }
                    }
                }
            }
        }
        discoverViewModel.startSearchState.observe(viewLifecycleOwner) {
            showStartSearch(it)
        }
    }

    private fun setupRecyclerView() {
        val spanCount = 2
        with(binding.rvDiscover) {
                layoutManager = GridLayoutManager(requireContext(), spanCount)
                setHasFixedSize(true)
                addItemDecoration(GridMarginItemDecoration(spanCount, 16, true))
                adapter = animeAdapter
        }
    }

    private fun closeKeyboard() {
        val view: View? = requireActivity().currentFocus
        if (view != null) {
            val imm: InputMethodManager =
                requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun showStartSearch(state: Boolean) {
        if (state) {
            binding.imgStartSearch.visibility = View.VISIBLE
            binding.tvStartSearch.visibility = View.VISIBLE
        } else {
            binding.imgStartSearch.visibility = View.GONE
            binding.tvStartSearch.visibility = View.GONE
        }
    }

    private fun showLoading(state: Boolean) {
        binding.spinMain.visibility = if (state) View.VISIBLE else View.GONE
    }

    override fun onAnimeClick(anime: Anime) {
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ANIME_ID, anime.id)
        startActivity(intent)
    }
}