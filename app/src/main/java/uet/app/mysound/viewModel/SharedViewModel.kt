package uet.app.mysound.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uet.app.mysound.data.model.mediaService.Song
import uet.app.mysound.data.repository.MainRepository
import uet.app.mysound.service.SimpleMediaServiceHandler
import uet.app.mysound.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class SharedViewModel @Inject constructor(private val mainRepository: MainRepository, val musicServiceHandler: SimpleMediaServiceHandler): ViewModel(){
    private val _mediaItems = MutableLiveData<Resource<ArrayList<Song>>>()
    val mediaItems: LiveData<Resource<ArrayList<Song>>> = _mediaItems

    init {
        musicServiceHandler.simpleMediaState
    }
}