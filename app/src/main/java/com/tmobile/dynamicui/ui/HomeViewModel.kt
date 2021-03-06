package com.tmobile.dynamicui.ui

import androidx.lifecycle.*
import com.tmobile.dynamicui.data.model.HomeResponse
import com.tmobile.dynamicui.repository.HomeDataRepository
import com.tmobile.dynamicui.util.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homeDataRepository: HomeDataRepository
) : ViewModel() {

    private var _homeData = MutableLiveData<Result<HomeResponse>>()

    val homeData : LiveData<Result<HomeResponse>>
    get() = _homeData

    init {
        fetchHomeData()
    }

    /**
     * TODO: This private method can be made public and can be called from view
     * (for example  on swipe to refresh) to refresh the UI
     */

    private fun fetchHomeData() {
        viewModelScope.launch {
            _homeData.value = Result.loading()
            _homeData.value = homeDataRepository.getHomeData()
        }
    }

}