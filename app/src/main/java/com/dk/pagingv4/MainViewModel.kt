package com.dk.pagingv4

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dk.pagingv4.data.GithubItems
import com.dk.pagingv4.network.GitApi
import com.dk.pagingv4.network.RetrofitInstance
import kotlinx.coroutines.flow.Flow

class MainViewModel(val str : String) : ViewModel() {

    private val api = RetrofitInstance.getInstance().create(GitApi::class.java)

    val items : Flow<PagingData<GithubItems>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            GithubPagingSource(api, str)
        }
    )
        .flow
        .cachedIn(viewModelScope)
}