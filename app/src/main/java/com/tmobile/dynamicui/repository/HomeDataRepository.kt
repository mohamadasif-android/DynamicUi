package com.tmobile.dynamicui.repository

import com.tmobile.dynamicui.api.TMobileApiService
import javax.inject.Inject

class HomeDataRepository @Inject constructor(
    private val tMobileApiService: TMobileApiService
) : BaseRepository() {

    suspend fun getHomeData() = getResult { tMobileApiService.getHomeData() }
}