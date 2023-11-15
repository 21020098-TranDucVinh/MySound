package uet.app.mysound.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import uet.app.mysound.data.model.mediaService.Song
import uet.app.mysound.data.repository.MainRepository
import uet.app.mysound.service.SimpleMediaServiceHandler
import uet.app.mysound.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject
constructor(private val mainRepository: MainRepository, private val musicServiceHandler: SimpleMediaServiceHandler, savedStateHandle: SavedStateHandle): ViewModel(){


    private val _mediaItems = MutableLiveData<Resource<ArrayList<Song>>>()
    val mediaItems: LiveData<Resource<ArrayList<Song>>> = _mediaItems


}