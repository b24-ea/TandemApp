package com.aldanmaz.tandemapp.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aldanmaz.tandemapp.model.Response
import com.aldanmaz.tandemapp.di.ApiClient
import retrofit2.HttpException
import java.io.IOException

class UserSource : PagingSource<Int, Response>() {

    override fun getRefreshKey(state: PagingState<Int, Response>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1)?:anchorPage?.nextKey?.minus(1)
        }
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Response> {

        return try {
            val nextPage = params.key ?:1
            val userList = ApiClient.apiService.getUserList(nextPage)
            LoadResult.Page (
                data = userList.response,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (userList.response.isEmpty()) null else userList.page +1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}