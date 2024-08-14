package com.dk.pagingv4

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dk.pagingv4.data.GithubItems
import com.dk.pagingv4.network.GitApi
import kotlinx.coroutines.delay
import kotlin.Exception

private const val STARTING_KEY = 1


class GithubPagingSource (

    private val gitApi : GitApi,
    private val query : String
) : PagingSource<Int, GithubItems>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubItems> {

        delay(1500)

        return try {

            val page = params.key ?: STARTING_KEY
            val res = gitApi.getData(query, page, params.loadSize)
            val data = res.items

            //테스트용 임시 에러
//            var cnt = 0
//            if(page != 1){
//                cnt = (0..1).random()
//            }
//
//            Log.d("count", cnt.toString())
//
//            if(cnt ==1){
//                throw Exception("ex")
//            }

            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = page + (params.loadSize / 30)
            )

        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, GithubItems>): Int? {

      val archorPosition = state.anchorPosition

        return archorPosition?.let{ it ->
            state.closestPageToPosition(it)?.prevKey?.plus(1)
            ?: state.closestPageToPosition(it)?.prevKey?.minus(1)}
    }

}