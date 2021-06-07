package com.sd.moviedb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.sd.moviedb.R
import com.sd.moviedb.databinding.FragmentHomeBinding
import com.sd.moviedb.ui.base.BaseFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: MoviesAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun initViews() {
        adapter = MoviesAdapter()
        binding.list.adapter = adapter.withLoadStateFooter(footer = MovieStateAdapter(adapter))
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.fetchMovies().collectLatest {
                adapter.submitData(it)
            }
        }
       /* lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                progressBar.isVisible = loadStates.refresh is LoadState.Loading
                retry.isVisible = loadState.refresh !is LoadState.Loading
                errorMsg.isVisible = loadState.refresh is LoadState.Error
            }
        }*/
       /* adapter.addLoadStateListener { state ->
            when (state.refresh) {
                is LoadState.Loading -> {
                    showToast("loading${LoadState.Loading}")
                    //view.news_progress.visibility = View.VISIBLE
                }
                is LoadState.NotLoading -> {
                    showToast("not loading")
                    //view.news_progress.visibility = View.GONE
                }
                is LoadState.Error -> {
                    showToast("error")
                    //view.news_progress.visibility = View.GONE
                    // Toast.makeText(requireContext(), "Error Occured", Toast.LENGTH_SHORT).show()


                }
            }

        }*/

    }
}