package uet.app.mysound.viewModel

import android.graphics.drawable.GradientDrawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import uet.app.mysound.data.model.browse.album.AlbumBrowse
import uet.app.mysound.data.model.searchResult.songs.Thumbnail
import uet.app.mysound.data.model.thumbnailUrl
import uet.app.mysound.data.repository.MainRepository
import uet.app.mysound.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(private val mainRepository: MainRepository): ViewModel() {
    var gradientDrawable: MutableLiveData<GradientDrawable> = MutableLiveData()
    var loading = MutableLiveData<Boolean>()

    private val _albumBrowse: MutableLiveData<Resource<AlbumBrowse>> = MutableLiveData()
    var albumBrowse: LiveData<Resource<AlbumBrowse>> = _albumBrowse

    fun browseAlbum(channelId: String){
        loading.value = true
        var job = viewModelScope.launch {
            mainRepository.browseAlbum(channelId).collect{ values ->
                _albumBrowse.value = values
            }
            withContext(Dispatchers.Main){
                loading.value = false
            }
        }
    }
}