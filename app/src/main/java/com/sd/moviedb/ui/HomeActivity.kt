package com.sd.moviedb.ui

import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.sd.moviedb.R
import com.sd.moviedb.databinding.ActivityHomeBinding
import com.sd.moviedb.ui.base.BaseActivity

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
    }

    override fun init() {
        val navController = findNavController(R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home -> {
                    binding.title.text = getString(R.string.title_home)
                }
                R.id.navigation_favourite -> {
                    binding.title.text = getString(R.string.title_favourite)
                }
            }
        }
        binding.navView.setupWithNavController(findNavController(R.id.nav_host_fragment))
    }
}