package uet.app.mysound.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay
import uet.app.mysound.data.repository.MainRepository

class RecentPagingSource (private val mainRepository: MainRepository): PagingSource<Int, Any>() {
    override fun getRefreshKey(state: PagingState<Int, Any>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Any> {
        val page = params.key ?: 0

        return try {
            val entities = mainRepository.getRecentSong(params.loadSize, page * params.loadSize)
            if (page != 0) delay(500)
            LoadResult.Page(
                data = entities,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (entities.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}