package com.tmobile.dynamicui.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import com.tmobile.dynamicui.R
import com.tmobile.dynamicui.util.Result
import com.tmobile.dynamicui.util.gone
import com.tmobile.dynamicui.util.showToast
import com.tmobile.dynamicui.util.visible
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val homeViewModel: HomeViewModel by viewModels { viewModelFactory }
    private val homeAdapter = HomeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUi()

    }

    private fun setupUi() {
        recyclerview.apply {
            adapter = homeAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        homeViewModel.homeData.observe(this) { result ->

            when (result.status) {
                Result.Status.LOADING -> {
                    progressBar.visible()
                    recyclerview.gone()
                }
                Result.Status.SUCCESS -> {
                    progressBar.gone()
                    recyclerview.visible()
                    val data = result.data?.page?.cards
                    data?.let { homeAdapter.setData(it) }
                }
                Result.Status.ERROR -> {
                    progressBar.gone()
                    showToast(getString(R.string.error_loading_data))
                }
            }

        }
    }


}


