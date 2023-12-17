package uet.app.mysound.viewModel

import android.app.Application
import android.graphics.drawable.GradientDrawable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uet.app.mysound.data.model.podcast.PodcastBrowse
import uet.app.mysound.data.repository.MainRepository
import uet.app.mysound.utils.Resource
import javax.inject.Inject
import android.util.Log

@HiltViewModel
class PodcastViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val application: Application
) : AndroidViewModel(application) {
    var gradientDrawable: MutableLiveData<GradientDrawable?> = MutableLiveData()
    var loading = MutableLiveData<Boolean>()
    var id = MutableLiveData<String>()

    private val _podcastBrowse: MutableLiveData<Resource<PodcastBrowse>?> = MutableLiveData()
    val podcastBrowse: MutableLiveData<Resource<PodcastBrowse>?> = _podcastBrowse

    fun clearPodcastBrowse() {
        _podcastBrowse.value = null
        gradientDrawable.value = null
    }

    fun getPodcastBrowse(id: String) {
        loading.value = true
        viewModelScope.launch {
            mainRepository.getPodcastData(id).collect {
                _podcastBrowse.value = it
                withContext(Dispatchers.Main) {
                    loading.value = false
                }
            }
        }
    }
}