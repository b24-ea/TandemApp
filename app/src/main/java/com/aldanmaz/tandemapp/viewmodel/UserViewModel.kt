package com.aldanmaz.tandemapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.aldanmaz.tandemapp.model.Response
import com.aldanmaz.tandemapp.utils.UserSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor() : ViewModel(){

    val user: Flow<PagingData<Response>> = Pager(PagingConfig(pageSize = 10)) {
        UserSource()
    }.flow.cachedIn(viewModelScope)
}