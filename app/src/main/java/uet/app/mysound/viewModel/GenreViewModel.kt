package uet.app.mysound.viewModel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import uet.app.mysound.data.model.explore.mood.genre.GenreObject
import uet.app.mysound.data.repository.MainRepository
import uet.app.mysound.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GenreViewModel @Inject constructor(private val mainRepository: MainRepository, application: Application) : AndroidViewModel(application)  {
    private val _genreObject: MutableLiveData<Resource<GenreObject>> = MutableLiveData()
    var genreObject: LiveData<Resource<GenreObject>> = _genreObject
    var loading = MutableLiveData<Boolean>()

    fun getGenre(params: String){
        loading.value = true
        var job = viewModelScope.launch {
            mainRepository.getGenre(params).collect{values ->
                _genreObject.value = values
            }

            withContext(Dispatchers.Main){
                loading.value = false
            }
        }
    }
}