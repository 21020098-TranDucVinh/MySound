package uet.app.mysound.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uet.app.mysound.data.db.entities.ArtistEntity
import uet.app.mysound.data.repository.MainRepository
import javax.inject.Inject
import android.util.Log

@HiltViewModel
class FollowedViewModel @Inject constructor(application: Application, private val mainRepository: MainRepository): AndroidViewModel(application) {
    private var _listFollowedArtist: MutableLiveData<ArrayList<ArtistEntity>> = MutableLiveData()
    val listFollowedArtist: LiveData<ArrayList<ArtistEntity>> get() = _listFollowedArtist

    fun getListLikedSong() {
        viewModelScope.launch {
            mainRepository.getFollowedArtists().collect{ likedSong ->
                _listFollowedArtist.value = likedSong as ArrayList<ArtistEntity>
            }
        }
    }
}