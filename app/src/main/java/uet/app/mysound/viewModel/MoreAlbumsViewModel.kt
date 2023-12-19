package uet.app.mysound.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uet.app.mysound.data.repository.MainRepository
import uet.app.youtubeExtractor.pages.BrowseResult
import javax.inject.Inject
import android.util.Log

@HiltViewModel
class MoreAlbumsViewModel @Inject constructor(application: Application, private val mainRepository: MainRepository): AndroidViewModel(application) {
    private var _browseResult: MutableStateFlow<BrowseResult?> = MutableStateFlow(null)
    val browseResult: StateFlow<BrowseResult?> = _browseResult

    fun getAlbumMore(id: String) {
        viewModelScope.launch {
            _browseResult.value = null
            mainRepository.getAlbumMore(id, ALBUM_PARAM).collect { data ->
                _browseResult.value = data
            }
        }
    }
    fun getSingleMore(id: String) {
        viewModelScope.launch {
            _browseResult.value = null
            mainRepository.getAlbumMore(id, SINGLE_PARAM).collect { data ->
                _browseResult.value = data
            }
        }
    }

    companion object {
        const val ALBUM_PARAM = "ggMIegYIARoCAQI%3D"
        const val SINGLE_PARAM = "ggMIegYIAhoCAQI%3D"
    }
}