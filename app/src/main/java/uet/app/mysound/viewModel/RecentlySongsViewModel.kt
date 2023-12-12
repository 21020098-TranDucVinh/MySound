package uet.app.mysound.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import uet.app.mysound.data.repository.MainRepository
import uet.app.mysound.pagination.RecentPagingSource
import javax.inject.Inject

@HiltViewModel
class RecentlySongsViewModel @Inject constructor(application: Application, private val mainRepository: MainRepository): AndroidViewModel(application) {
        val recentlySongs = Pager(
            PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 20
            ),
        ) {
            RecentPagingSource(mainRepository)
        }.flow.cachedIn(viewModelScope)
}