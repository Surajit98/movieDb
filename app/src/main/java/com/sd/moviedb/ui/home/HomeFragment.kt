package com.sd.moviedb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.sd.moviedb.R
import com.sd.moviedb.callback.MovieItemClickListener
import com.sd.moviedb.databinding.FragmentHomeBinding
import com.sd.moviedb.model.Movies
import com.sd.moviedb.ui.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModel()
    private var binding: FragmentHomeBinding? = null
    private lateinit var adapter: MoviesAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding!!.root
    }

    override fun initViews() {
        adapter = MoviesAdapter(object : MovieItemClickListener {
            override fun toggleLike(movie: Movies) {
                viewModel.update(movie)
            }
        })
        val footerAdapter=MovieStateAdapter(adapter)
        binding!!.list.adapter = adapter.withLoadStateFooter(footer = footerAdapter)
        (binding!!.list.layoutManager as? GridLayoutManager)?.apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position == adapter.itemCount && footerAdapter.itemCount > 0) {
                        2
                    } else {
                        1
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.fetchMovies().collectLatest {
                adapter.submitData(it)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding?.unbind()
        binding = null
    }


}