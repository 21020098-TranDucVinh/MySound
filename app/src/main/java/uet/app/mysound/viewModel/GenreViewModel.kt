package uet.app.mysound.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import uet.app.mysound.common.SELECTED_LANGUAGE
import uet.app.mysound.data.dataStore.DataStoreManager
import uet.app.mysound.data.model.explore.mood.genre.GenreObject
import uet.app.mysound.data.repository.MainRepository
import uet.app.mysound.utils.Resource
import javax.inject.Inject
import android.util.Log

class GenreViewModel @Inject constructor(private val mainRepository: MainRepository, application: Application, private var dataStoreManager: DataStoreManager) : AndroidViewModel(application)  {
    private val _genreObject: MutableLiveData<Resource<GenreObject>> = MutableLiveData()
    var genreObject: LiveData<Resource<GenreObject>> = _genreObject
    var loading = MutableLiveData<Boolean>()

    private var regionCode: String? = null
    private var language: String? = null
    init {
        regionCode = runBlocking { dataStoreManager.location.first() }
        language = runBlocking { dataStoreManager.getString(SELECTED_LANGUAGE).first() }
    }

    fun getGenre(params: String){
        loading.value = true
        viewModelScope.launch {
            mainRepository.getGenreData(params).collect { values ->
                _genreObject.value = values
            }
            withContext(Dispatchers.Main){
                loading.value = false
            }
        }
    }
}