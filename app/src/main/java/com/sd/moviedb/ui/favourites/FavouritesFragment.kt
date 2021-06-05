package com.sd.moviedb.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sd.moviedb.R
import com.sd.moviedb.databinding.FragmentFavouritesBinding
import com.sd.moviedb.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouritesFragment : BaseFragment() {

    private val viewModel: FavouritesViewModel by viewModel()
    private var binding: FragmentFavouritesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourites, container, false)
        binding?.apply {
            lifecycleOwner = this@FavouritesFragment.viewLifecycleOwner
        }
        return binding?.root
    }


    override fun initViews() {
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding?.unbind()
        binding = null
    }

}